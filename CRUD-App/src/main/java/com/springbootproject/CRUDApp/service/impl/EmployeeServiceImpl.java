package com.springbootproject.CRUDApp.service.impl;

import com.springbootproject.CRUDApp.exception.ResourceNotFoundException;
import com.springbootproject.CRUDApp.model.Employee;
import com.springbootproject.CRUDApp.repository.EmployeeRepository;
import com.springbootproject.CRUDApp.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new ResourceNotFoundException("Employee","ID",id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        Employee existingEmployee=employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);


        return existingEmployee;
    }

    @Override
    public void deleteEmployeeById(long id) {

        employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));

        employeeRepository.deleteById(id);
    }


}
