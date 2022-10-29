package space.harbour.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import space.harbour.domain.Friend;
import space.harbour.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FriendsAppController implements Initializable {

    public TextField searchTextField;
    public String filePath;
    public Label statusLabel;
    public TableView<Friend> dataTable;
    private List<Friend> friendsList;

    public FriendsAppController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Specific initializer executed after UI componentes instantiation
        prepareTableView();
    }

    private void prepareTableView() {
        TableColumn<Friend, String> idColumn = new TableColumn<>("Friend Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Friend, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Friend, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Friend, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Friend, String> addressSpeedColumn = new TableColumn<>("Address");
        addressSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Friend, Date> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        dataTable.getColumns().add(idColumn);
        dataTable.getColumns().add(nameColumn);
        dataTable.getColumns().add(emailColumn);
        dataTable.getColumns().add(phoneNumberColumn);
        dataTable.getColumns().add(addressSpeedColumn);
        dataTable.getColumns().add(birthdayColumn);

        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void searchFriend(ActionEvent event) {
        String searchText = searchTextField.getText();
        if (friendsList == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Friend list not loaded yet");
            alert.setHeaderText(null);
            alert.setTitle("Friend list not loaded yet");
            alert.show();
            return;
        }
        if (friendsList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Friend list is empty");
            alert.setHeaderText(null);
            alert.setTitle("Friend list is empty");
            alert.show();
            return;
        }

        List<Friend> friendsStream = friendsList.stream()
                .filter(Friend -> Friend.getName().contains(searchText) || Friend.getEmail().contains(searchText) || Friend.getAddress().contains(searchText))
                .collect(Collectors.toList());

        if (searchText.equals("") && friendsList.size() != 0) {
            dataTable.setItems(FXCollections.observableArrayList(friendsList));
            statusLabel.setText("All Friends loaded");
            return;
        }

        if (friendsStream.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No data found");
            alert.setHeaderText(null);
            alert.setTitle("No data found");
            alert.show();

            statusLabel.setText("No data for text: " + searchText);
            dataTable.getItems().clear();
            return;
        }
        dataTable.setItems(FXCollections.observableArrayList(friendsStream));
        statusLabel.setText("Result of searching: " + searchText);
    }

    @FXML
    public void deleteFriends(ActionEvent event) {
        ObservableList<Friend> selectedFriends = dataTable.getSelectionModel().getSelectedItems();
        if (selectedFriends.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not friend selected");
            alert.setHeaderText(null);
            alert.setTitle("Not friend selected");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Delete Information");
        alert.setContentText("Are you sure you want to delete the information?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            friendsList.removeAll(selectedFriends);
            dataTable.setItems(FXCollections.observableArrayList(friendsList));
            statusLabel.setText("Friends deleted correctly");
        }
    }

    @FXML
    public void saveFriends(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Save Information");
        alert.setContentText("Are you sure you want to save the information?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                FileUtils.writeFriendsFile(friendsList, filePath);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Information saved correctly");
                alert.setHeaderText(null);
                alert.setTitle("Information saved correctly");
                alert.show();

            } catch (FileNotFoundException fnfe) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("File not found");
                alert.setHeaderText(null);
                alert.setTitle("Error while loading file");
                alert.show();
            } catch (IOException ioe) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error while reading file");
                alert.setHeaderText(null);
                alert.setTitle("Error while loading file");
                alert.show();
            } catch (NullPointerException npe) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Not file selected");
                alert.setHeaderText(null);
                alert.setTitle("Not file selected");
                alert.show();
            }
        }
    }

    @FXML
    public void onSearchTextFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            searchFriend(null);
    }

    @FXML
    public void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(statusLabel.getScene().getWindow());
        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a valid file");
            alert.setHeaderText(null);
            alert.setTitle("Error while loading file");
            alert.show();
            return;
        }

        try {
            friendsList = FileUtils.readFriendsFile(file.getAbsolutePath());
            dataTable.setItems(FXCollections.observableArrayList(friendsList));
            statusLabel.setText("Friends loaded!");
            filePath = file.getAbsolutePath();
        } catch (FileNotFoundException fnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File not found");
            alert.setHeaderText(null);
            alert.setTitle("Error while loading file");
            alert.show();
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error while reading file");
            alert.setHeaderText(null);
            alert.setTitle("Error while loading file");
            alert.show();
        }
    }

    @FXML
    public void quit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Quit");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
