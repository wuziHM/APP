package allenhu.app.net;

import android.content.Context;

import com.hlib.http.request.MAPI;
import com.hlib.http.request.MHttpAble;
import com.hlib.http.request.MHttpResponseAble;
import com.hlib.http.request.MModel;

import java.util.Map;

/**
 * Author：燕青 $ on 17/6/26 17:53
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class HAPI extends MAPI {

    public HAPI(Context context) {
        super(context);
    }

    @Override
    protected String getApiServerPrefixDebug() {
        return "http://192.168.1.214:8094";
    }

    @Override
    protected String getApiServerPrefix() {
        return "http://192.168.1.214:8094";
    }

    @Override
    protected MHttpAble getMHttpAble() {
        GHttpAsync gHttpAsync = new GHttpAsync(context);

//        if (ProxyConfig.isNeedProxy
//                && !MStringUtil.isEmpty(ProxyConfig.proxyIp)
//                && !MStringUtil.isEmpty(ProxyConfig.proxyPort)
//                ) {
//        AsyncHttpClient httpInstance = gHttpAsync.getHttpInstance();
//            httpInstance.setProxy(ProxyConfig.proxyIp, MNumberUtil.convertToint(ProxyConfig.proxyPort));
//            Log.v("xy",ProxyConfig.proxyIp+" ProxyConfig.proxyPort"+ProxyConfig.proxyPort);
//        }
        return gHttpAsync;
    }

    @Override
    protected void requestHttpPrepare(int method, String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {

        //设置一些通用的参数，每个接口都需要的参数

//        params.put("vendor_user_id", GUserMsgUtil.getUserId(context));
//        params.put("app_ver", MPackageUtil.getAppVersionName(context));             // 应用版本号
//        params.put("ver", GConfig.API_VERSION);                                     // 接口版本号
//        params.put("os", "Android" + Build.VERSION.RELEASE);                        // 客户端系统信息	iPhone; iOS 9.3.1;
//
//        if (!params.containsKey("pagesize")) {
//            params.put("pagesize", "" + PAGE_COUNT);
//        }
//        if (!params.containsKey("pageSize")) {
//            params.put("pageSize", "" + PAGE_COUNT);
//        }

//        setSign(context, params);


    }
}
