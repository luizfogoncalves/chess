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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import xadrez.model.game.Board;
import xadrez.model.game.Piece;
import xadrez.model.game.Position;

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
        //all possible moves in the up positive diagonal
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
        //all possible moves in the up negative diagonal
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
        //all possible moves in the down negative diagonal
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

        /* determina a cor das casas origem e destino */
        Color c1 = this.getBoardPositionColor(actualPosition.getLinha(), actualPosition.getColuna());
        Color c2 = this.getBoardPositionColor(destino.getLinha(), destino.getColuna());

        /* verifica se as casas tem a mesma cor */
        int l = Math.abs(destino.getLinha() - actualPosition.getLinha());
        int m = Math.abs(destino.getColuna() - actualPosition.getColuna());
        /* verifica se � um deslocamento em diagonal */
        if (l == m) {
            /* - a cada casa q o bispo anda em X, deve andar uma  em Y, seguindo um offset */
            int offsetX = 1;
            int offsetY = 1;
            /* determina a direcao do bispo "cima-baixo" "esquerda-direita" */
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
