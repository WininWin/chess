package cs242chess.chess.pieces;


import java.util.ArrayList;

/**
 *
 * Class Knight
 */
public class Knight extends Pieces {

    /** Base constructor for Knight */
    public Knight(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "Knight";
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

    /** Find the possible move position of the Knight
    *  @return  Arraylist of all possible move position by Knight
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


}
