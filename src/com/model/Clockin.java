package com.model;

import java.util.Date;

/**
 * @author hu
 * @date 2022/11/9
 */
public class Clockin {
    private int onumber;
    private String telephone;
    private Date cdate;
    private  String location;
    private  String helthcode;
    private  String tripcode;
    private  String natresult;
    public Date getCdate() {
        return cdate;
    }
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    public int getOnumber() {
        return onumber;
    }
    public void setOnumber(int onumber) {
        this.onumber = onumber;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setHelthcode(String helthcode) {
        this.helthcode =helthcode;
    }
    public String getHelthcode() {
        return  helthcode;
    }
    public String getTripcode() {
        return tripcode;
    }
    public void setTripcode(String tripcode) {
        this.tripcode =tripcode;
    }
    public String getNatresult() {
        return natresult;
    }
    public void setNatresult(String natresult) {
        this.natresult =natresult;
    }

}
