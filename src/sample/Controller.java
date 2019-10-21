package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.sql.*;


public class Controller {
    TrainModel m = new TrainModel();

    @FXML
    ComboBox<String> cbDepart;

    @FXML
    ComboBox<String> cbDesti;

    @FXML
    ComboBox<String> cbDepartureTime;

    @FXML
    TextArea textInfo;

    @FXML
    private void getStation() {

        cbDepart.setItems(stationsList);
    }
    public void routeHandler(ActionEvent e) {
        System.out.println("Find route");
        textInfo.setText(m.findRoute(cbDepart.getButtonCell().getText(), cbDesti.getButtonCell().getText(), cbDepartureTime.getButtonCell().getText()));
    }


    private ObservableList<String> stationsList = FXCollections.observableArrayList();

    public void initialize() {
        //
        String stationsName = " select * from Stations ";
        Connection conn;
        String url = "jdbc:sqlite:C:/Users/Morten/IdeaProjects/Train_schedule_APP/src/db/Train_schedule.db";
        try {
        conn = DriverManager.getConnection(url);
            if (conn != null)
            { conn.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }

}

    class TrainModel {
    }

    String findRoute(String stat1, String stat2, String time) {
        return "route from " + stat1 + "\n to " + stat2 + " at " + time;
    }
}