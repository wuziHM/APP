package allenhu.app.Fragment;

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
public class FragmentB extends Fragment {

    private View rootView;

    public FragmentB(String name) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_two,container,false);
        return rootView;

    }
}
