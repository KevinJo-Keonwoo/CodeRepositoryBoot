package com.example.demo.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString
public class DTO {
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    //empty도 불가능하고 null도 불가능하다
    //통과하지 못했을때(불가능)의 메세지 
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{4,10}$\n", message = "비밀번호는 4~10자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    
    @Max(value = 10)
    @Min(value = 1)
    //javax.validation에서 제공되는 어노테이션 
    //1~10이 유효하고 나머지는 유효하지 않은 값이다 
    private int amount;
   
}
