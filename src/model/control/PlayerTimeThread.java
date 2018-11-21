/*
 * PlayerTimeThread.java
 *
 * Created on 15 de Novembro de 2006, 15:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package model.control;

import xadrez.model.game.Chess;
import xadrez.model.game.Player;

/**
 *
 * @author Marcelo Gonzaga
 */
public class PlayerTimeThread extends Thread {
    
    
    // Cria uma instancia de ChessGUI
//    ChessGUI chessgui;
    Chess chess;
    
    private volatile boolean running = true;
    
    /** Creates a new instance of PlayerTimeThread */
//    public PlayerTimeThread(ChessGUI chessgui) {
//        this.setChessGUI(chessgui);
//        this.setChess(chessgui.getChess());
//    }

    public Chess getChess() {
        return chess;
    }
    
    public void setChess(Chess chess) {
        this.chess = chess;
    }

//    public void setChessGUI(ChessGUI chess) {
//        this.chessgui = chess;
//    }

    public synchronized void setRunning(boolean value) {
        this.running = value;
    }
    
//    public ChessGUI getChessgui() {
//        return chessgui;
//    }
    

    public void run(){
        
        Player player1 = this.chess.getPlayer1();
        Player player2 = this.chess.getPlayer2();
        
//        this.chessgui.getJlbTempo1().setText(String.valueOf(player1.getTime()));
//        this.chessgui.getJlbTempo2().setText(String.valueOf(player2.getTime()));
        
        //this.chessgui.getJlbJogadorDaVez().setText(text);
        while(running && !this.chess.isGameover()){
            if (this.chess.isFirstPlayersTime()){
                player1.contaTempo();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
//                this.chessgui.getJlbTempo1().setText(player1.getTime().toString());                
            }else{
                player2.contaTempo();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
//                this.chessgui.getJlbTempo2().setText(player2.getTime().toString());                
            }
            
            String text;
            if (this.chess.isFirstPlayersTime()){
                text = player1.getName();
            }else{
                text = player2.getName();
            }
//            this.chessgui.getJlbJogadorDaVez().setText(text);
            
            if (player1.getTime().isOver()) {
                this.chess.setGameover(true);
                javax.swing.JOptionPane.showMessageDialog(null,player1.getName()+" perdeu o jogo","Tempo esgotado!",0);
                break;
            } else if (player2.getTime().isOver()) {
                this.chess.setGameover(true);
                javax.swing.JOptionPane.showMessageDialog(null,player2.getName()+" perdeu o jogo","Tempo esgotado!",0);
                break;
            }
        }
    }
    
}
