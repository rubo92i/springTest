package am.basic.springTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test() {
        new ModelAndView();
        new ModelAndView("index");
        new ModelAndView("index", "key", "dfhn");
        return "index";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/test1")
    public ModelAndView test1() {
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/test2")
    public ModelAndView test2(
            @RequestParam(name = "name", required = false, defaultValue = "John") String name,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {


        return new ModelAndView("index");
    }

}
