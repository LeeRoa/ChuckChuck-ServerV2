package cc.exception;

import cc.resultcode.ResultCodeType;
import lombok.Getter;

/**
 * 비즈니스 로직 오류 시 정의할 수 있는 커스텀 예외 클래스
 */
@Getter
public class CustomRuntimeException extends RuntimeException {
    private final ResultCodeType resultCode;

    public CustomRuntimeException(ResultCodeType resultCode) {
        super(resultCode.resultMsg());
        this.resultCode = resultCode;
    }

}