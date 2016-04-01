package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 *
 * Class Queen
 */
public class Queen extends Pieces {


    /**
     * Base Constructor
     * @param y col positions
     * @param x row positions
     * @param c color of the pieces
     */
    public Queen(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Queen";
    }

    /**
     * Find the possible move position of the Queen
    *  @return  Arraylist of all possible move position by Queen
    * */
    public ArrayList get_move_positions(){
        this.possible_m = new ArrayList();

        Bishop b = new Bishop(this.getPositionY(), this.getPositionX(), this.color);
        Rook r = new Rook(this.getPositionY(), this.getPositionX(), this.color);

        ArrayList b_positions = b.get_move_positions();
        ArrayList r_positions = r.get_move_positions();

        for(int i = 0; i < b_positions.size();i++){
            Pieces temp = (Pieces)b_positions.get(i);
            this.possible_m.add(new Queen(temp.getPositionY(), temp.getPositionX(), temp.color));
        }
        for(int i = 0; i < r_positions.size();i++){
            Pieces temp = (Pieces)r_positions.get(i);
            this.possible_m.add(new Queen(temp.getPositionY(), temp.getPositionX(), temp.color));
        }


        return this.possible_m;
    }


}
