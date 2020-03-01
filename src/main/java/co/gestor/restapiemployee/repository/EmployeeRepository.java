package co.gestor.restapiemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.gestor.restapiemployee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}