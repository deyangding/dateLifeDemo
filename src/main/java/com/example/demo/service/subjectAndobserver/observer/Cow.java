package com.example.demo.service.subjectAndobserver.observer;

public class Cow implements ObserverA {
    private String message;

    public Cow(String message) {
        this.message = message;
    }

    @Override
    public void update() {
        write();
    }

    private void write(){
        System.out.println("update: "+  this.message);
    }
}
