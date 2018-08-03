package com.my.network.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/***
 * 参考 http://www.androidchina.net/5758.html
 */
public interface NetService {

    /***
     *
     * @param paramMap      参数列表
     * @param signature     header中添加的签名
     * @return
     */
    @POST("xxx/yyy/list")        // 获取某个列表信息
    Observable<ResuleFormat<List<String>>>getListInfo(@Body Map<String, Object> paramMap, @Header("Signature") String signature);

    /***
     *  适用restful风格
     * @param queryMap      GET请求中?后面的参数列表
     * @param signature     header中添加的签名
     * @return
     */
    @GET("xxx/yyy/get")          // 获取某个列表信息
    Observable<ResuleFormat<List<String>>>getListRecord(@QueryMap Map<String, Object> queryMap, @Header("Signature") String signature);

}
