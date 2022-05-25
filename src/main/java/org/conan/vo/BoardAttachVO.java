package org.conan.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "BoardAttachVO")
public class BoardAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private Long bno;
}
