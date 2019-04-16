package com.xelvias.imsms.Models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Message implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


//    @OneToOne(mappedBy = "message")
//    MessageLog messageLog;
//
//    public MessageLog getMessageLog() {
//        return messageLog;
//    }
//
//    public void setMessageLog(MessageLog messageLog) {
//        this.messageLog = messageLog;
//    }

    String phonenumber;

    String message;

    String status;

    String mask;

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
