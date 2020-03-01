package co.gestor.restapiemployee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gestor.restapiemployee.entity.Employee;
import co.gestor.restapiemployee.exception.EmployeeNotFoundException;
import co.gestor.restapiemployee.repository.EmployeeRepository;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/employees")
    public List<Employee> all() {
        return this.repository.findAll();
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> one(@PathVariable Long id) {
        Employee employee = this.repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee updatedEmployee = this.repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return this.repository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return this.repository.save(newEmployee);
        });
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}