package com.scm.studentcoursemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	   @GetMapping("/")
	    public ModelAndView home() {
		   ModelAndView mv = new ModelAndView("index");
		   mv.addObject("jwt","hjc");
		   return mv;
	    }
}
