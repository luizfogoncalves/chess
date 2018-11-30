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
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.utils.TypeMove;
import xadrez.model.game.Chess;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;
import xadrez.model.game.Board;

/**
 *
 * @author newen
 */
public class Pawn extends Piece {

    /* Pawn attributes */
    Position posicaoInicial; // atributo que guarda o local de criacao do peao
    // pois nesta posicao, ele pode pular 2 casas

    /**
     * Creates a new instance of Pawn
     */
    public Pawn(Color cor) {
        super("Pawn", cor);
    }

    /**
     * Creates a new instance of Pawn
     */
    public Pawn(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Pawn", cor, pos, imageView, gridPane);
        this.setPosicaoInicial(pos);

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

    /* Peao nao utiliza este metodo */
    public ArrayList<Position> getPath(Position destino) {
        return null;
    }

    /**
     * Retorna a relac�o de posi��es(caminho) a serem percorridas para alcan�ar
     * o destino
     */
    public ArrayList<Position> getPath(Position destino, Board board) {

        ArrayList path = new ArrayList<Position>();
        Position posicaoAtual = this.getPosition();

        if (!destino.equals(posicaoAtual)) {
            int offset = 1;

            if (this.getColor() == Color.WHITE) {
                offset *= -1; // passa a andar em direcao para cima do tabuleiro
            }
            /* -> testa se a posicao destino pode ser alcancada a partir da origem
         *    - se o peao esta na posicao original (ainda nao foi movido)
         *      entao ele pode pular 2 casas
         *    - ou a posicao destino esta no msm X que a posicao origem
         *    - ou (no caso de comer uma outra peca), a posicao destino pode ser
         *    - 1 casa acima e 1 casa ao lado (desde que exista uma peca em destino)
         * -> pecas bracas andam para cima (indice menor), pretas o contrario
             */

            if ((posicaoAtual.getColuna() == destino.getColuna()) && board.isNullPosition(destino)) {
                /* movimento vertical, nao vai comer nenhuma peca */
                if (posicaoAtual.equals(this.getPosicaoInicial())) {
                    /* ainda nao se moveu, pode entao pular 2 casas (se quiser), basta verificar se
                 * a posicao destino � valida e adicionar no caminho */
                    if (destino.getLinha() == posicaoAtual.getLinha() + (offset * 2)) {
                        /* o jogador deseja pular 2 casas */
                        path = new ArrayList<Position>();
                        Position pos = new Position(posicaoAtual.getLinha() + offset, posicaoAtual.getColuna());
                        path.add(pos);
                        path.add(destino);
                    } else if (destino.getLinha() == posicaoAtual.getLinha() + offset) {
                        /* o jogador deseja pular somente 1 casa */
                        path = new ArrayList<Position>();
                        path.add(destino);
                    }
                } else /* peao ja foi movido, entao pode pular somente 1 casa */ if (destino.getLinha() == posicaoAtual.getLinha() + offset) {
                    /* verificando se a posicao clicada corresponde a um possivel movimento */
                    path = new ArrayList<Position>();
                    path.add(destino);
                }
            } else /* movimento em diagonal, quer comer uma outra peca */ /* so podera se movimentar 1 casa em X e uma casa em Y */ if ((destino.getColuna() == posicaoAtual.getColuna() + 1) || (destino.getColuna() == posicaoAtual.getColuna() - 1)) {
                /* a posicao clicada (destino) esta 1 casa de offset em X */
                if (destino.getLinha() == posicaoAtual.getLinha() + offset) {

                    /* a posicao clicada (destino) esta 1 casa de offset em Y, entao
                     * tem q verificar se existe 1 peca de outra cor na posicao destino
                     * caso contrario, n � possivel fazer o movimento, e path = null
                     */
                    Piece adv;
                    if (((adv = board.getPieceAtPosition(destino)) != null) && (adv.getColor() != this.getColor())) {
                        path = new ArrayList<Position>();
                        path.add(destino);
                    }
                }
            }
        }
        return path;
    }

    public ArrayList<Position> showPossibilities(Position actualPosition) {

        int offset = 1;

        if (this.getColor() == Color.WHITE) {
            offset *= -1; // passa a andar em direcao para cima do tabuleiro
        }
        ArrayList<Position> list = new ArrayList<Position>();
        Position possiblePositon;
        int moveTwoColumn = actualPosition.getColuna() + (TypeMove.UP.coluna() * 2 * offset);
        int moveTowRow = actualPosition.getLinha() + (TypeMove.UP.linha() * 2) * offset;
        int moveOneRow = actualPosition.getLinha() + TypeMove.UP.linha() * offset;
        int moveOneColumn = actualPosition.getColuna() + TypeMove.UP.coluna() * offset;
        if (actualPosition.equals(this.getPosicaoInicial())) {
            if (this.getBoard().isNullPosition(new Position(moveOneRow, moveOneColumn))) {
                possiblePositon = new Position();
                possiblePositon.setLinha(moveOneRow);
                possiblePositon.setColuna(moveOneColumn);
                list.add(possiblePositon);
            }
            if (this.getBoard().isNullPosition(new Position(moveOneRow, moveOneColumn))) {
                possiblePositon = new Position();
                possiblePositon.setLinha(moveTowRow);
                possiblePositon.setColuna(moveTwoColumn);
                list.add(possiblePositon);
            }
        } else if (this.getBoard().isNullPosition(new Position(moveOneRow, moveOneColumn)) && moveOneRow < 8 && moveOneRow > -1) {
            possiblePositon = new Position();
            possiblePositon.setLinha(moveOneRow);
            possiblePositon.setColuna(moveOneColumn);
            list.add(possiblePositon);
        }
        int enemyRowRight = actualPosition.getLinha() + TypeMove.RIGHT_UP.linha() * offset;
        int enemyColumnRight = actualPosition.getColuna() + TypeMove.RIGHT_UP.coluna() * offset;
        int enemyRowLeft = actualPosition.getLinha() + TypeMove.LEFT_UP.linha() * offset;
        int enemyColumnLeft = actualPosition.getColuna() + TypeMove.LEFT_UP.coluna() * offset;
        if (enemyColumnRight < 8 && enemyRowRight < 8) {
            if (!this.getBoard().isNullPosition(new Position(enemyRowRight, enemyColumnRight))) {
                if (this.getBoard().getBoard()[enemyRowRight][enemyColumnRight].getColor() != this.getColor()) {
                    possiblePositon = new Position();
                    possiblePositon.setLinha(enemyRowRight);
                    possiblePositon.setColuna(enemyColumnRight);
                    list.add(possiblePositon);
                }
            }
        }
        if (enemyColumnLeft < 8 && enemyRowLeft < 8 && enemyColumnLeft > -1 && enemyRowLeft > -1) {
            if (!this.getBoard()
                    .isNullPosition(new Position(enemyRowLeft, enemyColumnLeft))) {
                if (this.getBoard().getBoard()[enemyRowLeft][enemyColumnLeft].getColor() != this.getColor()) {

                    possiblePositon = new Position();
                    possiblePositon.setLinha((actualPosition.getLinha() + TypeMove.LEFT_UP.linha() * offset));
                    possiblePositon.setColuna((actualPosition.getColuna() + TypeMove.LEFT_UP.coluna() * offset));
                    list.add(possiblePositon);
                }
            }

        }
        return list;
    }

    public boolean checkDestiny(Position actualPosition, Position destino) {

        int offset = 1;

        if (this.getColor() == Color.WHITE) {
            offset *= -1; // passa a andar em direcao para cima do tabuleiro
        }
        if (destino.equals(new Position(actualPosition.getLinha() + TypeMove.UP.linha() * offset, actualPosition.getColuna() + TypeMove.UP.coluna() * offset))) {
            if (this.getBoard().isNullPosition(new Position(actualPosition.getLinha() + TypeMove.UP.linha() * offset, actualPosition.getColuna() + TypeMove.UP.coluna() * offset))) {
                return true;
            }
            return false;
        } else if (destino.equals(new Position(actualPosition.getLinha() + (TypeMove.UP.linha() * 2) * offset, actualPosition.getColuna() + (TypeMove.UP.coluna() * 2 * offset))) && actualPosition.equals(this.getPosicaoInicial())) {
            if (this.getBoard().isNullPosition(new Position(actualPosition.getLinha() + (TypeMove.UP.linha() * 2) * offset, actualPosition.getColuna() + (TypeMove.UP.coluna() * 2 * offset)))) {
                return true;
            }
            return false;
        } else if (destino.equals(new Position(actualPosition.getLinha() + TypeMove.RIGHT_UP.linha() * offset, actualPosition.getColuna() + TypeMove.RIGHT_UP.coluna() * offset))) {
            if (!this.getBoard().isNullPosition(new Position(destino.getLinha(), destino.getColuna()))) {
                if (this.getBoard().getBoard()[destino.getLinha()][destino.getColuna()].getColor() != this.getColor()) {
                    return true;
                }
            }
            return false;
        } else if (destino.equals(new Position(actualPosition.getLinha() + TypeMove.LEFT_UP.linha() * offset, actualPosition.getColuna() + TypeMove.LEFT_UP.coluna() * offset))) {
            if (!this.getBoard().isNullPosition(new Position(destino.getLinha(), destino.getColuna()))) {
                if (this.getBoard().getBoard()[destino.getLinha()][destino.getColuna()].getColor() != this.getColor()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
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
    public Position getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(Position posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public ArrayList<Position> getPath(Position destino, Chess chess) {
        return this.getPath(destino, chess.getBoard());
    }

}
