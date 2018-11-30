
package xadrez.model.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import xadrez.model.game.Piece;

public abstract class Piece {
    
    /* Nome da peca (Ex: Pawn, King, etc ) */
    String name;
    
    /* Cor da peca (preto/branco) */
    Color color;
    
    /* Position peca (posicao no tabuleiro) */
    Position position;
    
    /* Image */
    private ImageView imageView;
    
    /* Matriz das peças */
    private GridPane gridPane;
    
    private Board board;
    
    private boolean pieceRemoved = false;
    
    /**
     * 
     * Creates a new instance of Piece
     */
    public Piece(String nome, Color cor) {
       this( nome, cor, new Position(), new ImageView(), new GridPane());            
    }
    
    /**
     * 
     * Creates a new instance of Piece
     */
    public Piece(String nome, Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        this.name = nome;
        this.color = cor;
        this.position = pos; 
        this.imageView = imageView;
        this.gridPane = gridPane;
    }
       
    /**
     * 
     * Define a posi��o da pe�a no tabuleiro
     */ 
    public void setPosition(Position pos) {
        this.position = pos;
    }

    /**
     * 
     * Pega a posi��o da pe�a no tabuleiro
     */   
    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the imageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * @param imageView the imageView to set
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the piceRemoved
     */
    public boolean isPieceRemoved() {
        return pieceRemoved;
    }

    /**
     * @param piceRemoved the piceRemoved to set
     */
    public void setPieceRemoved(boolean pieceRemoved) {
        this.pieceRemoved = pieceRemoved;
    }
    

    
    

}
