package cc.controller.employee;

import cc.dto.ApiResponse;
import cc.domain.employee.entity.Employee;
import cc.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 사원을 추가하는 API
     * {
     *   "empName": "홍길동",
     *   "empBirth": "2024-12-17",
     *   "empEmail": "test@gmail.com",
     *   "empPw": "12qweasdzxc"
     * }
     * @return 처리 결과 값
     */
    @PostMapping
    public ApiResponse<?> insertEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    /**
     * 사원을 조회하는 API
     * {
     *   "empId": "사번",
     *   "empEmail": "test@gmail.com",
     *   "empPhonenum": "010-1234-1234",
     *   "empAccountStatus": "Y",
     *   "page": {
     *     "currentPage": "2",
     *     "viewCount": "10"
     *   }
     * }
     * @return 조회된 사원 List
     */
    @GetMapping
    public ApiResponse<?> findEmployee() {
        return null;
    }
}
