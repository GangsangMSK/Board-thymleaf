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
    private int page;
    private int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(this.page - 1, this.size, sort);
    }
}
