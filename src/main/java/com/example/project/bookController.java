package com.example.project;

//import com.example.project.model.SwitchScene;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class bookController implements Initializable {

    private boolean BbookTbl ;
    private boolean BroomTbl ;
    private boolean BininventoryTbl ;

    @FXML
    private VBox bookVbox;
    @FXML
    private AnchorPane BookMainPane , srchbookpane , addbookpane , inventorypane , roompane , inventorypane2;
    @FXML
    private Button GoToCustomerBtn , menuebtn , srchbookbtn , addbookbtn , roombtn , inventorybtn;
    @FXML
    private TextField ISBNtxt , BookNametxt , Authortxt , Publishertxt , Pricetxt , Typetxt , Partstxt , BindingTypetxt , Yeartxt ,
            Pagestxt , Availabletxt , SerieTxt , Datetxt , RoomIDtxt , InShelveTxt , InColumnTxt , InRIDTxt,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10
            ,t11,t12,t13,t14, amontBuyTxt , Room2Txt , Column2Txt , Shelve2Txt , InvAmount2Txt , InvISBN2Txt;
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
    private TableColumn<BookTable, Integer> ShelveC;

    @FXML
    private TableColumn<BookTable, Integer> ColumnsC;

    @FXML
    private TableView<RoomTable> RoomTbl;

    @FXML
    private TableColumn<RoomTable, Integer> RoomIDColumns;

    @FXML
    private TableColumn<RoomTable, String> RoomNameC;

    @FXML
    private TableColumn<RoomTable, Integer> RoomColumnsC;

    @FXML
    private TableColumn<RoomTable, Integer> RoomShelvesC;

    @FXML
    private TableColumn<RoomTable, Integer> RoomBooksC;

    @FXML
    private TableView<InventoryTable> inventoryTbl;

    @FXML
    private TableColumn<InventoryTable, Integer> InISBNC;

    @FXML
    private TableColumn<InventoryTable, Integer> InAmountC;

    @FXML
    private TableColumn<InventoryTable, String > InAuthorC;

    @FXML
    private TableColumn<InventoryTable, String> InNameC;

    @FXML
    private TableColumn<InventoryTable, Integer> InPriceC;

    @FXML
    private TableColumn<InventoryTable, String> InPublisherC;

    @FXML
    private TableColumn<InventoryTable, String> InTypeC;

    @FXML
    private Label warningTxt;


    /*@FXML
    protected void onGoToCustomerBtnClick(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer.fxml"));
        Stage window =(Stage) GoToCustomerBtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 800, 600));
        window.show();
    }*/

 /*   @FXML
    protected void onGoToCustomerBtnClick(ActionEvent e) {
        try {
            new SwitchScene(BookMainPane , "hello-view.fxml");
        } catch (Exception ex) {
            System.out.println("E");
        }
    }*/
    @FXML
    protected void onmenuebtnClick(ActionEvent e) {
        if(bookVbox.isVisible()) {
            bookVbox.setVisible(false);
            srchbookpane.setVisible(false);
            addbookpane.setVisible(false);
            roompane.setVisible(false);
            inventorypane.setVisible(false);
            inventorypane2.setVisible(false);
            //RoomTbl.setVisible(false);

        }
        else
            bookVbox.setVisible(true);
    }
    @FXML
    protected void onsrchbookbtnClick(ActionEvent e) {
        addbookpane.setVisible(false);
        inventorypane.setVisible(false);
        roompane.setVisible(false);
        inventorypane2.setVisible(false);
        //RoomTbl.setVisible(false);

        srchbookpane.setVisible(true);
        for ( int k = 0; k<Table.getItems().size(); k++) {
            Table.getItems().clear();
        }
        ISBNtxt.setText(" ");
        BookNametxt.setText(" ");
        Publishertxt.setText(" ");
        Authortxt.setText(" ");
        Typetxt.setText(" ");
        Pricetxt.setText(" ");
    }
    @FXML
    protected void onaddbookbtnClick(ActionEvent e) {
        srchbookpane.setVisible(false);
        inventorypane.setVisible(false);
        roompane.setVisible(false);
        inventorypane2.setVisible(false);
        //RoomTbl.setVisible(false);

        addbookpane.setVisible(true);
    }
    @FXML
    protected void onroombtnClick(ActionEvent e) {
        srchbookpane.setVisible(false);
        addbookpane.setVisible(false);
        inventorypane.setVisible(false);
        inventorypane2.setVisible(false);
        roompane.setVisible(true);

        for ( int k = 0; k<RoomTbl.getItems().size(); k++) {
            RoomTbl.getItems().clear();
        }

        String viewRoom = "select * from room ";
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(viewRoom);

            while(re.next()){
                int id = re.getInt(1);
                String name = re.getString(2);
                int Col = re.getInt(3);
                int Shel = re.getInt(4);
                int book = re.getInt(5);
                RoomTable RT = new RoomTable(id , name , Col , Shel , book);
                ObservableList<RoomTable> RoomList = RoomTbl.getItems();
                RoomList.add(RT);
                RoomTbl.setItems(RoomList);

            }

            DB.con.commit();

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
        inventorypane2.setVisible(false);
     //   RoomTbl.setVisible(false);

        inventorypane.setVisible(true);
        for ( int k = 0; k<inventoryTbl.getItems().size(); k++) {
            inventoryTbl.getItems().clear();
        }

        InRIDTxt.setText(" ");
        InShelveTxt.setText(" ");
        InColumnTxt.setText(" ");
    }

    @FXML
    protected void onInvSrchBtnClick(ActionEvent e){
        for ( int k = 0; k<inventoryTbl.getItems().size(); k++) {
            inventoryTbl.getItems().clear();
        }

       // inventoryTbl.getItems().removeAll();
        String findISBN = "select ISBN , AMOUNT_OF_BOOKS from ROOM_BOOK where RID = " + InRIDTxt.getText() + " and COLUMN_NUM = " + InColumnTxt.getText() + " and SHELVE_NUM = " + InShelveTxt.getText();
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(findISBN);

            while(re.next()){
                String name, author , publisher , type ;
                int id = re.getInt(1);
                int amount = re.getInt(2);
                int price;

                String findBook = "select BOOK_NAME , AUTHOR_NAME , PUBLISHER , BTYPE , PRICE from book where ISBN = " + id ;
                Statement st2 = DB.con.createStatement();
                ResultSet re2 = st2.executeQuery(findBook);
               while(re2.next()) {

                   name = re2.getString(1);
                   author = re2.getString(2);
                   publisher = re2.getString(3);
                   type = re2.getString(4);
                   price = re2.getInt(5);

                   InventoryTable IT = new InventoryTable(id, name, author, publisher, type, price, amount);
                   ObservableList<InventoryTable> InvList = inventoryTbl.getItems();
                   InvList.add(IT);
                   inventoryTbl.setItems(InvList);
               }

            }

            DB.con.commit();


        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }

    @FXML
    protected void onaddBtnClick(ActionEvent e) {
       /*String InsertBook= "insert into book values (" + Integer.parseInt(ISBNtxt.getText()) + ", '" + BookNametxt.getText() + "' , '" + Authortxt.getText() +
                "' , '" + Publishertxt.getText() + "' ," + Pricetxt.getText() + ", '" + Typetxt.getText() + "' ," + Partstxt.getText() +
               ", '"+ BindingTypetxt.getText() + "' ," + Yeartxt.getText() + "," + Pagestxt.getText() + "," + Availabletxt.getText() +
                ", " + RoomIDtxt.getText() + ")";*/
      //  int x = Integer.valueOf(ISBNtxt.getText()) ;
       // System.out.println(x);
       // String InsertBook= "insert into book (ISBN) values (" + Integer.valueOf(ISBNtxt.getText()) + ")";
       String InsertBook= "insert into book (ISBN , BOOK_NAME, AUTHOR_NAME, PUBLISHER, PRICE, BTYPE,NUMBER_OF_PARTS,BINDING_TYPE,BOOK_YEAR,NUMBER_OF_PAGES) values (?,?,?,?,?,?,?,?,?,?)";
       String InsertR_B="insert into ROOM_BOOK(RID,ISBN,COLUMN_NUM,SHELVE_NUM,AMOUNT_OF_BOOKS) values(?,?,?,?,?)";
       try {
            /*Statement st = DB.con.createStatement();
            st.execute(InsertBook);*/
            PreparedStatement ps = DB.con.prepareStatement(InsertBook);
            ps.setInt(1,Integer.parseInt(t1.getText()));
            ps.setString(2,t2.getText());
            ps.setString(3,t3.getText());
            ps.setString(4,t4.getText());
            ps.setInt(5,Integer.parseInt(t5.getText()));
            ps.setString(6,t6.getText());
            ps.setInt(7,Integer.parseInt(t7.getText()));
            ps.setString(8,t8.getText());
            ps.setInt(9,Integer.parseInt(t9.getText()));
            ps.setInt(10, Integer.parseInt(t10.getText()));
            PreparedStatement ps2 = DB.con.prepareStatement(InsertR_B);
            ps2.setInt(1,Integer.parseInt(t11.getText()));
            ps2.setInt(2,Integer.parseInt(t1.getText()));
            ps2.setInt(3,Integer.parseInt(t12.getText()));
           ps2.setInt(4,Integer.parseInt(t13.getText()));
           ps2.setInt(5,Integer.parseInt(t14.getText()));
            DB.con.commit();
          //  DB.con.close();


           if(ps.execute()){
               System.out.println("kkk");
           }

        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }
    @FXML
    protected void onNEWBtnClick(){
        t1.setText(" ");
        t2.setText(" ");
        t3.setText(" ");
        t4.setText(" ");
        t5.setText(" ");
        t6.setText(" ");
        t7.setText(" ");
        t8.setText(" ");
        t9.setText(" ");
        t10.setText(" ");
        t11.setText(" ");
        t12.setText(" ");
        t13.setText(" ");
        t14.setText(" ");


    }

    @FXML
    protected void  onUpdateBookBtnClick(ActionEvent e) {
        int n = 0 ;
        int selectedRow = Table.getSelectionModel().getSelectedIndex();
        BookTable m =  Table.getItems().get(selectedRow);
      /*  ObservableList selectedCells = Table.getSelectionModel().getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        System.out.println("Selected Value" + val);*/

        n=Integer.parseInt(amontBuyTxt.getText());
        int av = m.getAvailablebook();
        int x = av - n;
        int rISBN = m.getISBN();
        int rRID = m.getRoomID();

        System.out.println(x);
        System.out.println(rISBN);
        System.out.println(rRID);
        if (x > 0) {
            warningTxt.setVisible(false);
            String UpdateBook = "Update ROOM_BOOK set AMOUNT_OF_BOOKS = " + x + "where ISBN = " +rISBN+ " and RID = " + rRID;
            try{
                Statement st = DB.con.createStatement();
                st.execute(UpdateBook);
                DB.con.commit();
            }
            catch (Exception ex){
                System.out.println("ERROR");
                System.out.println(ex.toString());
            }
            Table.getItems().remove(selectedRow);
        }
        else {
            warningTxt.setVisible(true);
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
        String viewBook = "select * from book where ISBN = " + ISBNtxt.getText() ;
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(viewBook);

            while(re.next()){
                int ISBN = re.getInt(1);
                String bookName = re.getString(2);
                String author = re.getString(3);
                String publisher = re.getString(4);
                int price = re.getInt(5);
                String type = re.getString(6);
                int parts = re.getInt(7);
                String binding_type = re.getString(8);
                int year = re.getInt(9);
                int pages = re.getInt(10);
                int available = re.getInt(11);
                Date arrive = re.getDate(12);

                String findRCS = "select RID , COLUMN_NUM , SHELVE_NUM , AMOUNT_OF_BOOKS from ROOM_BOOK where ISBN = "+ ISBN;
                Statement st2 = DB.con.createStatement();
                ResultSet re2 = st2.executeQuery(findRCS);
                while(re2.next()) {
                    int roomID = re2.getInt(1);
                    int columns = re2.getInt(2);
                    int shelves = re2.getInt(3);
                    int amount = re2.getInt(4);

                    BookTable BT = new BookTable(ISBN, bookName, author, publisher, price, type, parts, binding_type, year, pages, amount, arrive, roomID, columns, shelves);
                    ObservableList<BookTable> list = Table.getItems();
                    list.add(BT);
                    Table.setItems(list);
                }
            }

            DB.con.commit();

        } catch (Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }
    }
    @FXML
    protected void onnewAddBtnClick(ActionEvent e) {
        for ( int i = 0; i<Table.getItems().size(); i++) {
            Table.getItems().clear();
        }
        ISBNtxt.setText(" ");
        BookNametxt.setText(" ");
        Publishertxt.setText(" ");
        Authortxt.setText(" ");
        Typetxt.setText(" ");
        Pricetxt.setText(" ");
        //srcTxtarea.setText(" ");
    }

    @FXML
    protected void onInvADDBtnClick (ActionEvent e){
        for ( int k = 0; k<inventoryTbl.getItems().size(); k++) {
            inventoryTbl.getItems().clear();
        }
        inventorypane.setVisible(false);
        inventorypane2.setVisible(true);
    }

    @FXML
    protected void onInvADD2BtnClick (ActionEvent e){
        //System.out.println(111);
       String existISBN = "select ISBN from ROOM_BOOK where RID = " + Integer.parseInt(Room2Txt.getText())+ "and COLUMN_NUM = " +
                Integer.parseInt(Column2Txt.getText()) + "and SHELVE_NUM = " + Integer.parseInt(Shelve2Txt.getText());
        int i = 0 ;
        try{
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(existISBN);
            while(re.next()) {
                int ISBN = re.getInt(1);
              //  System.out.println(ISBN);
                if(ISBN==Integer.parseInt(InvISBN2Txt.getText())){
                    String updateBook = "update ROOM_BOOK set AMOUNT_OF_BOOKS = "+ Integer.parseInt(InvAmount2Txt.getText()) +
                            "where ISBN = " + ISBN ;
                    i=1;
                    Statement st2 = DB.con.createStatement();
                    st2.execute(updateBook);
                    DB.con.commit();
                }
            }
            if(i==0){
                String InsertBook = "insert into ROOM_BOOK (RID , ISBN , COLUMN_NUM , SHELVE_NUM , AMOUNT_OF_BOOKS) values (?,?,?,?,?)";
                PreparedStatement ps = DB.con.prepareStatement(InsertBook);
                ps.setInt(1,Integer.parseInt(Room2Txt.getText()));
                ps.setInt(2,Integer.parseInt(InvISBN2Txt.getText()));
                ps.setInt(3,Integer.parseInt(Column2Txt.getText()));
                ps.setInt(4,Integer.parseInt(Shelve2Txt.getText()));
                ps.setInt(5,Integer.parseInt(InvAmount2Txt.getText()));
                DB.con.commit();
                Room2Txt.setText(" ");
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

    }

    @FXML
    protected void onInvSEARCH2BtnClick (ActionEvent e){
        inventorypane2.setVisible(false);
        inventorypane.setVisible(true);
    }
   @FXML
    protected void onInvNEW2BtnClick (ActionEvent e){
        InvISBN2Txt.setText(" ");
        InvAmount2Txt.setText(" ");
        Column2Txt.setText(" ");
        Shelve2Txt.setText(" ");
        Room2Txt.setText(" ");
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
        ColumnsC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("columns"));
        ShelveC.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("shelves"));

            RoomIDColumns.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("RID"));
            RoomNameC.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Rname"));
            RoomColumnsC.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("Columns"));
            RoomShelvesC.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("Shelves"));
            RoomBooksC.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("Books"));

        InISBNC.setCellValueFactory(new PropertyValueFactory<InventoryTable,Integer>("RoomID"));
        InNameC.setCellValueFactory(new PropertyValueFactory<InventoryTable, String>("Bname"));
        InAuthorC.setCellValueFactory(new PropertyValueFactory<InventoryTable, String>("Bauthor"));
        InPublisherC.setCellValueFactory(new PropertyValueFactory<InventoryTable, String>("Bpublisher"));
        InTypeC.setCellValueFactory(new PropertyValueFactory<InventoryTable, String>("Btype"));
        InPriceC.setCellValueFactory(new PropertyValueFactory<InventoryTable,Integer>("Bprice"));
        InAmountC.setCellValueFactory(new PropertyValueFactory<InventoryTable,Integer>("Bamount"));
    }
}
