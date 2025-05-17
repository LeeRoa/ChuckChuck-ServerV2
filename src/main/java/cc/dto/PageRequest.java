package cc.dto;

import lombok.Data;

/**
 * 페이징 처리를 위해 클라이언트로 부터 전달받기 위한 DTO
 */
@Data
public class PageRequest {
    private int currentPage = 1;
    private int viewCount = 10;

    public int getOffset() {
        return (currentPage - 1) * viewCount;
    }

    public int getLimit() {
        return viewCount;
    }
}
