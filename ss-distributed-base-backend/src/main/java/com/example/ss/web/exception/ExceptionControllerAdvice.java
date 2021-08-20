package com.example.ss.web.exception;

import com.example.ss.core.Constants;
import com.example.ss.data.ApiData;
import com.example.ss.data.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 全局异常控制器建议
 *
 * @author Aaric, created on 2021-08-20T15:12.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 自定义异常
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public ApiData<String> handleApiException(ApiException e) {
        log.error("handleApiException", e);
        return new ApiData<String>()
                .setCode(e.getCode())
                .setErrorMessage(e.getMessage());
    }

    /**
     * 数据校验异常-400
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> tips = new HashMap<>(e.getBindingResult().getAllErrors().size());
        bindingResult.getFieldErrors().forEach(error -> {
            tips.put(error.getField(), error.getDefaultMessage());
        });
        return new ApiData<>()
                .setCode(Constants.ApiCode.ERROR_400)
                .setErrorMessage("valid error")
                .setData(tips);
    }

    /**
     * 数据校验（参数）异常-400
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiData<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("handleMissingServletRequestParameterException", e);
        Map<String, String> tips = new HashMap<>(1);
        tips.put(e.getParameterName(), e.getMessage());
        return new ApiData<>()
                .setCode(Constants.ApiCode.ERROR_400)
                .setErrorMessage("valid params error")
                .setData(tips);
    }

    /**
     * 其他异常-500
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiData<String> handleDefaultException(Exception e) {
        log.error("handleDefaultException", e);
        return new ApiData<String>()
                .setCode(Constants.ApiCode.ERROR_500)
                .setErrorMessage(e.getMessage());
    }

    /**
     * 无法解决异常-503
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiData<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("handleConstraintViolationException", e);
        Map<String, String> tips = new HashMap<>(e.getConstraintViolations().size());
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        errors.forEach(error -> {
            tips.put(error.getPropertyPath().toString(), error.getMessage());
        });
        return new ApiData<>()
                .setCode(Constants.ApiCode.ERROR_503)
                .setErrorMessage("server error")
                .setData(tips);
    }
}
