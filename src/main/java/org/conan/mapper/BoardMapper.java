package org.conan.mapper;

import java.util.List;

import org.conan.domain.Criteria;
import org.conan.vo.BoardVO;

public interface BoardMapper {
//	@Select("SELECT * FROM tbi_board")
//	public List<BoardVO> getList();
	public List<BoardVO> getBoardList();
	public int insert(BoardVO vo);
	public int insertSelectKey(BoardVO vo);
	public BoardVO read(Long bno);
	public int update(BoardVO vo);
	public int delete(Long bno);
	public int getTotalCount(Criteria cri);
	public List<BoardVO> getListWithSearch(Criteria cri);
}
