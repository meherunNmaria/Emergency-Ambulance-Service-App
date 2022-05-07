package com.example.ambulanceapp;

public class ModelDriver {
    String ambulance_fee,driver_id,driver_name,hospital_name,license_plate_no,phone,service_type;

    public ModelDriver(){
    }

    public ModelDriver(String ambulance_fee, String driver_id, String driver_name, String hospital_name, String license_plate_no, String phone, String service_type) {
        this.ambulance_fee = ambulance_fee;
        this.driver_id = driver_id;
        this.driver_name = driver_name;
        this.hospital_name = hospital_name;
        this.license_plate_no = license_plate_no;
        this.phone = phone;
        this.service_type = service_type;
    }

    public String getAmbulance_fee() {
        return ambulance_fee;
    }

    public void setAmbulance_fee(String ambulance_fee) {
        this.ambulance_fee = ambulance_fee;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getLicense_plate_no() {
        return license_plate_no;
    }

    public void setLicense_plate_no(String license_plate_no) {
        this.license_plate_no = license_plate_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }
}