package cs242chess.chess.rule;


import cs242chess.chess.main.MainGame;
import cs242chess.chess.pieces.*;

import java.util.ArrayList;

/**
 *
 * Class Rules
 */
public class Rules extends MainGame {


    /**
     *  Function that can move piece
     *  @param select  Piece that player want to move
     *  @param x  row position that player want to move
     *  @param y  col position that plywer want to move
     *
     *
     */
    public static void move_piece(Pieces select, int x, char y){
        if(player1.get_turn()) {

            ArrayList possible_positions = select.get_move_positions();
            player1.is_check = 0;


            for(int i = 0; i<possible_positions.size();i++){
                Pieces temp = (Pieces)possible_positions.get(i);

                if(temp.getPositionX() == x){
                    if(temp.getPositionY()== y){
                        player1.get_pieces().remove(select);
                        player1.get_pieces().add(temp);
                        game_history.add(temp);

                        int kx;
                        char ky;
                        int check = 0;
                        for(int p = 0; p<player2.get_pieces().size();p++){
                            Pieces k = (Pieces)player2.get_pieces().get(p);
                            if(k.get_name() == "King"){
                                kx = k.getPositionX();
                                ky = k.getPositionY();
                                for(int j = 0; j<temp.get_move_positions().size();j++){
                                    Pieces t = (Pieces)temp.get_move_positions().get(j);
                                    if(t.getPositionX() == kx){
                                        if(t.getPositionY()==ky){
                                           check = 1;
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }

                        if(check == 1){
                            player2.is_check = 1;
                        }

                        break;
                    }

                }
            }

            ArrayList p2_positions = player2.get_pieces();
            for(int i = 0; i<p2_positions.size();i++){
                Pieces temp = (Pieces)p2_positions.get(i);

                if(temp.getPositionX() == x){
                    if(temp.getPositionY() == y){
                        player1.get_captured_pieces().add(temp);
                        player2.get_pieces().remove(temp);
                        break;
                    }
                }
            }
        }


        else{
            ArrayList possible_positions = select.get_move_positions();
            player2.is_check = 0;


            for(int i = 0; i<possible_positions.size();i++){
                Pieces temp = (Pieces)possible_positions.get(i);

                if(temp.getPositionX() == x){
                    if(temp.getPositionY()== y){
                        player2.get_pieces().remove(select);
                        player2.get_pieces().add(temp);
                        game_history.add(temp);


                        int kx;
                        char ky;
                        int check = 0;
                        for(int p = 0; p<player1.get_pieces().size();p++){
                            Pieces k = (Pieces)player1.get_pieces().get(p);
                            if(k.get_name() == "King"){
                                kx = k.getPositionX();
                                ky = k.getPositionY();
                                for(int j = 0; j<temp.get_move_positions().size();j++){
                                    Pieces t = (Pieces)temp.get_move_positions().get(j);
                                    if(t.getPositionX() == kx){
                                        if(t.getPositionY()==ky){
                                            check = 1;
                                            break;
                                        }

                                    }
                                }
                                break;
                            }
                        }

                        if(check == 1){
                            player1.is_check = 1;
                        }

                        break;
                    }

                }
            }

            ArrayList p1_positions = player1.get_pieces();
            for(int i = 0; i<p1_positions.size();i++){
                Pieces temp = (Pieces)p1_positions.get(i);

                if(temp.getPositionX() == x){
                    if(temp.getPositionY() == y){
                        player2.get_captured_pieces().add(temp);
                        player1.get_pieces().remove(temp);
                        break;
                    }
                }
            }


        }





    }

    /**
    *
    *  Check that King can move or not
    * @return true if king cannot move, false otherwise
    *
    *
    *
    * */
    public static boolean king_cannot_move() {
        if (player1.get_turn() && player1.is_check == 1) {

            ArrayList p1_pieces = player1.get_pieces();
            ArrayList king_move_positions = new ArrayList();

            for (int i = 0; i < p1_pieces.size(); i++) {
                Pieces temp = (Pieces) p1_pieces.get(i);

                if (temp.get_name() == "King") {
                    king_move_positions = temp.get_move_positions();
                    break;
                }
            }

            ArrayList p2_pieces = player2.get_pieces();

            int size = king_move_positions.size();

            for (int k = 0; k < size; k++) {
                Pieces king_position = (Pieces) king_move_positions.get(king_move_positions.size()-1);
                char p = king_position.getPositionY();
                int q = king_position.getPositionX();

                for (int j = 0; j < p2_pieces.size(); j++) {
                    Pieces temp = (Pieces) p2_pieces.get(j);
                    ArrayList can_move = temp.get_move_positions();

                    for (int z = 0; z < can_move.size(); z++) {
                        Pieces after_move = (Pieces) can_move.get(z);
                        if (after_move.getPositionX() == q) {
                            if (after_move.getPositionY() == p) {
                                king_move_positions.remove(king_position);
                            }
                        }
                    }

                }

            }

            if (king_move_positions.size() == 0) {

                return true;
            }
            else {
                return false;
            }


        }

        else if (!player1.get_turn() && player2.is_check == 1) {
            ArrayList p2_pieces = player2.get_pieces();
            ArrayList king_move_positions = new ArrayList();

            for (int i = 0; i < p2_pieces.size(); i++) {
                Pieces temp = (Pieces) p2_pieces.get(i);

                if (temp.get_name() == "King") {
                    king_move_positions = temp.get_move_positions();
                    break;
                }
            }

            ArrayList p1_pieces = player1.get_pieces();

            int size = king_move_positions.size();

            for (int k = 0; k < size; k++) {
                Pieces king_position = (Pieces) king_move_positions.get(king_move_positions.size()-1);
                char p = king_position.getPositionY();
                int q = king_position.getPositionX();

                for (int j = 0; j < p1_pieces.size(); j++) {
                    Pieces temp = (Pieces) p1_pieces.get(j);
                    ArrayList can_move = temp.get_move_positions();

                    for (int z = 0; z < can_move.size(); z++) {
                        Pieces after_move = (Pieces) can_move.get(z);
                        if (after_move.getPositionX() == q) {
                            if (after_move.getPositionY() == p) {
                                king_move_positions.remove(king_position);
                            }
                        }

                    }

                }

            }

            if (king_move_positions.size() == 0) {

                return true;
            }
            else {
                Pieces t = (Pieces)king_move_positions.get(0);

                return false;
            }


        }


            return false;

    }


    /** Check that other pieces can defend the King
    * @return true if other pieces can defend the King, false otherwise
    *
    * */
    public static boolean can_def_check(){

        if(player1.is_check == 1){
            Pieces last_event = (Pieces)game_history.get(game_history.size()-1);

            ArrayList check_move = last_event.get_move_positions();

            ArrayList p1_pieces_position = player1.get_pieces();

            for(int i= 0; i < check_move.size();i++){
                Pieces check_pieces = (Pieces)check_move.get(i);

                for(int j = 0; j < p1_pieces_position.size(); j++){
                    Pieces temp = (Pieces)p1_pieces_position.get(j);
                    if(temp.get_name() == "King"){
                        continue;
                    }
                    ArrayList each_moving_position = temp.get_move_positions();

                    for(int k = 0; k < each_moving_position.size();k++){
                        Pieces after_temp_move = (Pieces)each_moving_position.get(k);
                        if(after_temp_move.getPositionX() == check_pieces.getPositionX()){
                            if(after_temp_move.getPositionY() == check_pieces.getPositionY()){
                                return true;
                            }
                        }

                    }

                }

            }


            return false;

        }


        else if(player2.is_check == 1){
            Pieces last_event = (Pieces)game_history.get(game_history.size()-1);

            ArrayList check_move = last_event.get_move_positions();

            ArrayList p2_pieces_position = player2.get_pieces();

            for(int i= 0; i < check_move.size();i++){
                Pieces check_pieces = (Pieces)check_move.get(i);

                for(int j = 0; j < p2_pieces_position.size(); j++){
                    Pieces temp = (Pieces)p2_pieces_position.get(j);
                    if(temp.get_name() == "King"){
                        continue;
                    }
                    ArrayList each_moving_position = temp.get_move_positions();

                    for(int k = 0; k < each_moving_position.size();k++){
                        Pieces after_temp_move = (Pieces)each_moving_position.get(k);
                        if(after_temp_move.getPositionX() == check_pieces.getPositionX()){
                            if(after_temp_move.getPositionY() == check_pieces.getPositionY()){
                                return true;
                            }
                        }

                    }

                }

            }


            return false;


        }

    else{
            return true;
        }

    }

    /**
     * Check the checkmate condition
    *
    * @return true if checkmate, false otherwise
    *
    *
    * */
    public static boolean checkmate(){
        if(player1.is_check == 1){
            if(king_cannot_move() && !can_def_check()){
                player2.win = 1;
                return true;
            }
            else{
                return false;
            }
        }
        else if(player2.is_check == 1){
            if(king_cannot_move() && !can_def_check()){
                player1.win = 1;
                return true;
            }
            else{
                return false;
            }
        }
        else {

            return false;
        }
    }



}
