package com.tips.restapi.controller;

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

import com.tips.restapi.common.RestResponseEntity;
import com.tips.restapi.model.json.TableOneJson;
import com.tips.restapi.repository.TableOneEntityRepository;
import com.tips.restapi.service.TableOneEntityService;
import com.tips.restapi.service.TableOneEntityService;

@RestController
@RequestMapping("/")
public class BackEnd2Controller
{
    @Autowired
    private TableOneEntityService tableOneEntityService;
    
    @GetMapping("/list")
    public RestResponseEntity<TableOneJson> findTableOneAll()
    {
        RestResponseEntity<TableOneJson> result = null;
        
        result = new RestResponseEntity<TableOneJson>(this.tableOneEntityService.findTableOneEntity());
        
        return result;
    }
}