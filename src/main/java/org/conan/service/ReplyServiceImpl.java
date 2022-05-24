package org.conan.service;

import java.util.List;

import org.conan.mapper.ReplyMapper;
import org.conan.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int regstaer(ReplyVO vo) {
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		return replyMapper.getReply(rno);
	}

	@Override
	public boolean modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		return 1==replyMapper.update(vo);
	}

	@Override
	public boolean remove(Long rno) {
		// TODO Auto-generated method stub
		return 1==replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno) {
		// TODO Auto-generated method stub
		return replyMapper.getAllreply(bno);
	}
	
}
