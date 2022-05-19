package org.conan.controller;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.conan.config.RootConfig;
import org.conan.sample.Chef;
import org.conan.sample.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class SampleTest {
  @Setter(onMethod_ = {@Inject})
  private Restaurant restaurant;
  
  @Setter(onMethod_ = {@Inject})
  private Chef chef;
  
  
//  @Autowired
//  private SampleHotel hotel;
  
  @Test
  public void testExist() {
    assertNotNull(restaurant);
    log.info(restaurant);
    log.info("---------------------------------");
    log.info(restaurant.getChef());
  }
  
//  @Test
//  public void textExistHotel() {
//    assertNotNull(hotel);
//    log.info(hotel);
//    log.info("---------------------------------");
//    log.info(hotel.getChef());
//  }
  

}