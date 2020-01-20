package com.tips.backend1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tips.backend1.common.RestResponseEntity;
import com.tips.backend1.model.entity.FineDust;
import com.tips.backend1.model.json.FineDustJson;
import com.tips.backend1.model.json.FineDustStageJson;
import com.tips.backend1.repository.FineDustRepository;
import com.tips.backend1.service.FineDustService;
import com.tips.backend1.service.FineDustServicePub;

@RestController
@RequestMapping("/")
public class WeatherController {
 
//    @Autowired
//    FineDustRepository fineDustRepository;
    
//    @RequestMapping("/finedustlist")
//    public ResponseEntity<List<FineDust>> list(Model model) {
//    	List<FineDust> carModel = fineDustRepository.findAll();
//    	
//        //model.addAttribute("carList", carRepository.findAll());
//        //return "home";
//    	
//    	System.out.println(carModel);
//    	
//    	return ResponseEntity.ok(carModel);
//    }

//    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
//    public String addCar(@ModelAttribute FineDust findDust) {
//        fineDustRepository.save(findDust);
//        return "redirect:home";
//    }
//
//    @RequestMapping(value = "/search")
//    public String search(@RequestParam String search) {
//        return "home";
//    }

    @Autowired
    private FineDustService    fineDustService;

    @Autowired
    private FineDustServicePub fineDustServicePub;
    
    @GetMapping("/finedustlist")
    public RestResponseEntity<FineDustJson> findFineDustList()
    {
        RestResponseEntity<FineDustJson> result = null;
        
        result = new RestResponseEntity<FineDustJson>(this.fineDustService.findFineDustJson());
        
        return result;
    }
    
    @GetMapping("/pubfinedustlist")
    public FineDustStageJson findFineDustStageJson()
    {
        FineDustStageJson result = null;
        
        result = this.fineDustServicePub.findFineDustStageJson();
        
        return result;
    }
}