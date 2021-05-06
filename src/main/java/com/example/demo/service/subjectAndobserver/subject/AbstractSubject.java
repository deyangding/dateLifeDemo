package com.example.demo.service.subjectAndobserver.subject;

import com.example.demo.service.subjectAndobserver.observer.ObserverA;

public interface AbstractSubject {
    void subscribe(ObserverA observerA);
    void unSubscribe(ObserverA observerA);
    void notification(String message);
}
