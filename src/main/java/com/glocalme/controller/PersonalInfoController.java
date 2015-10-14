package com.glocalme.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Scope("request")
@RequestMapping("/personal")
public class PersonalInfoController {

	
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save() {
    	System.out.println("PersonalInfoController - Save");
    }
    
    
}
