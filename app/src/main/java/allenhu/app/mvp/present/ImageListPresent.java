package allenhu.app.mvp.present;

import android.content.Context;
import android.text.format.DateUtils;

import com.hlib.util.MStringUtil;
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
import java.util.Map;

import allenhu.app.bean.Atlas;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.Type;
import allenhu.app.bean.debean.ILikeType;
import allenhu.app.db.ILikeDao;
import allenhu.app.db.TypeDao;
import allenhu.app.mvp.view.ImageListView;
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
public class ImageListPresent extends BasePresentImpl<ImageListView> {


    private static Map<String, String> mTypeMap;
    private static List<Type> mTypeList;
    private Context context;
    private String mCurrentUrl;
    private Map<String, Integer> mTypeMaxPageMap;
    private ArrayList<ImageBean> imageBeans;
    private ILikeDao iLikeDao;
    private ILikeType type;

    public ImageListPresent(Context context, ImageListView view) {
        this.context = context;
        iLikeDao = new ILikeDao(context);
        attachView(view);
    }

    public void getData(String url, int page) {
        url = WelfarePresenter.MEIZI_BASE_URL + url;
        mCurrentUrl = url;
        String value = page <= 1 ? url : url + "page/" + page;


        OkGo.<Document>get(value)
                .converter(response -> {
                    ResponseBody body = response.body();
                    String s = body.string();
                    Logger.d("body:" + s);
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
                        List<Atlas> list = parseAtlasData(documentResponse.body(), page);
                        mView.showData(getListEn(list), page == 1);

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


    public ArrayList<ImageBean> getListEn(List<Atlas> atlases) {
        if (MStringUtil.isEmptyList(atlases)) {
            Logger.e("getListEn-->atlases是空的");
            return null;
        }
        ArrayList<ImageBean> imageBeans = new ArrayList<>();
        for (Atlas atlas : atlases) {
            ImageBean imageBean = new ImageBean();
            imageBean.setBig(atlas.getCover());
            imageBean.setMiddle(atlas.getCover());
            imageBean.setSmall(atlas.getCover());
            imageBean.setTitle(atlas.getTitle());
            imageBean.setMeiziDetailUrl(atlas.getDetailUrl());
            imageBean.setMeiziUrl(atlas.getUrl());
            imageBean.setDate(DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_YEAR));
            if (type == null) {
                type = new TypeDao(context).getTypeAndAdd("妹子图");
            }
            imageBean.setType(type);
            imageBean.setTypeName("妹子图");
            imageBean.setDate(atlas.getTitle());
            imageBeans.add(imageBean);
        }
        return imageBeans;
    }

    private void addType(Elements elements, List<Type> typeList) {
        for (Element element : elements) {
            Type type = new Type();
            String text = element.text();
            String value = element.attr("href").replaceAll(WelfarePresenter.MEIZI_BASE_URL, "");
            if ("首页".equals(text) || "zipai/".equals(value) || "all/".equals(value) || "best/".equals(value) || "zhuanti/".equals(value)) {
                continue;
            }
            type.setName(text);
            type.setUrl(value);
            typeList.add(type);
            mTypeMap.put(value, text);
        }
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


    /**
     * 判断当前页数是否小于等于最大页数
     *
     * @param document
     * @param currentPage
     * @return
     */
    private boolean hasPage(Document document, int currentPage) {
        if (mTypeMaxPageMap == null) {
            mTypeMaxPageMap = new HashMap<>();
        }
        if (mTypeMaxPageMap.get(mCurrentUrl) != null) {
            return currentPage <= mTypeMaxPageMap.get(mCurrentUrl);
        }

        Elements elements = document.select("nav .nav-links .page-numbers");
        int maxPage = -1;
        for (Element element : elements) {
            if (element.hasClass("dots")) {
                continue;
            }
            String text = element.text();
            try {
                int page = Integer.parseInt(text);
                if (page > maxPage) {
                    maxPage = page;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mTypeMaxPageMap.put(mCurrentUrl, maxPage);
        return currentPage <= maxPage;
    }


    /**
     * 解析对应类型的图册对象
     *
     * @param document
     * @param currentPage
     * @return
     */
    private List<Atlas> parseAtlasData(Document document, int currentPage) {
        List<Atlas> atlasList = new ArrayList<>();
        if (mTypeList == null) {
            mTypeList = parseTypes(document);
        }
        if (!hasPage(document, currentPage)) {
            return atlasList;
        }
        Elements elements = document.select(".postlist > ul > li");
        for (Element element : elements) {
            if (element.children().size() < 1) {
                continue;
            }
            Element aElement = element.child(0);
            Element spanElement = element.child(1);
            Atlas atlas = new Atlas();
            atlas.setTypeName(mTypeMap.get(mCurrentUrl));
            atlas.setUrl(parseUrl(aElement.attr("href")));
            atlas.setDetailUrl(parseUrl(spanElement.child(0).attr("href")) + "/");
            Element imgElement = aElement.child(0);
            atlas.setCover(imgElement.attr("data-original"));
            atlas.setTitle(imgElement.attr("alt"));
            atlasList.add(atlas);
        }
        return atlasList;
    }


    private String parseUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
