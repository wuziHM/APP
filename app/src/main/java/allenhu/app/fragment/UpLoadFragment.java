package allenhu.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.imagepicker.bean.ImageItem;

import java.text.NumberFormat;
import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.widget.NumberProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpLoadFragment extends Fragment {


    @BindView(R.id.selectImage)
    Button selectImage;
    @BindView(R.id.formUpload)
    Button formUpload;
    @BindView(R.id.downloadSize)
    TextView downloadSize;
    @BindView(R.id.netSpeed)
    TextView netSpeed;
    @BindView(R.id.tvProgress)
    TextView tvProgress;
    @BindView(R.id.pbProgress)
    NumberProgressBar pbProgress;
    @BindView(R.id.images)
    TextView images;

    private ArrayList<ImageItem> imageItems;
    private NumberFormat numberFormat;


    public UpLoadFragment() {
        // Required empty public constructor
    }

    public static DownLoadFragment newInstance() {
        DownLoadFragment fragment = new DownLoadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_down, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.selectImage, R.id.formUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectImage:
                break;
            case R.id.formUpload:
                break;
        }
    }
}
