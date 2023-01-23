package com.employee.springbootbackend.repository;

import com.employee.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //All CRUD database methods
}

