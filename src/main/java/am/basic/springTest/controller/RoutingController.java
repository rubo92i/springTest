package am.basic.springTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static am.basic.springTest.util.constants.Pages.*;

@Controller
public class RoutingController {




    @RequestMapping(method = RequestMethod.GET, path = "/go-register")
    public String getRegisterPage() {
        return REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/go-forget-password")
    public String getForgetPasswordPage() {
        return FORGET_PASSWORD_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/secure/home")
    public String getHomePage() {
        return HOME_PAGE;
    }

}
