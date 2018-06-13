package allenhu.app.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hlib.util.MToastUtil;

import org.jsoup.nodes.Document;

import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ImagePagerAdapter;
import allenhu.app.bean.Type;
import allenhu.app.mvp.present.WelfarePresenter;
import allenhu.app.mvp.view.WelfareView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareActivity extends BaseActivity implements WelfareView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private WelfarePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);

        presenter = new WelfarePresenter(this, this);
        presenter.getData();

    }

    @Override
    public void showBaseProgressDialog(String msg) {
        showProgress();
    }

    @Override
    public void hideBaseProgressDialog() {
        hideProgress();
    }

    @Override
    public void showData(Document object) {

    }

    @Override
    public void setTypes(List<Type> types) {
        if (types == null) {
            MToastUtil.show(WelfareActivity.this, "types为空");
            return;
        }
        PagerAdapter pagerAdapter = ImagePagerAdapter.createPageAdapter(getSupportFragmentManager(), types);
        viewPage.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
