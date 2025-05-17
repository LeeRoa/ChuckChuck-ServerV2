package cc.resultcode.impl;

import cc.resultcode.ResultCodeType;
import lombok.Getter;

/**
 * AdminResultCode 9000 ~ 9998 정의 (관리자 권한으로 이루어진 작업이나 근무 정책 설정과 관련된 코드만 정의)
 * 이 클래스 이외는 모두 관리자가 아닌 일반 사용자 (인사팀, CEO 등 관리자 권한이 없는 사원에게만 보여주는 ResultCode)
 */
@Getter
public enum AdminResultCode implements ResultCodeType {
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

    AdminResultCode(String resultCode, String resultMsg) {
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
