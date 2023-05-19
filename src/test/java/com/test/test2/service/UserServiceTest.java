//package com.test.test2.service;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EmptySource;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.NullSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.junit.platform.commons.util.StringUtils;
//
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceTest {
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void saveUser() {
//    }
//
//    @Test
//    void getUser() {
//    }
//
//    @Test
//    void getDepartmentDtoList() {
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    @NullSource
//    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
//    void test1(String str) {
//     assertTrue(!StringUtils.isBlank(str));
//    }
//
//    @ParameterizedTest
//    @MethodSource("ranges")
//    void test2(int arg) {
//      assertTrue(arg>0);
//    }
//
//    static IntStream ranges() {
//        return IntStream.range(1,11);
//    }
//}