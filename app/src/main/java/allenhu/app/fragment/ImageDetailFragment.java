package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 */
public class ImageDetailFragment extends BaseFragment implements MeiziImgDetailView {

    @BindView(R.id.photoImageView)
    PhotoView photoImageView;

    private String url;
    private MeiziImgDetailPresent detailPresent;

    private View.OnClickListener listener;
    private View rootView;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

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
        rootView = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        /*很快的滑动，ImageDetailFragment的实例A被创建了，
            开始请求图片接口，接口还没请求完的时候实例A已经被销毁了，
            所以需要判断 getActivity() == null
            要不然会闪退
         */
        if (getActivity() == null) {
            Logger.e("activity已经销毁");
            return;
        }
        Glide.with(getContext()).load(glideUrl).into(photoImageView);
        photoImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {

                if (listener != null) {
                    listener.onClick(view);
                }
            }
        });
    }
}
