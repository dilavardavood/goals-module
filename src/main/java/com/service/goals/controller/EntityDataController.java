package com.service.goals.controller;

import com.service.goals.service.EntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entity/node")
public class EntityDataController {

    @Autowired
    EntityDataService entityDataService;

}
