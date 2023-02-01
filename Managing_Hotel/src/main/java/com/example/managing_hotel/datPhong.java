package com.example.managing_hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class datPhong {
    @FXML
    TextField dpName;
    @FXML
    TextField dpNgayThue;
    @FXML
    TextField dpSoNgayThue;

    Stage stage;

    private static double doanhthu = 16800000;

    public void xacNhan(ActionEvent e) throws SQLException {
        int a = Integer.valueOf(dpName.getText());
        String d = dpNgayThue.getText();
        int f = Integer.valueOf(dpSoNgayThue.getText());


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/managing_hotel", "haisql", "ngaongo119001190");
        PreparedStatement ps0 = connection.prepareStatement("select * from hotel where Name = '" + a + "'; ");
        ResultSet resulset = ps0.executeQuery();

        if (resulset.getString("Trang_Thai") == "avaiable") {
            PreparedStatement ps1 = connection.prepareStatement("update hotel set NGAY_THUE = '" + d + "', THOI_GIAN_THUE = '" + e + "' where Name = '" + a + "' ;");
            ps1.executeUpdate();
            if (resulset.getString("Loai") == "VIP") {
                int sotang = Integer.valueOf(resulset.getString("Loai")) / 100;
                doanhthu += 150000 * Integer.valueOf(resulset.getString("SO_NGAY_THUE")) + sotang * 500000;
            } else {
                doanhthu += 100000 * Integer.valueOf(resulset.getString("SO_NGAY_THUE"));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Phong Dang Duoc Cho Thue");
            alert.showAndWait();
        }


        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        Menu restart = new Menu();
        restart.start(new Stage());

    }

    public double getDoanhthu()
    {
        return doanhthu;
    }
}
