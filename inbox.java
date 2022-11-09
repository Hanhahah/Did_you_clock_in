package com.model;

import java.sql.Date;

public class inbox {
	  private Date gdate;
	    private int onumber;
	    private String report;
	    public inbox() {}
	    public inbox(Date gdate,int onumber, String report) {
	        super();
	        this.gdate=gdate;
	        this.onumber = onumber;
	        this.report=report;
	    }
	    public Date getGdate() {
	        return gdate;
	    }
	    public void setGdate(Date gdate) {
	        this.gdate = gdate;
	    }
	    public int getOnumber() {
	        return onumber;
	    }
	    public void setOnumber(int onumber) {
	        this.onumber = onumber;
	    }

	    public String getReport() {
	        return report;
	    }
	    public void setReport(String report) {
	        this.report = report;
	    }

}
