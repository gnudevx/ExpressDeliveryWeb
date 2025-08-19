package vn.iostar.exception;


import lombok.Getter;
import lombok.Setter;

// đây là nơi xử lí code:
@Getter
@Setter
public class HandleException  extends RuntimeException{
        StateErrorCode stateErrorCode;
        public HandleException(StateErrorCode stateErrorCode) {
            this.stateErrorCode = stateErrorCode;
        }
}
