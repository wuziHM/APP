package allenhu.app.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import allenhu.app.R;
import allenhu.app.util.Convert;
import allenhu.app.widget.NumberProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DownLoadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DownLoadFragment extends Fragment {


    @BindView(R.id.fileDownload)
    Button btnDown;
    @BindView(R.id.downloadSize)
    TextView tvDownloadSize;
    @BindView(R.id.netSpeed)
    TextView tvNetSpeed;
    @BindView(R.id.tvProgress)
    TextView tvProgress;
    @BindView(R.id.pbProgress)
    NumberProgressBar pbProgress;
    @BindView(R.id.requestState)
    TextView requestState;
    @BindView(R.id.requestHeaders)
    TextView requestHeaders;
    @BindView(R.id.responseData)
    TextView responseData;
    @BindView(R.id.responseHeader)
    TextView responseHeader;

    private NumberFormat numberFormat;


    public DownLoadFragment() {
        // Required empty public constructor
    }

    public static DownLoadFragment newInstance() {
        DownLoadFragment fragment = new DownLoadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_down_load, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);

    }


    @OnClick(R.id.fileDownload)
    public void onClick() {
        OkGo.<File>get("http://server.jeasonlzy.com/OkHttpUtils/download")//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new FileCallback("OkGo.apk") {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        btnDown.setText("正在下载中");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        btnDown.setText("下载完成");
                        handleResponse(response);

                    }

                    @Override
                    public void onError(Response<File> response) {
                        handleError(response);
                        btnDown.setText("下载出错");
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        System.out.println(progress);

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
                responseData.setText(Convert.toJson(body));
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
