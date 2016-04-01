package cs242chess.chess.players;


import cs242chess.chess.pieces.*;

import java.util.ArrayList;

/**
 *
 * Class Player
 */
public class Player {

        /** public variable for player turn */
        public boolean turn;  //player turn

        /** private variable for player's pieces */
        private ArrayList p;  // player's pieces

        /** private variable for player's captured pieces */
        private ArrayList captured_p;  //player's captured pieces

        /** public variables for player check condition 1: check 0: not check*/
        public int is_check;  // check condition

        /** public variables for player win condition 1: win 0:lose*/
        public int win;  // win or lose condition

        public int agree_restart;

    /**
     *
     * @param color color of player
     * @param y init col position of the player's pawn position
     * @param x init row position of the player's pawn positio
     */
        public Player(boolean color, char y, int x) {
            this.turn = color;
            Pieces t = new Pieces();
            this.captured_p = new ArrayList();
            this.p = t.init_pieces(color, y, x);
            is_check = 0;
            win = 0;
            this.agree_restart = 0;


        }

        /**
         * Get current pieces of the player
        * @return Arraylist of the pieces
        * */
        public ArrayList get_pieces(){
            return this.p;
        }

        /**
         * Get turn of the player
        * @return current player of turn
        * */
        public boolean get_turn(){
            return this.turn;
        }

        /** Get captured pieces of the player
        * @return Arraylist of the captured pieces
        * */
        public ArrayList get_captured_pieces(){
            return this.captured_p;
        }


        /**
         * Remove selected piece
         * @param p piece that need to remove*/
        public void remove_piece(Pieces p){
            this.p.remove(p);
        }

        /**
        * remove all pieces */
        public void remove_all_pieces(){
            this.p.clear();
        }

    /**
     * Add piece to player
     * @param p piece that need to add
     */
        public void add_pieces(Pieces p){
            this.p.add(p);
        }

    /**
     * Print all position of pieces
     * @param game_count Number of turn
     */
        public void print_current_pieces_position(int game_count){

            System.out.println("---------------------" + game_count +  "------------------------");
            for(int i=0; i < this.p.size();i++){
                Pieces t = (Pieces)this.p.get(i);
                System.out.println(t.get_name() + " " + t.getPositionY() + " " + t.getPositionX());
            }

        }




}
