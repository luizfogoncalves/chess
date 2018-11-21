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
import xadrez.model.game.Board;
import xadrez.model.game.Chess;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;

/**
 *
 * @author newen
 */
public class Knight extends Piece {
    
    /**
     * Creates a new instance of Knight
     */
    public Knight(Color cor) {
        super("Knight",cor);
    }
    
    /**
     * Creates a new instance of Knight
     */
    public Knight(Color cor, Position pos) {
        super("Knight",cor,pos);
    }
    
    /**
     *  Retorna a relac�o de posi��es(caminho) a
     * serem percorridas para alcan�ar o destino
     */
    public ArrayList <Position> getPath(Position destino){
        
        ArrayList <Position> path = null;
        Position posicaoAtual = this.getPosition();
        if (!destino.equals(posicaoAtual)) {
            
            Color c1 = this.getBoardPositionColor(posicaoAtual.getLinha(),posicaoAtual.getColuna());
            Color c2 = this.getBoardPositionColor(destino.getLinha(),destino.getColuna());
            
            /* o cavalo sempre muda de cor ao se movimentar */
            if (c1 != c2) {
                int difY = Math.abs(destino.getColuna()-posicaoAtual.getColuna());
                int difX = Math.abs(destino.getLinha()-posicaoAtual.getLinha());
                
                /* verifica se esta fazendo um L */
                if (((difX==2)&&(difY==1)) || ((difX==1) && (difY ==2))) {
                    path = new ArrayList <Position> ();
                    path.add(destino);
                }
            }
        }
        return path;
    }
    
    public Color getBoardPositionColor(int x,int y) {
        if (((x+y)%2) == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
    
    /* Cavalo nao utiliza este metodo */
    public ArrayList <Position> getPath(Position destino,Board board) {
        return null;
    }
    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return null;
    }
    
    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    
}
