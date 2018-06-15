package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.orhanobut.logger.Logger;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.Image;
import allenhu.app.mvp.present.MeiziImgDetailPresent;
import allenhu.app.mvp.view.MeiziImgDetailView;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 */
public class ImageDetailFragment extends Fragment implements MeiziImgDetailView {

    @BindView(R.id.photoImageView)
    PhotoView photoImageView;
    private String url;
    MeiziImgDetailPresent detailPresent;


    public ImageDetailFragment() {
    }

    public static ImageDetailFragment newInstance(String url) {
        ImageDetailFragment fragment = new ImageDetailFragment();
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
        detailPresent = new MeiziImgDetailPresent(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("detail:" + url);
        detailPresent.getImage(url);
    }

    @Override
    public void complete() {

    }

    @Override
    public void setImageUrls(List<String> urls) {

    }

    @Override
    public void setImage(Image image) {
        Logger.d(image.toString());
        GlideUrl glideUrl = new GlideUrl(image.getUrl(), new LazyHeaders.Builder()
                .addHeader("referer", image.getUrl())
                .addHeader("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
                .build()
        );
        Glide.with(getContext()).load(glideUrl).into(photoImageView);
    }
}
