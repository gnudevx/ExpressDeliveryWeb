package vn.iostar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
    private int office_id;
    private String address;
    private long daily;
    private long monthly;
    private long quarterly;
    private long yearly;
}
