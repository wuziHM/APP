package allenhu.app.net;

import android.content.Context;

import com.hlib.http.request.MHttpResponseAble;
import com.hlib.http.request.MModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：燕青 $ on 17/6/26 19:38
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class TestAPI extends HAPI {


    public TestAPI(Context context) {
        super(context);
    }

    /**
     * 编辑退货单备注
     */
    public void testServer(MHttpResponseAble res) {
        Map<String, Object> map = new HashMap<>();
//        map.put("token", "TRR9TBnxAU0=");
//        map.put("userId", "752");
//        map.put("prodId", "2571");
//        map.put("pageSize", "10");
//        map.put("pageNo", "1");
//        map.put("storeId", "611");

        post("/static/json/meal_desc.json", map, MModel.class, res);
    }

}
