package com.hosp.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);


    /*@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }*/

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseMap.put("error", "Unauthorized");
        responseMap.put("message", authException.getMessage());
        responseMap.put("path", request.getServletPath());

        OutputStream out = response.getOutputStream();

        final ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(response.getOutputStream(), body);
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, responseMap);
    }

}
