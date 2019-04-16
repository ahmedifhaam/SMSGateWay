package com.xelvias.imsms.Controllers;

import com.xelvias.imsms.Dao.MessageLogDao;
import com.xelvias.imsms.Models.MessageLog;
import com.xelvias.imsms.services.MessageLogService;
import com.xelvias.imsms.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageLogController {
    @Autowired
    MessageLogService messageLogService;

    @RequestMapping(value = "/messagelogs" ,method = RequestMethod.GET)
    @ResponseBody
    public List<MessageLog> getAllMessageLogs()  {
        return messageLogService.findAllLogs();
    }

    @RequestMapping(value = "/filtermessagelogs" ,method = RequestMethod.GET)
    @ResponseBody
    public List<MessageLog> getMessageLogs(@RequestParam String user,@RequestParam String mask,
                                           @RequestParam String number, @RequestParam String status)  {
        return messageLogService.findLogs(user,mask,number,status);
    }

    @RequestMapping(value = "/filemessagelogs" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getMessageLogsFile(@RequestParam String user,@RequestParam String mask,
                                           @RequestParam String number, @RequestParam String status)  throws Exception{
        List<MessageLog> all = messageLogService.findLogs(user,mask,number,status);

        File file = new File(FileUtils.WritetoFile(all, ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamResource);
    }

    @RequestMapping(value = "/messagelogfile" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getAllMessageLogFile() throws IOException {
        List<MessageLog> all = messageLogService.findAllLogs();
        File file = new File(FileUtils.WritetoFile(all, ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamResource);

//        return messageLogDao.findAll();
    }


}
