package cc.handler;

import cc.dto.ApiResponse;
import cc.exception.CustomCheckedException;
import cc.exception.CustomRuntimeException;
import cc.resultcode.ResultCodeType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 커스텀 예외 처리 (비즈니스 로직 오류 시 발생하는 예외 처리)
     * 정의한 예외를 catch 하여 설정한 ResultCode 를 이용하여 Json 포맷을 만들어 Response 로 반환한다.
     * {
     *      "resultCode":"결과 코드",
     *      "resultMsg":"결과 메시지",
     *      "data": null
     * }
     */
    @ExceptionHandler({CustomRuntimeException.class, CustomCheckedException.class})
    public ResponseEntity<ApiResponse<?>> handleCustomExceptions(Exception e) {
        ResultCodeType resultCode = (e instanceof CustomRuntimeException)
                ? ((CustomRuntimeException) e).getResultCode()
                : ((CustomCheckedException) e).getResultCode();

        return ResponseEntity.ok(ApiResponse.fail(resultCode));
    }
}
