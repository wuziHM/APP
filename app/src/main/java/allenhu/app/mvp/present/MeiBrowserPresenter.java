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

import allenhu.app.bean.request.MeiBrowserBean;
import allenhu.app.bean.request.MeiCameraBean;
import allenhu.app.mvp.view.MeiBrowserView;
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
public class MeiBrowserPresenter extends BasePresentImpl<MeiBrowserView> {


    public MeiBrowserPresenter(MeiBrowserView view) {
        attachView(view);
    }


    /**
     * 获取数据
     */
    public void getData(int id) {

        Type type = new TypeToken<List<MeiCameraBean>>() {
        }.getType();


        OkGo.<String>get(MeiziURL.getMeiCombo())
                .params("id", id)
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
                        MeiBrowserBean browserBean = gson.fromJson(stringResponse.body(),MeiBrowserBean.class);
                        mView.setData(browserBean);
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
