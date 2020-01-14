package com.tips.restapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tips.restapi.model.CarModel;
import com.tips.restapi.repository.CarRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/list")
    public ResponseEntity<List<CarModel>> list(Model model) {
    	List<CarModel> carModel = carRepository.findAll();
    	
        //model.addAttribute("carList", carRepository.findAll());
        //return "home";
    	
    	System.out.println(carModel);
    	
    	return ResponseEntity.ok(carModel);
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String addCar(@ModelAttribute CarModel car) {
        carRepository.save(car);
        return "redirect:home";
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam String search) {
        return "home";
    }

}