package com.example.mongorest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mongorest.model.CarModel;
import com.example.mongorest.repository.CarRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/list")
    public List<CarModel> list(Model model) {
    	return carRepository.findAll();
    	
        //model.addAttribute("carList", carRepository.findAll());
        //return "home";
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