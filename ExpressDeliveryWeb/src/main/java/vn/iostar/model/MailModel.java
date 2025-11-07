package vn.iostar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class MailModel {
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> props; // contain information
}
