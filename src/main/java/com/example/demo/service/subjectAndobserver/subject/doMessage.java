package com.example.demo.service.subjectAndobserver.subject;

import com.example.demo.service.subjectAndobserver.observer.Cow;
import com.example.demo.service.subjectAndobserver.observer.ObserverA;

public class doMessage {

    public static void main(String[] args) {
        ObserverA cow1 = new Cow("1");
        ObserverA cow2 = new Cow("2");

        CowSubject cowSubject = new CowSubject();
        cowSubject.subscribe(cow1);
        cowSubject.subscribe(cow2);
//        cowSubject.unSubscribe(cow2);
        cowSubject.notification("开始");
    }
}
