package com.my.network.Retrofit;

import java.io.Serializable;

/**
 *
 */

/***
 * http请求通用模板1
 * @param <T>
 */
public class ResuleFormat<T> implements Serializable {
    private int count;
    private int ret;
    private int total;
    private int offset;
    private int size;
    private boolean is_end;
    private String begin_time;
    private String end_time;
    private String msg;
    private T info_list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo_list() {
        return info_list;
    }

    public void setInfo_list(T info_list) {
        this.info_list = info_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean is_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }
}
