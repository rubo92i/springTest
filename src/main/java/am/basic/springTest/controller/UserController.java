package am.basic.springTest.controller;

import am.basic.springTest.model.User;
import am.basic.springTest.model.exceptions.NotFoundException;
import am.basic.springTest.model.exceptions.UnverifiedException;
import am.basic.springTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static am.basic.springTest.util.constants.Messages.INTERNAL_ERROR_MESSAGE;
import static am.basic.springTest.util.constants.Pages.*;
import static am.basic.springTest.util.constants.ParameterKeys.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam(required = false) String remember,
                              HttpSession session) {

        try {
            User user = userService.login(username, password);
            session.setAttribute(USER_ATTRIBUTE_KEY, user);
            return new ModelAndView(HOME_PAGE);
        } catch (UnverifiedException e) {
            ModelAndView modelAndView = new ModelAndView(VERIFICATION_PAGE);
            modelAndView.addObject(MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            modelAndView.addObject(USERNAME_PARAM_KEY, username);
            return modelAndView;
        } catch (NotFoundException e) {
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, e.getMessage());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return new ModelAndView(INDEX_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }

    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String name,
                                 @RequestParam String surname) {


        return null;
    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
