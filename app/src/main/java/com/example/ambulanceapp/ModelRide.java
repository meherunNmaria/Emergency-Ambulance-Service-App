package com.example.ambulanceapp;

public class ModelRide {
    String avaibility_for,driver_id,fee,license_plate_no,name,service_type,type;

    public ModelRide(){
    }

    public ModelRide(String avaibility_for, String driver_id, String fee, String license_plate_no, String name, String service_type, String type) {
        this.avaibility_for = avaibility_for;
        this.driver_id = driver_id;
        this.fee = fee;
        this.license_plate_no = license_plate_no;
        this.name = name;
        this.service_type = service_type;
        this.type = type;
    }

    public String getAvaibility_for() {
        return avaibility_for;
    }

    public void setAvaibility_for(String avaibility_for) {
        this.avaibility_for = avaibility_for;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLicense_plate_no() {
        return license_plate_no;
    }

    public void setLicense_plate_no(String license_plate_no) {
        this.license_plate_no = license_plate_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
