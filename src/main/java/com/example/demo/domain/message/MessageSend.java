package com.example.demo.domain.message;

import com.example.demo.frm.kafka.AbstactDomin;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@Entity
public class MessageSend  extends AbstactDomin implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;
}
