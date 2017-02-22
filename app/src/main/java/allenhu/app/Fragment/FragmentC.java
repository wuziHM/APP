package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allenhu.app.R;

/**
 * Created by AllenHu on 2016/2/14.
 */
public class FragmentC extends Fragment {
    private View rootView;

    public static FragmentC newInstance() {
        Bundle args = new Bundle();
        FragmentC fragment = new FragmentC();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_three, container, false);
        return rootView;
    }
}
