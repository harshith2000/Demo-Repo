package com.spring.crud.controller;

import com.spring.crud.model.Employee;
import com.spring.crud.model.Instance;
import com.spring.crud.model.InstanceUsage;
import com.spring.crud.repository.EmployeeRepository;
import com.spring.crud.repository.InstanceRepository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.*;

import com.spring.crud.repository.InstanceUsageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/v1/")
public class InstanceController {

    @Autowired
    private InstanceRepository instanceRepository;
    @Autowired
    private InstanceUsageRepository instanceUsageRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getInstances")
    public List<Instance> getAllInstances(){
        return instanceRepository.findAll();
    }

    @GetMapping("/getInstanceUsages")
    public List<InstanceUsage> getAllInstanceUsages(){
        return instanceUsageRepository.findAllInAscending();
    }

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/getInstanceById/{id}")
    public Instance getInstanceById(@PathVariable int id){
        return instanceRepository.findById(id).orElse(null);
    }

    @GetMapping("/getInstanceUsageById/{id}")
    public List<InstanceUsage> getInstanceUsageById(@PathVariable int id){
        return instanceUsageRepository.findAllByInstanceId(id);
    }
    @GetMapping("/getInstanceByName/{name}")
    public List<Instance> getInstanceByName(@PathVariable String name){
        return instanceRepository.findByName(name);
    }

    @GetMapping("/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping("/getPage")
    public Page<InstanceUsage> getPage(Pageable pageable){
        return instanceUsageRepository.findAll(pageable);
    }

    @GetMapping("/getInstanceUsageByLimit/{id}/{limit}")
    public List<InstanceUsage> getInstanceUsageByLimit(@PathVariable int id, @PathVariable int limit){
        return instanceUsageRepository.findAllByInstanceIdLimit(id,limit);
    }

    @PostMapping("/createInstance")
    public Instance createInstance(@RequestBody Instance instance){
        return instanceRepository.saveAndFlush(instance);
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.saveAndFlush(employee);
    }

    @DeleteMapping("/deleteInstance/{id}")
    public String deleteInstance(@PathVariable int id){
        instanceRepository.deleteById(id);
        return "Deleted instance" + id;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeRepository.deleteById(id);
        return "Deleted employee" + id;
    }

    @PutMapping("/updateInstanceState/{id}")
    public Instance updateInstance(@PathVariable int id){
        Instance existingInstance = instanceRepository.findById(id).orElse(null);
        Instance tempInstance = existingInstance;
        tempInstance.setInstance_state(!existingInstance.isInstance_state());
        BeanUtils.copyProperties(tempInstance,existingInstance);
        if(existingInstance.isInstance_state()){
            if(existingInstance!=null){
                InstanceUsage inst = new InstanceUsage(existingInstance.getInstance_id(), 1,"Sai","In", LocalDateTime.now());
                instanceUsageRepository.save(inst);
            }
        }
        else{
            if(existingInstance!=null){
                InstanceUsage inst = new InstanceUsage(existingInstance.getInstance_id(), 1,"Sai","Out", LocalDateTime.now());
                instanceUsageRepository.save(inst);
            }
        }
        return instanceRepository.saveAndFlush(existingInstance);

    }

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(employee,existingEmployee);
        return employeeRepository.saveAndFlush(existingEmployee);
    }



}
