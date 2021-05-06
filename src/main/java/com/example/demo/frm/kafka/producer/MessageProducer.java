package com.example.demo.frm.kafka.producer;

import com.example.demo.domain.message.MessagePublish;
import com.example.demo.domain.message.MessageSend;
import com.example.demo.domain.message.MessageSendRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    @Autowired
    private MessageSendRepository messageRepository;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, Object data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String dataString =  gson.toJson(data).toString();
        kafkaTemplate.send(topic, dataString);
        doSave(dataString);
    }

    public void doSave(String data){
        MessageSend message = new MessageSend();
        message.setValue(data);
        messageRepository.save(message);
    }
}
