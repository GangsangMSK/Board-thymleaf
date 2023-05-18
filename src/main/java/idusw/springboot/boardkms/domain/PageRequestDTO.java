package idusw.springboot.boardkms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
public class PageRequestDTO {
    private int page; // 요청하는 페이지
    private int size; // 페이지당 데이터 개수
    private int perPage; // 한 페이지당 보여줄 페이지 개수

    private String type; // 검색 조건
    private String keyword; // 검색 키워드

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
        this.perPage = 5;
    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(this.page - 1, this.size,sort);
    }
}
