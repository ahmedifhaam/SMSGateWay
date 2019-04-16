package com.xelvias.imsms.services;

import com.xelvias.imsms.Dao.RoleDao;
import com.xelvias.imsms.Dao.UserDao;
import com.xelvias.imsms.Models.Role;
import com.xelvias.imsms.Models.User;
import com.xelvias.imsms.utils.StringUtils;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDao userDao,RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public User findUserByUsername(String username){
        return userDao.findByUsername(username);
    }

    public boolean addUser(User user){
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        user.setActive(1);
////        Role userRole = roleDao.findByRole("ADMIN");
////        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        userDao.save(user);

        User usertoUpdate = new User();
        if(!StringUtils.isEmpty(user.getUsername())){


            usertoUpdate.setUsername(user.getUsername());
            usertoUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            usertoUpdate.setName(user.getName());

            usertoUpdate.setRoles(new HashSet<>());
            usertoUpdate.getRoles().add(roleDao.findByRole("USER"));
            for(Role role:user.getRoles()){
                Role roletoAdd = roleDao.findByRole(role.getRole());
                usertoUpdate.getRoles().add(roletoAdd);
            }
            usertoUpdate.setActive(1);
            userDao.save(usertoUpdate);
            return true;
        }else{
            return false;
        }
    }

    public boolean updateUser(User user){
        User usertoUpdate = null;
        System.out.println(user.getUsername());
        if(!StringUtils.isEmpty(user.getUsername())){
            usertoUpdate = userDao.findByUsername(user.getUsername());

            System.out.println("Update user details ");
            System.out.println("Password before => "+user.getPassword());
            if(usertoUpdate==null) return false;
            if(!StringUtils.isEmpty(user.getPassword())) {
                System.out.println("User Password is : = > "+user.getPassword());
                usertoUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            if(!StringUtils.isEmpty(user.getName()))usertoUpdate.setName(user.getName());

            usertoUpdate.setRoles(new HashSet<>());
            usertoUpdate.getRoles().add(roleDao.findByRole("USER"));
            for(Role role:user.getRoles()){
                Role roletoAdd = roleDao.findByRole(role.getRole());
                usertoUpdate.getRoles().add(roletoAdd);
            }
            usertoUpdate.setActive(1);
            userDao.save(usertoUpdate);
            return true;
        }else{
            return false;
        }

    }

    public boolean updateCurrentUserDetails(User user){
        org.springframework.security.core.userdetails.User principal
                = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = principal.getUsername();

        User usertoUpdate = null;
        if(!StringUtils.isEmpty(username)){
            usertoUpdate = userDao.findByUsername(username);

            if(usertoUpdate==null) return false;
            if(!StringUtils.isEmpty(user.getPassword())) {

                usertoUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }


            usertoUpdate.setActive(1);
            userDao.save(usertoUpdate);
            return true;
        }else{
            return false;
        }


    }

    public boolean deleteUser(String username){
        User usertodelete = null;

        if(!StringUtils.isEmpty(username)){
            usertodelete = userDao.findByUsername(username);
            if(usertodelete==null) return false;
            userDao.delete(usertodelete);
            return true;
        }else{
            return false;
        }
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
