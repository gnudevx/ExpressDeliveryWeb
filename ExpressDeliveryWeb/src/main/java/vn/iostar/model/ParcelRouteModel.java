package vn.iostar.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//search parcel model:
@Getter
@Setter
public class ParcelRouteModel {
        private String ladingCode;
        private String  userName;
        private String status;
        private List<RouteDetail> routeDetails;
        public static class RouteDetail {
            private String checkinTime;
            private String checkoutTime;
            private String address;
            private String description;
            public RouteDetail(String checkinTime, String checkoutTime, String address, String description) {
                this.checkinTime = checkinTime;
                this.checkoutTime = checkoutTime;
                this.address = address;
                this.description = description;
            }

            public String getCheckinTime() {
                return checkinTime;
            }

            public String getCheckoutTime() {
                return checkoutTime;
            }

            public String getAddress() {
                return address;
            }

            public String getDescription() {
                return description;
            }
        }
        public ParcelRouteModel(String ladingCode, String userName, String status, List<RouteDetail> routeDetails) {
            this.ladingCode = ladingCode;
            this.userName = userName;
            this.status = status;
            this.routeDetails = routeDetails;
        }
}
