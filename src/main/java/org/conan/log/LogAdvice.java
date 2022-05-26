package org.conan.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* org.conan.service.SampleService*.*(..))")
	  public void logBefore() {
	    log.info("=================================================");
	  }
	
	@Before("execution(* org.conan.service.SampleService*.*(..))&&args(str1,str2)")
	  public void logBeforeWithParam(String str1, String str2) {
	    log.info("=================================================");
	    log.info("str1 : "+str1);
	    log.info("str1 : "+str2);
	  }
}
