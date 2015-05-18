package com.example.controller;

import com.example.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by soumya on 5/18/2015.
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    TaskController(final ITaskService taskService){
        this.taskService = taskService;
    }

    @RequestMapping(value = "/distribute", method = RequestMethod.GET)
    public void distributeTasks() {
        taskService.distributeTasks();
    }

}
