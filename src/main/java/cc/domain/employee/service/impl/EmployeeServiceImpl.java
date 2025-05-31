package cc.domain.employee.service.impl;

import cc.dto.ApiResponse;
import cc.domain.employee.entity.Employee;
import cc.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public ApiResponse<?> insertEmployee(Employee employee) {
        return null;
    }
}
