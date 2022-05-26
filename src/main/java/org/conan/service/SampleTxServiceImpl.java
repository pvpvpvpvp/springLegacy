package org.conan.service;

import org.conan.mapper.Sample1Mapper;
import org.conan.mapper.Sample2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {
	@Autowired
	Sample1Mapper sample1Mapper;
	@Autowired
	Sample2Mapper sample2Mapper;
	
	@Override
	@Transactional
	public void addData(String value) {
		// TODO Auto-generated method stub
		log.info("mapper1.........."); sample1Mapper.insertCol1(value);
		log.info("mapper2.........."); sample2Mapper.insertCol2(value);
		log.info("end..........");
	}

}
