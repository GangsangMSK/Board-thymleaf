package idusw.springboot.boardkms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "a201912014_member")

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(sequenceName = "a201912014_member_seq", name = "a201912014_member_seq_gen", allocationSize = 1, initialValue = 1)
public class MemberEntity extends BaseEntity {
    //Entity : Service -> Repository -> Service 데이터 객체
    //Repository : Persistence Data 처리
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a201912014_member_seq_gen")
    //Oracle : GenerationType.SEQUENCE, MySQL : GenerationType.IDENTITY
    //Oracle : SEQUENCE를 사용, MySQL : AUTO_INCREMENT를 사용
    private Long seq;

    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String email;
    @Column(length = 20, nullable = false)
    private String pw;
}
