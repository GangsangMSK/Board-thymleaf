package iducs.springboot.boardthymeleaf.domain;

import lombok.*; // 어노테이션 기반 상용코드(boilerplate)를 줄여주는 라이브러리

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@EqualsAndHashCode
public class Memo {
    private Long mNo;
    private String memoText;
}
