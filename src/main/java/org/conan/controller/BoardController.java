package org.conan.controller;

import java.util.ArrayList;
import java.util.List;

import org.conan.mapper.BoardMapper;
import org.conan.vo.BoardVO;
import org.conan.vo.BoardVOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	  public @ResponseBody BoardVOList boardGet() {
		List<BoardVO> voList = new ArrayList<>();
		voList = boardMapper.getBoardList();
		BoardVOList bo = new BoardVOList();
		bo.setList(voList);
		System.out.println();
		return bo;
	  }
}
