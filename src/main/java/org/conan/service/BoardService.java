package org.conan.service;

import java.util.List;

import org.conan.domain.Criteria;
import org.conan.vo.BoardAttachVO;
import org.conan.vo.BoardVO;

public interface BoardService {
	public void regstaer(BoardVO vo);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO vo);
	public boolean remove(Long bno);
	public List<BoardVO> getList();
	public int getTotal(Criteria cri);
	public List<BoardVO> getListWithSearch(Criteria cri);
	public List<BoardAttachVO> getAttachList(Long bno);
}
