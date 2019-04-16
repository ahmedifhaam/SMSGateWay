package com.xelvias.imsms.Controllers;

import com.xelvias.imsms.Models.Message;
import com.xelvias.imsms.Models.MessageLog;
import com.xelvias.imsms.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api" )
public class SMSController {

    @Autowired
    SmsService smsService;

    @RequestMapping(value = "test")
    @ResponseBody
    public String test(){
        return "Success";
    }

    @RequestMapping(value = "/sendSms",method = RequestMethod.POST)
    @ResponseBody
    public MessageLog sendsms (@RequestBody Message message){
        return smsService.sendSms(message);
    }

    @RequestMapping(value = "/sendmultisms")
    @ResponseBody
    public List<MessageLog> sendmultiplesms(@RequestParam MultipartFile file,String message,String mask) throws Exception{
        return smsService.sendmultisms(file,message,mask);
    }
}
