package vn.iostar.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.entity.ParcelDetail;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	
    private Integer productId;

    private String name;

    private String description;

    private String image;

    private Float weight;

    private List<ParcelDetail> parcelDetails;
    
    private Boolean isEdit = false;
}
