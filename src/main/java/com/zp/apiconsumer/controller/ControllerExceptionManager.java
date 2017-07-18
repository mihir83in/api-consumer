package com.zp.apiconsumer.controller;

import com.codahale.metrics.annotation.Counted;
import com.zp.apiconsumer.constants.Constants;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Class which manages handle the controller exceptions.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionManager {

    @Value
    public class ExceptionResponse {

        private String errorMessage;
        private String stackTrace;


        public ExceptionResponse(final Throwable tw) {

            errorMessage = tw.getMessage();
            stackTrace = ExceptionUtils.getStackTrace(tw);
        }

    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @Counted(name = Constants.METRIC_NAME_ERRORS, monotonic = true, absolute = true)
    public String processOtherException(Throwable tw, Model model) {

        log.error("Internal server error", tw);
        model.addAttribute("error", new ExceptionResponse(tw));
        return "/error";
    }
}
