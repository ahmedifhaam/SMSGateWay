package com.xelvias.imsms.services;

import com.xelvias.imsms.Dao.MessageDao;
import com.xelvias.imsms.Dao.MessageLogDao;
import com.xelvias.imsms.Models.Message;
import com.xelvias.imsms.Models.MessageLog;
import com.xelvias.imsms.utils.DateUtil;
import com.xelvias.imsms.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SmsService {
    @Autowired
    MessageLogDao messageLogDao;

    @Autowired
    MessageDao messageDao;

    @Value("${spring.apikey}")
    private String apikey;

    public MessageLog sendSms(Message message){
        messageDao.save(message);
        String status = sendSmsCall(message);
        MessageLog messageLog = new MessageLog();
        messageLog.setMessage(message);
        messageLog.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        messageLog.getMessage().setStatus(status);
        messageLog.setDate(DateUtil.getCurrentDateTime());
        messageLogDao.save(messageLog);
        return messageLog;
    }

    public List<MessageLog> sendmultisms(MultipartFile file,String message,String mask) throws Exception{
        List<MessageLog> messageLogs = new ArrayList<>();

        File file1 = FileUtils.getFile(file);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            MessageLog messageLog = new MessageLog();
            Message msg = new Message();
            msg.setMask(mask);
            msg.setMessage(message);
            msg.setPhonenumber(replaceSpecialchars(line));
            String status = sendSmsCall(msg);
            msg.setStatus(status);
            messageLog.setUser(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            messageLog.setMessage(msg);
            messageLog.setDate(DateUtil.getCurrentDateTime());
            messageDao.save(msg);
            messageLogDao.save(messageLog);
            if(!status.equals("SENT"))messageLogs.add(messageLog);
        }

        return messageLogs;
    }


    private String sendSmsCall(Message message){
        String Uri = "https://cpsolutions.dialog.lk/index.php/cbs/sms/send?destination="+message.getPhonenumber()+
                "&q="+apikey+"&message="+message.getMessage()+"&from="+message.getMask();//api key should be removed


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(Uri,String.class);

        if(result.equals("0")){
            return "SENT";
        }else{
            return "FAILED :"+result;
        }
//        return "SENT";
    }

    private String replaceSpecialchars(String in){
        String specialchars = "\\ufeff";
        return in.replaceAll(specialchars,"");

    }
}
