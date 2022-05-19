package org.conan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	  @RequestMapping(value = "/basic", method = {RequestMethod.GET})
	  public void basicGet() {
	    log.info("basic get.....");
	  }
	  
	  @GetMapping("/only")
	  public void onlyGet() {
	    log.info("only get........");
	  }
}