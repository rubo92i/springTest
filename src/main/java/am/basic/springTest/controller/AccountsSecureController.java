package am.basic.springTest.controller;

import am.basic.springTest.model.User;
import am.basic.springTest.model.exceptions.AccessDeniedException;
import am.basic.springTest.model.exceptions.NotFoundException;
import am.basic.springTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import static am.basic.springTest.util.constants.Messages.INTERNAL_ERROR_MESSAGE;
import static am.basic.springTest.util.constants.Messages.PASSWORD_CHANGE_SUCCESS_MESSAGE;
import static am.basic.springTest.util.constants.Pages.HOME_PAGE;
import static am.basic.springTest.util.constants.ParameterKeys.MESSAGE_ATTRIBUTE_KEY;

@Controller
@RequestMapping("/secure")
public class AccountsSecureController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam String password,
                                       @RequestParam String newPassword,
                                       @SessionAttribute("user") User sessionUser) {

        try {
            userService.changePassword(sessionUser.getUsername(), password, newPassword);
            return new ModelAndView(HOME_PAGE, MESSAGE_ATTRIBUTE_KEY, PASSWORD_CHANGE_SUCCESS_MESSAGE);
        } catch (NotFoundException | AccessDeniedException e) {
            return new ModelAndView(HOME_PAGE, MESSAGE_ATTRIBUTE_KEY, e.getMessage());
        }catch (RuntimeException ex){
            return new ModelAndView(HOME_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }

    }

}
