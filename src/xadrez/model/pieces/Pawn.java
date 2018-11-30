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
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.utils.TypeMove;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;
import xadrez.model.game.Board;

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
            if (playerTime() && !endGame()) {
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
            }

        });
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
                                if (getImageView(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), gridPane).getId().contains("rei")) {
                                    final Stage primaryStage = new Stage();
                                    final Stage dialog = new Stage();
                                    dialog.initModality(Modality.APPLICATION_MODAL);
                                    dialog.initOwner(primaryStage);
                                    VBox dialogVbox = new VBox(20);
                                    dialogVbox.getChildren().add(new Text("Fim de Jogo"));
                                    dialogVbox.getChildren().add(new Text("Jogador vencedor: " + this.getBoard().getPlayerTime().getName()));
                                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                                    dialog.setScene(dialogScene);
                                    dialog.show();
                                    this.getBoard().setEndGame(true);
                                }
                                gridPane.getChildren().remove(getImageView(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), gridPane));
                                this.getBoard().getBoard()[GridPane.getRowIndex(pane)][GridPane.getColumnIndex(pane)].setPieceRemoved(true);
                            }
                        }
                        gridPane.getChildren().remove(imageView);
                        gridPane.add(imageView, GridPane.getColumnIndex(pane), GridPane.getRowIndex(pane));
                        this.getBoard().setNullPosition(this.getPosition());
                        this.getBoard().setPosition(this, new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
                        this.setPosition(new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane)));
                        this.getBoard().alterPlayer();
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

    public boolean playerTime() {
        return this.getBoard().getPlayerTime().getColor() == this.getColor();
    }
    
    public boolean endGame() {
        return this.getBoard().isEndGame();
    }

}
