package vn.iostar.exception;


import lombok.*;

// nơi chứa những mã code bị lỗi, có nghĩa đây là nơi bắt lỗi exception để xử lý
@AllArgsConstructor
@Getter
public enum StateErrorCode {
    USER_NOT_FOUND(1001, "Không tìm thấy user"),
    WRONG_PASSWORD(1002, "Sai mật khẩu"),
    ;
    // define class
    private final int code;
    private final String message;
}
