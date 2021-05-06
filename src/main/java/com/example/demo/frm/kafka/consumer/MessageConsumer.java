package com.example.demo.frm.kafka.consumer;

import com.example.demo.domain.message.MessagePublish;
import com.example.demo.domain.message.MessagePublishRepository;
import com.example.demo.domain.message.MessageSendRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @Autowired
    private MessagePublishRepository messageRepository;

    @KafkaListener(topics = {"ddy"})
    public void listen(ConsumerRecord record) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        System.out.println("收到消息啦！！！！！！");
        System.out.println(gson.toJson(record.value()));
        String message = gson.toJson(record.value());
        doSave(message);

    }

    public void doSave(String data) {
        MessagePublish messagePublish = new MessagePublish();
        messagePublish.setValue(data);
        messageRepository.save(messagePublish);
    }

}
