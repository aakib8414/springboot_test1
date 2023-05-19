//package com.test.test2.adapter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.test.test2.dto.DepartmentDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.client.ExpectedCount;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
////@ExtendWith(MockitoExtension.class)
//@RestClientTest(UserAdapter.class)
//class UserAdapterTest {
//
//    @Autowired
//    private ObjectMapper mapper;
//    @Mock
//    private RestTemplate restTemplate;
//    @Autowired
//    private MockRestServiceServer mockRestServiceServer;
//    DepartmentDto departmentDto;
//    @Autowired
//    private UserAdapter adapter;
//
//    @BeforeEach
//    void setUp() {
//
//        departmentDto = DepartmentDto.builder().id(Long.valueOf(1))
//                .departmentCode("TS002")
//                .departmentName("CSE1")
//                .departmentAddress("MANUU HYDERABAD")
//                .build();
//        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void testGetDeptDto() throws JsonProcessingException {
//        String json = this.mapper.writeValueAsString(departmentDto);
//        this.mockRestServiceServer
//                .expect(requestTo("http://localhost:8080/api/departments/department/1"))
//                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
//        DepartmentDto dto = adapter.getDeptDto("1");
//        assertNotNull(dto);
//    }
//
//    @Test
//    void testGetDeptDto1() throws JsonProcessingException, URISyntaxException {
//        String json = this.mapper.writeValueAsString(departmentDto);
//        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/api/departments/department/1")))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(json)
//                );
//        final DepartmentDto deptDto = adapter.getDeptDto("1");
//        assertEquals(departmentDto.getDepartmentName(), deptDto.getDepartmentName());
////        Mockito.eq()
//    }
//
//    @Test
//    void testGetDeptDto2() throws  URISyntaxException {
//
//        Mockito
//                .when(restTemplate.getForEntity(
//                        "http://localhost:8080/api/departments/department/1", DepartmentDto.class))
//                .thenReturn(new ResponseEntity(departmentDto, HttpStatus.OK));
//
//        DepartmentDto dto = adapter.getDeptDto("1");
//        Assertions.assertEquals(departmentDto.getDepartmentCode(), dto.getDepartmentCode());
//    }
//
//}