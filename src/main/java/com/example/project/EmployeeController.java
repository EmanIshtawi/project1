package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.pool.OracleDataSource;

import net.sf.jasperreports.engine.design.JasperDesign;

import java.io.*;
import java.sql.Connection;

public class EmployeeController {
    @FXML
    private TextField EmpNameTxt;
    @FXML
    public void displayName(String EmpName){
        EmpNameTxt.setText(EmpName);
    }


    @FXML
    protected void report(ActionEvent e){
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setUser("Library_dba");
            ods.setPassword("library12345");
            ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            Connection con=ods.getConnection();
            InputStream input = new FileInputStream(new File("Tree.jrxml"));
            JasperDesign jd = JRXmlLoader.load(input);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr,null , con);

            OutputStream out = new FileOutputStream(new File("Tree.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,out);
            out.close();
            input.close();
        }
        catch (Exception ex){
            ex.toString();
        }
    }
}
