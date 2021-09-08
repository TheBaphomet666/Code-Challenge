package com.example.codechallenge.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler that will map the raised exception into rest responses
 *
 * @author <a href="mailto:nilson.marmolejo@payu.com">Nilson Marmolejo</a>
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiRestErrorHandler {


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {

        log.error("An error occurred: [{}]", buildRequestString(request), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "";
        if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(message, status);
    }

    private String buildRequestString(HttpServletRequest request) {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(request);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(request.getMethod()).append("] ");
        stringBuilder.append("path=[").append(request.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(request)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        return stringBuilder.toString();
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {

        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

}
