package com.springbootproject.CRUDApp.repository;

import com.springbootproject.CRUDApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
