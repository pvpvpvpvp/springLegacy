package org.conan.service;

import java.util.List;

import org.conan.domain.Criteria;
import org.conan.mapper.BoardAttachMapper;
import org.conan.mapper.BoardMapper;
import org.conan.mapper.ReplyMapper;
import org.conan.vo.BoardAttachVO;
import org.conan.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	BoardAttachMapper attachMapper;
	@Autowired
	ReplyMapper replyMapper;
	
	@Override
//	@Transactional
	public void regstaer(BoardVO vo) {

		boardMapper.insertSelectKey(vo);

		if(vo.getAttachList() == null || vo.getAttachList().size()<=0) {
			return;
		}
		vo.getAttachList().forEach(attach->{
			log.info("active ~ file upload");
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		
	}
 
	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return boardMapper.read(bno);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		// TODO Auto-generated method stub
		return attachMapper.findByBno(bno);
	}

	@Override
//	@Transactional
	public boolean modify(BoardVO vo) {
		// TODO Auto-generated method stub
		if(vo.getAttachList() == null || vo.getAttachList().size()<=0) {
			return 1==boardMapper.update(vo);
		}
		vo.getAttachList().forEach(attach->{
			log.info("active ~ file upload");
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		
		return 1==boardMapper.update(vo);
	}
	

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return attachMapper.delete(uuid);
	}

	@Override
//	@Transactional
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub'
		replyMapper.boardDelete(bno);
		attachMapper.deleteAll(bno);
		return 1==boardMapper.delete(bno);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return boardMapper.getBoardList();
	}
	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<BoardVO> getListWithSearch(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithSearch(cri);
	}
	
}
