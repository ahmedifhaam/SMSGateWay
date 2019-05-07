package com.xelvias.imsms.services;

import com.xelvias.imsms.Dao.RegexEntryDao;
import com.xelvias.imsms.Models.RegexEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegexEntryService {

    @Autowired
    RegexEntryDao regexEntryDao;

    public RegexEntry findRegexfor(String name){
        return regexEntryDao.findByName(name);
    }

    public void saveRegexEntry(RegexEntry entry){
        regexEntryDao.save(entry);
    }

    public void deleteRegexEntry(String name){
        RegexEntry entry = regexEntryDao.findByName(name);
        if(entry!=null)regexEntryDao.delete(entry);
    }

    public void deleteRegexEntry(RegexEntry regexEntry){
        if(regexEntry!=null)regexEntryDao.delete(regexEntry);
    }

    public List<RegexEntry> getAllEntries(){
        return regexEntryDao.findAll();
    }
}
