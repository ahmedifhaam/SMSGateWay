package com.xelvias.imsms.Models;

import com.xelvias.imsms.utils.DateUtil;
import com.xelvias.imsms.utils.FileUtils;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

 //
@Entity
@Table(name = "MessageLog",uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id")
})
public class MessageLog implements Serializable, FileUtils.CSVwritable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name="message_id")
    Message message;


    String user;



    @Column(name="date")
    @Type(type = "date")
    @Temporal(TemporalType.DATE)
    Date date;

     public String getDate() {
         return date==null?null:DateUtil.formatDate(date);
     }

     public void setDate(Date date) {
         this.date = date;
     }

     public Long getId() {
         return Id;
     }

     public void setId(Long id) {
         Id = id;
     }

     public Message getMessage() {
         return message;
     }

     public void setMessage(Message message) {
         this.message = message;
     }

     public String getUser() {
         return user;
     }

     public void setUser(String user) {
         this.user = user;
     }

     public String[] toStringArray(){
         return new String[]{this.message.getPhonenumber(),this.message.getMessage(),this.message.getMask(),this.message.getStatus(), this.getDate()};
     }

//     public Date getDate() {
//         return date;
//     }
//
//     public void setDate(Date date) {
//         this.date = date;
//     }
 }
