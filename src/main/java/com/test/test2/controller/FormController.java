package com.test.test2.controller;

import com.test.test2.entity.LoginData;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class FormController {

    @GetMapping("/form")
    public String openForm(Model model) {
        System.out.println("open form ");
        model.addAttribute("loginData", new LoginData());
        return "form";
    }

    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult result) {
        System.out.println(loginData.getUserName().isBlank()+"----or-- "+loginData.getUserName().isEmpty());
        if(result.hasErrors()){
            System.out.println(result);
            return "form";
        }
        return "success";
    }
}
