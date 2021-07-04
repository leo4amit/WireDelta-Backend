package com.wiredelta.backend.exceptionhandler;

import com.wiredelta.backend.errorCodes.WireDeltaError;
import com.wiredelta.backend.exception.WireDeltaException;
import com.wiredelta.backend.models.error.Error;
import com.wiredelta.backend.models.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class WireDeltaExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({WireDeltaException.class, Exception.class})
    protected ResponseEntity<Object> handleServiceException(Exception ex, WebRequest webRequest) {

        BaseResponse BaseResponse = new BaseResponse();
        Error Error = new Error();
        if (ex instanceof WireDeltaException) {
            WireDeltaException inclusiveException = (WireDeltaException) ex;
            logger.error("Exception caught : " + inclusiveException.getErrorCode() + " error Message : " + inclusiveException.getErrorMessage(), ex);
            Error.setErrorCode(inclusiveException.getErrorCode());
            Error.setErrorMessage(inclusiveException.getErrorMessage());
        } else {
            logger.error("exception caught ", ex);
            Error.setErrorCode(WireDeltaError.WD_ERR_500.name());
            Error.setErrorMessage(WireDeltaError.WD_ERR_500.getErrorMessage());
        }

        BaseResponse.setError(Error);

        return handleExceptionInternal(ex, BaseResponse, new HttpHeaders(), HttpStatus.OK, webRequest);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorBuilder.append(error.getDefaultMessage());
            break;
        }
        BaseResponse BaseResponse = new BaseResponse();
        Error Error = new Error();
        Error.setErrorCode(WireDeltaError.WD_ERR_1006.name());
        Error.setErrorMessage(errorBuilder.toString());

        BaseResponse.setError(Error);

        logger.info("argument map exception : " + BaseResponse);

        return handleExceptionInternal(ex, BaseResponse, new HttpHeaders(), HttpStatus.OK, request);

    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        logger.error("handleHttpMessageNotReadable:  ", ex);

        BaseResponse BaseResponse = new BaseResponse();
        Error Error = new Error();
        Error.setErrorCode(WireDeltaError.WD_ERR_500.name());
        Error.setErrorMessage(WireDeltaError.WD_ERR_500.getErrorMessage());
        BaseResponse.setError(Error);

        return handleExceptionInternal(ex, BaseResponse, new HttpHeaders(), HttpStatus.OK, request);
    }


}
