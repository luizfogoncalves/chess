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

public class Knight extends Piece {

    /**
     * Creates a new instance of Knight
     */
    public Knight(Color cor) {
        super("Knight", cor);
    }

    /**
     * Creates a new instance of Knight
     */
    public Knight(Color cor, Position pos, ImageView imageView, GridPane gridPane) {
        super("Knight", cor, pos, imageView, gridPane);
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

    public Color getBoardPositionColor(int x, int y) {
        if (((x + y) % 2) == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public ArrayList<Position> showPossibilities(Position actualPosition) {

        ArrayList<Position> list = new ArrayList<Position>();

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
                if (possibilites[i][j].getColuna() < 8 && possibilites[i][j].getColuna() > -1 && possibilites[i][j].getLinha() < 8 && possibilites[i][j].getLinha() > -1) {
                    if (this.getBoard().isNullPosition(possibilites[i][j])) {
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

    public boolean playerTime() {
        return this.getBoard().getPlayerTime().getColor() == this.getColor();
    }
    
    public boolean endGame() {
        return this.getBoard().isEndGame();
    }
}
