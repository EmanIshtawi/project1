package com.example.project;

public class RoomTable {
    private int RID , Columns , Shelves , Books ;
    private String Rname ;
    RoomTable(int RID ,String Rname , int Columns , int Shelves , int Books ){
        this.RID=RID;
        this.Rname=Rname;
        this.Columns=Columns;
        this.Shelves=Shelves;
        this.Books=Books;
    }
    public int getRID(){
        return RID;
    }
    public String getRname(){
        return Rname;
    }

    public int getColumns() {
        return Columns;
    }

    public int getShelves() {
        return Shelves;
    }
    public int getBooks(){
        return Books;
    }
}
