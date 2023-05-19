package com.test.test2.adapter;

import com.test.test2.dto.DepartmentDto;
import com.test.test2.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserAdapter {
    private final static String url = "http://localhost:8080/api/departments";
    private final RestTemplate restTemplate;

    public UserAdapter(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("").build();
    }

    public DepartmentDto getDeptDto(final String id) {
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.
                getForEntity(url + "/department/" + id,
                        DepartmentDto.class);
        return responseEntity.getBody();
    }

    public List<DepartmentDto> getDtoList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<List<DepartmentDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }


    public DepartmentDto saveDeptDto(final DepartmentDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(dto, headers);
        ResponseEntity<DepartmentDto> resp = restTemplate.exchange(url,
                HttpMethod.POST,
                entity,
                DepartmentDto.class);
        HttpStatusCode statusCode = resp.getStatusCode();
        log.info("statusCode:{}", statusCode);
        DepartmentDto departmentDto = Optional.ofNullable(resp.getBody())
                .orElseThrow(() -> {
                    throw new CustomException("Null Method is called");
                });
        log.info("Name:", departmentDto.getDepartmentName());
        return departmentDto;
    }
}
