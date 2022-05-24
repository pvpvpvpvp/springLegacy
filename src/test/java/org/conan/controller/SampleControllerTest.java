package org.conan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.conan.config.RootConfig;
import org.conan.config.ServletConfig;
import org.conan.domain.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //WebApplicationContext를 이용하기 위함
@ContextConfiguration(classes={RootConfig.class, ServletConfig.class})
@Log4j
public class SampleControllerTest {
	  @Setter(onMethod_ = {@Autowired})
	  private WebApplicationContext ctx;
	  private MockMvc mockMvc;
	  
	  //MockMvc : 가짜 mvc, 브라우저에서 사용하는 거처럼 Controller 실행
	  @Before
	  public void setup() {
	    this.mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	  }

	  
	  @Test
	  public void testConvert() throws Exception{
		  Ticket ticket = new Ticket();
		  ticket.setGrade("SSS");
		  ticket.setOwner("admin");
		  ticket.setTno(123);
		  
		  String jsonStr = new Gson().toJson(ticket);
		  log.info(jsonStr);
		  mockMvc.perform(MockMvcRequestBuilders.post("/rsample/ticket")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonStr)).andExpect(status().is(200));
				    
	  }
	  
}