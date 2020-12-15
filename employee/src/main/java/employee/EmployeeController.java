package employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired private EmployeeService employeeService;
	
	@PostMapping("employee/create")
	public String saveEmployee(@RequestBody EmployeeMapper employeeMapper) {
		
		String id = employeeService.saveEmployee(employeeMapper);
		
		return id;
		
		
	}
	
	@GetMapping("fetch/all/employee")
	public List<EmployeeMapper> fetchAllEmployee() {
		
		List<EmployeeMapper> employeeList = employeeService.getAllEmployee();
		
		return employeeList;
		
		
	}
	
	@GetMapping("fetch/employee/{empId}")
	public EmployeeMapper fetchParticularEmployee(@PathVariable("empId") Long empId) {
		
		EmployeeMapper employeeMapper = employeeService.getAparticularEmployeeData(empId);
		
		return employeeMapper;
		
		
	}
	
	@PutMapping("update/employee")
	public EmployeeMapper updateEmployee(@RequestBody EmployeeMapper employeeMapper) {
		
		long id = employeeService.updateEmployee(employeeMapper);
		
		EmployeeMapper employeeMapperNew = employeeService.getAparticularEmployeeData(id);
		
		
		return employeeMapperNew;
		
		
	}
	
	@DeleteMapping("delete/{empId}")
	
	public boolean deleteEmployee(@PathVariable("empId") Long empId) {
		
		boolean b = employeeService.deleteEmployee(empId);
		
		return b;
		
		
	}
	

}
