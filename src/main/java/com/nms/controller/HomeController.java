package com.nms.controller;

import com.nms.model.User;
import com.nms.repository.UserRepository;
import com.nms.service.support.SViewHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Controller
public class HomeController {

    @Autowired
    private SViewHelper sViewHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(DeviceRouterController.class);

    @RequestMapping(value = "", method = GET)
    public String index() {

        return "login";
    }

    @RequestMapping(value="/login", method = GET)
    public String login(Model model,
                        @RequestParam(value="error", required = false) String error,
                        @RequestParam(value="logout", required = false) String logout,
                        @RequestParam(value="profile", required = false) String password) {
        if(error != null) {
            model.addAttribute("msg", "error");
        }

        if(logout != null) {
            model.addAttribute("msg", "logout");
        }

        if(password != null) {
            model.addAttribute("msg", "password");
        }

        return "login";
    }
/*
    @RequestMapping(value="/logout", method = GET)
    public String logout(Model model) {


        return "login";
    }
*/
    @RequestMapping(value="/password", method = GET)
    public String changePasswordPage(Model model) {

        //model.addAttribute("username", sViewHelper.getUsername());

        return "password";
    }

    @RequestMapping(value="/password", method = POST)
    public String changePassword(Model model, HttpServletRequest request,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword) {

        if(oldPassword == null || newPassword == null || confirmPassword == null ||
                newPassword.equals(confirmPassword) == false) {
            logger.debug("====== invalid params");
            model.addAttribute("msg", "error");
            return "password";
        }

        String username = sViewHelper.getUsername();
        if(username == null || username.length() == 0) {
            model.addAttribute("msg", "name");
            logger.debug("====== invalid username");
            return "password";
        }

        User user = userRepository.findByUsername(username);
        if(user == null) {
            logger.debug("====== invalid username");
            model.addAttribute("msg", "name");
            return "password";
        }

        logger.debug("====== old: " + oldPassword + "\n encode: " + passwordEncoder.encode(oldPassword)
            + "\n sql: " + user.getPassword());

        //if(passwordEncoder.encode(oldPassword).equals(user.getPassword()) == false) {
        if(passwordEncoder.matches(oldPassword, user.getPassword()) == false) {
            logger.debug("====== password wrong");
            model.addAttribute("msg", "old");
            return "password";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        /* clean session */
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        securityContextImpl.setAuthentication(null);

        logger.debug("====== it is life");
        /*   */
        model.addAttribute("msg", "profile");

        return "portal";
    }
}
