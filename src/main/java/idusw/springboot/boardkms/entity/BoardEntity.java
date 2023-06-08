package idusw.springboot.boardkms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "a201912014_board")
@ToString(exclude = "writer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a201912014_board_seq_gen")
    @SequenceGenerator(sequenceName = "a201912014_board_seq", name = "a201912014_board_seq_gen", allocationSize = 1, initialValue = 1)
    private Long bno;

    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity writer;
}
