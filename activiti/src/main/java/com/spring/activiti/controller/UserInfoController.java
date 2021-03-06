package com.spring.activiti.controller;

import com.spring.activiti.service.UserInfoService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/getUserInfo")
    public Object getUserInfo(){
        return userInfoService.getUserInfo();
    }

    @ApiOperation(value = "开始一个任务",notes = "根据业务编号开启一个任务")
    @GetMapping("/apply/{businessKey}")
    public Object apply(@PathVariable("businessKey") String businessKey){
        Map<String,Object> map = new HashMap<>();
        map.put("apply","张三");
        map.put("approve","李四");
        ExecutionEntity executionEntity = (ExecutionEntity )runtimeService.startProcessInstanceByKey("testprocess",businessKey,map);

        return executionEntity.getId();
    }
    @ApiOperation(value = "处理任务",notes = "根据业务编号处理任务")
    @GetMapping("/deal/{businessKey}")
    public Object deal(@PathVariable("businessKey") String businessKey){
        Map<String,Object> map = new HashMap<>();
        map.put("apply","张三");
        map.put("approve","李四");
        Task task = taskService.createTaskQuery().processDefinitionKey("testprocess").taskDefinitionKey("application").processInstanceBusinessKey(businessKey).singleResult();
        taskService.complete(task.getId(),map);
        return "success";
    }
    @ApiOperation(value = "审批任务",notes = "根据业务编号审批任务")
    @GetMapping("/passApprove/{businesskey}")
    public Object passApprove(@PathVariable("businesskey") String businesskey){
        Map<String,Object> map = new HashMap<>();
        map.put("apply","张三");
        map.put("approve","李四");
        map.put("pass",true);
        Task task = taskService.createTaskQuery().processDefinitionKey("testprocess").taskDefinitionKey("approve").processInstanceBusinessKey(businesskey).singleResult();
        taskService.complete(task.getId(),map);
        return "passApprove";
    }
}
