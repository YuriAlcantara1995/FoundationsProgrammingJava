package space.harbour.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import space.harbour.tictactoe.util.Resources;
import space.harbour.tictactoe.domain.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class TicTacToeController implements Initializable {

    public ImageView tile00;
    public ImageView tile01;
    public ImageView tile02;
    public ImageView tile10;
    public ImageView tile11;
    public ImageView tile12;
    public ImageView tile20;
    public ImageView tile21;
    public ImageView tile22;
    public Board board;

    public TicTacToeController() {
        board = new Board();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Specific initializer executed after UI componentes instantiation
    }

    public boolean checkStatus(String currentPlaying) {
        if (board.checkWinner()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Game finished");
            if(currentPlaying == "O") {
                alert.setContentText("Harbour Space is the winner!");
            } else {
                alert.setContentText("You are the winner, congratulation!");
            }
            alert.showAndWait();
            System.exit(0);
            return true;
        }
        if(!board.isMovesLeft()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Game finished");
            alert.setContentText("It's a draw!");
            alert.showAndWait();
            System.exit(0);
            return true;
        }
        return false;
    }

    public void clickOnSlot(int slot) {
        if (!board.isEmpty(slot)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Invalid play");
            alert.setContentText("Invalid play");
            alert.show();
        } else {
            board.play("X", slot);
            setImage(slot, "X");
            if(checkStatus("X"))
                return;
            Move bestMove = board.findBestMove();
            int bestSlot = bestMove.getRow() * 3 + bestMove.getColumn() + 1;
            board.play("O", bestSlot);
            setImage(bestSlot, "O");
            if(checkStatus("O"))
                return;
            System.out.println(board.toString());
        }
    }

    public void setImage(int slot, String turn) {
        switch (slot) {
            case 1:
                tile00.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 2:
                tile01.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 3:
                tile02.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 4:
                tile10.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 5:
                tile11.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 6:
                tile12.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 7:
                tile20.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 8:
                tile21.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            case 9:
                tile22.setImage(new Image(Resources.getImage(turn + ".png")));
                break;
            default:
        }
    }

    @FXML
    public void tile00MouseClicked(MouseEvent event) {
        clickOnSlot(1);
    }

    @FXML
    public void tile01MouseClicked(MouseEvent event) {
        clickOnSlot(2);
    }

    @FXML
    public void tile02MouseClicked(MouseEvent event) {
        clickOnSlot(3);
    }

    @FXML
    public void tile10MouseClicked(MouseEvent event) {
        clickOnSlot(4);
    }

    @FXML
    public void tile11MouseClicked(MouseEvent event) {
        clickOnSlot(5);
    }

    @FXML
    public void tile12MouseClicked(MouseEvent event) {
        clickOnSlot(6);
    }

    @FXML
    public void tile20MouseClicked(MouseEvent event) {
        clickOnSlot(7);
    }

    @FXML
    public void tile21MouseClicked(MouseEvent event) {
        clickOnSlot(8);
    }

    @FXML
    public void tile22MouseClicked(MouseEvent event) {
        clickOnSlot(9);
    }

}
