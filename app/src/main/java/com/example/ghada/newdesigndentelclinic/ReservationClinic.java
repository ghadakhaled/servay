package com.example.ghada.newdesigndentelclinic;

class ReservationClinic
{
    String date,time,numreserv;

public ReservationClinic()
{

}


    public ReservationClinic(String date, String time,String numreserv) {
        this.date = date;
        this.time = time;
        this.numreserv  =numreserv;
    }

    public String getNumreserv()
    {
        return numreserv;
    }

    public void setNumreserv(String numreserv) {
        this.numreserv = numreserv;
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
}
