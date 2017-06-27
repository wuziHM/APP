package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import allenhu.app.R;
import allenhu.app.adapter.MeiziAdapter;
import allenhu.app.base.BaseActivity;

public class View2Activity extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private int[] imageIds = new int[]
            {
                    R.mipmap.pre1, R.mipmap.pre2, R.mipmap.pre3, R.mipmap.pre4,
                    R.mipmap.pre5, R.mipmap.pre6, R.mipmap.pre7, R.mipmap.pre8,
                    R.mipmap.pre9, R.mipmap.pre10, R.mipmap.pre11, R.mipmap.pre12,
                    R.mipmap.pre13, R.mipmap.pre14, R.mipmap.pre15, R.mipmap.pre16,
                    R.mipmap.pre17, R.mipmap.pre18, R.mipmap.pre19, R.mipmap.pre20,
                    R.mipmap.pre21
            };

    private Button btnChoose;
    private ImageView mImage;
    private Gallery gallery;
    private Context mContext;
    private MeiziAdapter mAdapter;
    private int index = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view2;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

        mContext = this;

        btnChoose = (Button) findViewById(R.id.btn_choose);
        mImage = (ImageView) findViewById(R.id.img_choose);
        gallery = (Gallery) findViewById(R.id.gay_choose);

        mAdapter = new MeiziAdapter(this, imageIds);
        gallery.setAdapter(mAdapter);
        gallery.setOnItemSelectedListener(this);
        btnChoose.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mImage.setImageResource(imageIds[position]);
        index = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(mContext,CaClothes.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("num", Integer.toString(index));
        it.putExtras(bundle);
        startActivity(it);
    }
}
