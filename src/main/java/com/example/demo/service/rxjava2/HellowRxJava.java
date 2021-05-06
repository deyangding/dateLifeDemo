package com.example.demo.service.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.LinkedList;

public class HellowRxJava {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("aa");
                observableEmitter.onNext("bb");
                observableEmitter.onNext("cc");
            }
        });
        Consumer<String> consumer =  new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() +"===consumer==" + s);
            }
        };
//
        observable.subscribe(consumer);

        //
        observable.observeOn(Schedulers.newThread()).subscribe(consumer);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName() +"===22222consumer==" + s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.observeOn(Schedulers.newThread()).subscribe(observer);



//        for (;;);


        LinkedList<String> j = new LinkedList<>();;
        j.add("A");
        j.add("B");
        j.add("C");
        j.get(1);


    }
}
