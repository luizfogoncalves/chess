package model.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luizj
 */
public enum TypeMove {
    
    UP (-1,0),
    DOWN (1, 0),
    LEFT (0, -1),
    LEFT_UP (-1, -1),
    LEFT_DOWN (1, -1),
    RIGHT (0, 1),
    RIGHT_UP (-1, 1),
    RIGHT_DOWN (1, 1);
    
    private final int linha;
    private final int coluna;
    
    private TypeMove(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    
    
    public int linha() {
        return this.linha;
    }
    public int coluna() {
        return this.coluna;
    }
    
}
