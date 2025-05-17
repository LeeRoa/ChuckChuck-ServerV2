package cc.util;


import java.util.ArrayList;
import java.util.List;

/**
 * 페이지 버튼 리스트 계산 유틸리티
 */
public class PaginationUtils {

    /**
     * 한 페이지에 표시할 페이지 버튼의 개수
     */
    private static final int PAGE_BLOCK_SIZE = 5;

    public static List<Integer> makePageList(int currentPage, int totalPage) {
        int blockStart = ((currentPage - 1) / PAGE_BLOCK_SIZE) * PAGE_BLOCK_SIZE + 1;
        int blockEnd = Math.min(blockStart + PAGE_BLOCK_SIZE - 1, totalPage);

        List<Integer> list = new ArrayList<>();
        for (int i = blockStart; i <= blockEnd; i++) {
            list.add(i);
        }
        return list;
    }
}