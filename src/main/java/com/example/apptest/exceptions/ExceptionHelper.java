package com.example.apptest.exceptions;

import com.example.apptest.domain.LocalaziableData;
import com.example.apptest.domain.LocalizationMessage;
import com.example.apptest.repository.LocalizationMessageRepository;
import com.example.apptest.service.ApiResult;
import com.example.apptest.service.BaseResponseDTO;
import com.example.apptest.service.ErrorData;
import com.example.apptest.utils.LanguageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
@RequiredArgsConstructor
public class ExceptionHelper {

    private final LocalizationMessageRepository localizationMessageRepository;


    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<BaseResponseDTO<ApiResult<ErrorData>>> handleException(RestException ex) {

        ErrorTypeEnum errorType = ex.getErrorType();

        String message = null;
        Optional<LocalizationMessage> optionalLocalizationMessage = localizationMessageRepository.findByErrorType(errorType);
        if (optionalLocalizationMessage.isPresent()) {
            message = LocalaziableData.getSingleMessage(optionalLocalizationMessage.get().getLocalaziableData(), LanguageUtil.getMessageFunction());
            if (ex.getMessage() != null)
                message = message + " " + ex.getMessage();
        }

        return new ResponseEntity<>(BaseResponseDTO.fail(errorType.getCode(),
                ApiResult.errorResponse(message, errorType.getCode())),
                ex.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponseDTO<ApiResult<ErrorData>>> handleException(Exception ex) {
        ex.printStackTrace();
        if (!(ex instanceof BusinessException businessException)) {
            return new ResponseEntity<>(BaseResponseDTO.fail(
                    ApiResult.errorResponse(ex.getMessage(), -99999)),
                    HttpStatus.BAD_REQUEST);
        }

        ErrorTypeEnum errorType = businessException.getErrorType();

        String message = localizationMessageRepository.findByErrorType(errorType)
                .map(LocalizationMessage::getLocalaziableData)
                .map(localaziableData -> LocalaziableData.getSingleMessage(localaziableData, LanguageUtil.getMessageFunction()))
                .orElse("");

        return new ResponseEntity<>(BaseResponseDTO.fail(errorType.getCode(),
                ApiResult.errorResponse(message, errorType.getCode())),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponseDTO<ApiResult<ErrorData>>> handleException(MethodArgumentNotValidException ex) {
        List<ErrorData> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String[] codes = error.getCodes();
            assert codes != null;
            String code = codes[codes.length - 1];
            String errorMessage = error.getDefaultMessage() + "_" + code;
            FieldError fieldError = (FieldError) error;
            errors.add(new ErrorData(errorMessage, HttpStatus.BAD_REQUEST, fieldError.getField()));
        });
        return new ResponseEntity<>(BaseResponseDTO.fail(ApiResult.errorResponse(errors)), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<BaseResponseDTO<ApiResult<ErrorData>>> handleException(NoHandlerFoundException ex) {
        return new ResponseEntity<>(BaseResponseDTO.fail(
                ApiResult.errorResponse(ex.getMessage(), HttpStatus.NOT_FOUND)),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BaseResponseDTO<ApiResult<ErrorData>>> handleException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(BaseResponseDTO.fail(
                ApiResult.errorResponse(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED)),
                HttpStatus.METHOD_NOT_ALLOWED);
    }
}
