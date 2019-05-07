package com.xelvias.imsms.Dao;

import com.xelvias.imsms.Models.RegexEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegexEntryDao extends JpaRepository<RegexEntry,Long> {

    RegexEntry findByName(String name);

}
