package allenhu.app.net.retrofit2;


import allenhu.app.bean.request.ImgListBean;
import allenhu.app.bean.request.ShowImgBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author：HM $ on 17/11/20 16:03
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface ImageApi {

    @GET("showapi_open_bus/pic/pic_type")
    Observable<ImgListBean> getImageList();




    @GET("showapi_open_bus/pic/pic_search")
    Observable<ShowImgBean> getImageShow(@Query("type") String type, @Query("page") int page);


}
