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
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.utils.TypeMove;
import xadrez.model.game.Board;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;

public class Queen extends Piece {

    /**
     * Creates a new instance of Queen
     */
    public Queen(Color cor) {
        super("Queen", cor);
    }

    /**
     * Creates a new instance of Queen
     */
    public Queen(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Queen", cor, pos, imageView, gridPane);
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

        ArrayList<Position> list = new ArrayList<Position>();

        int row = actualPosition.getLinha();
        int column = actualPosition.getColuna();

        for (int j = column + 1, i = row + 1; j < 8 && i < 8; j++, i++) {
            if (this.getBoard().isNullPosition(new Position(i, j))) {
                list.add(new Position(i, j));
            } else if (this.getBoard().getBoard()[i][j].getColor() != this.getColor()) {
                list.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int j = column - 1, i = row + 1; j > -1 && i < 8; j--, i++) {
            if (this.getBoard().isNullPosition(new Position(i, j))) {
                list.add(new Position(i, j));
            } else if (this.getBoard().getBoard()[i][j].getColor() != this.getColor()) {
                list.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int j = column - 1, i = row - 1; j > -1 && i > -1; j--, i--) {
            if (this.getBoard().isNullPosition(new Position(i, j))) {
                list.add(new Position(i, j));
            } else if (this.getBoard().getBoard()[i][j].getColor() != this.getColor()) {
                list.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int j = column + 1, i = row - 1; j < 8 && i > -1; j++, i--) {
            if (this.getBoard().isNullPosition(new Position(i, j))) {
                list.add(new Position(i, j));
            } else if (this.getBoard().getBoard()[i][j].getColor() != this.getColor()) {
                list.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }

        Position[][] possibilites = {
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},
            {new Position((actualPosition.getLinha()), (actualPosition.getColuna()))},};

        TypeMove[][] movements = {
            {TypeMove.UP},
            {TypeMove.RIGHT},
            {TypeMove.LEFT},
            {TypeMove.DOWN}
        };

        int cont = 1;
        boolean move = true;
        for (int i = 0; i < possibilites.length; i++) {
            for (int j = 0; j < possibilites[0].length; j++) {
                move = true;
                Position pos;
                cont = 1;
                while (move) {
                    if (possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont) < 8 && possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont) > -1 && possibilites[i][j].getLinha() + (movements[i][j].linha() * cont) < 8 && possibilites[i][j].getLinha() + (movements[i][j].linha() * cont) > -1) {
                        if (this.getBoard().isNullPosition(new Position(possibilites[i][j].getLinha() + (movements[i][j].linha() * cont), possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont)))) {
                            pos = new Position(possibilites[i][j].getLinha() + (movements[i][j].linha() * cont), possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont));
                            list.add(pos);
                        } else if (this.getBoard().getBoard()[possibilites[i][j].getLinha() + (movements[i][j].linha() * cont)][possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont)].getColor() != this.getColor()) {
                            pos = new Position(possibilites[i][j].getLinha() + (movements[i][j].linha() * cont), possibilites[i][j].getColuna() + (movements[i][j].coluna() * cont));
                            list.add(pos);
                            move = false;
                        } else {
                            move = false;
                        }
                    } else {
                        move = false;
                    }
                    cont++;
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

    public boolean checkDestiny(Position actualPosition, Position destino) {

        int l = Math.abs(destino.getLinha() - actualPosition.getLinha());
        int m = Math.abs(destino.getColuna() - actualPosition.getColuna());
        if (l == m) {
            int offsetX = 1;
            int offsetY = 1;
            if (destino.getLinha() < actualPosition.getLinha()) {
                offsetX *= -1;
            }
            if (destino.getColuna() < actualPosition.getColuna()) {
                offsetY *= -1;
            }

            int x, y;
            if (this.getBoard().isNullPosition(destino)) {
                for (x = actualPosition.getLinha() + offsetX, y = actualPosition.getColuna() + offsetY;
                        x != destino.getLinha(); x += offsetX, y += offsetY) {
                    return true;
                }
                return true;
            } else if (this.getBoard().getBoard()[actualPosition.getLinha()][actualPosition.getColuna()].getColor() != this.getBoard().getBoard()[destino.getLinha()][destino.getColuna()].getColor()) {
                for (x = actualPosition.getLinha() + offsetX, y = actualPosition.getColuna() + offsetY;
                        x != destino.getLinha(); x += offsetX, y += offsetY) {
                    return true;
                }
                return true;
            }

        }
        int offset = 1;
        if (destino.getColuna() == actualPosition.getColuna()) {
            if (destino.getLinha() < actualPosition.getLinha()) {
                offset = -1;
            }
            if (this.getBoard().isNullPosition(destino)) {
                int x;
                for (x = actualPosition.getLinha() + offset; x != destino.getLinha(); x += offset) {
                    return true;
                }
                return true;
            } else if (this.getBoard().getBoard()[actualPosition.getLinha()][actualPosition.getColuna()].getColor() != this.getBoard().getBoard()[destino.getLinha()][destino.getColuna()].getColor()) {
                int x;
                for (x = actualPosition.getLinha() + offset; x != destino.getLinha(); x += offset) {
                    return true;
                }
                return true;
            }

        } else if (destino.getLinha()
                == actualPosition.getLinha()) {
            if (destino.getColuna() < actualPosition.getColuna()) {
                offset *= -1;
            }

            if (this.getBoard().isNullPosition(destino)) {
                int y;
                for (y = actualPosition.getColuna() + offset; y != destino.getColuna(); y += offset) {
                    return true;
                }
                return true;
            } else if (this.getBoard().getBoard()[actualPosition.getLinha()][actualPosition.getColuna()].getColor() != this.getBoard().getBoard()[destino.getLinha()][destino.getColuna()].getColor()) {
                int y;
                for (y = actualPosition.getColuna() + offset; y != destino.getColuna(); y += offset) {
                    return true;
                }
                return true;
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
    
    public boolean playerTime() {
        return this.getBoard().getPlayerTime().getColor() == this.getColor();
    }
    
    public boolean endGame() {
        return this.getBoard().isEndGame();
    }

}
