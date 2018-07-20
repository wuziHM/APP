package allenhu.app.mvp.present;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import allenhu.app.bean.request.FindItemBean;
import allenhu.app.mvp.view.MeiFindView;
import allenhu.app.net.MeiziURL;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：HM $ on 18/7/13 10:39
 * E-mail：359222347@qq.com
 * <p>
 * use to...妹子图最新部分的presenter
 */
public class MeiFindPresenter extends BasePresentImpl<MeiFindView> {


    public MeiFindPresenter(MeiFindView view) {
        attachView(view);
    }

    private int pageSize = 5;

    /**
     * 获取数据
     */
    public void getData() {
        Type type = new TypeToken<List<List<FindItemBean>>>() {
        }.getType();


        OkGo.<String>get(MeiziURL.getMeiNum())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    //开始请求
//                    mView.showProgress();
                })
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        Gson gson = new Gson();
                        try {
                            ArrayList<ArrayList<ArrayList<FindItemBean>>> list1 = gson.fromJson(stringResponse.body(), type);
                            com.orhanobut.logger.Logger.d("size:" + list1.size());
                            mView.setData(list1);
                            getTopicAndGirl(list1);

                        } catch (Exception e) {
                            Logger.d("Exception:" + e.toString());
                            throw e;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getTopicAndGirl(ArrayList<ArrayList<ArrayList<FindItemBean>>> list1) {
        if (list1 == null || list1.size() < 2) {
            com.orhanobut.logger.Logger.e("拿到的每项的数据有问题");
            return;
        }

        List<ArrayList<FindItemBean>> topics = list1.get(0);
        List<ArrayList<FindItemBean>> girls = list1.get(1);

        ArrayList<ArrayList<FindItemBean>> returnTopic = new ArrayList<>();
        ArrayList<ArrayList<FindItemBean>> returnGirl = new ArrayList<>();

        int topicSize = topics.size();
        int girlSize = girls.size();

        Random random = new Random();
        int i = 0;

        if (topicSize > 4) {
            while (i < 4) {
                int n = random.nextInt(topicSize - 1);
                if (!returnTopic.contains(topics.get(n))) {
//                    List<FindItemBean> nBean = topics.get(n);
                    returnTopic.add(topics.get(n));
                    i++;
                }

            }
            mView.setTopics(returnTopic);
        } else {
            mView.setTopics(topics);

        }

        i = 0;
        if (girlSize > 4) {
            while (i < 4) {
                int n = random.nextInt(girlSize - 1);
                if (!returnGirl.contains(girls.get(n))) {
                    returnGirl.add(girls.get(n));
                    i++;
                }
            }
            mView.setGirls(returnGirl);
        } else {
            mView.setGirls(girls);
        }

    }

}
