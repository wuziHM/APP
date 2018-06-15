package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hlib.util.MStringUtil;
import com.hlib.util.MToastUtil;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ImagePagerAdapter;
import allenhu.app.bean.Image;
import allenhu.app.mvp.present.MeiziImgDetailPresent;
import allenhu.app.mvp.view.MeiziImgDetailView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeiziImgDetailActivity extends BaseActivity implements MeiziImgDetailView {


    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_text)
    TextView tvText;
    private MeiziImgDetailPresent detailPresent;
    private ImagePagerAdapter adapter;
    private List<String> datas = new ArrayList<>();

    public static void toMeiziImgDetailActivity(Context context, String url) {
        Intent intent = new Intent(context, MeiziImgDetailActivity.class);
        intent.putExtra("data", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_img_detail);
        ButterKnife.bind(this);
        detailPresent = new MeiziImgDetailPresent(this, this);
        detailPresent.getData(getIntent().getStringExtra("data"));

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    @Override
    public void complete() {

    }

    @Override
    public void setImageUrls(List<String> urls) {
        if (MStringUtil.isEmptyList(urls)) {
            MToastUtil.show(MeiziImgDetailActivity.this, "图片链接是空的");
        }
        detailPresent.getImage(urls.get(0));

        datas.clear();
        datas.addAll(urls);
        tvText.setText("1/" + urls.size());
        adapter = ImagePagerAdapter.createDetailPageAdapter(this.getSupportFragmentManager(), datas);
        viewPage.setAdapter(adapter);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvText.setText((position + 1) + "/" + datas.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void setImage(Image image) {
    }
}
