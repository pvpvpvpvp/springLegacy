package org.conan.controller;

import java.util.ArrayList;
import java.util.List;

import org.conan.mapper.BoardMapper;
import org.conan.service.BoardServiceImpl;
import org.conan.vo.BoardVO;
import org.conan.vo.BoardVOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	
	@Autowired
	private BoardServiceImpl serviceImpl;
	@RequestMapping(value = "/home", method = {RequestMethod.GET})
	  public String boardHome() {
		
		return "board_home";
	  }
	@GetMapping("/list")
	  public String boardGet(Model model) {
		model.addAttribute("list", serviceImpl.getList());
		return "board/list";
	}
	@GetMapping("/register")
	  public String boardRegister() {
		
		return "board/register";
	}
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		serviceImpl.regstaer(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:/board/list";
	}
	@GetMapping("/get")
	public void register(@RequestParam("bno")Long bno, Model model) {
		log.info("/get");
		model.addAttribute("board", serviceImpl.get(bno));
	}
	@PostMapping("modify")
	public String get(BoardVO board, RedirectAttributes rttr) {
		log.info("modify: "+board);
		if(serviceImpl.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	@PostMapping("remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		log.info("remove: "+bno);
		if(serviceImpl.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
}
