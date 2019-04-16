package com.xelvias.imsms.Dao;

import com.xelvias.imsms.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByRole(String role);
}
