package com.xelvias.imsms.services;


import com.xelvias.imsms.Dao.MessageLogDao;
import com.xelvias.imsms.Models.Message;
import com.xelvias.imsms.Models.MessageLog;
import com.xelvias.imsms.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageLogService {

    @Autowired
    MessageLogDao messageLogDao;

    public List<MessageLog> findAllLogs(){

        return messageLogDao.findTop100ByOrderByDateDesc();
    }

    public List<MessageLog> findLogs(String user,String mask,String number,String status){
        if(StringUtils.isEmpty(user))user=null;
        if(StringUtils.isEmpty(mask))mask=null;
        if(StringUtils.isEmpty(number))number=null;
        if(StringUtils.isEmpty(status))status=null;

        MessageLog messageLog = new MessageLog();
        messageLog.setUser(user);
        messageLog.setMessage(new Message());
        messageLog.getMessage().setMask(mask);
        messageLog.getMessage().setPhonenumber(number);
        messageLog.getMessage().setStatus(status);

        return messageLogDao.findAll(Example.of(messageLog));

    }
}
