package com.my.network.Retrofit;

import com.my.utils.Utils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpMethods {
    //	http请求返回状态码
    private static final int sRET_SUCCESS = 0;
    private static final int sRET_INVALID_TOKEN = 1004;
    private volatile static HttpMethods sInstance;
    private static String sPort = ":8000";
    private static String sBASE_URL = "www.baidu.com" + sPort;
    private static String sUrl = "http://" + sBASE_URL + "/";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private NetService mNetService;
    private HttpResultFormatJudge mHttpResultFormatJudge = new HttpResultFormatJudge();

    //获取单例
    public static HttpMethods getInstance() {
        if (sInstance == null) {
            synchronized (HttpMethods.class) {
                if (sInstance == null) {
                    sInstance = new HttpMethods();
                    return sInstance;
                }
            }
        }
        return sInstance;
    }

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
//				.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new UserConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(sUrl)
                .build();

        mNetService = retrofit.create(NetService.class);
    }

    /***
     * 通过Post 获取某个列表
     * @param observer
     * @param xx
     */
    public void getListInfo(Observer<ResuleFormat<List<String>>> observer, String xx, int id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("xx", xx);
        paramMap.put("id", id);

        String signature = "token" + System.currentTimeMillis() / 1000;
        Observable observable = mNetService.getListInfo(paramMap, Utils.resEncryptToHeader(signature));

        observable.map(mHttpResultFormatJudge)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    /***
     * 通过Get 获取某个列表
     * @param observer
     * @param xx
     * @param id
     */
    public void getListRecord(Observer<ResuleFormat<List<String>>> observer, String xx, int id) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("xx", xx);
        queryMap.put("id", id);

        String signature = "token" + System.currentTimeMillis() / 1000;
        Observable observable = mNetService.getListRecord(queryMap, Utils.resEncryptToHeader(signature));

        observable.map(mHttpResultFormatJudge)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    /***
     * 网络请求消息统一处理
     */
    private class HttpResultFormatJudge implements Function<ResuleFormat, ResuleFormat> {

        @Override
        public ResuleFormat apply(ResuleFormat resuleFormat) throws Exception {
            switch (resuleFormat.getRet()) {
                case sRET_SUCCESS:
                    break;
                case sRET_INVALID_TOKEN:
//                    Utils.getAppToken(mContext, null);
                default:
                    throw new Exception("error_code:" + resuleFormat.getRet() + ";	msg:" + resuleFormat.getMsg());
            }
            return resuleFormat;
        }
    }
}
