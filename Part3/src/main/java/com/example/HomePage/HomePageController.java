package com.example.HomePage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePageController {



    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        return "home";
    }
}
