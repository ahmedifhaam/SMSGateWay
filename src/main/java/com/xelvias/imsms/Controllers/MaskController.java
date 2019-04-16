package com.xelvias.imsms.Controllers;

import com.xelvias.imsms.Dao.MaskDao;
import com.xelvias.imsms.Models.Mask;
import com.xelvias.imsms.services.MaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MaskController {
    @Autowired
    MaskService maskService;

    @RequestMapping(value = "/mask")
    @ResponseBody
    public List<Mask> getMasks(){
        return maskService.getAllMasks();
    }

    @RequestMapping(value="/mask",method = RequestMethod.PUT)
    public boolean saveMask(@RequestBody Mask mask){
        maskService.addnewMask(mask);
        return true;
    }
}
