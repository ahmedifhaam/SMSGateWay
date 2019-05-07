package com.xelvias.imsms.Controllers;

import com.xelvias.imsms.Models.Constants;
import com.xelvias.imsms.Models.RegexEntry;
import com.xelvias.imsms.Models.Responses.BaseResponse;
import com.xelvias.imsms.services.RegexEntryService;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegexEntryController {
    @Autowired
    RegexEntryService regexEntryService;

    @RequestMapping(value = "/regexentry",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addnewregexentry(@RequestBody RegexEntry regexEntry){
        BaseResponse baseResponse = new BaseResponse();
        regexEntryService.saveRegexEntry(regexEntry);
        baseResponse.setResponse(Constants.SUCCESS);
        return baseResponse;
    }

    @RequestMapping(value = "/regexentry",method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse updateEntry(@RequestBody RegexEntry regexEntry){
        RegexEntry entry = regexEntryService.findRegexfor(regexEntry.getName());

        BaseResponse baseResponse = new BaseResponse();
        if(entry==null){
            baseResponse.setResponse(Constants.FAILED);
            return baseResponse;
        }
        entry.setExpression(regexEntry.getExpression());
        regexEntryService.saveRegexEntry(entry);
        baseResponse.setResponse(Constants.SUCCESS);
        return baseResponse;
    }

    @RequestMapping(value = "/regexentry/{name}",method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse deleteregexEntry(@PathVariable String name){
        BaseResponse baseResponse = new BaseResponse();
        RegexEntry regexfor = regexEntryService.findRegexfor(name);
        if(regexfor==null){
            baseResponse.setResponse(Constants.FAILED);
            return baseResponse;
        }
        regexEntryService.deleteRegexEntry(regexfor);
        baseResponse.setResponse(Constants.SUCCESS);
        return baseResponse;
    }


    @RequestMapping(value = "/regexentryall",method = RequestMethod.GET)
    @ResponseBody
    public List<RegexEntry> findall(){
        List<RegexEntry> allEntries = regexEntryService.getAllEntries();

        return allEntries;
    }

    @RequestMapping(value = "/regexentry/{name}",method = RequestMethod.GET)
    @ResponseBody
    public RegexEntry find(@PathVariable String name){
        return regexEntryService.findRegexfor(name);
    }





}
