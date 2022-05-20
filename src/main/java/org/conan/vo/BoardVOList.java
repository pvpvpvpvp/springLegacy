package org.conan.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BoardVOList {
	private List<BoardVO> list;
	public BoardVOList() {
		list = new ArrayList<>();
	}
}
