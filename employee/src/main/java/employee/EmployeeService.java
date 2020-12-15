package employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired EmployeeDao employeeDao;
	
	public String saveEmployee(EmployeeMapper employeeMapper) {
		
		
		if(null !=employeeMapper) {
			
			EmployeeModel employeeModel = new EmployeeModel();
			
			employeeModel.setFirst_name(employeeMapper.getFirstName());
			employeeModel.setLast_name(employeeMapper.getLastName());
			employeeModel.setEmail_id(employeeMapper.getEmailId());
			employeeModel.setAge(employeeMapper.getAge());
			employeeModel.setGender(employeeMapper.getGender());
			employeeModel.setAddress(employeeMapper.getAddress());
			
			
			employeeDao.save(employeeModel);
		}
		
		
		
		
		return "employee crreated";
		
		
		
	}

	public List<EmployeeMapper> getAllEmployee() {

		
		List<EmployeeModel> employeeList = employeeDao.findAll();
		
		List<EmployeeMapper> employeeMapperList = new ArrayList<>();
		
		if(null !=employeeList && !employeeList.isEmpty()) {
			
			for(EmployeeModel employeeModel : employeeList) {
				
				EmployeeMapper employeeMapper = new EmployeeMapper();

				
				employeeMapper.setFirstName(employeeModel.getFirst_name());
				employeeMapper.setLastName(employeeModel.getLast_name());
				employeeMapper.setAge(employeeModel.getAge());
				employeeMapper.setGender(employeeModel.getGender());
				employeeMapper.setAddress(employeeModel.getAddress());
				employeeMapper.setEmailId(employeeModel.getEmail_id());
				employeeMapperList.add(employeeMapper);
			}
		}
		
		
		System.out.println(employeeList);
		
		return employeeMapperList;
	}

	public EmployeeMapper getAparticularEmployeeData(Long empId) {

		Optional<EmployeeModel> employeeLists = employeeDao.findById(empId);
		
		EmployeeMapper employeeMapper = new EmployeeMapper();

		
		if(null !=employeeLists) {
			
			EmployeeModel empDetails = employeeLists.get();
			
			employeeMapper.setFirstName(empDetails.getFirst_name());
			employeeMapper.setLastName(empDetails.getLast_name());
			employeeMapper.setEmailId(empDetails.getEmail_id());
			employeeMapper.setAddress(empDetails.getAddress());
			employeeMapper.setGender(empDetails.getGender());
			employeeMapper.setAge(empDetails.getAge());
			employeeMapper.setEmpId(empId);
			
		}
		return employeeMapper;
		
		
	}

	public long updateEmployee(EmployeeMapper employeeMapper) {

		Optional<EmployeeModel> employee = employeeDao.findById(employeeMapper.getEmpId());
        
        if(null!=employeeMapper) 
        {
        	EmployeeModel newEntity = employee.get();
            newEntity.setEmail_id(employeeMapper.getEmailId());
            newEntity.setFirst_name(employeeMapper.getFirstName());
            newEntity.setLast_name(employeeMapper.getLastName());
            newEntity.setAge(employeeMapper.getAge());
            newEntity.setAddress(employeeMapper.getAddress());
            newEntity.setGender(employeeMapper.getGender());
 
            newEntity = employeeDao.save(newEntity);
             
        }
	
        
		  return employeeMapper.getEmpId();
		  
	}

	public boolean deleteEmployee(long empId) {

		Optional<EmployeeModel> employee = employeeDao.findById(empId);
         
	        if(null !=employee) 
	        {
	        	employeeDao.deleteById(empId);
	        	
	        	return true;
	        } else {
	            
	        	return false;
	        }
		
	}
}
