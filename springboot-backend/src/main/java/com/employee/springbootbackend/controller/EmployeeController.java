package com.employee.springbootbackend.controller;


import com.employee.springbootbackend.exception.ResourceNotFoundException;
import com.employee.springbootbackend.model.Employee;
import com.employee.springbootbackend.repository.EmployeeRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/apis/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee;
        employee = employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Employee not exist with id"+id));

        return new ResponseEntity(employee,HttpStatus.NOT_FOUND);
    }

    // build update employee by id REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id , @RequestBody Employee employeeDetails) {
        Employee updateEmployee;
        updateEmployee = employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Employee not exist with id"+ id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee;
        employee = employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Employee not exist with id"+id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}