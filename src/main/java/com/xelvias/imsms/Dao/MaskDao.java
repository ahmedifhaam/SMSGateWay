package com.xelvias.imsms.Dao;

import com.xelvias.imsms.Models.Mask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaskDao extends JpaRepository<Mask,Long> {
    Mask findMaskByValue(String value);
}
