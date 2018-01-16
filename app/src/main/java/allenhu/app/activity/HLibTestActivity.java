package allenhu.app.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.net.TestAPI;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HLibTestActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.textView3)
    HtmlTextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlib_test);
        ButterKnife.bind(this);
        TestAPI api = new TestAPI(this);

//        api.testServer(new MHttpResponseImpl() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onSuccessResult(int statusCode, Object object) {
//                String response = (String) object;
//                HLibTestBean bean = new Gson().fromJson(response, HLibTestBean.class);
//                String desc = bean.getDataList().get(1).getDesc();
//                MLogUtil.e("==============desc:" + desc);
//                MLogUtil.i("pos", "==============desc:" + desc);
//                textView.setText(Html.fromHtml(desc, Html.FROM_HTML_MODE_COMPACT));
//            }
//        });

        String text = "<p><span style=\"font-size:16px\">说明：</span></p><p><span style=\"font-size:16px\">金融机构导航：<span style=\"color:#e74c3c\">1~20万元</span>/年（根据不同位置收费）。</span></p><p><span style=\"font-size:16px\">交易及物流平台导航：<span style=\"color:#e74c3c\">1~20万元</span>/年（根据不同位置收费）。</span></p><p><span style=\"font-size:16px\">商城导航：<span style=\"color:#e74c3c\">1~5万元</span>/年（根据不同位置收费）。</span></p><p><span style=\"font-size:16px\">商贸流通企业官网导航：<span style=\"color:#e74c3c\">600元</span>/年，套餐<span style=\"color:#e74c3c\">3000元</span>/10年（相互链接收费减半）。</span></p><p><span style=\"font-size:16px\">政府官网相互链接：免费（需相互导航）。</span></p>";

        textView.setText(Html.fromHtml(text));
//        String text = "1、推荐商家开店缴纳网店租金、广告位、电子名片、网店运营培训(不退款)：由平台给予推荐者一次性<span style=\"color:#e74c3c\">30%</span>的推广奖励金。";
//        String text = "<p><span style=\"font-size:16px\">人人分享：</span></p><p><span style=\"font-size:16px\">(网店租金、广告位、电子名片)和通过网店运营培训考试(网店运营培训在线或者现场进行，考试通过由震海电商大学颁发结业证；在校大学、军人和残疾人免费在线或者现场培训，考试通过，无结业证)。</span></p><p><span style=\"font-size:16px\">备注: &nbsp;&nbsp;</span></p><p><span style=\"font-size:16px\">一、推荐商家开店</span></p><p><span style=\"font-size:16px\">1、推荐商家开店缴纳网店租金、广告位、电子名片、网店运营培训(不退款)：由平台给予推荐者一次性<span style=\"color:#e74c3c\">30%</span>的推广奖励金。</span></p><p><span style=\"font-size:16px\">2、网店风险金(一年之内不开网店随时可以退款)：</span></p><p><span style=\"font-size:16px\">推荐商家开店同时缴纳网店风险金由平台给予推荐者一次性<span style=\"color:#e74c3c\">1%</span>的推广奖励金</span></p><p><span style=\"font-size:16px\">推广商家开店三个月缴纳风险金由平台给予推荐者一次性<span style=\"color:#e74c3c\">0.5%</span>的推广奖励金</span></p><p><span style=\"font-size:16px\">3、商品质量保证金(采购商付款保证金以内的货款立即可以取现)：提醒商家缴纳质量保证金，由平台给予推荐者一次性<span style=\"color:#e74c3c\">1%</span>的推广奖励金。</span></p><p><span style=\"font-size:16px\">二、分享震海代</span></p><p><span style=\"font-size:16px\">分享链接&ldquo;震海代&ldquo;标识商品，由平台一次性给予推荐者推广奖励金是采购商交易额的<span style=\"color:#e74c3c\">1%-N%</span>(每件商品不同)。</span></p>";
//        textView3.setText(htmlSpanner.fromHtml(text));
//        textView3.setHtml(text);

    }
}
