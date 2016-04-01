package cs242chess.chess.control;

import cs242chess.chess.main.MainGame;
import cs242chess.chess.model.Model_image;
import cs242chess.chess.pieces.Pieces;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * Created by Machine on 2016. 2. 12..
 */
public class MouseControl extends MainGame implements MouseListener {


    @Override
    /**
     * All the mouse control
     */
    public void mouseClicked(MouseEvent e) {

        char col = get_col_position_by_click(e.getX());
        int row = get_row_position_by_click(e.getY());


        /* Player 1 turn and select Piece*/
        if(game_start==1 && player1.get_turn()) {
            if (player1.get_turn() && mouse_first_click == 0 ) {
                ArrayList p1_pieces = player1.get_pieces();

                for (int i = 0; i < p1_pieces.size(); i++) {
                    Pieces temp = (Pieces) p1_pieces.get(i);
                    if (temp.getPositionY() == col && temp.getPositionX() == row) {
                        selected_piece = temp;

                        for(int j = 0; j < labels_1.size();j++){
                            Model_image t_image = (Model_image)labels_1.get(i);
                            if(t_image.get_col() == col && t_image.get_row() == row){
                                selected_label = t_image;
                                System.out.println("Selected_label-1 " + t_image.get_col() + " " + t_image.get_row());
                                break;
                            }
                        }


                        is_piece_selected = 1;
                        mouse_first_click = 1;

                        label.setText(String.valueOf(player1.get_turn()) + " " + temp.get_name()+ " " + game_count + " " + mouse_first_click + " " + player1.is_check);
                        break;
                    }
                }
            }

            /*player 1 turn and move piece */
            else if (is_piece_selected == 1 && player1.get_turn()==true && selected_piece.get_color() == true && mouse_first_click == 1) {
                ArrayList pieces_movement_positions = selected_piece.get_move_positions();
                for (int i = 0; i < pieces_movement_positions.size(); i++) {
                    Pieces temp = (Pieces) pieces_movement_positions.get(i);
                    if (temp.getPositionY() == col && temp.getPositionX() == row) {
                        int q = (int) col_map.get(temp.getPositionY());
                        int p = (int) row_map.get(temp.getPositionX());
                        rule.move_piece(selected_piece, row, col);
                        JLabel t_label = selected_label.get_label();
                        t_label.setLocation(q, p);
                        Model_image move = new Model_image(t_label, col, row, selected_label.get_init_col(), selected_label.get_init_row());
                        labels_1.remove(selected_label);
                        undo_history.add(selected_label);
                        labels_1.add(move);
                        Model_image cap = remove_captured_piece(labels_2, col, row);
                        if(cap != null){
                            JLabel remove = cap.get_label();
                            remove.setLocation(prison_location, 700);
                            prison_location = prison_location + 50;
                            capturing_happen = 1;
                            undo_history.add(cap);
                            labels_2.remove(cap);
                        }
                        else{
                            capturing_happen = 0;
                        }
                        is_piece_selected = 0;
                        player1.turn = false;
                        game_count = game_count + 1;

                        if(rule.checkmate()){
                            status_label.setText("Game End");

                            game_start = 0;

                        }


                        break;

                    }
                }
                mouse_first_click = 0;


            }




   }


        /* player 2 turn and select piece */
        if(game_start == 1 && player1.get_turn() == false){
            if(player1.get_turn() == false && mouse_first_click == 0) {
                ArrayList p2_pieces = player2.get_pieces();

                for (int i = 0; i < p2_pieces.size(); i++) {
                    Pieces temp = (Pieces) p2_pieces.get(i);
                    if (temp.getPositionY() == col && temp.getPositionX() == row) {
                        selected_piece = temp;

                        for(int j = 0; j < labels_2.size();j++){
                            Model_image t_image = (Model_image)labels_2.get(i);
                            if(t_image.get_col() == col && t_image.get_row() == row){
                                selected_label = t_image;
                                System.out.println("Selected_label-2 " + t_image.get_col() + " " + t_image.get_row());
                                break;
                            }
                        }
                        is_piece_selected = 1;
                        mouse_first_click = 1;
                        label.setText(String.valueOf(player1.get_turn()) + " " + temp.get_name() + " " + game_count + " " + is_piece_selected + " " + player1.is_check);
                        break;
                    }
                }
            }

            /* Player 2 turn and move piece */
           else if (is_piece_selected == 1 && player1.get_turn() == false && selected_piece.get_color() == false && mouse_first_click == 1) {

                ArrayList pieces_movement_positions = selected_piece.get_move_positions();
                for (int i = 0; i < pieces_movement_positions.size(); i++) {
                    Pieces temp = (Pieces) pieces_movement_positions.get(i);
                    if (temp.getPositionY() == col && temp.getPositionX() == row) {
                        int q = (int) col_map.get(temp.getPositionY());
                        int p = (int) row_map.get(temp.getPositionX());
                        rule.move_piece(selected_piece, row, col);
                        JLabel t_label = selected_label.get_label();
                        t_label.setLocation(q, p);
                        Model_image move = new Model_image(t_label, col, row, selected_label.get_init_col(), selected_label.get_init_row());
                        labels_2.remove(selected_label);
                        undo_history.add(selected_label);
                        labels_2.add(move);
                        Model_image cap = remove_captured_piece(labels_1, col, row);
                        if(cap != null){
                            JLabel remove = cap.get_label();
                            remove.setLocation(prison_location, 750);
                            prison_location = prison_location + 50;
                            capturing_happen = 1;
                            undo_history.add(cap);
                            labels_1.remove(cap);

                        }
                        else{
                            capturing_happen = 0;
                        }
                        is_piece_selected = 0;
                        selected_label.setLocation(q, p);
                        player1.turn = true;
                        game_count = game_count + 1;

                        if(rule.checkmate()){
                            status_label.setText("Game End");

                            game_start = 0;

                        }

                        break;

                    }
                }
                mouse_first_click = 0;

            }
            else{
                mouse_first_click = 0;
            }
        }

    }


