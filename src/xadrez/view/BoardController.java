/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.view;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;
import xadrez.model.pieces.Bishop;
import xadrez.model.pieces.King;
import xadrez.model.pieces.Knight;
import xadrez.model.pieces.Pawn;
import xadrez.model.pieces.Queen;
import xadrez.model.pieces.Rook;

/**
 * FXML Controller class
 *
 * @author luizj
 */
public class BoardController implements Initializable {

    @FXML 
    private ImageView peao1Jogador1;

    @FXML 
    private ImageView peao2Jogador1;

    @FXML 
    private ImageView peao3Jogador1;

    @FXML 
    private ImageView peao4Jogador1;

    @FXML 
    private ImageView peao5Jogador1;

    @FXML 
    private ImageView peao6Jogador1;

    @FXML 
    private ImageView peao7Jogador1;

    @FXML 
    private ImageView peao8Jogador1;

    @FXML 
    private ImageView peao1Jogador2;

    @FXML 
    private ImageView peao2Jogador2;

    @FXML 
    private ImageView peao3Jogador2;

    @FXML 
    private ImageView peao4Jogador2;

    @FXML 
    private ImageView peao5Jogador2;

    @FXML 
    private ImageView peao6Jogador2;

    @FXML 
    private ImageView peao7Jogador2;

    @FXML 
    private ImageView peao8Jogador2;
    
    @FXML 
    private ImageView bispo1Jogador1;
    
    @FXML 
    private ImageView bispo2Jogador1;
    
    @FXML 
    private ImageView reiJogador1;
    
    @FXML 
    private ImageView rainhaJogador1;
    
    @FXML 
    private ImageView cavalo2Jogador1;
    
    @FXML 
    private ImageView cavalo1Jogador1;
    
    @FXML 
    private ImageView torre2Jogador1;
    
    @FXML 
    private ImageView torre1Jogador1;
    
    @FXML 
    private ImageView bispo1Jogador2;
    
    @FXML 
    private ImageView bispo2Jogador2;
    
    @FXML 
    private ImageView reiJogador2;
    
    @FXML 
    private ImageView rainhaJogador2;
    
    @FXML 
    private ImageView cavalo2Jogador2;
    
    @FXML 
    private ImageView cavalo1Jogador2;
    
    @FXML 
    private ImageView torre1Jogador2;
    
    @FXML 
    private ImageView torre2Jogador2;
    
    

    @FXML // fx:id="nameJogador1"
    private Text nameJogador1;

    @FXML
    private GridPane gridPane;

    private ArrayList<ImageView> peaoBranco = new ArrayList<ImageView>();
    private ArrayList<ImageView> peaoPreto = new ArrayList<ImageView>();

    private Bishop bishop;
    private Pawn pawn;
    private Knight knight;
    private Rook rook;
    private Position position;
    private Queen queen;
    private King king;
    private ArrayList<Piece> arrayPieces = new ArrayList<Piece>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(gridPane);

//        gridPane.add(torre1Jogador1, 4, 4);

        loadArrayImage();

        //cria pe�es
        for (int i = 0; i < 8; i++) {
            
            // Cria um pe�o branco e o coloca em uma posi��o, o pe�o tbm recebe a sua posi��o no board
            this.position = new Position(0, i);
            this.pawn = new Pawn(Color.WHITE, this.position, this.peaoBranco.get(i));

            this.arrayPieces.add(this.pawn);

            // Cria um pe�o preto e o coloca em uma posi��o, o pe�o tbm recebe a sua posi��o no board
            this.position = new Position(6, i);
            this.pawn = new Pawn(Color.BLACK, this.position, this.peaoPreto.get(i));

            this.arrayPieces.add(this.pawn);
        }
        // Cria as torres e o coloca em uma posi��o, a torre tbm recebe a sua posi��o no board
        this.position = new Position(0, 0);
        this.rook = new Rook(Color.WHITE, this.position, this.torre1Jogador2);

        this.arrayPieces.add(this.rook);

        this.position = new Position(0, 7);
        this.rook = new Rook(Color.WHITE, this.position, this.torre2Jogador2);

