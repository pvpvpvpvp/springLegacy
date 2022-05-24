package org.conan.service;

import java.util.List;

import org.conan.vo.ReplyVO;

public interface ReplyService {
	public int regstaer(ReplyVO vo);
	public ReplyVO get(Long rno);
	public boolean modify(ReplyVO vo);
	public boolean remove(Long rno);
	public List<ReplyVO> getList(Long bno);
}
