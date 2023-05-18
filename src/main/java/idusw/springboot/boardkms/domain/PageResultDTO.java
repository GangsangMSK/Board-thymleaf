package idusw.springboot.boardkms.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList;

    private int totalPage; // 총 페이지 번호
    private int curPage; // 현재 페이지 번호
    private int size; // 페이지당 데이터 개수
    private int perPage; // 한 페이지당 보여줄 페이지 개수

    private long totalRows; // 총 데이터 개수
    private int startRow;
    private int endRow;

    private int start, end; // 시작 페이지 번호, 끝 페이지 번호
    private boolean prev, next; // 이전, 다음

    private List<Integer> pageList; // 페이지 번호 목록

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn, int perPage) {
        totalRows = result.getTotalElements();
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        this.perPage = perPage;
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {

        this.curPage = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(curPage / (double)perPage)) * perPage;

        this.start = tempEnd - (perPage - 1);
        this.end = (totalPage > tempEnd) ? tempEnd : totalPage;

        this.startRow = start + (curPage - 1) * size;
        this.endRow = startRow + size - 1;

        this.prev = start > 1;
        this.next = totalPage > tempEnd;

        this.pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        for(Integer i : pageList){
            System.out.println(i.intValue());
        }

    }
}
