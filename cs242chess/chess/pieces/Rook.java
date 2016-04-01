package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 * @author : Yeonsung Kim
 * Class Rook
 */
public class Rook extends Pieces {

    /**
     * Base Constructor
     * @param y col positions
     * @param x row positions
     * @param c color of the pieces
     */
    public Rook(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Rook";
    }

    /** Find the possible move positions
     * @return : Arraylist of all possible move position by Rook
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


    /** Calculate for the moving position of Rook
    * @param pos : Indicate the direction of the piece 1 : right 2 : left 3 : up 4 : down
    * @param x : Current row position of piece
    * @param y : current col position of piece
    * @param added : Arraylist that is collecting the positions
    * @param p1 : current player1's pieces positions
    * @param p2 : current player2's pieces positions
    * @param player : current player
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
            }
            else if(pos==2){
                p = (char)(p-i);

            }
            else if(pos==3){
                q = q + i;

            }
            else{
                q = q - i;
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

                    int king_x;
                    char king_y;
                    for(int k = 0; k < p2.size(); k++){
                        Pieces temp = (Pieces) p2.get(k);

                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                block1 = 1;

                                if(player2.is_check == 1){
                                    if(temp.get_name() == "King"){
                                        king_x = temp.getPositionX();
                                        king_y = temp.getPositionY();
                                        if(king_x == x){
                                            if(king_y >= y){
                                                char w = (char)(king_y+1);
                                                added.add(new Rook(w, x, true));
                                            }
                                            if(king_y <= y){
                                                char w = (char)(king_y - 1);
                                                added.add(new Rook(w, x, true));                                            }

                                        }
                                        if(king_y == y){
                                            if(king_x >= x){
                                                int w = king_x + 1;
                                                added.add(new Rook(y, w, true));

                                            }
                                            if(king_x <= x){
                                                int w = king_x - 1;
                                                added.add(new Rook(y, w, true));
                                            }

                                        }
                                    }
                                }


                                break;
                            }
                        }
                    }

                    if(block1 == 1){
                        //System.out.println(p + " " + q);
                        added.add(new Rook(p,q, true));
                        break;
                    }
                    else{
                        added.add(new Rook(p,q, true));
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


                    int king_x;
                    char king_y;

                    for(int k = 0; k < p1.size(); k++){
                        Pieces temp = (Pieces) p1.get(k);
                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                is_check_player1(temp);
                                block1 = 1;

                                if(player1.is_check == 1){
                                    if(temp.get_name() == "King"){
                                        king_x = temp.getPositionX();
                                        king_y = temp.getPositionY();
                                        if(king_x == x){
                                            if(king_y >= y){
                                                char w = (char)(king_y+1);
                                                added.add(new Rook(w,x, false));
                                            }
                                            if(king_y <= y){
                                                char w = (char)(king_y - 1);
                                                added.add(new Rook(w, x, false));                                            }

                                        }
                                        if(king_y == y){
                                            if(king_x >= x){
                                                int w = king_x + 1;
                                                added.add(new Rook(y, w, false));

                                            }
                                            if(king_x <= x){
                                                int w = king_x - 1;
                                                added.add(new Rook(y, w, false));
                                            }

                                        }
                                    }
                                }


                                break;
                            }
                        }
                    }

                    if(block1 == 1){
                        added.add(new Rook(p,q, false));
                        break;
                    }
                    else{
                        added.add(new Rook(p,q, false));
                    }


                }

            }

        }



    }



}
