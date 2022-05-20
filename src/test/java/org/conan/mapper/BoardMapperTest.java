package org.conan.mapper;

import org.conan.config.RootConfig;
import org.conan.vo.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	@Test
	public void testGetTime() {
		log.info(boardMapper.getClass());
		log.info(boardMapper.getBoardList());
	}

	@Test
	public void testInsert() {
		log.info(boardMapper.getClass());
		BoardVO vo = new BoardVO();
		vo.setContent("하하하!");
		vo.setTitle("asd");
		vo.setWriter("qwe");
		log.info(boardMapper.insertSelectKey(vo));
	}
	@Test
	public void testRead() {
	
		log.info(boardMapper.read(5L));
	}
	@Test
	public void testDelete() {
	
		log.info(boardMapper.delete(5L));
	}
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setContent("하하하!@@@@@@@@@@@@@@");
		vo.setTitle("asd");
		vo.setWriter("qwe");
		vo.setBno(1L);
		log.info(boardMapper.update(vo));
	}
	
}