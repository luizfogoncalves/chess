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
import javafx.scene.image.Image;
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
public class Bishop extends Piece {

    /**
     * Creates a new instance of Pawn
     */
    public Bishop(Color cor) {
        super("Bishop", cor);
    }

    /**
     * Creates a new instance of Pawn
     */
    public Bishop(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Bishop", cor, pos, imageView, gridPane);

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

    public void setImage(Image image) {
        this.getImageView().setImage(image);

    }

    /* Bispo nao utiliza este metodo */
    public ArrayList<Position> getPath(Position destino, Board board) {
        return null;
    }

    public Color getBoardPositionColor(int x, int y) {
        if (((x + y) % 2) == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Retorna a relac�o de posi��es(caminho) a serem percorridas para alcan�ar
     * o destino
     */
    public ArrayList<Position> getPath(Position destino) {

        ArrayList<Position> path = null;
        Position posicaoAtual = this.getPosition();

        if (!destino.equals(posicaoAtual)) {

            /* determina a cor das casas origem e destino */
            Color c1 = this.getBoardPositionColor(posicaoAtual.getLinha(), posicaoAtual.getColuna());
            Color c2 = this.getBoardPositionColor(destino.getLinha(), destino.getColuna());

            /* verifica se as casas tem a mesma cor */
            if (c1 == c2) {
                int l = Math.abs(destino.getLinha() - posicaoAtual.getLinha());
                int m = Math.abs(destino.getColuna() - posicaoAtual.getColuna());
                /* verifica se � um deslocamento em diagonal */
                if (l == m) {
                    /* - a cada casa q o bispo anda em X, deve andar uma  em Y, seguindo um offset */
                    int offsetX = 1;
                    int offsetY = 1;
                    /* determina a direcao do bispo "cima-baixo" "esquerda-direita" */
                    if (destino.getLinha() < posicaoAtual.getLinha()) {
                        offsetX *= -1;
                    }
                    if (destino.getColuna() < posicaoAtual.getColuna()) {
                        offsetY *= -1;
                    }

                    path = new ArrayList<Position>();
                    int x, y;
                    for (x = posicaoAtual.getLinha() + offsetX, y = posicaoAtual.getColuna() + offsetY;
                            x != destino.getLinha(); x += offsetX, y += offsetY) {
                        path.add(new Position(x, y));
                    }
                    path.add(new Position(x, y));
                }
            }
        }
        return path;
    }

    /* Implementar metodos das jogadas possiveis.. getters and setters etc */
    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return null;
    }

    public ArrayList<Position> showPossibilities(Position actualPosition) {

        ArrayList<Position> list = new ArrayList<Position>();
        Position posicaoAtual = this.getPosition();

        Position[][] possibilites = {
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))}
        };

        TypeMove[][] movements = {
            {TypeMove.LEFT_UP},
            {TypeMove.LEFT_DOWN},
            {TypeMove.RIGHT_UP},
            {TypeMove.RIGHT_DOWN}
        };

        boolean andar = true;
        int cont = 1;

        Position destino;

        for (int i = 0; i < possibilites.length; i++) {
            destino = new Position();
            for (int j = 0; j < possibilites[0].length; j++) {

                int linha = 8 - posicaoAtual.getLinha();
                int coluna = 8 - posicaoAtual.getColuna();

                while (linha > 0 && coluna > 0) {
                    destino.setLinha(destino.getLinha() + movements[i][j].linha());
                    linha = 8 - destino.getLinha();
                    destino.setColuna(destino.getColuna() + movements[i][j].coluna());
                    coluna = 8 - destino.getColuna();

                    if (destino.getColuna() < 8 && destino.getColuna() > -1 && destino.getLinha() < 8 && destino.getLinha() > -1) {

                    }

                    int l = Math.abs(destino.getLinha() - posicaoAtual.getLinha());
                    int m = Math.abs(destino.getColuna() - posicaoAtual.getColuna());
                    /* verifica se � um deslocamento em diagonal */
                    if (l == m) {
                        /* - a cada casa q o bispo anda em X, deve andar uma  em Y, seguindo um offset */
                        int offsetX = 1;
                        int offsetY = 1;
                        /* determina a direcao do bispo "cima-baixo" "esquerda-direita" */
                        if (destino.getLinha() < posicaoAtual.getLinha()) {
                            offsetX *= -1;
                        }
                        if (destino.getColuna() < posicaoAtual.getColuna()) {
                            offsetY *= -1;
                        }

                        int x, y;
                        for (x = posicaoAtual.getLinha() + offsetX, y = posicaoAtual.getColuna() + offsetY;
                                x != destino.getLinha(); x += offsetX, y += offsetY) {
                            list.add(new Position(x, y));
                        }
                        list.add(new Position(x, y));
                    }
                }

            }
        }
        System.out.println(list);
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
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna() + TypeMove.UP.coluna() + TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_UP.linha()), (actualPosition.getColuna() + TypeMove.RIGHT.coluna() + TypeMove.RIGHT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.RIGHT.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna() + TypeMove.RIGHT.coluna() + TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.RIGHT_DOWN.linha()), (actualPosition.getColuna() + TypeMove.DOWN.coluna() + TypeMove.RIGHT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.DOWN.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna() + TypeMove.DOWN.coluna() + TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_DOWN.linha()), (actualPosition.getColuna() + TypeMove.LEFT.coluna() + TypeMove.LEFT_DOWN.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.LEFT.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna() + TypeMove.LEFT.coluna() + TypeMove.LEFT_UP.coluna()))},
            {new Position((actualPosition.getLinha() + TypeMove.UP.linha() + TypeMove.LEFT_UP.linha()), (actualPosition.getColuna() + TypeMove.UP.coluna() + TypeMove.LEFT_UP.coluna()))}
        };

        for (int i = 0; i < possibilites.length; i++) {
            for (int j = 0; j < possibilites[0].length; j++) {
                if (destino.equals(possibilites[i][j])) {
                    if (possibilites[i][j].getColuna() < 8 && possibilites[i][j].getColuna() > -1 && possibilites[i][j].getLinha() < 8 && possibilites[i][j].getLinha() > -1) {
                        if (this.getBoard().isNullPosition(possibilites[i][j])) {
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

}
