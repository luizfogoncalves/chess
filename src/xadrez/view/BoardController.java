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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import xadrez.model.game.Board;
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
    private ImageView peao1Jogador1, peao2Jogador1, peao3Jogador1, peao4Jogador1, peao5Jogador1, peao6Jogador1, peao7Jogador1, peao8Jogador1;

    @FXML
    private ImageView peao1Jogador2, peao2Jogador2, peao3Jogador2, peao4Jogador2, peao5Jogador2, peao6Jogador2, peao7Jogador2, peao8Jogador2;

    @FXML
    private ImageView bispo1Jogador1, bispo2Jogador1, reiJogador1, rainhaJogador1, cavalo2Jogador1, cavalo1Jogador1, torre2Jogador1, torre1Jogador1;

    @FXML
    private ImageView bispo1Jogador2, bispo2Jogador2, reiJogador2, rainhaJogador2, cavalo2Jogador2, cavalo1Jogador2, torre1Jogador2, torre2Jogador2;

    @FXML
    private ColumnConstraints coluna1;

    @FXML // fx:id="nameJogador1"
    private Text nameJogador1;

    @FXML
    private GridPane gridPane;

    private Board board;
    private ArrayList<ImageView> peaoBranco = new ArrayList<ImageView>();
    private ArrayList<ImageView> peaoPreto = new ArrayList<ImageView>();
    private Pawn pawn;
    private Position position;
    private ArrayList<Piece> arrayPieces = new ArrayList<Piece>();
    private Piece[][] matrizPecas = new Piece[8][8];

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.board = new Board();

//        GetNodeInGrid()
//        gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            GridPane source = (GridPane) e.getSource();
//            System.out.println(GridPane.getColumnIndex(torre1Jogador1));
//        });
        System.out.println(this.gridPane.getChildren());
        // TODO

        this.loadArrayImage();

        this.createPeoes();

        // Cria as torres e o coloca em uma posi��o, a torre tbm recebe a sua posi��o no board
        this.createPieces(new Rook(Color.WHITE, new Position(0, 0), this.torre1Jogador1, this.gridPane));
        this.createPieces(new Rook(Color.WHITE, new Position(0, 7), this.torre2Jogador1, this.gridPane));
        this.createPieces(new Rook(Color.BLACK, new Position(7, 0), this.torre1Jogador2, this.gridPane));
        this.createPieces(new Rook(Color.BLACK, new Position(7, 7), this.torre2Jogador2, this.gridPane));

        // Cria os bispos e o coloca em uma posi��o, o bispo tbm recebe a sua posi��o no board
        this.createPieces(new Bishop(Color.WHITE, new Position(0, 2), this.bispo1Jogador1, this.gridPane));
        this.createPieces(new Bishop(Color.WHITE, new Position(0, 5), this.bispo2Jogador1, this.gridPane));
        this.createPieces(new Bishop(Color.BLACK, new Position(7, 2), this.bispo1Jogador2, this.gridPane));
        this.createPieces(new Bishop(Color.BLACK, new Position(7, 5), this.bispo2Jogador2, this.gridPane));

        // Cria os cavalos e o coloca em uma posi��o, o cavalo tbm recebe a sua posi��o no board
        this.createPieces(new Knight(Color.WHITE, new Position(0, 1), this.cavalo1Jogador1, this.gridPane));
        this.createPieces(new Knight(Color.WHITE, new Position(0, 6), this.cavalo2Jogador1, this.gridPane));
        this.createPieces(new Knight(Color.BLACK, new Position(7, 1), this.cavalo1Jogador2, this.gridPane));
        this.createPieces(new Knight(Color.BLACK, new Position(7, 6), this.cavalo2Jogador2, this.gridPane));

        // Cria as Rainhas e as coloca em uma posi��o, a rainha tbm recebe a sua posi��o no board
        this.createPieces(new Queen(Color.WHITE, new Position(0, 3), this.rainhaJogador1, this.gridPane));
        this.createPieces(new Queen(Color.BLACK, new Position(7, 4), this.rainhaJogador2, this.gridPane));

        // Cria os Reis e os coloca em uma posi��o, o Rei tbm recebe a sua posi��o no board
        this.createPieces(new King(Color.WHITE, new Position(0, 4), this.reiJogador1, this.gridPane));
        this.createPieces(new King(Color.BLACK, new Position(7, 3), this.reiJogador2, this.gridPane));
        
        this.board.setBoard(this.matrizPecas);

        this.addBoard();

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

    public void createPeoes() {
        //cria pe�es
        for (int i = 0; i < 8; i++) {

            // Cria um pe�o branco e o coloca em uma posi��o, o pe�o tbm recebe a sua posi��o no board
            this.position = new Position(1, i);
            this.pawn = new Pawn(Color.WHITE, this.position, this.peaoBranco.get(i), this.gridPane);
            matrizPecas[1][i] = this.pawn;

            this.arrayPieces.add(this.pawn);

            // Cria um pe�o preto e o coloca em uma posi��o, o pe�o tbm recebe a sua posi��o no board
            this.position = new Position(6, i);
            this.pawn = new Pawn(Color.BLACK, this.position, this.peaoPreto.get(i), this.gridPane);
            matrizPecas[6][i] = this.pawn;

            this.arrayPieces.add(this.pawn);
        }
    }

    public void createPieces(Piece piece) {
        matrizPecas[piece.getPosition().getLinha()][piece.getPosition().getColuna()] = piece;
        this.arrayPieces.add(piece);
    }

    public void addBoard() {
        for (int i = 0; i < this.arrayPieces.size(); i++) {
            this.arrayPieces.get(i).setBoard(this.board);
        }
    }

}
