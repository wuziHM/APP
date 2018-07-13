package allenhu.app.mvp.present;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import allenhu.app.bean.Type;
import allenhu.app.mvp.view.WelfareView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static allenhu.app.MApplication.BASE_URL;

/**
 * Author：HM $ on 18/6/11 15:22
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class WelfarePresenter extends BasePresentImpl<WelfareView> {


    /**
     * 用jsoup抓妹子图数据的跟地址
     */
    public static final String MEIZI_BASE_URL = "http://www.mzitu.com/";

    private static HashMap mTypeMap;


    public WelfarePresenter(Context context, WelfareView view) {
        attachView(view);
    }


    public void getData() {
        OkGo.<Document>get(MEIZI_BASE_URL)
                .converter(response -> {
                    Logger.d(response.body().toString());
                    ResponseBody body = response.body();
                    String s = body.string();
                    Logger.d("body:" + s);
//                        return Jsoup.parse();
                    return Jsoup.parse(s);
                })
                .adapt(new ObservableResponse<>())
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(disposable -> {
                    //开始执行请求
                    Logger.d("=======doOnSubscribe-->accept======");
                    mView.showProgress();

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Document>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.d("=======onSubscribe======");
                    }

                    @Override
                    public void onNext(Response<Document> documentResponse) {
                        List<Type> types = parseTypes(documentResponse.body());
                        mView.showData(documentResponse.body());
                        mView.setTypes(types);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

    private List<Type> parseTypes(Document document) {
        Elements elements = document.select(".mainnav > ul > li > a");
        List<Type> typeList = new ArrayList<>();
        mTypeMap = new HashMap<>();
        Elements subElements = document.select(".main > .main-content > .subnav > a");
        if (subElements != null && !subElements.isEmpty()) {
            addType(subElements, typeList);
        }
        addType(elements, typeList);
        return typeList;
    }

    private void addType(Elements elements, List<Type> typeList) {
        for (Element element : elements) {
            Type type = new Type();
            String text = element.text();
            String value = element.attr("href").replaceAll(BASE_URL, "");
            if ("首页".equals(text) || "zipai/".equals(value) || "all/".equals(value) || "best/".equals(value) || "zhuanti/".equals(value)) {
                continue;
            }
            type.setName(text);
            type.setUrl(value);
            typeList.add(type);
            mTypeMap.put(value, text);
        }
    }

}
