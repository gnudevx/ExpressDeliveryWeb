// src/main/java/vn/iotstar/entity/id/UserOfficeId.java
package vn.iostar.embeddedld;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserOfficeId implements Serializable {
    private Integer userId;
    private Integer officeId;
    private LocalDateTime fromDate;
}
