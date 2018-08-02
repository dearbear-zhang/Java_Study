package com.my.main;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainAcitivity {
    public static void main(String[] args){
        Observable.just("1", "2", "3")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s + ": helloword");
                    }
                });
    }
}
