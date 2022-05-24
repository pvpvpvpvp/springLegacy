package org.conan.controller;

import java.util.List;

import org.conan.config.RootConfig;
import org.conan.config.ServletConfig;
import org.conan.domain.Criteria;
import org.conan.mapper.BoardMapper;
import org.conan.vo.BoardVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //WebApplicationContext를 이용하기 위함
@ContextConfiguration(classes={RootConfig.class, ServletConfig.class})
@Log4j
public class BoardControllerTest {
	  @Setter(onMethod_ = {@Autowired})
	  private WebApplicationContext ctx;
	  private MockMvc mockMvc;
	  @Autowired
	  private BoardMapper boardMapper;
	  
	  //MockMvc : 가짜 mvc, 브라우저에서 사용하는 거처럼 Controller 실행
	  @Before
	  public void setup() {
	    this.mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	  }
	  @Test
	  public void testList() throws Exception{
	    log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
	        .andReturn().getModelAndView().getModelMap());
	  }
	  @Test
	  public void testRegister() throws Exception{
		  String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				  .param("title", "test 한글깨지나?")
				  .param("content", "test Mock")
				  .param("writer", "test Mock"))
				  .andReturn().getModelAndView().getViewName();
		  log.info(resultPage);
	  }
	  @Test
	  public void testGet() throws Exception{
	    log.info(
	        mockMvc
	        .perform(MockMvcRequestBuilders.get("/board/get")
	        .param("bno","2")
	        ).andReturn().getModelAndView().getModelMap());
	  }
	  
	  @Test
	  public void testModify() throws Exception{
		  String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				  .param("title", "test modify")
				  .param("content", "test modify")
				  .param("writer", "test modify")
				  .param("bno", "1"))
				  .andReturn().getModelAndView().getViewName();
		  log.info(resultPage);
	  }
	  @Test
	  public void testRemove() throws Exception{
		  String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				  .param("bno", "1"))
				  .andReturn().getModelAndView().getViewName();
		  log.info(resultPage);
	  }
	  
	  @Test
	  public void testSearch() throws Exception{
		  Criteria cri = new Criteria();
		  cri.setKeyword("지성");
		  cri.setType("TWC");
		  List<BoardVO> list = boardMapper.getListWithSearch(cri);
		  list.forEach(board -> log.info(board));
		  
		  log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				  .param("keyword", "지성")
				  .param("type", "TWC"))
			        .andReturn().getModelAndView().getModelMap());
//		  
	  }
	  
}