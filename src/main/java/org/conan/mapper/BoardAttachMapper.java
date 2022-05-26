package org.conan.mapper;

import java.util.List;

import org.conan.vo.BoardAttachVO;

public interface BoardAttachMapper {
	public void insert(BoardAttachVO vo);
	public int delete(String uuid);
	public List<BoardAttachVO> findByBno(Long bno);
	public int deleteAll(Long bno);
	public List<BoardAttachVO> getOldFiles();
}
