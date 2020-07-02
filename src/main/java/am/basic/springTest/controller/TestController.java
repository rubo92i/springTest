package am.basic.springTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static am.basic.springTest.util.constants.ParameterKeys.MESSAGE_ATTRIBUTE_KEY;

@Controller
public class TestController {

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test() {
        new ModelAndView();
        new ModelAndView("index");
        new ModelAndView("index","key","dfhn");
        return "index";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/test1")
    public ModelAndView test1() {
        return new ModelAndView("index");
    }



}
