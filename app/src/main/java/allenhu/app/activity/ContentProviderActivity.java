package allenhu.app.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hlib.util.MLogUtil;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ContactAdapter;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.bean.ContactBean;
import allenhu.app.listener.OnItemClickListener;
import allenhu.app.util.ContactUtil;
import io.reactivex.functions.Consumer;

public class ContentProviderActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button read, write;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
    }



    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                            initData();
                            // 用户已经同意该权限
                            Logger.d(permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Logger.d(permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Logger.d(permission.name + " is denied.");
                        }
                    }
                });


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.re_contact);
        read = (Button) findViewById(R.id.btn_readContact);
        write = (Button) findViewById(R.id.btn_writeContact);
        read.setOnClickListener(this);
        write.setOnClickListener(this);

    }

    public void initData() {

        List<ContactBean> list = ContactUtil.getPhoneContacts(this);
        ContactAdapter adapter = new ContactAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ContentProviderActivity.this, "RecycleView的item事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_readContact:

                break;

            case R.id.btn_writeContact:

                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 37) {
            MLogUtil.e("权限获取结果:" + grantResults[0] + "");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                initData();
            } else {
                // Permission Denied
                Toast.makeText(this, "没有相关权限，无法实现该功能", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
