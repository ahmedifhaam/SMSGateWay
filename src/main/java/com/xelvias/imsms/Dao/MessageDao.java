package com.xelvias.imsms.Dao;

import com.xelvias.imsms.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message,Long> {
}
