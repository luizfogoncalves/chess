
package xadrez.model.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import xadrez.model.game.Piece;

/**
 *
 * @author newen
 */
public abstract class Piece {
    
    /* Nome da peca (Ex: Pawn, King, etc ) */
    String name;
    
    /* Cor da peca (preto/branco) */
    Color color;
    
    /* Position peca (posicao no tabuleiro) */
    Position position;
    
    /* Image */
    private ImageView imageView;
    
    /**
     * 
     * Creates a new instance of Piece
     */
    public Piece(String nome, Color cor) {
       this( nome, cor, new Position(), new ImageView());            
    }
    
    /**
     * 
     * Creates a new instance of Piece
     */
    public Piece(String nome, Color cor, Position pos, ImageView imageView) {
        this.name = nome;
        this.color = cor;
        this.position = pos; 
        this.imageView = imageView;
    }
    
    /** 
     *  Retorna a relac�o de posi��es(caminho) a 
     * serem percorridas para alcan�ar o destino 
     */
    public abstract ArrayList <Position> getPath(Position destino);
    public abstract ArrayList <Position> getPath(Position destino,Board board);
    public abstract ArrayList <Position> getPath(Position destino,Chess chess);
       
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
    


    

}
