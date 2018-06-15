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
import java.util.List;

import allenhu.app.bean.Image;
import allenhu.app.mvp.view.MeiziImgDetailView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Author：HM $ on 18/6/11 17:53
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MeiziImgDetailPresent extends BasePresentImpl<MeiziImgDetailView> {

    //
//    private static Map<String, String> mTypeMap;
//    private static List<Type> mTypeList;
    private Context context;
    private String mCurrentUrl;

//    private String mCurrentUrl;
//    private Map<String, Integer> mTypeMaxPageMap;
//    private ArrayList<ImageBean> imageBeans;
//    private ILikeDao iLikeDao;
//    private ILikeType type;

    public MeiziImgDetailPresent(Context context, MeiziImgDetailView view) {
        this.context = context;
        attachView(view);
    }

    public void getImage(String pageUrl) {


        OkGo.<Document>get(pageUrl)
                .converter(response -> {
                    ResponseBody body = response.body();
                    String s = body.string();
                    Logger.d("getImage--->body:" + s);
                    return Jsoup.parse(s);
                })
                .adapt(new ObservableResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    //开始执行请求
//                    Logger.d("=======doOnSubscribe-->accept======");

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Document>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

//                        Logger.d("=======onSubscribe======");
                    }

                    @Override
                    public void onNext(Response<Document> documentResponse) {
                        mView.setImage(parseImageUrl(documentResponse.body()));


                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.d("=======onError======" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        mView.complete();
                        Logger.d("=======onComplete======");
                    }
                });

    }

    public void getData(String url) {
        Logger.d("url:" + url);
        url = WelfarePresenter.MEIZI_BASE_URL + url;
        mCurrentUrl = url;
//        String value = page <= 1 ? url : url + "page/" + page;


        OkGo.<Document>get(url)
                .converter(response -> {
                    ResponseBody body = response.body();
                    String s = body.string();
                    Logger.d("getData--->body:" + s);
                    return Jsoup.parse(s);
                })
                .adapt(new ObservableResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    //开始执行请求
//                    Logger.d("=======doOnSubscribe-->accept======");

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Document>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Logger.d("=======onSubscribe======");
                    }

                    @Override
                    public void onNext(Response<Document> documentResponse) {
                        Logger.d("=======onNext======");
//                        List<Atlas> list = parseAtlasData(documentResponse.body(), page);
//                        mView.showData(getListEn(list), page == 1);
                        mView.setImageUrls(parseDetailUrl(documentResponse.body()));
//                        mView.setImageUrls();

                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.d("=======onError======" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        mView.complete();
                        Logger.d("=======onComplete======");
                    }
                });
    }

    /**
     * 获取对图册所有图片所在页面的Url地址
     *
     * @param document
     * @return
     */
    private List<String> parseDetailUrl(Document document) {
        List<String> list = new ArrayList<>();
        int maxPage = 0;
        Elements elements = document.select(".pagenavi > a > span");
        for (Element element : elements) {
            String strPage = element.text();
            if (strPage.matches("^[0-9]*$")) {
                int page = Integer.valueOf(strPage);
                if (page > maxPage) {
                    maxPage = page;
                }
            }
        }
        for (int i = 1; i <= maxPage; i++) {
            list.add(mCurrentUrl + i);
        }
        return list;
    }

    /**
     * 解析详情详情页图片对象
     *
     * @param document
     * @return
     */
    private Image parseImageUrl(Document document) {
        Image image = new Image();
        Elements elements = document.select(".main-image > p > a > img");
        if (elements.size() > 0) {
            Element element = elements.get(0);
            image.setUrl(element.attr("src"));
            image.setDesc(element.attr("alt"));
        }
        return image;
    }

}
