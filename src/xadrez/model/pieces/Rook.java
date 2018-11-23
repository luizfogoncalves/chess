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
public class Rook extends Piece {
    
    /* rook attributes */
    boolean notMovedYet;
    
    /** Creates a new instance of Pawn */
    public Rook(Color cor) {
        super("Rook",cor);
    }
    
    /** Creates a new instance of Pawn */
    public Rook(Color cor, Position pos, ImageView imageView) {
        super("Rook",cor,pos, imageView);
        this.setNotMovedYet(true);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println("teste");
        });
    }
    
    public boolean isNotMovedYet() {
        return notMovedYet;
    }
    
    public void setNotMovedYet(boolean notMovedYet) {
        this.notMovedYet = notMovedYet;
    }
    
    
    /**
     *  Retorna a relac�o de posi��es(caminho) a
     * serem percorridas para alcan�ar o destino
     */
    public ArrayList <Position> getPath(Position destino){
        ArrayList <Position> path = null;
        Position posicaoAtual = this.getPosition();
        if (!destino.equals(posicaoAtual)) {
            int offset = 1;            
            /* verifica se ele nao esta querendo se mover para a mesma
             * posicao atual (no caso da verificacao de xeque-mate */
            
            /* movimento vertical da torre */
            if (destino.getColuna()== posicaoAtual.getColuna()) {
                /* cria o caminho */
                path = new ArrayList <Position> ();
                
                /* determina a direcao a ser percorrida */
                if (destino.getLinha()< posicaoAtual.getLinha()) offset = -1;
                
                /* adiciona as posicoes do caminho */
                int x;
                for (x=posicaoAtual.getLinha()+offset; x != destino.getLinha(); x+=offset) {
                    path.add(new Position(x,destino.getColuna()));
                }
                path.add(new Position(x,destino.getColuna()));
            } else if (destino.getLinha()== posicaoAtual.getLinha()) {
                /* movimento horizontal da torre */
                /* cria o caminho */
                path = new ArrayList <Position> ();
                
                /* determina a direcao a ser percorrida */
                if (destino.getColuna()< posicaoAtual.getColuna()) offset*= -1;
                
                /* adiciona as posicoes do caminho */
                int y;
                for (y=posicaoAtual.getColuna()+offset; y != destino.getColuna(); y+=offset) {
                    path.add(new Position(destino.getLinha(),y));
                }
                path.add(new Position(destino.getLinha(),y));
            }
        }
        
        if (path != null) {
            this.setNotMovedYet(false);
        }
        return path;
    }
    
    /* Torre nao utiliza este metodo */
    public ArrayList <Position> getPath(Position destino,Board board) {
        return null;
    }
    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return null;
    }
    
    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    
}
