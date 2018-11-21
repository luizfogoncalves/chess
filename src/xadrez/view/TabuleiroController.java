/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.view;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author luizj
 */
public class TabuleiroController implements Initializable {
    
    @FXML // fx:id="bispo1Jogador1"
    private ImageView jogador1;
    
    @FXML // fx:id="nameJogador1"
    private Text nameJogador1;
    
    @FXML
    private ImageView torre1Jogador1;
    
    @FXML
    private GridPane gridPane;
    
    private Object pieces;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        System.out.println(gridPane);
        
        gridPane.add(torre1Jogador1, 4, 4);
        
        jogador1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println("teste");
        });

    }   
    
}

