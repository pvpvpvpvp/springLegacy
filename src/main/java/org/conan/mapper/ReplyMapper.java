package org.conan.mapper;

import java.util.List;

import org.conan.vo.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> getAllreply(Long bno);
	public int insert(ReplyVO vo);
	public ReplyVO getReply(Long rno);
	public List<ReplyVO> getReplyList(Long bno);
	public int delete(Long rno);
	public int update(ReplyVO vo);
}
