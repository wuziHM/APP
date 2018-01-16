package allenhu.app.net.retrofit2;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author：HM $ on 18/1/15 13:55
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface FileUploadService {
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);


    @Multipart
    @POST
    Observable<ResponseBody> uploadFileWithPartMap(
            @Url() String url,
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file);



    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}
