package com.example.managing_hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class timLoaiController {
    private String[][] tt;
    @FXML
    TextField gtTim;
    @FXML
    Label gtTra;

    public void xacNhan() throws SQLException {
        Connection_SQL a = new Connection_SQL();
        tt = a.getPhong();
        String tim = gtTim.getText();
        String tra = "";
        for(int i = 0; i < a.getSoPhong(); i++)
        {
            if(tt[i][2] == tim)
            {
                tra += tt[i][0] + " ";
            }
        }
        gtTra.setText(tra);
    }
}
