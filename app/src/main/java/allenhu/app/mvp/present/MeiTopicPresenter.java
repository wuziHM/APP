package allenhu.app.mvp.present;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.lang.reflect.Type;
import java.util.List;

import allenhu.app.bean.request.MeiNewBean;
import allenhu.app.mvp.view.MeiNewView;
import allenhu.app.net.MeiziURL;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：HM $ on 18/7/13 10:39
 * E-mail：359222347@qq.com
 * <p>
 * 专题部分的
 */
public class MeiTopicPresenter extends BasePresentImpl<MeiNewView> {


    public MeiTopicPresenter(MeiNewView view) {
        attachView(view);
    }

    private int pageSize = 5;

    /**
     * 获取数据
     */
    public void getData(String tags,int page) {
        Type type = new TypeToken<List<MeiNewBean>>() {
        }.getType();


        OkGo.<String>get(MeiziURL.getMeiSpecial())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params("tags",tags)
                .params("page",page)
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
                        List<MeiNewBean> list1 = gson.fromJson(stringResponse.body(), type);
                        mView.setData(list1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.finishRequest();
                    }
                });


    }

}
