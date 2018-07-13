package allenhu.app.net.okgo;


import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author：HM $ on 18/1/19 16:12
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class JsonCallback<T> implements Callback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

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

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonCallback<T> convert = new JsonCallback<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonCallback<T> convert = new JsonCallback<>(type);
        return convert.convertResponse(response);
    }

//    @Override
//    public T convertResponse(okhttp3.Response response) throws Throwable {
//        ResponseBody body = response.body();
//        com.orhanobut.logger.Logger.d(body.string());
//        if (body == null) return null;
//
//        T data = null;
//        Gson gson = new Gson();
////        JsonReader jsonReader = new JsonReader((body.charStream()));
////        com.orhanobut.logger.Logger.d("jsonReader:"+jsonReader.toString());
//        if (type != null) {
//            data = gson.fromJson(body.toString(), type);
//        } else if (clazz != null) {
//            data = gson.fromJson(body.toString(), clazz);
//        } else {
//            Type genType = getClass().getGenericSuperclass();
//            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
//            data = gson.fromJson(body.toString(), type);
//        }
//        return data;
//    }
}
