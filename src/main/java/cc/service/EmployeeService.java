package cc.service;

import cc.dto.ApiResponse;
import cc.entity.Employee;

public interface EmployeeService {

    ApiResponse<?> insertEmployee(Employee employee);
}
