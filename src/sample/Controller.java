package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller {

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
        departureTime();
        textInfo.setDisable(true);
        //calculateRoute();
    }

    public void stationsCombo() throws SQLException {
        try {
            // creating a list string with a query and connection to the database
            ObservableList<String> stationNames = FXCollections.observableArrayList();
            String stations = "SELECT Name FROM Stations";

            Connection connection = DB_Connection.getInstance().getConnection();

            // creating a prepared statement with connection to the stations table and executing the query
            PreparedStatement ps = connection.prepareStatement(stations);
            ResultSet rs = ps.executeQuery();
            // get the names as long as there is names to get
            while (rs.next()) {
                stationNames.add(rs.getString("Name"));
            }
            // sets the list in the comboboxes
            cbDepart.setItems(stationNames);
            cbDesti.setItems(stationNames);
            connection.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void departureTime() {
        String time = "00:00";
        timeField.setText(time);

        if (timeField.getText().matches("[a-z]")) {
            timeField.clear();
            timeField.setText(time);
        }
    }

    public void calculateRoute(ActionEvent event) throws SQLException {
        String stations = "SELECT name FROM Stations";
        String time = "SELECT departTime FROM Schedule";
        Connection connection = DB_Connection.getInstance().getConnection();
        PreparedStatement psDepart = connection.prepareStatement(stations);
        PreparedStatement psTime = connection.prepareStatement(time);
        ResultSet rsStations = psDepart.executeQuery();
        ResultSet rsTime = psTime.executeQuery();





        textInfo.setText("Take the train from: " + cbDepart.getValue() + " to: " + cbDesti.getValue() + " at: " + timeField.getText());


    }
}

