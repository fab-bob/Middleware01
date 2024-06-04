package com.example.Middleware01;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LegacyInterceptorTest {

    @Autowired
    LegacyInterceptor legacyInterceptor;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp () {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
    @Test
    void testGoneCase () throws Exception {
        when(request.getRequestURI()).thenReturn("/legacy");
        boolean result = legacyInterceptor.preHandle(request, response, null);
        assertEquals(false, result);
    }

    @Test
    void testTime () throws Exception {
        when(request.getRequestURI()).thenReturn("/time");
        boolean result = legacyInterceptor.preHandle(request, response, null);
        assertEquals(true, result);
    }

    @Test
    void testGabriel () throws  Exception {
        when(request.getRequestURI()).thenReturn("/Gabriel");
        boolean result = legacyInterceptor.preHandle(request, response, null);
        assertEquals(true, result);
    }
    @Test
    void verifyTest () throws  Exception {
        when(request.getRequestURI()).thenReturn("/legacy");
        legacyInterceptor.preHandle(request, response, null);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_GONE);
    }
    @Test
    void testTimeNoInteractions () throws  Exception {
        when(request.getRequestURI()).thenReturn("/time");
        legacyInterceptor.preHandle(request, response, null);
        verifyNoInteractions(response);
    }
}
