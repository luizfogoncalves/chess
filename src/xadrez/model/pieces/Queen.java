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
public class Queen extends Piece {
    
    /** Creates a new instance of Queen */
    public Queen(Color cor) {
        super("Queen",cor);
    }
    
    /** Creates a new instance of Queen */
    public Queen(Color cor, Position pos) {
        super("Queen",cor,pos);
    }
    
    /**
     *  Retorna a relac�o de posi��es(caminho) a
     * serem percorridas para alcan�ar o destino
     */
    public ArrayList <Position> getPath(Position destino){
        
        ArrayList <Position> path = null;
        Position posicaoAtual = this.getPosition();
        
        if (!destino.equals(posicaoAtual)) {
            /* verifica se utilizar� o movimento de torre */
            if ((destino.getColuna()== posicaoAtual.getColuna()) ||
                    (destino.getLinha()== posicaoAtual.getLinha()) ){
                Rook r = (new Rook(Color.PINK));
                r.setPosition(this.getPosition());
                path = r.getPath(destino);
            } else {
                /* ira utilizar o movimento de bispo */
                Bishop b = (new Bishop(Color.PINK));
                b.setPosition(this.getPosition());
                path = b.getPath(destino);
            }
        }
        
        return path;
    }
    
    /* Rainha nao utiliza este metodo */
    public ArrayList <Position> getPath(Position destino,Board board) {
        return null;
    }
    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return null;
    }
    
    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    
}
