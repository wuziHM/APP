package allenhu.app.net.okgo;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * Author：HM $ on 18/1/19 16:12
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class JsonCallback<T> implements Callback<T> {
    @Override
    public void onStart(Request<T, ? extends Request> request) {

    }

    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public void onCacheSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }
}
