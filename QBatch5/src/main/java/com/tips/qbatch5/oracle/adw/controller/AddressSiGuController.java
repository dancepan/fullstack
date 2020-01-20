package com.tips.qbatch5.oracle.adw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tips.qbatch5.oracle.adw.repository.entity.Korea_Address_Sigu;
import com.tips.qbatch5.oracle.adw.service.jpa.AddressSiGuJpaService;

@RestController
@EnableAutoConfiguration
public class AddressSiGuController {

	@Autowired
    AddressSiGuJpaService addressSiguJpaService;

    @RequestMapping(value = "/sigu", method = RequestMethod.GET)
    public Iterable<Korea_Address_Sigu> getAllSampleJpa() {
        return addressSiguJpaService.getAllAddressSiGu();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SampleController.class, args);
	}

}