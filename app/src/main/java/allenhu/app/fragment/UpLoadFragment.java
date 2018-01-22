package allenhu.app.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import allenhu.app.R;
import allenhu.app.adapter.ImageUpAdapter;
import allenhu.app.bean.LzyResponse;
import allenhu.app.bean.ServerModel;
import allenhu.app.net.okgo.JsonCallback;
import allenhu.app.util.Convert;
import allenhu.app.util.GlideImageLoader;
import allenhu.app.util.PhotoUtil;
import allenhu.app.view.MyRecyclerView;
import allenhu.app.widget.NumberProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpLoadFragment extends Fragment {


    @BindView(R.id.selectImage)
    Button selectImage;

    @BindView(R.id.formUpload)
    Button btnFormUpload;

    @BindView(R.id.downloadSize)
    TextView tvDownloadSize;

    @BindView(R.id.netSpeed)
    TextView tvNetSpeed;

    @BindView(R.id.tvProgress)
    TextView tvProgress;

    @BindView(R.id.pbProgress)
    NumberProgressBar pbProgress;

    @BindView(R.id.images)
    TextView images;


    @BindView(R.id.re_images)
    MyRecyclerView recyclerView;

    @BindView(R.id.requestState)
    TextView requestState;

    @BindView(R.id.requestHeaders)
    TextView requestHeaders;

    @BindView(R.id.responseData)
    TextView responseData;

    @BindView(R.id.responseHeader)
    TextView responseHeader;

    private ArrayList<ImageItem> imageItems;
    private ImageUpAdapter adapter;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        numberFormat = NumberFormat.getPercentInstance();
        imageItems = new ArrayList<>();
        adapter = new ImageUpAdapter(getContext(), R.layout.item_img_upload, imageItems);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setItemPrefetchEnabled(false);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.selectImage, R.id.formUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectImage:
                GlideImageLoader glideImageLoader = new GlideImageLoader();
                PhotoUtil.openImg(getActivity(), glideImageLoader, true, true,
                        5, false, PhotoUtil.OPEN_IMAGE_REQUEST_CODER);
                break;
            case R.id.formUpload:


                upLoad();


                break;
        }
    }

    private void upLoad() {
        ArrayList<File> files = new ArrayList<>();
        if (imageItems != null && imageItems.size() > 0) {
            for (int i = 0; i < imageItems.size(); i++) {
                files.add(new File(imageItems.get(i).path));
            }
        }
        //拼接参数
        OkGo.<LzyResponse<ServerModel>>post("http://server.jeasonlzy.com/OkHttpUtils/upload")//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .headers("header2", "headerValue2")//
                .params("param1", "paramValue1")//
                .params("param2", "paramValue2")//
//                .params("file1",new File("文件路径"))   //这种方式为一个key，对应一个文件
//                .params("file2",new File("文件路径"))
//                .params("file3",new File("文件路径"))
                .addFileParams("file", files)           // 这种方式为同一个key，上传多个文件
                .execute(new JsonCallback<LzyResponse<ServerModel>>() {
                    @Override
                    public void onStart(Request<LzyResponse<ServerModel>, ? extends Request> request) {
                        btnFormUpload.setText("正在上传中...");
                    }

                    @Override
                    public void onSuccess(Response<LzyResponse<ServerModel>> response) {
                        handleResponse(response);
                        btnFormUpload.setText("上传完成");
                    }

                    @Override
                    public void onError(Response<LzyResponse<ServerModel>> response) {
                        handleError(response);
                        btnFormUpload.setText("上传出错");
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        System.out.println("uploadProgress: " + progress);

                        String downloadLength = Formatter.formatFileSize(getContext(), progress.currentSize);
                        String totalLength = Formatter.formatFileSize(getContext(), progress.totalSize);
                        tvDownloadSize.setText(downloadLength + "/" + totalLength);
                        String speed = Formatter.formatFileSize(getContext(), progress.speed);
                        tvNetSpeed.setText(String.format("%s/s", speed));
                        tvProgress.setText(numberFormat.format(progress.fraction));
                        pbProgress.setMax(10000);
                        pbProgress.setProgress((int) (progress.fraction * 10000));
                    }
                });
    }

    public void setImageItems(ArrayList<ImageItem> imageItems) {
        this.imageItems.clear();
        this.imageItems.addAll(imageItems);
//        this.imageItems = imageItems;
        Logger.d("imageItems:" + imageItems.size());
        adapter.notifyDataSetChanged();
    }

    protected <T> void handleResponse(Response<T> response) {
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            requestState.setText("请求成功  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            requestHeaders.setText(sb.toString());
        } else {
            requestState.setText("--");
            requestHeaders.setText("--");
        }
        T body = response.body();
        if (body == null) {
            responseData.setText("--");
        } else {
            if (body instanceof String) {
                responseData.setText((String) body);
            } else if (body instanceof List) {
                sb = new StringBuilder();
                List list = (List) body;
                for (Object obj : list) {
                    sb.append(obj.toString()).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof Set) {
                sb = new StringBuilder();
                Set set = (Set) body;
                for (Object obj : set) {
                    sb.append(obj.toString()).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof Map) {
                sb = new StringBuilder();
                Map map = (Map) body;
                Set keySet = map.keySet();
                for (Object key : keySet) {
                    sb.append(key.toString()).append(" ： ").append(map.get(key)).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof File) {
                File file = (File) body;
                responseData.setText("数据内容即为文件内容\n下载文件路径：" + file.getAbsolutePath());
            } else if (body instanceof Bitmap) {
                responseData.setText("图片的内容即为数据");
            } else {
                responseData.setText(Convert.formatJson(body));
            }
        }

        okhttp3.Response rawResponse = response.getRawResponse();
        if (rawResponse != null) {
            Headers responseHeadersString = rawResponse.headers();
            Set<String> names = responseHeadersString.names();
            sb = new StringBuilder();
            sb.append("url ： ").append(rawResponse.request().url()).append("\n\n");
            sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
            for (String name : names) {
                sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
            }
            responseHeader.setText(sb.toString());
        } else {
            responseHeader.setText("--");
        }
    }

    protected <T> void handleError(Response<T> response) {
        if (response == null) return;
        if (response.getException() != null) response.getException().printStackTrace();
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            requestState.setText("请求失败  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            requestHeaders.setText(sb.toString());
        } else {
            requestState.setText("--");
            requestHeaders.setText("--");
        }

        responseData.setText("--");
        okhttp3.Response rawResponse = response.getRawResponse();
        if (rawResponse != null) {
            Headers responseHeadersString = rawResponse.headers();
            Set<String> names = responseHeadersString.names();
            sb = new StringBuilder();
            sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
            for (String name : names) {
                sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
            }
            responseHeader.setText(sb.toString());
        } else {
            responseHeader.setText("--");
        }
    }
}
