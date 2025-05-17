package cc.dto;

import cc.resultcode.ResultCodeType;
import cc.resultcode.impl.EmployeeResultCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    /**
     * -- GETTER --
     *  ApiResponse Getter 정의 부분
     */
    private final String resultCode;
    private final String resultMsg;
    private final T data;

    public ApiResponse(ResultCodeType resultCode, T data) {
        this.resultCode = resultCode.resultCode();
        this.resultMsg = resultCode.resultMsg();
        this.data = data;
    }

    /**
     * Api 요청이 성공일 경우
     * @param data 성공처리 시 반환되는 데이터
     * @return Json 포맷에 맞는 응답 데이터
     * @param <T> 어떤 객체든지 들어올 수 있는 제네릭 타입
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(EmployeeResultCode.SUCCESS, data);
    }

    public static ApiResponse<?> fail(ResultCodeType resultCode) {
        return new ApiResponse<>(resultCode, null);
    }

}
