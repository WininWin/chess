package cs242chess.chess.pieces;

import java.util.ArrayList;
import java.util.HashMap;

import cs242chess.chess.main.MainGame;
import cs242chess.chess.players.Player;

/**
 *
 * Class Piece
 */
public class Pieces extends MainGame {


    /** Col position of Pieces */
    char positionY;   // A ~ H

    /** Row position of Pieces */
    int positionX;    // 1 ~ 8

    /** Indicate color of piece */
    boolean color;     //color of piece

    /** Name of Piece */
    String piece_name;  //Name of Piece


    /** Arraylist of the all the possible move position of the piece*/
    ArrayList possible_m;


    /**
     * Out of edge of board
     * @param t  Position col
     * @param i  Position row
     * @return true if out of edge
     */
    public boolean out_of_edge(char t, int i){
        return (t < 'A' || t > 'H' || i < 1 || i > 8);
    }


    /**
     *  Return the Arralist of possible movement position
     * @return  Arraylist of possible move position
     */
    public ArrayList get_move_positions(){
        return this.possible_m;
    }

    /** return the col position of piece *
     * @return  char of position of piece
     */
    public char getPositionY(){
        return this.positionY;
    }

    /** return the row position of piece *
     * @return  int of position of piece
     */
    public int getPositionX(){
        return this.positionX;
    }

    /** return the name of piece *
     * @return  String of position of piece
     */
    public String get_name(){
        return this.piece_name;
    }

    /**
     * return color of piece
     * @return color of piece ture == white false == black
     */
    public boolean get_color(){return this.color;}


    public String get_image_name(){
        return this.piece_name + ".jpg";
    }

    /**
     * Initialize of position of the pieces
    *  @param color  color of the piece
     * @param start_y  start col position of piece
    *  @param start_x  start row position of piece
    *  @return  Arraylist of all the pieces with positions
    *
    *
    */
    public ArrayList init_pieces(boolean color, char start_y, int start_x){



        ArrayList temp = new ArrayList();
        temp.add(new King(start_y, start_x, color));
        temp.add(new Queen((char)(start_y-1), start_x, color));

        for(int i = 0; i<2; i++){

            temp.add(new Rook((char)(start_y-4+(4*i+3*i)), start_x, color));
            temp.add(new Knight((char)(start_y-3+(3*i+2*i)), start_x, color));
            temp.add(new Bishop((char)(start_y-2+(2*i+i)), start_x, color));

        }

        for(int i =0; i < 8; i++){
            if(color) {
                temp.add(new Pawn((char) (start_y - 4 + i), start_x + 1, color));
            }
            else{
                temp.add(new Pawn((char) (start_y - 4 + i), start_x - 1, color));
            }
        }

        return temp;
    }






}
