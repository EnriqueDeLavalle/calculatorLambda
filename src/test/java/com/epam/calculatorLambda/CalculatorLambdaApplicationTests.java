package com.epam.calculatorLambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.epam.calculatorLambda.services.CalculatorService;

@SpringBootTest
public class CalculatorLambdaApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	CalculatorService calculator;

	@Test
	public void whenCalculationFromFile_thenVerifyBadOperation() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "contentMock.txt", MediaType.TEXT_PLAIN_VALUE,
				"2;2\n5;5".getBytes());

		String invalidOperationMsg = "Invalid operation!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("operation", "odd");
		requestParams.add("resultFormat", "SCREEN");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc.perform(multipart("/calculator").file(file).params(requestParams))
				.andExpect(status().isBadRequest()).andReturn();
		assertEquals(invalidOperationMsg, result.getResponse().getContentAsString());

	}

	@Test
	public void whenCalculationFromFile_thenVerifyBadResultFormat() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "contentMock.txt", MediaType.TEXT_PLAIN_VALUE,
				"2;2\n5;5".getBytes());
		String invalidResultFormatMsg = "Invalid result format!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "SCREN");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc.perform(multipart("/calculator").file(file).params(requestParams))
				.andExpect(status().isBadRequest()).andReturn();
		assertEquals(invalidResultFormatMsg, result.getResponse().getContentAsString());

	}

	@Test
	public void whenCalculationFromFile_thenSucceed() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "contentMock.txt", MediaType.TEXT_PLAIN_VALUE,
				"2;2\n5;5".getBytes());
		String resultMsg = "4.0\n10.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "SCREEN");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc.perform(multipart("/calculator").file(file).params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}

	@Test
	public void whenSubFromFile_thenSucceed() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "contentMock.txt", MediaType.TEXT_PLAIN_VALUE,
				"2;2\n5;5".getBytes());
		String resultMsg = "4.0\n10.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "SCREEN");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc.perform(multipart("/calculator").file(file).params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}

	@Test
	public void whenCalculationFromFile_thenSucce() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "contentMock.txt", MediaType.TEXT_PLAIN_VALUE,
				"2;2\n5;5".getBytes());
		String resultMsg = "The result was written to the file processed!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "FILE");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc.perform(multipart("/calculator").file(file).params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}

	@Test
	public void whenAdd_thenSucceed() throws Exception {
		
		String resultMsg = "3.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "1.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "SCREEN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenSub_thenSucceed() throws Exception {
		
		String resultMsg = "10.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "12.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "sub");
		requestParams.add("resultFormat", "SCREEN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenMult_thenSucceed() throws Exception {
		
		String resultMsg = "24.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "12.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "mult");
		requestParams.add("resultFormat", "SCREEN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenDiv_thenSucceed() throws Exception {
		
		String resultMsg = "6.0";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "12.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "div");
		requestParams.add("resultFormat", "SCREEN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenAdd_thenInvalidOperation() throws Exception {
		
		String resultMsg = "Invalid operation!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "1.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "Odd");
		requestParams.add("resultFormat", "SCREEN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isBadRequest()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenAdd_thenInvalidResultFormat() throws Exception {
		
		String resultMsg = "Invalid result format!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "1.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "SCReN");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isBadRequest()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}
	
	@Test
	public void whenAdd_thenSucceedToFile() throws Exception {
		
		String resultMsg = "The result was written to the file processed!";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("number1", "1.0");
		requestParams.add("number2", "2.0");
		requestParams.add("operation", "add");
		requestParams.add("resultFormat", "FILE");
	
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MvcResult result = mockMvc
				.perform(get("/calculator").params(requestParams))
				.andExpect(status().isOk()).andReturn();
		assertEquals(resultMsg, result.getResponse().getContentAsString());

	}


}