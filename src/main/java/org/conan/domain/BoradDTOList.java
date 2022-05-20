package org.conan.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BoradDTOList {
	private List<BoardDTO> list;
	public BoradDTOList() {
		list = new ArrayList<>();
	}
}
