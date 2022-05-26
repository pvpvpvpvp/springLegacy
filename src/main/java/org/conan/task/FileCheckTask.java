package org.conan.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.conan.mapper.BoardAttachMapper;
import org.conan.vo.BoardAttachVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	@Autowired
	BoardAttachMapper attachMapper;
	
	@Scheduled(cron="0 * * * * *")
	public void checkFiles() throws Exception{
		log.warn("file check~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		List<Path> fileListPaths = fileList.stream()
				.map(vo->Paths.get("c:/upload/",vo.getUploadpath(),vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());
		fileList.stream().filter(vo->vo.isFileType()==true)
		.map(vo->Paths.get("c:/upload/",vo.getUploadpath(),"s_"+vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList())
		.forEach(p->fileListPaths.add(p));
		fileListPaths.forEach(p->log.warn(p));
		File targetDir = Paths.get("c:/upload/",getFolderYesterDay()).toFile();
		File[] removeFile = targetDir.listFiles(file->fileListPaths.contains(file.toPath())==false);
		for (File file : removeFile) {
			log.warn(file.getAbsolutePath()); file.delete();
		}
	}
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		return str.replace("-", File.separator);
	}
}
