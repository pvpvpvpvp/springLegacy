package org.conan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.conan.domain.SampleVO;
import org.conan.domain.Ticket;
import org.conan.service.ReplyServiceImpl;
import org.conan.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rsample")
@Log4j
@AllArgsConstructor
public class RSampleController {
	
	@Autowired
	private ReplyServiceImpl serviceImpl;
	
	@GetMapping(value = "/getTest")
	public String getText() {
//		log.info("MIME TYPE: "+MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112,"스타","로드");
	}
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("data", new SampleVO(111,"나","강림"));
		return map;
	}
	
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(000,""+height, ""+weight);
		ResponseEntity<SampleVO> result = null;
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	@GetMapping(value = "/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat,
					@PathVariable("pid") String pid) {
		return new String[] {"category: "+cat,"productid: "+pid};
	}
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("connvert............ticket: "+ticket);
		return ticket;
	}
	
	@PostMapping(value =  "/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create (@RequestBody ReplyVO vo) {
		
		int insertCount = serviceImpl.regstaer(vo);
	
		return insertCount == 1? new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}", produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") Long bno,
					@PathVariable("page") int page) {
		return new ResponseEntity<>(serviceImpl.getList(bno) ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}", produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		return new ResponseEntity<>(serviceImpl.get(rno) ,HttpStatus.OK);
	}
	
	@RequestMapping(
			method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}",
			consumes = "application/json",
			produces =  {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify (@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		vo.setRno(rno);
		return serviceImpl.modify(vo)? new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		return serviceImpl.remove(rno)? new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	
}
