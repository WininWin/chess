package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 *
 * Class Bishop
 */
public class Bishop extends Pieces {

    /**
     * Base Constructor
     * @param y col positions
     * @param x row positions
     * @param c color of the pieces
     */
    public Bishop(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Bishop";
    }

    /** Find the possible move position of the Bishop
   *  @return  Arraylist of all possible move position by Bishop
   * */
    public ArrayList get_move_positions(){
        this.possible_m = new ArrayList();

        ArrayList p1_positions = player1.get_pieces();
        ArrayList p2_positions = player2.get_pieces();

        if(player1.get_turn()){

            char curr_y = this.positionY;
            int curr_x = this.positionX;
            for(int i = 1; i < 5; i++) {
                calculate_positions(i, curr_x, curr_y, this.possible_m, p1_positions, p2_positions, true);
            }

        }


        else{

            char curr_y = this.positionY;
            int curr_x = this.positionX;
            for(int i = 1; i < 5; i++) {
                calculate_positions(i, curr_x, curr_y, this.possible_m, p1_positions, p2_positions, false);
            }


        }


        return this.possible_m;
    }

    /** Calculate for the diagonal moving position of Bishop
    * @param pos  Indicate the direction of the piece 1 : up right 2 : down left 3 : up left 4 : down right
    * @param x  Current row position of piece
    * @param y  current col position of piece
    * @param added  Arraylist that is collecting the positions
    * @param p1  current player1's pieces positions
    * @param p2  current player2's pieces positions
    * @param player  current player
    *
    * */
    private void calculate_positions(int pos, int x, char y, ArrayList added, ArrayList p1, ArrayList p2, boolean player){
        int block0 =0;
        int block1 = 0;
        for(int i = 1; i<8; i++){
            char p = y; // curr_y
            int q = x;  // curr_x
            if(pos==1){
                p = (char)(p+i);
                q = q+i;
            }
            else if(pos==2){
                p = (char)(p-i);
                q = q - i;

            }
            else if(pos==3){
                q = q + i;
                p = (char)(p-i);

            }
            else{
                q = q - i;
                p = (char)(p+i);
            }
            if(!out_of_edge(p,q)) {
                if (player) {
                    for (int j = 0; j < p1.size(); j++) {
                        Pieces temp = (Pieces) p1.get(j);
                        if (temp.getPositionX() == q) {
                            if (temp.getPositionY() == p){
                                block0 = 1;
                                break;
                            }
                        }
                    }

                    if(block0 == 1){
                        break;

                    }

                    for(int k = 0; k < p2.size(); k++){
                        Pieces temp = (Pieces) p2.get(k);
                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                block1 = 1;
                                is_check_player2(temp);
                                break;
                            }
                        }
                    }

                    if(block1 == 1){
                        added.add(new Bishop(p,q, true));
                        break;
                    }
                    else{
                        added.add(new Bishop(p,q, true));
                    }
                }
                else {
                    for (int j = 0; j < p2.size(); j++) {
                        Pieces temp = (Pieces) p2.get(j);
                        if (temp.getPositionX() == q) {
                            if (temp.getPositionY() == p){
                                block0 = 1;
                                break;
                            }
                        }
                    }

                    if(block0 == 1){
                        break;

                    }

                    for(int k = 0; k < p1.size(); k++){
                        Pieces temp = (Pieces) p1.get(k);
                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                block1 = 1;
                                is_check_player1(temp);
                                break;
                            }
                        }
                    }

                    if(block1 == 1){
                        added.add(new Bishop(p,q, false));
                        break;
                    }
                    else{
                        added.add(new Bishop(p,q, false));
                    }


                }

            }

        }



    }
}
