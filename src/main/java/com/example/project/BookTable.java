package com.example.project;

import java.sql.*;
public class BookTable {
    private int ISBN , price , parts , year , pages , availablebook , roomID , columns , shelves ;
    private String bookname , author , publisher ,type , bindingtype ;
    private Date arrdate ;
    public BookTable (int ISBN ,String bookname , String author , String publisher , int price , String type , int parts , String bindingtype , int year , int pages , int availablebook , Date arrdate , int roomID , int columns , int shelves){
        this.ISBN = ISBN ;
        this.bookname = bookname ;
        this.author = author ;
        this.publisher = publisher ;
        this.price = price ;
        this.type = type ;
        this.parts = parts ;
        this.bindingtype = bindingtype ;
        this.year = year ;
        this.pages = pages ;
        this.availablebook = availablebook ;
        this.arrdate = arrdate ;
        this.roomID = roomID ;
        this.columns = columns ;
        this.shelves = shelves ;
    }
    public int getISBN(){
        return ISBN ;
    }
    public String getBookname(){
        return bookname ;
    }
    public String getAuthor(){
        return author ;
    }
    public String getPublisher(){
        return publisher ;
    }
    public int getPrice (){
        return price;
    }
    public String getType(){
        return type;
    }
    public int getParts(){
        return parts;
    }
    public String getBindingtype(){
        return bindingtype ;
    }
    public int getYear(){
        return year;
    }
    public int getPages(){
        return pages ;
    }
    public int getAvailablebook (){
        return availablebook ;
    }
    public Date getArrdate(){
        return arrdate ;
    }
    public int getRoomID(){
        return roomID ;
    }
    public int getColumns (){
        return columns ;
    }
    public int getShelves(){
        return shelves ;
    }
}
