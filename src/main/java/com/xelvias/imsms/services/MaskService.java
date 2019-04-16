package com.xelvias.imsms.services;

import com.xelvias.imsms.Dao.MaskDao;
import com.xelvias.imsms.Models.Mask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MaskService {



    @Autowired
    MaskDao maskDao;

    public List<Mask> getAllMasks(){
        List<Mask> masks = maskDao.findAll();
        if(masks.size()==0){
            masks = getDefaultList();
            for(Mask mask:masks){
                maskDao.save(mask);
            }
        }
        masks = maskDao.findAll();
        return masks;
    }

    public static List<Mask> getDefaultList(){
        String[] defaultmaskvalues = new String[]{"U Kelaniya","UoK Pay","UoK R","UoK P","UoK Admin",
                "UoK AcEst", "UoK Exam"};
        List<Mask> masks = new ArrayList<>();
        for(String mask : defaultmaskvalues){
            Mask mk = new Mask();
            mk.setValue(mask);

            masks.add(mk);

        }
        return masks;

    }


    public Mask addnewMask(Mask mask){
        if(getMaskByValue(mask.getValue())==null){
            return maskDao.save(mask);
        }else{
            return null;
        }
    }

    public Mask getMaskByValue(String value){
        return maskDao.findMaskByValue(value);
    }

    public void saveMask(Mask masktoSave){
        maskDao.save(masktoSave);
    }
}
