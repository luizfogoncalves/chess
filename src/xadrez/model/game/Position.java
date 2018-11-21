/*
 * Position.java
 *
 * Created on October 12, 2006, 10:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xadrez.model.game;

public class Position {
    
    /*
     Class Atributes 
     */
    int linha;
    int coluna;
    
     public Position() {
        this(0,0);
    }
             
    /**
     * Creates a new instance of Position
     */
    public Position(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    public boolean equals (Position pos) {
        return (this.getLinha() == pos.getLinha()) && (this.getColuna() == pos.getColuna());
    }
    
    public String toString() {
       return this.getLinha()+" - "+ this.getColuna();
    }
}
