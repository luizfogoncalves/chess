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
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    public Queen(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Queen",cor,pos, imageView, gridPane);
        imageView.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {

            EventHandler<MouseEvent> object_clicked = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    for (Node node : gridPane.getChildren()) {
                        if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                            System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                        }
                    }
                    gridPane.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            };

            gridPane.addEventFilter(MouseEvent.MOUSE_PRESSED, object_clicked);

//            gridPane;
        });
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
