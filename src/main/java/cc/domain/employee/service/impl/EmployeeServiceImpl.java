package cc.domain.employee.service.impl;

import cc.domain.code.entity.Code;
import cc.domain.employee.repository.EmployeeRepository;
import cc.dto.ApiResponse;
import cc.domain.employee.entity.Employee;
import cc.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResponse<Employee> insertEmployee(Employee employee) {
        System.out.println("employee: " + employee.toString());

        employee.setPasswordHash(passwordEncoder.encode(employee.getEmpPw()));

        return ApiResponse.success(employeeRepository.save(employee));
    }
}
