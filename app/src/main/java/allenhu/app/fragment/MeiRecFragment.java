package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allenhu.app.R;
import allenhu.app.mvp.present.MeiziImgDetailPresent;
import butterknife.ButterKnife;

/**
 */
public class MeiRecFragment extends BaseFragment  {

    private String url;
    private MeiziImgDetailPresent detailPresent;

    private View.OnClickListener listener;
    private View rootView;


    public MeiRecFragment() {
    }

    public static MeiRecFragment newInstance(String url) {
        MeiRecFragment fragment = new MeiRecFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
