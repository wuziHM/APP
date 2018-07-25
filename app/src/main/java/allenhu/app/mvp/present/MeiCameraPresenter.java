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
import java.util.List;

import allenhu.app.bean.request.MeiCameraBean;
import allenhu.app.mvp.view.MeiCameraView;
import allenhu.app.net.MeiziURL;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：HM $ on 18/7/13 10:39
 * E-mail：359222347@qq.com
 * <p>
 * use to...妹子图自拍部分图片
 */
public class MeiCameraPresenter extends BasePresentImpl<MeiCameraView> {


    public MeiCameraPresenter(MeiCameraView view) {
        attachView(view);
    }


    /**
     * 获取数据
     */
    public void getCameraData(int page) {

        Type type = new TypeToken<List<MeiCameraBean>>() {
        }.getType();


        OkGo.<String>get(MeiziURL.getMeiCamera())
                .params("page", page)
                .params("post", 3238)
                .params("per_page", 36)
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    //开始请求
                    mView.showProgress();
                })
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        Gson gson = new Gson();
                        Logger.d("body:"+stringResponse.body());
                        try {
                            List<MeiCameraBean> list1 = gson.fromJson(stringResponse.body(), type);
                            mView.setData(list1);
                        } catch (Exception e) {
                            Logger.d("Exception:" + e.toString());
                            throw e;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();

                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                        mView.finishRequest();
                    }
                });


    }

}
