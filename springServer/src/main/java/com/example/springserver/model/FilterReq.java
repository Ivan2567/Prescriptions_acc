package com.example.springserver.model;

import java.util.Date;

public class FilterReq {
    private long id;
    private Date startdate;
    private Date enddate;
    private boolean activeflag;
    private boolean disactiveflag;
    private boolean timeoutflag;

    public FilterReq(long id, Date startdate, Date enddate, boolean activeflag, boolean disactiveflag, boolean timeoutflag)
    {
        this.id = id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.activeflag = activeflag;
        this.disactiveflag = disactiveflag;
        this.timeoutflag = timeoutflag;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }
    public void setEnddate(Date id) {
        this.enddate = enddate;
    }

    public boolean getActiveflag() {
        return activeflag;
    }
    public void setActiveflag(boolean activeflag) {
        this.activeflag = activeflag;
    }

    public boolean getDisactiveflag() {
        return disactiveflag;
    }
    public void setDisactiveflag(boolean disactiveflag) {
        this.disactiveflag = disactiveflag;
    }

    public boolean getTimeoutflag() {
        return timeoutflag;
    }
    public void setTimeoutflag(boolean timeoutflag) {
        this.timeoutflag = timeoutflag;
    }

}
