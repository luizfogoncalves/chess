/*
 * Board.java
 *
 * Created on October 3, 2006, 8:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package xadrez.model.game;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import xadrez.model.pieces.*;

public class Board {

    /* Matriz de pecas do board */
    private Piece board[][];
    
    private Player player1;
    private Player player2;
    private Player playerTime;
    private boolean endGame;
    
    /* 
     Contrutor da classe board
     */
    public Board(Player player1, Player player2) {
        this.endGame = false;
        this.player1 = player1;
        this.player2 = player2;
        this.playerTime = player1;
        this.setPlayerTime(player1);
        this.board = new Piece[8][8];
        this.clearBoard();
    }
    
    public Board(Piece board[][], Player player1, Player player2) {
        this.endGame = false;
        this.player1 = player1;
        this.player2 = player2;
        this.playerTime = player2;
        this.board = board;
    }

    /* 
     Inicializa board
     */
    public void clearBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.getBoard()[i][j] = null;
            }
        }
    }

    /* 
       Coloca a pe�a em uma posicao do board
     */
    public void setPosition(Piece peca, Position pos) {
        this.getBoard()[pos.getLinha()][pos.getColuna()] = peca;
    }

    /* 
       Remove pe�a em uma posicao do board
     */
    public void setNullPosition(Position pos) {
        this.getBoard()[pos.getLinha()][pos.getColuna()] = null;
    }

    /* 
       Verifica se posi��o � vazia
     */
    public boolean isNullPosition(Position pos) {
        if((pos.getLinha() < 8 && pos.getLinha() > -1) && (pos.getColuna() < 8 && pos.getColuna() > -1)){
            return this.getBoard()[pos.getLinha()][pos.getColuna()] == null;
        } else {
            return true;
        }
        
    }

    /*
        Move a peca localizada em origem para o destino
        - sobreescreve a peca localizada em destino (se existir)
        - limpa a posicao origem
     */
    public void movePiece(Position origem, Position destino) {

        //Pega a pe�a da posi��o
        Piece peca = this.getPieceAtPosition(origem);

        // Move a pe�a de origem para o destino
        this.setPosition(peca, destino);

        // Atualiza posi��o da pe�a movida
        peca.setPosition(destino);

        // Seta origem como vazia
        this.setNullPosition(origem);
    }

    public Board getBoardClone() {

        Board board = new Board(this.player1, this.player2);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPieceAtPosition(new Position(i, j)) != null) {
                    Piece p = this.getPieceAtPosition(new Position(i, j));
                    if (p instanceof Bishop) {
//                        Bishop b = new Bishop(p.getColor(),p.getPosition());
//                        board.setPosition(b,b.getPosition());                        
                    } else if (p instanceof King) {
//                        King k = new King(p.getColor(),p.getPosition());
//                        board.setPosition(k,k.getPosition());
                    } else if (p instanceof Knight) {
//                        Knight n = new Knight(p.getColor(),p.getPosition());
//                        board.setPosition(n,n.getPosition());
                    } else if (p instanceof Pawn) {
//                        Pawn pa = new Pawn(p.getColor(),p.getPosition());
//                        board.setPosition(pa,pa.getPosition());
                    } else if (p instanceof Queen) {
//                        Queen q = new Queen(p.getColor(),p.getPosition());
//                        board.setPosition(q,q.getPosition());
                    } else if (p instanceof Rook) {
//                        Rook r = new Rook(p.getColor(),p.getPosition());
//                        board.setPosition(r,r.getPosition());
                    }
                } else {
                    board.setPosition(null, new Position(i, j));
                }
            }
        }
        return board;
    }

    /* 
       Retorna a pe�a em uma determinada posicao
     */
    public Piece getPieceAtPosition(Position pos) {
        return this.getBoard()[pos.getLinha()][pos.getColuna()];
    }
    
    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            System.out.println(node);
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column && node.getId() != null) {
                result = node;
                break;
            }
        }

        return result;
    }
    
    public void alterPlayer() {
        if(player1.getColor() == playerTime.getColor()){
            playerTime = player2;
        } else {
            playerTime = player1;
        }
    }

    /**
     * @return the board
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Piece[][] board) {
        this.board = board;
    }    

    /**
     * @return the player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * @return the playerTime
     */
    public Player getPlayerTime() {
        return playerTime;
    }

    /**
     * @param playerTime the playerTime to set
     */
    public void setPlayerTime(Player playerTime) {
        this.playerTime = playerTime;
    }

    /**
     * @return the endGame
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * @param endGame the endGame to set
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    
    
    
}
