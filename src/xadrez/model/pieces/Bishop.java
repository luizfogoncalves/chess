/*
 * Pawn.java
 *
 * Created on October 4, 2006, 8:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xadrez.model.pieces;

import java.awt.Color;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import xadrez.model.game.Board;
import xadrez.model.game.Chess;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;

/**
 *
 * @author newen
 */
public class Bishop extends Piece {
    
    /** Creates a new instance of Pawn */
    public Bishop(Color cor) {
        super("Bishop",cor);
    }
    
    /** Creates a new instance of Pawn */
    public Bishop(Color cor, Position pos, ImageView imageView) {
        super("Bishop",cor,pos, imageView);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println("teste");
        });
    }
    
    public void setImage(Image image){
        this.getImageView().setImage(image);
        
    }
    
    
    /* Bispo nao utiliza este metodo */
    public ArrayList <Position> getPath(Position destino,Board board){
        return null;
    }
    
    public Color getBoardPositionColor(int x,int y) {
        if (((x+y)%2) == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
    
    
    /**
     *  Retorna a relac�o de posi��es(caminho) a
     * serem percorridas para alcan�ar o destino
     */
    public ArrayList <Position> getPath(Position destino) {
        
        ArrayList <Position> path = null;
        Position posicaoAtual = this.getPosition();
        
        if (!destino.equals(posicaoAtual)) {
            
            /* determina a cor das casas origem e destino */
            Color c1 = this.getBoardPositionColor(posicaoAtual.getLinha(),posicaoAtual.getColuna());
            Color c2 = this.getBoardPositionColor(destino.getLinha(),destino.getColuna());
            
            /* verifica se as casas tem a mesma cor */
            if (c1 == c2) {
                int l = Math.abs(destino.getLinha()-posicaoAtual.getLinha());
                int m = Math.abs(destino.getColuna()-posicaoAtual.getColuna());
                /* verifica se � um deslocamento em diagonal */
                if (l == m) {
                    /* - a cada casa q o bispo anda em X, deve andar uma  em Y, seguindo um offset */
                    int offsetX = 1;
                    int offsetY = 1;
                    /* determina a direcao do bispo "cima-baixo" "esquerda-direita" */
                    if (destino.getLinha()< posicaoAtual.getLinha()) offsetX *= -1;
                    if (destino.getColuna()< posicaoAtual.getColuna()) offsetY *= -1;
                    
                    path = new ArrayList <Position> ();
                    int x, y;
                    for (x = posicaoAtual.getLinha()+offsetX, y = posicaoAtual.getColuna()+offsetY;
                    x != destino.getLinha(); x+=offsetX, y+=offsetY) {
                        path.add(new Position(x,y));
                    }
                    path.add(new Position(x,y));
                }
            }
        }
        return path;
    }
    
    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    
    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return null;
    }
    
}
