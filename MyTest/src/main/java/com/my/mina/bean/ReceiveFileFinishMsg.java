package com.my.mina.bean;

import java.io.Serializable;

public class ReceiveFileFinishMsg implements Serializable {
    private int id;
    private boolean success;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
