package idusw.springboot.boardkms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply_a201912014")
@ToString(exclude = "board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class ReplyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_a201912014_seq_gen")
    @SequenceGenerator(sequenceName = "reply_a201912014_seq", name = "reply_a201912014_seq_gen", allocationSize = 1, initialValue = 1)
    private Long rno;

    private String text;
    private String replier;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;
}