    @Override

    public void mouseEntered(MouseEvent e) {

        int x = e.getX();

        int y = e.getY();



    }



    @Override

    public void mouseExited(MouseEvent e) {

        int x = e.getX();

        int y = e.getY();



    }



    @Override

    public void mousePressed(MouseEvent e) {

        int x = e.getX();

        int y = e.getY();




    }



    @Override

    public void mouseReleased(MouseEvent e) {

        int x = e.getX();

        int y = e.getY();



    }

    private char get_col_position_by_click(int clicked_positions){
        if(100 < clicked_positions && clicked_positions < 150){
            return 'A';
        }
        else if(150 < clicked_positions && clicked_positions < 200){
            return 'B';
        }
        else if(200 < clicked_positions && clicked_positions < 250){
            return 'C';
        }
        else if(250 < clicked_positions && clicked_positions < 300){
            return 'D';
        }
        else if(300 < clicked_positions && clicked_positions < 350){
            return 'E';
        }
        else if(350 < clicked_positions && clicked_positions < 400){
            return 'F';
        }
        else if(400 < clicked_positions && clicked_positions < 450){
            return 'G';
        }
        else if(450 < clicked_positions && clicked_positions < 500){
            return 'H';
        }
        else{
            return 'I';
        }
    }

    private int get_row_position_by_click(int clicked_positions){
        if(100 < clicked_positions && clicked_positions < 150){
            return 8;
        }
        else if(150 < clicked_positions && clicked_positions < 200){
            return 7;
        }
        else if(200 < clicked_positions && clicked_positions < 250){
            return 6;
        }
        else if(250 < clicked_positions && clicked_positions < 300){
            return 5;
        }
        else if(300 < clicked_positions && clicked_positions < 350){
            return 4;
        }
        else if(350 < clicked_positions && clicked_positions < 400){
            return 3;
        }
        else if(400 < clicked_positions && clicked_positions < 450){
            return 2;
        }
        else if(450 < clicked_positions && clicked_positions < 500){
            return 1;
        }
        else{
            return 9;
        }
    }

    private Model_image remove_captured_piece(ArrayList captured, char y, int x){

        for(int i = 0; i < captured.size(); i++){
            Model_image temp = (Model_image)captured.get(i);
            if(temp.get_col() == y && temp.get_row() == x){
                return temp;
            }
        }
      return null;
    }


}
