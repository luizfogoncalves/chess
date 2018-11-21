/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez.model.game;

import javafx.scene.image.ImageView;
import model.utils.CellMod;

/**
 *
 * @author luizj
 */
public class Cell {
    
    private Piece piece = null;
    private CellMod mod = CellMod.EMPYT;

    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @param piece the piece to set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return the mod
     */
    public CellMod getMod() {
        return mod;
    }

    /**
     * @param mod the mod to set
     */
    public void setMod(CellMod mod) {
        this.mod = mod;
    }
    
}
