package allenhu.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allenhu.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpLoadFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_up_down, container, false);
    }

}
