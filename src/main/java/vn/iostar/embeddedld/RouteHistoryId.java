package vn.iostar.embeddedld;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class RouteHistoryId implements Serializable {
    private Integer parcelId;
    private Integer officeId;
}
