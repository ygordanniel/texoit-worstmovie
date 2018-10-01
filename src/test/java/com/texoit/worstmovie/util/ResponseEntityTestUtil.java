package com.texoit.worstmovie.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ResponseEntityTestUtil {

    private ResponseEntityTestUtil() {}

    public static void statusOkBodyNotNull(ResponseEntity responseEntity) {
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertNotNull(responseEntity.getBody());
    }
}
