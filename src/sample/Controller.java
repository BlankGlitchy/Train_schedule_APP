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
        textInfo.setDisable(true);



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


    @FXML
    public void routeHandler(ActionEvent event) {
        Connection connection = DB_Connection.getInstance().getConnection();
        String To = "", From =  "", Time = "";
        String query = "SELECT t.id, t.start_station, t.destination, sc.depart_time FROM Schedule as sc, Trains as t, Stations as s WHERE t.id = sc.train_id AND t.start_station LIKE ?" +
                "AND t.destination LIKE ?" +
                "AND sc.depart_time >= ? ORDER BY sc.depart_time";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, From + "%");
            ps.setString(2, To + "%");
            ps.setString(3, (Time));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String trainID = rs.getString(1);
                String startStation = rs.getString(2);
                String destination = rs.getString(3);
                String departure = rs.getString(4);
                textInfo.appendText("The train to: " + destination + "\n" + "from: " + startStation + "\n" + "leaves at: " + departure + "\n" + "\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


