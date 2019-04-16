//package com.xelvias.imsms;
//
//import com.xelvias.imsms.Dao.RoleDao;
//import com.xelvias.imsms.Dao.UserDao;
//import com.xelvias.imsms.Models.Role;
//import com.xelvias.imsms.Models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.HashSet;
//import java.util.Set;
//
//////@SpringBootApplication
////public class InitialRecordGen implements CommandLineRunner {
//////    @Autowired
//////    RoleDao roleDao;
//////
//////    @Autowired
//////    UserDao userDao;
//////
//////
//////    public static void main(String[] args){
//////        SpringApplication app = new SpringApplication(InitialRecordGen.class);
//////        app.run(args);
//////    }
//////
//////
//////    @Override
//////    public void run(String... args) throws Exception {
////////        Role role = new Role();
////////        role.setRole("ADMIN");
////////        roleDao.save(role);
////////        role = new Role();
////////        role.setRole("USER");
////////        roleDao.save(role);
//////
//////        User user = new User();
//////        user.setActive(1);
//////        user.setPassword(getEncoder().encode("pass"));
//////        user.setUsername("user");
//////        user.setName("Ahmed Ifhaam");
//////        user.setLastName("Ifhaam");
//////        Set<Role> roles = new HashSet<>();
//////        Role adminRole = roleDao.findByRole("ADMIN");
//////        Role userRole = roleDao.findByRole("USER");
//////        roles.add(userRole);
//////        roles.add(adminRole);
//////        user.setRoles(roles);
//////        userDao.save(user);
//////
//////
//////        System.out.println("test");
//////    }
//////
//////    BCryptPasswordEncoder getEncoder(){
//////        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//////        return encoder;
//////    }
////}
