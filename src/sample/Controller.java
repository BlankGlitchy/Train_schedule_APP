package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;


public class Controller {
    Connection conn = null;
    String url = "jdbc:sqlite:C:/Users/Morten/IdeaProjects/Train_schedule_APP/src/db/Train_schedule.db";

    @FXML
    ComboBox<String> cbDepart;

    @FXML
    ComboBox<String> cbDesti;

    @FXML
    TextField timeField;

    @FXML
    TextArea textInfo;

    @FXML
    Button routeButton;

    @FXML
    public void initialize() throws SQLException {
        stationsCombo();
        departureTimeCombo();
        calculateRoute();
    }

    public void stationsCombo() throws SQLException {
        try {
            // creating a list string with a query and connection to the database
            ObservableList<String> stationNames = FXCollections.observableArrayList();
            String stations = "SELECT Name FROM Stations";
            conn = DriverManager.getConnection(url);
            // creating a prepared statement with connection to the stations table and executing the query
            PreparedStatement ps = conn.prepareStatement(stations);
            ResultSet rs = ps.executeQuery();
            // get the names as long as there is names to get
            while (rs.next()) {
                stationNames.add(rs.getString("Name"));
            }
            // sets the list in the comboboxes
            cbDepart.setItems(stationNames);
            cbDesti.setItems(stationNames);
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void departureTimeCombo() {
        timeField.getText();

    }

    public void calculateRoute() {

    }
}