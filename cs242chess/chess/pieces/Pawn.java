package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 *
 * Class Pawn
 */
public class Pawn extends Pieces {


    /**
     * Base Constructor
     * @param y col positions
     * @param x row positions
     * @param c color of the pieces
     */
    public Pawn(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Pawn";
    }



    /** Find possible move position of pawn by player
    * @return Arraylist of all possible move position by Pawn
    *
    */
    public ArrayList get_move_positions(){

        ArrayList p1_positions = player1.get_pieces();
        ArrayList p2_positions = player2.get_pieces();
        this.possible_m = new ArrayList();
        //initial movement of the pawn
        if(this.positionX == 2 || this.positionX == 7){

            if(player1.get_turn()) {

                this.possible_m.add(new Pawn(this.positionY, this.positionX + 2, this.color));

            }
            else{
                this.possible_m.add(new Pawn(this.positionY, this.positionX - 2, this.color));

            }

        }


        if(player1.get_turn()){

                char t = (char)(this.getPositionY()-1);
                char p = (char)(this.getPositionY()+1);
                int j = this.getPositionX() + 1;



                for(int i = 0; i < p2_positions.size(); i++) {
                    Pieces temp = (Pieces) p2_positions.get(i);

                    if (temp.getPositionX() == j) {
                        if (temp.getPositionY() == t) {
                            this.possible_m.add(new Pawn(t, j, this.color));


                        }
                        if (temp.getPositionY() == p) {
                            this.possible_m.add(new Pawn(p, j, this.color));

                        }
                    }
                }
                int block = 0;
                for(int b = 0; b < p1_positions.size();b++){
                    Pieces temp = (Pieces) p1_positions.get(b);
                    if(temp.getPositionX()==j){
                        block = 1;
                    }
                }
                if(block != 1) {
                    this.possible_m.add(new Pawn(this.getPositionY(), j, this.color));
                }

                return this.possible_m;
            }

            else{

                char t = (char)(this.getPositionY()-1);
                char p = (char)(this.getPositionY()+1);
                int j = this.getPositionX() - 1;



                for(int i = 0; i < p1_positions.size(); i++){
                    Pieces temp = (Pieces)p1_positions.get(i);

                    if(temp.getPositionX() == j){
                        if(temp.getPositionY() == t){
                            this.possible_m.add(new Pawn(t, j, this.color));


                        }
                        if(temp.getPositionY() == p){
                            this.possible_m.add(new Pawn(p, j, this.color));

                        }
                    }

                    this.possible_m.add(new Pawn(this.getPositionY(), j, this.color));

                }

                int block = 0;
                for(int b = 0; b < p2_positions.size();b++){
                    Pieces temp = (Pieces) p2_positions.get(b);
                    if(temp.getPositionX()==j){
                        block = 1;
                    }
                }
                if(block != 1) {
                    this.possible_m.add(new Pawn(this.getPositionY(), j, this.color));
                }
                return this.possible_m;
            }






    }
}
