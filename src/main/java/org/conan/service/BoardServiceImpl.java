package org.conan.service;

import java.util.List;

import org.conan.mapper.BoardMapper;
import org.conan.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void regstaer(BoardVO vo) {
		boardMapper.insertSelectKey(vo);
		
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO vo) {
		// TODO Auto-generated method stub
		return 1==boardMapper.update(vo);
	}

	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		return 1==boardMapper.delete(bno);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return boardMapper.getBoardList();
	}

}
