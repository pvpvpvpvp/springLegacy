package org.conan.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.conan.domain.SampleDTO;
import org.conan.domain.SampleDTOList;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	  @GetMapping("/ex01")
	  public String ex01(SampleDTO dto) {
	    log.info(""+dto);
	    return "ex01";
	  }
	  @GetMapping("/ex02")
	  public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
	    log.info("name: "+name);
	    log.info("age: "+age);
	    return "ex02";
	  }
	  
	  @GetMapping("/ex02-list")
	  public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
	    log.info("ids: "+ids);
	    return "ex02List";
	  }
	  @GetMapping("/ex02-array")
	  public String ex02Array(@RequestParam("ids") String[] ids) {
	    log.info("ids: "+Arrays.toString(ids));
	    return "ex02Array";
	  }
	  @GetMapping("/ex02-bean")
	  public String ex02Array(SampleDTOList list) {
	    log.info("list dtos: "+list);
	    return "ex02Bean";
	  }
	  @GetMapping("/ex02-bean-json")
	  @ResponseBody
	  public String ex02ArrayJson(SampleDTOList list) {
	    log.info("list dtos: "+list);
	    return list.toString();
	  }
	  
	  @RequestMapping(value="/json-ex",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	  @ResponseBody
	  public String jsonEX() {
	    JSONObject jsonObject = new JSONObject();
	   	jsonObject.append("test", "json");
	
	    return jsonObject.toString();
	  }
	  
	  @GetMapping("/ex04")
	  public String ex04(SampleDTO dto, @ModelAttribute(value="page") String page) {
		  log.info(dto);
		  log.info(page);
	    return "/sample/ex04";
	  }
	  @GetMapping("/ex06")
	  @ResponseBody
	  public SampleDTO ex06() {
		SampleDTO dto = new SampleDTO();
		dto.setAge(10); dto.setName("conan");
	    return dto;
	  }
	   
	  @GetMapping("/ex07")
	  public @ResponseBody SampleDTOList ex07() {
		SampleDTOList dtoList = new SampleDTOList();
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10); dto.setName("conan");
		ArrayList<SampleDTO> dtoList1 = new ArrayList<>();
		dtoList1.add(dto);
		dtoList1.add(dto);
		dtoList.setList(dtoList1);
	    return dtoList;
	  }
	  @GetMapping("/exupload")
	  public String expppp() {
		return "exupload";
	  }
	  @PostMapping("/ex-upload")
	  public void exUpload(ArrayList<MultipartFile>files) {
		for (MultipartFile multipartFile : files) {
			log.info(multipartFile.getOriginalFilename());
		}
		log.info(" 살려줘");
	  }
}