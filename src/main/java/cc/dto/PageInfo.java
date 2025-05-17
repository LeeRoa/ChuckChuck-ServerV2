package cc.dto;

import cc.util.PaginationUtils;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 클라이언트에게 요청 처리 결과를 포함한 페이징 처리 정보를 전달하기 위한 DTO
 * @param <T> 요청 처리 결과 DTO
 */
@Data
public class PageInfo<T> {
    private int currentPage;
    private int viewCount;
    private int totalCount;
    private int totalPage;

    private boolean isPrev;
    private boolean isNext;
    private List<Integer> pageList;

    /**
     * 조회 결과 데이터 List<DTO>
     */
    private List<T> content;

    @Builder
    public PageInfo(int currentPage, int viewCount, int totalCount, List<T> content) {
        this.currentPage = currentPage;
        this.viewCount = viewCount;
        this.totalCount = totalCount;
        this.content = content;

        this.totalPage = (int) Math.ceil((double) totalCount / viewCount);
        this.isPrev = currentPage > 1;
        this.isNext = currentPage < totalPage;
        this.pageList = PaginationUtils.makePageList(currentPage, totalPage);
    }
}
