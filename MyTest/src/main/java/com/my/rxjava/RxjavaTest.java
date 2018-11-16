package com.my.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.flowable.FlowablePublish;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxjavaTest {
    public static void main(String[] args) {
//        Observable.interval(1000, TimeUnit.MILLISECONDS)
//                .sample(500, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println("收到信息:" + aLong);
////                        Thread.sleep(1500);
//                    }
//                });
        System.out.println("主线程" + Thread.currentThread().getId());

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
                        System.out.println("结束" + Thread.currentThread().getId());
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {

            }
        });

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });
        observable.startWith(observable1).subscribe();

        Observable.just("zhang", "li", "wang")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        List<Integer> datas = new ArrayList<>();
        Observable.fromIterable(datas)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        return new Observable<Object>() {
                            @Override
                            protected void subscribeActual(Observer<? super Object> observer) {

                            }
                        };
                    }
                }).subscribe();

        FlowableProcessor rxbus = PublishProcessor.create().toSerialized();
        rxbus.ofType(String.class);
        rxbus.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });

        while (true) {
        }


    }

}
