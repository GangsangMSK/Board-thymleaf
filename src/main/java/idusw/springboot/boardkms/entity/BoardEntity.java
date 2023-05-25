package idusw.springboot.boardkms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "board_a201912014")
@ToString(exclude = "writer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_a201912014_seq_gen")
    @SequenceGenerator(sequenceName = "board_a201912014_seq", name = "board_a201912014_seq_gen", allocationSize = 1, initialValue = 1)
    private Long bno;

    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String content;
    @ManyToOne
    private MemberEntity writer;
}
