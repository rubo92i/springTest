package am.basic.springTest.controller;

import am.basic.springTest.model.User;
import am.basic.springTest.model.exceptions.AccessDeniedException;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.model.exceptions.NotFoundException;
import am.basic.springTest.model.exceptions.UnverifiedException;
import am.basic.springTest.service.UserService;
import am.basic.springTest.util.CookieUtil;
import am.basic.springTest.util.ValidationMessageConverter;
import am.basic.springTest.util.encoder.Encryptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import static am.basic.springTest.util.constants.Messages.*;
import static am.basic.springTest.util.constants.Pages.*;
import static am.basic.springTest.util.constants.ParameterKeys.*;

@Log4j2
@Controller
public class AccountsController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView start(@CookieValue(name = "remember_token", required = false) String rememberToken,
                              HttpSession session, HttpServletResponse response) throws Exception {

        if (rememberToken == null) {
            return new ModelAndView(INDEX_PAGE);
        }

        String token = Encryptor.decrypt(rememberToken);
        String username = token.split(":")[0];
        String password = token.split(":")[1];

        return login(username, password, "ON", session, response);
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam(required = false, defaultValue = "OFF") String remember,
                              HttpSession session,
                              HttpServletResponse response) throws Exception {

        try {
            User user = userService.login(username, password);
            session.setAttribute(USER_ATTRIBUTE_KEY, user);

            if ("ON".equalsIgnoreCase(remember)) {
                Cookie cookie = new Cookie(REMEMBER_TOKEN_COOKIE_KEY, Encryptor.encrypt(username + ":" + password));
                cookie.setMaxAge(360000);
                response.addCookie(cookie);
            }

            return new ModelAndView(HOME_PAGE);

        } catch (UnverifiedException e) {
            log.info("Attempt to login for unauthorized user with username : {}",username);
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            return modelAndView;
        } catch (NotFoundException e) {
            log.info("Attempt to login with wrong credentials username : {}  and password : {}",username,password);
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, e.getMessage());
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }

    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String name,
                                 @RequestParam String surname) {


        try {

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);

            userService.register(user);


            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, REGISTRATION_SUCCESS_MESSAGE);
            return modelAndView;

        } catch (DuplicateDataException ex) {
            return new ModelAndView(REGISTER_PAGE, MESSAGE_ATTRIBUTE_KEY, ex.getMessage());
        } catch (ConstraintViolationException exception) {
            return new ModelAndView(REGISTER_PAGE, MESSAGE_ATTRIBUTE_KEY, ValidationMessageConverter.getMessage(exception));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return new ModelAndView(REGISTER_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }
    }


    @RequestMapping(path = "/forget-password", method = RequestMethod.POST)
    public ModelAndView forgetPassword(@RequestParam String username) {
        try {
            userService.sendCode(username);
            return new ModelAndView(RECOVER_PASSWORD_PAGE, USERNAME_PARAM_KEY, username);
        } catch (NotFoundException e) {
            return new ModelAndView(FORGET_PASSWORD_PAGE, MESSAGE_ATTRIBUTE_KEY, e.getMessage());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return new ModelAndView(FORGET_PASSWORD_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }
    }


    @RequestMapping(path = "/recover-password", method = RequestMethod.POST)
    public ModelAndView recoverPassword(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String code) {

        try {
            userService.recoverPassword(username, code, password);
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, PASSWORD_CHANGE_SUCCESS_MESSAGE);
        } catch (NotFoundException | AccessDeniedException e) {
            ModelAndView modelAndView = new ModelAndView(RECOVER_PASSWORD_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            return modelAndView;
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            ModelAndView modelAndView = new ModelAndView(RECOVER_PASSWORD_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
            return modelAndView;
        }
    }


    @RequestMapping(path = "/resend", method = RequestMethod.POST)
    public ModelAndView resend(@RequestParam String username) {

        try {
            userService.sendCode(username);

            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, CODE_SUCCESSFULLY_SEND_MESSAGE);
            return modelAndView;

        } catch (NotFoundException e) {
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            return modelAndView;
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
            return modelAndView;
        }
    }

    @RequestMapping(path = "/verify", method = RequestMethod.POST)
    public ModelAndView verify(@RequestParam String username, @RequestParam String code) {
        try {
            userService.verify(username, code);
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, VERIFICATION_SUCCESS_MESSAGE);
        } catch (NotFoundException | AccessDeniedException e) {
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            return modelAndView;
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
            return modelAndView;
        }
    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate();
        CookieUtil.removeCookie(request, response, REMEMBER_TOKEN_COOKIE_KEY);
        return INDEX_PAGE;
    }

}
