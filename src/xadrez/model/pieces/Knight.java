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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.utils.TypeMove;
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
    public Knight(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Knight",cor,pos, imageView, gridPane);
        imageView.setPickOnBounds(true);
        imageView.setOnMouseClicked((MouseEvent e) -> {
            if (!this.isPieceRemoved()) {
                for (Position position : showPossibilities(this.getPosition())) {
                    Pane pane = (Pane) getNodeByRowColumnIndex(position.getLinha(), position.getColuna(), gridPane);
                    pane.getStyleClass().add("border");
                }
                EventHandler<MouseEvent> object_clicked = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {

                        getEventClickGrid(gridPane, e, imageView);
                        removeClassBorder(gridPane);
                        gridPane.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                    }
                };

                gridPane.addEventFilter(MouseEvent.MOUSE_PRESSED, object_clicked);
            }

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
    
    public ArrayList<Position> showPossibilities(Position actualPosition) {

        ArrayList<Position> list = new ArrayList<Position>();

        Position[][] possibilites = {
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna()+ TypeMove.UP.coluna()+ TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna()+ TypeMove.RIGHT.coluna()+ TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.RIGHT.coluna()+ TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.DOWN.coluna()+ TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.DOWN.coluna()+ TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.LEFT.coluna()+ TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna()+ TypeMove.LEFT.coluna()+ TypeMove.LEFT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna()+ TypeMove.UP.coluna()+ TypeMove.LEFT_UP.coluna()))}
        };
        
        for (int i = 0; i < possibilites.length; i++) {
            for (int j = 0; j < possibilites[0].length; j++) {
                if (possibilites[i][j].getColuna() < 8 && possibilites[i][j].getColuna() > -1 && possibilites[i][j].getLinha()< 8 && possibilites[i][j].getLinha() > -1) {
                    if (this.getBoard().isNullPosition(possibilites[i][j]) ) {
                        list.add(possibilites[i][j]);
                    } else if (this.getBoard().getBoard()[possibilites[i][j].getLinha()][possibilites[i][j].getColuna()].getColor() != this.getColor()) {
                        list.add(possibilites[i][j]);
                    }
                }
            }
        }

        return list;
    }
    
    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (node instanceof Pane) {
                if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column && node.getId() == null) {
                    result = node;
                    break;
                }
            }
        }

        return result;
    }
    
    public Node removeClassBorder(GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (node instanceof Pane) {
                node.getStyleClass().clear();
            }
        }

        return result;
    }

    public void getEventClickGrid(GridPane gridPane, MouseEvent e, ImageView imageView) {
        for (Node pane : gridPane.getChildren()) {
            if (pane instanceof Pane) {
                if (pane.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {

                    if (this.checkDestiny(this.getPosition(), new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)))) {
                        if (!this.getBoard().isNullPosition(new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)))) {
                            if (this.getBoard().getBoard()[GridPane.getRowIndex(pane)][GridPane.getColumnIndex(pane)].getColor() != this.getColor()) {
                                gridPane.getChildren().remove(getImageView(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), gridPane));
                                this.getBoard().getBoard()[GridPane.getRowIndex(pane)][GridPane.getColumnIndex(pane)].setPieceRemoved(true);
                            }
                        }
                        gridPane.getChildren().remove(imageView);
                        gridPane.add(imageView, GridPane.getColumnIndex(pane), GridPane.getRowIndex(pane));
                        this.getBoard().setNullPosition(this.getPosition());
                        this.getBoard().setPosition(this, new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
                        this.setPosition(new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
                    }

                    break;
                }
            }
        }
    }
    
    public boolean checkDestiny(Position actualPosition, Position destino) {

        Position[][] possibilites = {
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna()+ TypeMove.UP.coluna()+ TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna()+ TypeMove.RIGHT.coluna()+ TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.RIGHT.coluna()+ TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.DOWN.coluna()+ TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.DOWN.coluna()+ TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna()+ TypeMove.LEFT.coluna()+ TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna()+ TypeMove.LEFT.coluna()+ TypeMove.LEFT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna()+ TypeMove.UP.coluna()+ TypeMove.LEFT_UP.coluna()))}
        };
        
        for (int i = 0; i < possibilites.length; i++) {
            for (int j = 0; j < possibilites[0].length; j++) {
                if (destino.equals(possibilites[i][j])) {
                    if (possibilites[i][j].getColuna() < 8 && possibilites[i][j].getColuna() > -1 && possibilites[i][j].getLinha()< 8 && possibilites[i][j].getLinha() > -1) {
                        if (this.getBoard().isNullPosition(possibilites[i][j]) ) {
                            return true;
                        } else if (this.getBoard().getBoard()[possibilites[i][j].getLinha()][possibilites[i][j].getColuna()].getColor() != this.getColor()) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    
    public Node getImageView(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (node instanceof ImageView) {

                if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                    result = node;
                    break;
                }
            }
        }

        return result;
    }
    
    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    
}
