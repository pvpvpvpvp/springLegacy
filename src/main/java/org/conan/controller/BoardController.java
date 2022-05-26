package org.conan.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.conan.domain.Criteria;
import org.conan.service.BoardServiceImpl;
import org.conan.vo.BoardAttachVO;
import org.conan.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	  public String boardGet(Criteria cri,Model model) {
		model.addAttribute("list", serviceImpl.getListWithSearch(cri));
		model.addAttribute("total", serviceImpl.getTotal(cri));
		return "board/list";
	}
	@GetMapping("/register")
	  public String boardRegister() {
		
		return "board/register";
	}
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		
		
		serviceImpl.regstaer(vo);
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach->log.info(attach));
		}
		
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:/board/list";
	}
	@GetMapping({"/get","/modify"})
	public void register(@RequestParam("bno")Long bno, Model model) {
		log.info("/get");
		model.addAttribute("board", serviceImpl.get(bno));
	}
	@PostMapping("/modify")
	public String get(BoardVO vo, RedirectAttributes rttr) {
		log.info("modify: "+vo);
		if(serviceImpl.modify(vo)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		log.info("remove: "+bno);
		List<BoardAttachVO> attachList = serviceImpl.getAttachList(bno);
		if(serviceImpl.remove(bno)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result","success");
		}
		
		return "redirect:/board/list";
	}
	@DeleteMapping("/deleteFileTable/{uuid}")
	public void deleteFileTable(@PathVariable("uuid") String uuid, RedirectAttributes rttr) {
		log.info("remove: "+uuid);
		
		if(1==serviceImpl.removeFile(uuid)) {
			rttr.addFlashAttribute("result","success");
		}
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
	    if (attachList==null||attachList.size()==0) {
	      return;
	    }
	    log.info("delete attach files.......");
	    log.info(attachList);
	    attachList.forEach(attach->{
	      try {
	        Path file = Paths.get("e:/upload/"+attach.getUploadpath()+"/"+attach.getUuid()+"_"+attach.getFileName());
	        Files.deleteIfExists(file);
	        if (Files.probeContentType(file).startsWith("image")) {
	          Path thumbNail = Paths.get("d:/upload/"+attach.getUploadpath()+"/s_"+attach.getUuid()+"_"+attach.getFileName());
	          Files.delete(thumbNail);
	        }
	      }catch (Exception e) {
	        log.error("delete file error : " +e.getMessage());
	      }
	    });
	  }
	@GetMapping(value = "/getAttachList" ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<>(serviceImpl.getAttachList(bno),HttpStatus.OK);
	}
	
}
