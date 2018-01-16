package allenhu.app.net.retrofit2;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Author：HM $ on 18/1/15 15:46
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class DownLoadObserver implements Observer<ResponseBody> {
    CallBack callBack;
    private Context mContext;

    public DownLoadObserver(CallBack callBack,Context mContext) {
        this.callBack = callBack;
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (callBack != null)
            callBack.onSubscribe();
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        if (callBack != null)
            DownLoadManager.getInstance(callBack).writeResponseBodyToDisk(mContext, responseBody);

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
