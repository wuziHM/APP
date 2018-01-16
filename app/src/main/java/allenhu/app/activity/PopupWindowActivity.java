package allenhu.app.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.CommentPopupFrag;
import allenhu.app.fragment.CustomInterpolatorPopupFrag;
import allenhu.app.fragment.DialogPopupFrag;
import allenhu.app.fragment.InputPopupFrag;
import allenhu.app.fragment.ListPopupFrag;
import allenhu.app.fragment.MenuPopupFrag;
import allenhu.app.fragment.ScalePopupFrag;
import allenhu.app.fragment.SlideFromBottomPopupFrag;

public class PopupWindowActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private ScalePopupFrag mNormalPopupFrag;
    private SlideFromBottomPopupFrag mSlideFromBottomPopupFrag;
    private CommentPopupFrag mCommentPopupFrag;
    private InputPopupFrag mInputPopupFrag;
    private ListPopupFrag mListPopupFrag;
    private MenuPopupFrag mMenuPopupFrag;
    private DialogPopupFrag mDialogPopupFrag;
    private CustomInterpolatorPopupFrag mInterpolatorPopupFrag;
    private PopupWindow popupWindow;
    private String[] ss = {"燕青", "武松", "关胜", "朱仝", "吴用"};
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initView();
        initFragment();

    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.ly_test);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });
    }

    private void showPop() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_pop_list, null);
        ListView listView = (ListView) view.findViewById(R.id.lv_test);
        listView.setDivider(null);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item2, R.id.tv_item2_text, ss);
        listView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(linearLayout, 0, 0);
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();

        mNormalPopupFrag = new ScalePopupFrag();
        mSlideFromBottomPopupFrag = new SlideFromBottomPopupFrag();
        mCommentPopupFrag = new CommentPopupFrag();
        mInputPopupFrag = new InputPopupFrag();
        mListPopupFrag = new ListPopupFrag();
        mMenuPopupFrag = new MenuPopupFrag();
        mDialogPopupFrag = new DialogPopupFrag();
        mInterpolatorPopupFrag = new CustomInterpolatorPopupFrag();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.id_scale_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mNormalPopupFrag).commit();
                break;
            case R.id.id_slide_from_bottom_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mSlideFromBottomPopupFrag).commit();
                break;
            case R.id.id_comment_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mCommentPopupFrag).commit();
                break;
            case R.id.id_input_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mInputPopupFrag).commit();
                break;
            case R.id.id_list_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mListPopupFrag).commit();
                break;
            case R.id.id_menu_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mMenuPopupFrag).commit();
                break;
            case R.id.id_dialog_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mDialogPopupFrag).commit();
                break;
            case R.id.id_custom_interpolator_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent, mInterpolatorPopupFrag).commit();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
