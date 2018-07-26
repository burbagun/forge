package forge.sbcrudrestful.repository;
  

import org.springframework.data.repository.CrudRepository;

import forge.sbcrudrestful.entity.Employee;

	public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  
	}