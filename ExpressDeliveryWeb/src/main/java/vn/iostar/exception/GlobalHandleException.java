package vn.iostar.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(value = HandleException.class)
    public ResponseEntity<String> handleException(HandleException e){
        StateErrorCode stateErrorCode = e.getStateErrorCode();
        String message = stateErrorCode.getMessage();
        return ResponseEntity.badRequest().body(message);
    }
}
