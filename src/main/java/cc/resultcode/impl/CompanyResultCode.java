package cc.resultcode.impl;

import cc.resultcode.ResultCodeType;
import lombok.Getter;

/**
 * CompanyResultCode 1400 ~ 1499 정의 (회사, 부서, 팀장, 직급 등 회사와 관련된 코드만 정의)
 */
@Getter
public enum CompanyResultCode implements ResultCodeType {
    /**
     * HTTP 상태 코드 정의
     */
    SUCCESS("200", "요청이 성공적으로 처리되었습니다."),
    BAD_REQUEST("400", "잘못된 요청입니다."),
    UNAUTHORIZED("401", "인증이 필요합니다."),
    FORBIDDEN("403", "접근 권한이 없습니다."),
    NOT_FOUND("404", "요청한 리소스를 찾을 수 없습니다."),
    CONFLICT("409", "이미 등록된 정보입니다."),
    INTERNAL_ERROR("500", "서버 내부 오류입니다."),

    /**
     * API 통신 결과 코드 정의
     */
    ERROR("9999", "예기치 못한 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");

    private final String resultCode;
    private final String resultMsg;

    CompanyResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String resultCode() {
        return resultCode;
    }

    @Override
    public String resultMsg() {
        return resultMsg;
    }
}
