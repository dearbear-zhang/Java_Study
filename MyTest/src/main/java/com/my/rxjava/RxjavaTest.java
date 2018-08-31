package com.my.rxjava;

import io.reactivex.*;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class RxjavaTest {
    public static void main() {
//        Observable.interval(1000, TimeUnit.MILLISECONDS)
//                .sample(500, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println("收到信息:" + aLong);
////                        Thread.sleep(1500);
//                    }
//                });
        System.out.println("主线程"+ Thread.currentThread().getId());

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong > 3;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("结束"+ Thread.currentThread().getId());
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
        while (true) {
        }
    }

}
