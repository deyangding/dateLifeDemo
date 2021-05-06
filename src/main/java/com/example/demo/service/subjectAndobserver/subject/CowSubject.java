package com.example.demo.service.subjectAndobserver.subject;

import com.example.demo.service.subjectAndobserver.observer.ObserverA;

import java.util.ArrayList;
import java.util.List;

public class CowSubject implements AbstractSubject {
    List<ObserverA> list = new ArrayList<>();


    @Override
    public void subscribe(ObserverA observerA) {
        list.add(observerA);
    }

    @Override
    public void unSubscribe(ObserverA observerA) {
        list.remove(observerA);
    }

    @Override
    public void notification(String message) {
        list.forEach(observerA -> observerA.update());
    }
}
