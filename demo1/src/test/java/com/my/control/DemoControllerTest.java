package com.my.control;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//@WebMvcTest(DemoController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DemoControllerTest {
	@Autowired
	private MockMvc mockMvc; // 모의 객체 : "흉내"내는 "가짜" 모듈
//	@Test
//	void testGreeting() throws Exception {
//		MockHttpServletRequestBuilder  mockRequestBuilder = MockMvcRequestBuilders.get("/greeting")
//				                            .accept(org.springframework.http.MediaType.APPLICATION_JSON);
//		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
//		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//		resultActions.andExpect(MockMvcResultMatchers.content().string("welcome"));
//	}
	
	@Test
	void testUseRepository() throws Exception {
//		MockHttpServletRequestBuilder  mockRequestBuilder1 = MockMvcRequestBuilders.get("/userepository"); 
//		MockHttpServletRequestBuilder  mockRequestBuilder = mockRequestBuilder1.accept(org.springframework.http.MediaType.APPLICATION_JSON); 
		MockHttpServletRequestBuilder  mockRequestBuilder = MockMvcRequestBuilders.get("/userepository") //요청방식(url)  위의 두줄을 줄인거임 
				.accept(org.springframework.http.MediaType.APPLICATION_JSON); //method chaining
		int expectedCount = 7;
		
		//모의객체를 이용해서 요청/응답    응답결과 : ResultAction
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder); 
		
		//결과가 이런식으로 예상된다 
		resultActions.andExpect(MockMvcResultMatchers.status().isOk()); //정상예상(상태코드 200)
		
		//응답결과는 {"totalCnt" : 7}이다
		//jsonPath를 이용해서 totalCnt 프로퍼티값이 7과같은거 예상 
		org.hamcrest.Matcher<Integer> matcher;
		ResultMatcher resultMatcher;
		//총카운트가 7번인가를 예상함 
		matcher = org.hamcrest.CoreMatchers.is(expectedCount);
		resultMatcher = jsonPath("totalCnt", matcher);
		resultActions.andExpect(resultMatcher);
	}
}
