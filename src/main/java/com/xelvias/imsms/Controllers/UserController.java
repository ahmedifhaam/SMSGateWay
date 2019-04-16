package com.xelvias.imsms.Controllers;


import com.xelvias.imsms.Dao.UserDao;
import com.xelvias.imsms.Models.Constants;
import com.xelvias.imsms.Models.Responses.BaseResponse;
import com.xelvias.imsms.Models.User;
import com.xelvias.imsms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

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

        }else{
            baseResponse.setResponse(Constants.FAILED);
        }
        return baseResponse;
    }

    @Secured("SADMIN")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public BaseResponse updateUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.updateUser(user)){
            baseResponse.setResponse(Constants.SUCCESS);

        }else{
            baseResponse.setResponse(Constants.FAILED);
        }
        return baseResponse;
    }

    @Secured("USER")
    @RequestMapping(value = "/cuser",method = RequestMethod.PUT)
    public BaseResponse updateCurrentUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.updateCurrentUserDetails(user)){
            baseResponse.setResponse(Constants.SUCCESS);

        }else{
            baseResponse.setResponse(Constants.FAILED);
        }
        return baseResponse;
    }

    @Secured("SADMIN")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public BaseResponse addnewUser(@RequestBody User user){
        BaseResponse  baseResponse = new BaseResponse();
        if(userService.addUser(user)){

            baseResponse.setResponse(Constants.SUCCESS);

        }else{
            baseResponse.setResponse(Constants.FAILED);
        }
        return baseResponse;

    }



}
