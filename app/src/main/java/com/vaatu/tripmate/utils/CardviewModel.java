package com.vaatu.tripmate.utils;

public class CardviewModel {


    String startloc,endloc,date,time,status,tripname;
    //@TODO add lat Long

    public CardviewModel(String startloc, String endloc, String date, String time, String tripname) {
        this.startloc = startloc;
        this.endloc = endloc;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tripname = tripname;
    }

    public CardviewModel() {
    }

    public String getStartloc() {
        return startloc;
    }

    public void setStartloc(String startloc) {
        this.startloc = startloc;
    }

    public String getEndloc() {
        return endloc;
    }

    public void setEndloc(String endloc) {
        this.endloc = endloc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }
}
