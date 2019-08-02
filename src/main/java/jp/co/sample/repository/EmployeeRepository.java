package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;

@Repository
@RequestMapping("/employee")
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
		
	};
	
	@RequestMapping("/findAll")
	public List<Employee> findAll(){
		
		String findAllSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count";
		List<Employee> employeeList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	@RequestMapping("/load")
		public Employee load(Integer id) {
			String loadSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
					+ "WHERE id=:id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);		
			Employee employee = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);
			return employee;
		}
	@RequestMapping("/update")
		public void update(Employee employee) {
			SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
			String updateSql = "UPDATE employee SET dependents_count=:dependmentsCount WHERE id = employee.getId()";
			template.update(updateSql, param);
		}
}


