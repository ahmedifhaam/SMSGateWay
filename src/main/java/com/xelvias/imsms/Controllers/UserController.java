package com.xelvias.imsms.Controllers;

import com.xelvias.imsms.Models.Constants;
import com.xelvias.imsms.Models.Responses.BaseResponse;
import com.xelvias.imsms.Models.Responses.LoginResponse;
import com.xelvias.imsms.Models.User;
import com.xelvias.imsms.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private Log log = LogFactory.getLog(UserController.class);

    @Autowired
    UserService userService;

    @Secured("SADMIN")
    @RequestMapping("/allusers")
    public List<User> findallUsers(){
        return userService.findAllUsers();
    }

    @Secured("SADMIN")
    @RequestMapping("/user/{username}")
    public User findUser(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @Secured("SADMIN")
    @RequestMapping(value = "/user/{username}",method = RequestMethod.DELETE)
    public BaseResponse deleteUser(@PathVariable String username){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.deleteUser(username)){
            baseResponse.setResponse(Constants.SUCCESS);
            log.info("User Deleted : "+username);
        }else{
            baseResponse.setResponse(Constants.FAILED);
            log.info("User Deletion Failed : "+username);
        }
        return baseResponse;
    }

    @Secured("SADMIN")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public BaseResponse updateUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.updateUser(user)){
            baseResponse.setResponse(Constants.SUCCESS);
            log.info("Updated user : "+user.toString());

        }else{
            baseResponse.setResponse(Constants.FAILED);
            log.info("Updating user failed : "+user.toString());
        }
        return baseResponse;
    }

    @Secured("USER")
    @RequestMapping(value = "/cuser",method = RequestMethod.PUT)
    public BaseResponse updateCurrentUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.updateCurrentUserDetails(user)){
            baseResponse.setResponse(Constants.SUCCESS);
            log.info("Updated current user : "+user.toString());

        }else{
            baseResponse.setResponse(Constants.FAILED);
            log.info("Updating current user Failed : "+user.toString());
        }
        return baseResponse;
    }

    @Secured("SADMIN")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public BaseResponse addnewUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.addUser(user)){
            baseResponse.setResponse(Constants.SUCCESS);
            log.info("New user added "+user);
        }else{
            baseResponse.setResponse(Constants.FAILED);
            log.info("New user adding failed : "+user);
        }
        return baseResponse;

    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    public LoginResponse getcurrentUser(){
        org.springframework.security.core.userdetails.User user = null;
        try{
            user =
                    ((org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }catch (ClassCastException ex){
            user = null;
        }

        LoginResponse loginResponse = new LoginResponse();
        if(user!=null){


            loginResponse.setResponse(Constants.SUCCESS);
            loginResponse.setUser(user);
        }else{
            loginResponse.setResponse(Constants.FAILED);
            loginResponse.setUser(null);
        }
        return loginResponse;
    }


}
