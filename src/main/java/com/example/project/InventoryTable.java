package com.example.project;

public class InventoryTable {
    private int RoomID , Bprice , Bamount ;
    private String Bname , Bauthor , Bpublisher , Btype ;
    InventoryTable(int RoomID , String Bname , String Bauthor , String Bpublisher , String Btype , int Bprice , int Bamont){
        this.RoomID=RoomID;
        this.Bname=Bname;
        this.Bauthor=Bauthor;
        this.Bpublisher=Bpublisher;
        this.Btype=Btype;
        this.Bprice=Bprice;
        this.Bamount=Bamont;
    }

    public int getRoomID(){
        return RoomID;
    }

    public String getBauthor() {
        return Bauthor;
    }

    public String getBname() {
        return Bname;
    }

    public String getBpublisher() {
        return Bpublisher;
    }

    public String getBtype() {
        return Btype;
    }

    public int getBprice() {
        return Bprice;
    }

    public int getBamount() {
        return Bamount;
    }
}
