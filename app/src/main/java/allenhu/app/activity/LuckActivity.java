package allenhu.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.Random;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.net.retrofit2.NetWork;
import allenhu.app.widget.LuckyMonkeyPanelView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LuckActivity extends BaseActivity {

    @BindView(R.id.lucky_panel)
    LuckyMonkeyPanelView luckyPanel;

    @BindView(R.id.btn_action)
    Button btnAction;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.view_container)
    LinearLayout viewContainer;
    private String[] names = {"王二狗", "张狗蛋", "赵铁根", "珍香妹子", "杨伟", "范建", "李建仁", "沈得健"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck);
        ButterKnife.bind(this);
        luckyPanel.setName(names);
        luckyPanel.setActivity(this);
        tvResult.setText("公平抽奖系统");

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("hhahahha");
                if (!luckyPanel.isGameRunning()) {
                    luckyPanel.startGame();
                } else {
                    int stayIndex = new Random().nextInt(8);
                    luckyPanel.tryToStop(stayIndex);
                }
            }
        });
    }

    public void uploadFile(File file) {

        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        // 执行请求
        Call<ResponseBody> call = NetWork.getFileUploadService().upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Logger.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.e("Upload error:", t.getMessage());
            }
        });
    }




    public void uploadFiles(String url) {

        NetWork.getFileUploadService().downloadFile(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
//    public void uploadFiles() {
//
//        //构建body
//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("name", name)
//                .addFormDataPart("psd", psd)
//                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
//                .build();
//
//        //如果和rxjava1.x , call就换成 Observable
//        Call<ResponseBody> call = service.upload(url, requestBody);
//        // 执行
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call,
//                                   Response<ResponseBody> response) {
//                Logger.v("Upload", "success");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Logger.e("Upload error:", t.getMessage());
//            }
//        });
//
//
//    }

}
