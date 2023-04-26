package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class bookController {
    @FXML
    private VBox bookVbox;
    @FXML
    private AnchorPane srchbookpane , addbookpane , inventorypane , roompane;
    @FXML
    private Button menuebtn , srchbookbtn , addbookbtn , roombtn , inventorybtn;
    @FXML
    private TextField ISBNtxt , BookNametxt , Authortxt , Publishertxt , Pricetxt , Typetxt , Partstxt , BindingTypetxt , Yeartxt ,
            Pagestxt , Availabletxt , Datetxt , RoomIDtxt ;
    @FXML
    private TextArea RoomListView ;

    @FXML
    protected void onmenuebtnClick(ActionEvent e) {
        if(bookVbox.isVisible()) {
            bookVbox.setVisible(false);
            srchbookpane.setVisible(false);
            addbookpane.setVisible(false);
            roompane.setVisible(false);
            inventorypane.setVisible(false);
        }
        else
            bookVbox.setVisible(true);
    }
    @FXML
    protected void onsrchbookbtnClick(ActionEvent e) {
        addbookpane.setVisible(false);
        inventorypane.setVisible(false);
        roompane.setVisible(false);
        srchbookpane.setVisible(true);
    }
    @FXML
    protected void onaddbookbtnClick(ActionEvent e) {
        srchbookpane.setVisible(false);
        inventorypane.setVisible(false);
        roompane.setVisible(false);
        addbookpane.setVisible(true);
    }
    @FXML
    protected void onroombtnClick(ActionEvent e) {
        srchbookpane.setVisible(false);
        addbookpane.setVisible(false);
        inventorypane.setVisible(false);
        roompane.setVisible(true);

        String viewRoom = "select * from room ";
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(viewRoom);

            RoomListView.setText("Room ID    Room Name    # Columns    # Shelves    # Books \n");
            while(re.next()){
                int id = re.getInt(1);
                String name = re.getString(2);
                int Col = re.getInt(3);
                int Shel = re.getInt(4);
                int book = re.getInt(5);
                RoomListView.appendText(id + "    " + name + "    " + Col + "    " + Shel + "    " + book + "\n");
            }
            DB.con.commit();
            DB.con.close();

        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }

    @FXML
    protected void oninventorybtnClick(ActionEvent e) {
        srchbookpane.setVisible(false);
        addbookpane.setVisible(false);
        roompane.setVisible(false);
        inventorypane.setVisible(true);
    }

    @FXML
    protected void onaddBtnClick(ActionEvent e) {
        String InsertBook= "insert into book values (" + ISBNtxt.getText() + ", '" + BookNametxt.getText() + "' , '" + Authortxt.getText() +
                "' , '" + Publishertxt.getText() + "' ," + Pricetxt.getText() + ", '" + Typetxt.getText() + "' ," + Partstxt.getText() +
               ", '"+ BindingTypetxt.getText() + "' ," + Yeartxt.getText() + "," + Pagestxt.getText() + "," + Availabletxt.getText() +
                ", " + RoomIDtxt.getText() + ")";
        try {
            Statement st = DB.con.createStatement();
            st.execute(InsertBook);
            DB.con.commit();
            DB.con.close();
            ISBNtxt.setText(" ");
        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }
    @FXML
    protected void onsrchBtnClick(ActionEvent e) {
        //SrchBtn

        String search = "select * from book where ISBN = " +ISBNtxt.getText() ;
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(search);

            RoomListView.setText("ISBN BOOK_NAME AUTHOR PUBLISHER  PRICE BTYPE PARTS BINDING_TYPE YEAR PAGES NUMBER ARRDATE RID\n");
            while(re.next()){
                int id = re.getInt(1);
                String name = re.getString(2);
                String Aname = re.getString(3);
                String Pub = re.getString(4);
                int price = re.getInt(5);
                String type = re.getString(6);
                int parts = re.getInt(7);
                String Bindingtype = re.getString(8);
                int year = re.getInt(9);
                int pages = re.getInt(10);
                int available = re.getInt(11);
                Date adate = re.getDate(12);
                int RID = re.getInt(13);
                if(RoomListView.getText()=="")
                RoomListView.appendText(id+" "+name+" "+Aname+" "+Pub+""+price+" "+type+" "+parts+" "+Bindingtype+" "+year+" "+pages+" "+available+" "+adate+" "+RID+"\n");
                DB.con.commit();
                DB.con.close();
                //DB.con.setAutoCommit(false);

            }


        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }


    }

}
