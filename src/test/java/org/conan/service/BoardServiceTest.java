package org.conan.service;

import static org.junit.Assert.assertNotNull;

import org.conan.config.RootConfig;
import org.conan.vo.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardServiceTest {
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testBoardService() {
		log.info("boardServieTestResult: "+boardService);
		assertNotNull(boardService);
	}
	@Test
	public void testGetList() {
		log.info("boardServieTestResult: "+boardService.getList());
	}
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("test");
		vo.setContent("reg");
		vo.setWriter("qwer");
		boardService.regstaer(vo);
		log.info("boardServieTestResult:  "+vo.getBno());
	
	}
	@Test
	public void testget() {
		log.info("boardServieTestResult: "+boardService.get(6L).toString());
	
	}
	@Test
	public void testDelete() {
		log.info("boardServieTestResult: "+boardService.remove(6L));
	}
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(7L);
		vo.setTitle("testUpdate");
		vo.setContent("regUPdate");
		vo.setWriter("qwerUPDATE");
		log.info("boardServieTestResult: "+boardService.modify(vo));
	}
}
