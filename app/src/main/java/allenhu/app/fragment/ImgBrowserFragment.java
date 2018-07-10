package allenhu.app.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;

import allenhu.app.R;
import allenhu.app.view.ganhuo.ProgressWheel;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Author：HM $ on 17/11/24 14:31
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImgBrowserFragment extends Fragment {

    @BindView(R.id.photoImageView)
    PhotoView photoImageView;

    @BindView(R.id.iv_fail)
    ImageView ivFail;

    @BindView(R.id.rl_image_placeholder_bg)
    RelativeLayout rlImagePlaceholderBg;

    @BindView(R.id.progressWheel)
    ProgressWheel progressWheel;

    @BindView(R.id.rl_browser_root)
    RelativeLayout rlBrowserRoot;

    private View rootView;

    private View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private static final String PARAM_URL = "param_url";
    private static final String PARAM_TITLE = "param_title";
    private static final String PARAM_POSITION = "param_position";

    private String mUrl;
    private String mTitle;
//    private String mPosition;


    public ImgBrowserFragment() {
        // Required empty public constructor
    }

    public static ImgBrowserFragment newInstance(String url, String title, int position) {
        ImgBrowserFragment fragment = new ImgBrowserFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_URL, url);
        args.putString(PARAM_TITLE, title);
        args.putInt(PARAM_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(PARAM_URL);
//            mPosition = getArguments().getInt(PARAM_POSITION,0);
            mTitle = getArguments().getString(PARAM_URL);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("=====ImgBrowserFragment=====onActivityCreated====");
        initView();

    }

    private void initView() {
//        final PhotoView imageView = (PhotoView) inflate.findViewById(R.id.photoImageView);
//        RelativeLayout rl_browser_root = (RelativeLayout) inflate.findViewById(R.id.rl_browser_root);
//        final ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(R.id.progressWheel);
//        final RelativeLayout rl_image_placeholder_bg = (RelativeLayout) inflate.findViewById(R.id.rl_image_placeholder_bg);
//        final ImageView iv_fail = (ImageView) inflate.findViewById(R.id.iv_fail);
        ivFail.setVisibility(View.GONE);

//        final String url = arrayList.get(position).getBig();
        Glide.with(getContext())
                .load(mUrl)
                .thumbnail(0.2f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        ivFail.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        rlImagePlaceholderBg.setVisibility(View.GONE);
                        ivFail.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(photoImageView);


        photoImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {

                if (listener != null) {
                    listener.onClick(view);
                }
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mn_image_browser_item_show_image, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
