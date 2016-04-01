package cs242chess.chess.model;

import cs242chess.chess.main.MainGame;

import javax.swing.*;

/**
 * Class model image
 */
public class Model_image extends MainGame{
    private JLabel panel;

    private char p;

    private int q;


    private char init_p;
    private int init_q;

    /**
     * Model image constructor
     * @param j label that is added to frame
     * @param p current col-location of the image
     * @param q current row-location of the image
     * @param init_p initial col-location of the image
     * @param init_q initial row-location of the image
     */
    public Model_image(JLabel j, char p, int q, char init_p, int init_q){
        this.panel = j;
        this.p = p;
        this.q = q;
        this.init_p = init_p;
        this.init_q = init_q;
    }

    /**
     * Get current col-location of image
     * @return col-location by char
     */
    public char get_col(){
        return this.p;
    }

    /**
     * Get current col-location of image
     * @return row-location by char
     */
    public int get_row(){
        return this.q;
    }

    /**
     * Get current image
     * @return label of current image
     */
    public JLabel get_label(){
        return this.panel;
    }

    /**
     * Get initial col-location of image
     * @return col-location by char
     */
    public char get_init_col(){
        return this.init_p;
    }

    /**
     * Get initial col-location of image
     * @return col-location by char
     */
    public int get_init_row(){
        return this.init_q;
    }
}
