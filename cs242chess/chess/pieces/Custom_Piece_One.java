package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 * class Custom piece one
 */
public class Custom_Piece_One extends Pieces {


    /** Combine Knight and Rook */
    public Custom_Piece_One(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Knight";
    }

    /** Find the possible move position of the Knight and Rook
     *  @return  Arraylist of all possible move position by Knight and Rook
     * */
    public ArrayList get_move_positions(){
        this.possible_m = new ArrayList();

        ArrayList p1_positions = player1.get_pieces();
        ArrayList p2_positions = player2.get_pieces();

        char curr_y = this.positionY;
        int curr_x = this.positionX;

        int o = curr_x + 1;
        int q = curr_x - 1;
        char r = (char)(curr_y+1);
        char s = (char)(curr_y-1);

        if(player1.get_turn()){

            for(int i = 1; i < 5; i++) {
                calculate_positions_st(i, curr_x, curr_y, this.possible_m, p1_positions, p2_positions, true);
            }

            char a = (char)(r+1);
            int b = o+ 1;
            calculate_positions(r,o,a,b,p1_positions);

            char c = (char)(r+1);
            int d = q - 1;
            calculate_positions(r,q,c,d,p1_positions);

            char e = (char)(s-1);
            int f = o+1;
            calculate_positions(s,o,e,f,p1_positions);

            char g = (char)(s-1);
            int h = q-1;
            calculate_positions(s,q,g,h,p1_positions);

        }

        else{

            for(int i = 1; i < 5; i++) {
                calculate_positions_st(i, curr_x, curr_y, this.possible_m, p1_positions, p2_positions, false);
            }


            char a = (char)(r+1);
            int b = o+ 1;
            calculate_positions(r,o,a,b,p2_positions);

            char c = (char)(r+1);
            int d = q - 1;
            calculate_positions(r,q,c,d,p2_positions);

            char e = (char)(s-1);
            int f = o+1;
            calculate_positions(s,o,e,f,p2_positions);

            char g = (char)(s-1);
            int h = q-1;
            calculate_positions(s,q,g,h,p2_positions);


        }


        return this.possible_m;
    }


    /** Calculate positions for the knight
     *
     * @param y  initial col direction of knight
     * @param x  initial row direction of knight
     * @param a  col position of knight can move
     * @param b  row position of knight can move
     * @param p  Arraylist of  current player's pieces
     *
     * */
    private void calculate_positions(char y, int x, char a, int b, ArrayList p){
        if(!out_of_edge(y,x)){
            int block0 = 0;
            int block1 = 0;
            int count = 0;

            for(int k = 0; k < p.size(); k++){
                Pieces temp = (Pieces) p.get(k);
                if(temp.getPositionX()==b){
                    if(temp.getPositionY()==y){
                        block0 = 1;
                        count = count + 1;
                    }
                }
                if(temp.getPositionY()==a){
                    if(temp.getPositionX()==x){
                        block1 = 1;
                        count = count + 1;
                    }
                }

                if(count == 2){
                    break;
                }
            }

            if(block0 != 1 && !out_of_edge(y, b)){
                this.possible_m.add(new Knight(y,b, this.color));
            }

            if(block1 != 1 && !out_of_edge(a, x)){
                this.possible_m.add(new Knight(a, x, this.color));
            }
        }

    }

    /** Calculate for the straight moving position of piece
     * @param pos  Indicate the direction of the piece 1 : right 2 : left 3 : up 4 : down
     * @param x  Current row position of piece
     * @param y  current col position of piece
     * @param added  Arraylist that is collecting the positions
     * @param p1  current player1's pieces positions
     * @param p2  current player2's pieces positions
     * @param player  current player
     *
     * */
    private void calculate_positions_st(int pos, int x, char y, ArrayList added, ArrayList p1, ArrayList p2, boolean player){
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

                    for(int k = 0; k < p2.size(); k++){
                        Pieces temp = (Pieces) p2.get(k);
                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                block1 = 1;

                                break;
                            }
                        }
                    }

                    if(block1 == 1){
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

                    for(int k = 0; k < p1.size(); k++){
                        Pieces temp = (Pieces) p1.get(k);
                        if(temp.getPositionX()==q){
                            if(temp.getPositionY()==p){
                                block1 = 1;

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
