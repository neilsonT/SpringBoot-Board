package com.programmers.jpaboard.board.exhandler;

import com.programmers.jpaboard.board.exception.BoardNotFoundException;
import com.programmers.jpaboard.response.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice(basePackages = "com.programmers.jpaboard.board")
public class BoardControllerAdvice {

    @ExceptionHandler
    public ApiResponse<BoardErrorResult> handleBoardNotFoundException(BoardNotFoundException exception) {
        BoardErrorResult boardErrorResult = new BoardErrorResult(exception.getClass().getSimpleName(), exception.getMessage());
        return ApiResponse.fail(BoardErrorStatus.BOARD_NOT_FOUND.getCode(), BoardErrorStatus.BOARD_NOT_FOUND.getMessage(), boardErrorResult);
    }

    @ExceptionHandler
    public ApiResponse<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new ConcurrentHashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> putError(error, errors)
                );

        return ApiResponse.fail(BoardErrorStatus.METHOD_ARGUMENT_NOT_VALID.getCode(), BoardErrorStatus.METHOD_ARGUMENT_NOT_VALID.getMessage(), errors);
    }

    private void putError(ObjectError error, Map<String, String> errors) {
        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
    }

    @ExceptionHandler
    public ApiResponse<BoardErrorResult> handleException(Exception exception) {
        BoardErrorResult boardErrorResult = new BoardErrorResult(exception.getClass().getSimpleName(), exception.getMessage());
        return ApiResponse.fail(BoardErrorStatus.INTERNAL_SERVER_ERROR.getCode(), BoardErrorStatus.INTERNAL_SERVER_ERROR.getMessage(), boardErrorResult);
    }
}
