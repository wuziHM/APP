package allenhu.app.net.retrofit2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import allenhu.app.util.Constant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：HM $ on 17/11/20 15:59
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class NetWork {


    public static final String ROOT_URL = "http://apis.baidu.com/";


    //    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static ImageApi imageApi;


    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("apikey", Constant.BAIDU_API_ID)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        //打印retrofit日志
//                        MLogUtil.e("retrofitBack = " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(4, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }


    /**
     * 获取图片分类
     *
     * @return
     */
    public static ImageApi getImageApi() {

        if (imageApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(genericClient())
                    .baseUrl(ROOT_URL)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            imageApi = retrofit.create(ImageApi.class);
        }
        return imageApi;
    }

}
