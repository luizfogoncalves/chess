/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author luizj
 */
public class Xadrez extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
        stage1.setScene(new Scene(root));
        stage1.setTitle("Personal Settings");
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