        this.arrayPieces.add(this.rook);

        this.position = new Position(7, 0);
        this.rook = new Rook(Color.BLACK, this.position, this.torre1Jogador1);

        this.arrayPieces.add(this.rook);

        this.position = new Position(7, 7);
        this.rook = new Rook(Color.BLACK, this.position, this.torre2Jogador1);

        this.arrayPieces.add(this.rook);

        // Cria os bispos e o coloca em uma posi��o, o bispo tbm recebe a sua posi��o no board
        this.position = new Position(0, 2);
        this.bishop = new Bishop(Color.WHITE, this.position, this.bispo1Jogador2);

        this.arrayPieces.add(this.bishop);

        this.position = new Position(0, 5);
        this.bishop = new Bishop(Color.WHITE, this.position, this.bispo2Jogador2);

        this.arrayPieces.add(this.bishop);

        this.position = new Position(7, 2);
        this.bishop = new Bishop(Color.BLACK, this.position, this.bispo1Jogador1);

        this.arrayPieces.add(this.bishop);

        this.position = new Position(7, 5);
        this.bishop = new Bishop(Color.BLACK, this.position, this.bispo2Jogador1);

        this.arrayPieces.add(this.bishop);

        // Cria os cavalos e o coloca em uma posi��o, o cavalo tbm recebe a sua posi��o no board
        this.position = new Position(0, 1);
        this.knight = new Knight(Color.WHITE, this.position, this.cavalo1Jogador2);

        this.arrayPieces.add(this.knight);

        this.position = new Position(0, 6);
        this.knight = new Knight(Color.WHITE, this.position, this.cavalo2Jogador2);

        this.arrayPieces.add(this.knight);

        this.position = new Position(7, 1);
        this.knight = new Knight(Color.BLACK, this.position, this.cavalo1Jogador1);

        this.arrayPieces.add(this.knight);

        this.position = new Position(7, 6);
        this.knight = new Knight(Color.BLACK, this.position, this.cavalo2Jogador1);

        this.arrayPieces.add(this.knight);
      
        // Cria as Rainhas e as coloca em uma posi��o, a rainha tbm recebe a sua posi��o no board
        this.position = new Position(0, 3);
        this.queen = new Queen(Color.WHITE, this.position, this.rainhaJogador2);

        arrayPieces.add(this.queen);

        this.position = new Position(7, 3);
        this.queen = new Queen(Color.BLACK, this.position, this.rainhaJogador2);

        this.arrayPieces.add(this.queen);
        // Cria os Reis e os coloca em uma posi��o, o Rei tbm recebe a sua posi��o no board
        this.position = new Position(0, 4);
        this.king = new King(Color.WHITE, this.position, this.reiJogador2);

        this.arrayPieces.add(this.king);

        this.position = new Position(7, 4);
        this.king = new King(Color.BLACK, this.position,this. reiJogador1);

        this.arrayPieces.add(this.king);

//        jogador1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            System.out.println("teste");
//        });

    }
    
    public void loadArrayImage() {
        this.peaoBranco.add(this.peao1Jogador1);
        this.peaoBranco.add(this.peao2Jogador1);
        this.peaoBranco.add(this.peao3Jogador1);
        this.peaoBranco.add(this.peao4Jogador1);
        this.peaoBranco.add(this.peao5Jogador1);
        this.peaoBranco.add(this.peao6Jogador1);
        this.peaoBranco.add(this.peao7Jogador1);
        this.peaoBranco.add(this.peao8Jogador1);
        
        this.peaoPreto.add(this.peao1Jogador2);
        this.peaoPreto.add(this.peao2Jogador2);
        this.peaoPreto.add(this.peao3Jogador2);
        this.peaoPreto.add(this.peao4Jogador2);
        this.peaoPreto.add(this.peao5Jogador2);
        this.peaoPreto.add(this.peao6Jogador2);
        this.peaoPreto.add(this.peao7Jogador2);
        this.peaoPreto.add(this.peao8Jogador2);
        
    }

}
