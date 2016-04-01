package cs242chess.chess.pieces;

import java.util.ArrayList;

/**
 * King Class
 */
public class King extends Pieces {

    /**
     * Base Constructor
     * @param y col positions
     * @param x row positions
     * @param c color of the pieces
     */
    public King(char y, int x, boolean c){
        this.positionY = y;
        this.positionX = x;
        this.color = c;
        this.piece_name = "King";
    }

    /**
     * Find possible move position of pawn by player
     * @return Arraylist of all possible move position by King
     *
     */
    public ArrayList get_move_positions(){
        this.possible_m = new ArrayList();


        ArrayList p1_positions = player1.get_pieces();
        ArrayList p2_positions = player2.get_pieces();

        if(player1.get_turn()){
            char y = this.positionY;
            int x = this.positionX;

            int o = x + 1;
            int p = x -1;
            char r = (char)(y+1);
            char s = (char)(y-1);

            int block1 = 0;
            int block2 = 0;
            int block3 = 0;
            int block4 = 0;
            int block5 = 0;
            int block6 = 0;
            int block7 = 0;
            int block8 = 0;
            for(int i = 0; i < p1_positions.size();i++){
                Pieces temp = (Pieces)p1_positions.get(i);

                if(temp.getPositionX()==x){
                    if(temp.getPositionY() == r){
                        block1 = 1;
                    }

                    if(temp.getPositionY() == s){
                        block2 = 1;
                    }

                }
                if(temp.getPositionY() == y){
                    if(temp.getPositionX() == o){
                        block3 = 1;
                    }

                    if(temp.getPositionX()==p){
                        block4 = 1;
                    }
                }

                if(temp.getPositionX() == o){
                    if(temp.getPositionY() == r){
                        block5 = 1;
                    }
                    if(temp.getPositionY()==s){
                        block6 = 1;
                    }
                }

                if(temp.getPositionX() == p){
                    if(temp.getPositionY() == r){
                        block7 = 1;
                    }
                    if(temp.getPositionY() == s){
                        block8 = 1;
                    }
                }
            }

            if(!out_of_edge(r,x) && block1 != 1){
                this.possible_m.add(new King(r,x,this.color));
            }
            if(!out_of_edge(s,x) && block2 != 1){
                this.possible_m.add(new King(s,x,this.color));
            }
            if(!out_of_edge(y,o) && block3 != 1){
                this.possible_m.add(new King(y,o,this.color));
            }
            if(!out_of_edge(y,p) && block4 != 1){
                this.possible_m.add(new King(y,p,this.color));
            }
            if(!out_of_edge(r,o) && block5 != 1){
                this.possible_m.add(new King(r,o,this.color));
            }
            if(!out_of_edge(r,s) && block6 != 1){
                this.possible_m.add(new King(r,s,this.color));
            }
            if(!out_of_edge(r,p) && block7 != 1){
                this.possible_m.add(new King(r,p,this.color));
            }
            if(!out_of_edge(s,p) && block8 != 1){
                this.possible_m.add(new King(s,p,this.color));
            }




        }

        else{
            char y = this.positionY;
            int x = this.positionX;

            int o = x + 1;
            int p = x -1;
            char r = (char)(y+1);
            char s = (char)(y-1);

            int block1 = 0;
            int block2 = 0;
            int block3 = 0;
            int block4 = 0;
            int block5 = 0;
            int block6 = 0;
            int block7 = 0;
            int block8 = 0;
            for(int i = 0; i < p2_positions.size();i++){
                Pieces temp = (Pieces)p2_positions.get(i);

                if(temp.getPositionX()==x){
                    if(temp.getPositionY() == r){
                        block1 = 1;
                    }

                    if(temp.getPositionY() == s){
                        block2 = 1;
                    }

                }
                if(temp.getPositionY() == y){
                    if(temp.getPositionX() == o){
                        block3 = 1;
                    }

                    if(temp.getPositionX()==p){
                        block4 = 1;
                    }
                }

                if(temp.getPositionX() == o){
                    if(temp.getPositionY() == r){
                        block5 = 1;
                    }
                    if(temp.getPositionY()==s){
                        block6 = 1;
                    }
                }

                if(temp.getPositionX() == p){
                    if(temp.getPositionY() == r){
                        block7 = 1;
                    }
                    if(temp.getPositionY() == s){
                        block8 = 1;
                    }
                }
            }

            if(!out_of_edge(r,x) && block1 != 1){
                this.possible_m.add(new King(r,x,this.color));
            }
            if(!out_of_edge(s,x) && block2 != 1){
                this.possible_m.add(new King(s,x,this.color));
            }
            if(!out_of_edge(y,o) && block3 != 1){
                this.possible_m.add(new King(y,o,this.color));
            }
            if(!out_of_edge(y,p) && block4 != 1){
                this.possible_m.add(new King(y,p,this.color));
            }
            if(!out_of_edge(r,o) && block5 != 1){
                this.possible_m.add(new King(r,o,this.color));
            }
            if(!out_of_edge(r,s) && block6 != 1){
                this.possible_m.add(new King(r,s,this.color));
            }
            if(!out_of_edge(r,p) && block7 != 1){
                this.possible_m.add(new King(r,p,this.color));
            }
            if(!out_of_edge(s,p) && block8 != 1){
                this.possible_m.add(new King(s,p,this.color));
            }

        }



        return this.possible_m;
    }
}
