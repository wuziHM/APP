package allenhu.app.net.retrofit2;

/**
 * Author：HM $ on 18/1/15 15:46
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public abstract class CallBack {
    public void onStart(){}
    public void onSubscribe(){}

    public void onCompleted(){}

    abstract public void onError(Throwable e);

    public void onProgress(long fileSizeDownloaded){}

    abstract public void onSucess(String path, String name, long fileSize);
}
