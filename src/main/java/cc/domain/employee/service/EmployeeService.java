package cc.domain.employee.service;

import cc.dto.ApiResponse;
import cc.domain.employee.entity.Employee;

public interface EmployeeService {

    ApiResponse<?> insertEmployee(Employee employee);
}
