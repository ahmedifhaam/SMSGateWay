package com.xelvias.imsms.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {

    @Value("${server.servlet.context-path}")
    String contextpath;


    @RequestMapping(path = {"/error","**/processing","**/error","**/adminpanel"})
    public void redirectWithUsingRedirectPrefix(HttpServletResponse response) throws IOException {
        response.sendRedirect(contextpath+"/index.html");

    }





}
