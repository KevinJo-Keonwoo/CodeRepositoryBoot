package com.example.demo.control;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DTO;

@RestController
public class AopController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@GetMapping("say1")
	public void say1() {
		logger.error("This is say1()");
	}
	@GetMapping("say2")
	public String say2(String msg) {
		logger.error("This is say2("+msg+")");
		return msg;
	}
	@GetMapping("except1")
	public String except1(
			@RequestParam(required=false,defaultValue="-1" )
			int num) {
		try {
			if(num < 0) {
				throw new Exception("음수입니다");
			}
			return "숫자:" + num;
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	@GetMapping("except2")
	public String except2(
			@RequestParam(required=false,defaultValue="싫다" )
			String str) {
		try {
			String []positive = {"좋다", "기쁘다", "고맙다", "잘한다"};
			if(!Arrays.asList(positive).contains(str)) { //포함되어있으면 true 아니면 false
				throw new Exception(str+"는 말을 들으면 힘이 빠져요");
				//Arrays.AsList -> 배열을 리스트로 변환하기 
				//for(String a : positve) {}와 똑같음 
				/* 아래도 같은 구문  
				 * for(String a : positive){
				 * 		if(!a.equals(str)){
				 * 			throw new Exception(str+"는 말을 들으면 힘이 빠져요");
				 * 		}
				 * }
				 *
				 */
			}
			return str+"는 말을 들으면 힘이 나요";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	@GetMapping("advice1")	
	public String advice1(
			@RequestParam(required=false,defaultValue="-1" )
			int num) throws Exception{
		if(num < 0) {
			throw new Exception("음수입니다");
		}
		return "숫자:" + num;
	}
	@GetMapping("advice2")
	public String advice2(
			@RequestParam(required=false,defaultValue="싫다" )
			String str) 
					throws Exception{		
		String []positive = {"좋다", "기쁘다", "고맙다", "잘한다"};
		if(!Arrays.asList(positive).contains(str)) {
			throw new Exception(str +"는 말을 들으면 힘이 빠져요");
		}
		return str+"는 말을 들으면 힘이 나요";		
	}
	
	@PostMapping("validate1")
	//@Validate 유효성검사실패인 경우 MethodArgumentNotValidException이 발생
	public String validate1(@RequestBody @Validated DTO dto, Errors errors) {
		//@RequestBody : JSON문자열로 응답 
        //@Validated validation check
		//DTO쪽에 NotEmpty, @Min, @Max 효과가 나게 설정해줘야함 
        if(errors.hasErrors()) { 
        	return errors.getAllErrors().get(0).getDefaultMessage();
        	//모든에러중 첫번째 에러만 가져오겠다. (테스트기에 간단하게)
        }else {
        	return "성공";
        }
	}
	@PostMapping("validate2")	
	public String validate2(@RequestBody @Validated DTO dto) {
		return "성공";
	}
}
