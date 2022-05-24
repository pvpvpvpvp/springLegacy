package org.conan.mapper;

import java.util.stream.IntStream;

import org.conan.config.RootConfig;
import org.conan.vo.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper replyMapper;
	private Long[] bnoArr = {11L,12L,70L};
 	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 3).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 3]);
			vo.setReply("댓글 테스트 "+ i);
			vo.setReplyer("replyer"+ i);
			replyMapper.insert(vo);
		});
	}
 	
 	@Test
	public void testGetReply() {
		log.info(replyMapper.getReply(11L)); 
	}
 	
 	@Test
	public void testDelte() {
		log.info(replyMapper.delete(1L)); 
	}
 	
 	@Test
	public void testUpdate() {
		
			ReplyVO vo = new ReplyVO();
			vo.setRno(2L);
			vo.setBno(11L);
			vo.setReply("댓글 테스트 변경하기! ");
			vo.setReplyer("replyer 변경하기!");
			int cnt = replyMapper.update(vo);
			log.info("################ cnt: "+cnt);
	}
 	

//	@Test
//	public void testInsert() {
//		log.info(boardMapper.getClass());
//		BoardVO vo = new BoardVO();
//		vo.setContent("하하하!");
//		vo.setTitle("asd");
//		vo.setWriter("qwe");
//		log.info(boardMapper.insertSelectKey(vo));
//	}
//	@Test
//	public void testRead() {
//	
//		log.info(boardMapper.read(5L));
//	}
//	@Test
//	public void testDelete() {
//	
//		log.info(boardMapper.delete(5L));
//	}
//	@Test
//	public void testUpdate() {
//		BoardVO vo = new BoardVO();
//		vo.setContent("하하하!@@@@@@@@@@@@@@");
//		vo.setTitle("asd");
//		vo.setWriter("qwe");
//		vo.setBno(1L);
//		log.info(boardMapper.update(vo));
//	}
//	
}