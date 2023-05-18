package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    private Stage stage ;
    private Scene scene ;
    private Parent root ;

    @FXML
    private TextField EmailTxt ;

    @FXML
    private PasswordField PasswordTxt ;

    @FXML
    private Label Invalidtxt;
  /*  @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/


    @FXML
    protected void onLoginBtnClick (ActionEvent event){
        String LogInSQL = "select EID , EMPLOYEE_NAME , EMAIL , PASSWORD from employee " ;
        try {
            Statement st = DB.con.createStatement();
            ResultSet re = st.executeQuery(LogInSQL);
            while(re.next()){
                int EID = re.getInt(1);
                String Emp_Name = re.getString(2);
                String email = re.getString(3);
                String password = re.getString(4);
                System.out.println(EID);
               if(email.equals(EmailTxt.getText()) && password.equals(PasswordTxt.getText())){
                    FXMLLoader Loader = new FXMLLoader(getClass().getResource("employee.fxml"));
                    root=Loader.load();
                    EmployeeController Econ = Loader.getController();
                    Econ.displayName(Emp_Name);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
               else
               {
                   Invalidtxt.setVisible(true);
               }
            }
        }
        catch(Exception ex) {
            System.out.println("ERROR");
            System.out.println(ex.toString());
        }

    }
}