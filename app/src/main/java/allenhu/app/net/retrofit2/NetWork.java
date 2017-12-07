package allenhu.app.net.retrofit2;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import allenhu.app.MApplication;
import allenhu.app.util.Constant;
import allenhu.app.util.NetWorkUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
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


    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static ImageApi imageApi;
    private static FindBgApi findBgApi;


    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //方案一：有网和没有网都是先读缓存
//                Request request = chain.request();
//                Log.i(TAG, "request=" + request);
//                Response response = chain.proceed(request);
//                Log.i(TAG, "response=" + response);
//
//                String cacheControl = request.cacheControl().toString();
//                if (TextUtils.isEmpty(cacheControl)) {
//                    cacheControl = "public, max-age=60";
//                }
//                return response.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();

            //方案二：无网读缓存，有网根据过期时间重新请求
            boolean netWorkConection = NetWorkUtil.isNetworkConnected(MApplication.getIntstance());
            Request request = chain.request();
            if (!netWorkConection) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (netWorkConection) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                response.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", cacheControl)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };

//    getInterceptor();

    @NonNull
    private static Interceptor getInterceptor(final boolean isUTF) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = getRequest(chain, isUTF);
                long t1 = System.nanoTime();
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Logger.i("-----LoggingInterceptor----- :\nrequest url:"
                        + request.url() + "\ntime:" + (t2 - t1) / 1e6d
                        + "    response:" + response.body().string());
                //            return response;
                return chain.proceed(request);
            }
        };
    }

    private static Request getRequest(Interceptor.Chain chain, boolean isUtf) {
        Logger.d("isUtf:" + isUtf);
        Request.Builder builder = chain.request().newBuilder();
        if (isUtf)
            builder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("apikey", Constant.BAIDU_API_ID)
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive");

        return builder.build();
    }


    public static OkHttpClient genericClient(boolean isUTF) {

        //设置缓存路径
        File httpCacheDirectory = new File(MApplication.getIntstance().getCacheDir(), "okhttpCache");
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(getInterceptor(isUTF))
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
//                        Logger.d("request-->message:" + message);
                        //打印retrofit日志
//                        MLogUtil.e("retrofitBack = " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .connectTimeout(4, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        return httpClient;
    }


//    public static OkHttpClient genericClient(int i) {
//
//        //设置缓存路径
//        File httpCacheDirectory = new File(MApplication.getIntstance().getCacheDir(), "okhttpCache");
//        //设置缓存 10M
//        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(LoggingInterceptor)
//                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Logger.d("request-->message:"+message);
//                        //打印retrofit日志
////                        MLogUtil.e("retrofitBack = " + message);
//                    }
//                }).setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .connectTimeout(4, TimeUnit.SECONDS)
//                .cache(cache)
//                .build();
//
//        return httpClient;
//    }


//    public static OkHttpClient genericClient() {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("apikey", Constant.BAIDU_API_ID)
//                                .build();
//                        return chain.proceed(request);
//                    }
//
//                })
//                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        //打印retrofit日志
////                        MLogUtil.e("retrofitBack = " + message);
//                    }
//                }).setLevel(HttpLoggingInterceptor.Level.BODY))
//                .connectTimeout(4, TimeUnit.SECONDS)
//                .build();
//
//        return httpClient;
//    }

    /**
     * 获取图片分类
     *
     * @return
     */
    public static ImageApi getImageApi() {

        if (imageApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(genericClient(true))
                    .baseUrl(ROOT_URL)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            imageApi = retrofit.create(ImageApi.class);
        }
        return imageApi;
    }

    /**
     * 获取图片分类
     *
     * @return
     */
    public static FindBgApi getIFindApi() {

        if (findBgApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(genericClient(false))
                    .baseUrl("http://www.bing.com/")
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            findBgApi = retrofit.create(FindBgApi.class);
        }
        return findBgApi;
    }

}
