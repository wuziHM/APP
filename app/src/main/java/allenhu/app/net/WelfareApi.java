package allenhu.app.net;

import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zjh on 2018/3/17.
 *
 */

public interface WelfareApi {
    @GET("/{url}")
    Observable<Document> getData(@Path("url") String value);
}
