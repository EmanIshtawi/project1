package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ResourceBundle;

public class bookController implements Initializable {
    @FXML
    private VBox bookVbox;
    @FXML
    private AnchorPane srchbookpane , addbookpane , inventorypane , roompane;
    @FXML
    private Button menuebtn , srchbookbtn , addbookbtn , roombtn , inventorybtn;
    @FXML
    private TextField ISBNtxt , BookNametxt , Authortxt , Publishertxt , Pricetxt , Typetxt , Partstxt , BindingTypetxt , Yeartxt ,
            Pagestxt , Availabletxt , SerieTxt , Datetxt , RoomIDtxt ;
    @FXML
    private TextArea RoomListView , srcTxtarea ;

    @FXML
    private TableView<BookTable> Table;

    @FXML
    private TableColumn<BookTable, String> AuthorC;

    @FXML
    private TableColumn<BookTable, Integer> AvailableBookC;

    @FXML
    private TableColumn<BookTable, String> BindingTypeC;

    @FXML
    private TableColumn<BookTable, String> BookNameC;

    @FXML
    private TableColumn<BookTable, Date> DateC;


    @FXML
    private TableColumn<BookTable, Integer> ISBNC;

    @FXML
    private TableColumn<BookTable, Integer> PagesC;

    @FXML
    private TableColumn<BookTable, Integer> PartsC;

    @FXML
    private TableColumn<BookTable, Integer> PriceC;

    @FXML
    private TableColumn<BookTable, String> PublisherC;

    @FXML
    private TableColumn<BookTable, Integer> RoomIDC;

    @FXML
    private TableColumn<BookTable, String> TypeC;

    @FXML
    private TableColumn<BookTable, Integer> YearC;

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

    @FXML
    protected void onSearchbtnClick(ActionEvent e) {


        String viewBook = "select * from book b where b.ISBN = " + ISBNtxt.getText() ;
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(viewBook);




            while(re.next()){
                int ISBN = re.getInt(1);
                String bookName = re.getString(2);

                BookTable BT = new BookTable(ISBN , bookName , " A", "B" , 1 , "C" , 5 , "D" ,2 , 3 ,6 , Date.valueOf("2015-03-31"), 10 );
                ObservableList<BookTable> list = Table.getItems();
                list.add(BT);
                Table.setItems(list);
            }
            DB.con.commit();
            DB.con.close();

        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }
    @FXML
    protected void onnewAddBtnClick(ActionEvent e) {
        ISBNtxt.setText(" ");
        RoomIDtxt.setText(" ");
        BookNametxt.setText(" ");
        Publishertxt.setText(" ");
        Authortxt.setText(" ");
        Pricetxt.setText(" ");
        Yeartxt.setText(" ");
        BindingTypetxt.setText(" ");
        Typetxt.setText(" ");
        Availabletxt.setText(" ");
        Pagestxt.setText(" ");
        SerieTxt.setText(" ");
        Partstxt.setText(" ");
        Datetxt.setText(" ");
        srcTxtarea.setText(" ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ISBNC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("ISBN"));
        BookNameC.setCellValueFactory(new PropertyValueFactory<BookTable,String>("bookname"));
        AuthorC.setCellValueFactory(new PropertyValueFactory<BookTable,String>("author"));
        PublisherC.setCellValueFactory(new PropertyValueFactory<BookTable,String>("publisher"));
        PriceC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("price"));
        TypeC.setCellValueFactory(new PropertyValueFactory<BookTable,String>("type"));
        PartsC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("parts"));
        BindingTypeC.setCellValueFactory(new PropertyValueFactory<BookTable,String>("bindingtype"));
        YearC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("year"));
        PagesC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("pages"));
        AvailableBookC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("availablebook"));
        DateC.setCellValueFactory(new PropertyValueFactory<BookTable,Date>("arrdate"));
        RoomIDC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("roomID"));

    }
}
