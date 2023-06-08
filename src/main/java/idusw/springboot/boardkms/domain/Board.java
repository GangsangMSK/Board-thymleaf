package idusw.springboot.boardkms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    //board
    private Long bno;
    private String title;
    private String content;

    //join
    private Long writerSeq;
    private String writerName;
    private String writerEmail;

    private Long replyCount;

    //auditing
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
