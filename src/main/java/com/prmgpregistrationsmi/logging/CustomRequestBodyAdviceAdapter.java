package com.prmgpregistrationsmi.logging;

import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@ControllerAdvice
@AllArgsConstructor
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
    private final HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {

        if(httpServletRequest.getRequestURI().contains("registration")) {
            logger.info("Incoming request on URI: " + httpServletRequest.getRequestURI(), body);
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}