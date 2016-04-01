package cs242chess.chess.view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import com.sun.javafx.sg.prism.NGShape;
import cs242chess.chess.main.MainGame;
import cs242chess.chess.model.Model_image;
import cs242chess.chess.pieces.*;
import cs242chess.chess.players.Player;

/** Class Display */
public class Display extends MainGame {



    public static JButton start_button;

    public static JButton restart_button1;

    public static JButton restart_button2;

    public static JButton forfeit_button;

    public static JButton undo_button;



    /** Draw a board
     * @param g Graphics object that has board
     * */
    public void draw_board(Graphics g){
        for(int i = 100; i <= 400; i+=100){
            for(int j = 100; j <= 400; j+=100){
                g.clearRect(i, j, 50, 50);
            }
        }

        for(int i = 150; i <= 450; i+=100){
            for(int j = 150; j <= 450; j+=100){
                g.clearRect(i, j, 50, 50);
            }
        }

    }

    /** Draw initial pieces
     * @param f Graphics object that has pieces
     * */
    public void draw_init_pieces(JFrame f){
        ArrayList p1_pieces = player1.get_pieces();
        ArrayList p2_pieces = player2.get_pieces();

        for(int i = 0; i < p1_pieces.size(); i++){
            Pieces temp = (Pieces)p1_pieces.get(i);
            ImageIcon t_image = new ImageIcon(this.getClass().getResource("/img/w_" + temp.get_name() + ".png"));
            int  p = (int)col_map.get(temp.getPositionY());
            int q = (int)row_map.get(temp.getPositionX());

            JLabel added = new JLabel(t_image);
            added.setSize(new Dimension(50, 50));
            added.setLocation(p, q);
            Model_image add_image = new Model_image(added, temp.getPositionY(), temp.getPositionX(), temp.getPositionY(), temp.getPositionX());

            labels_1.add(add_image);
            player1_init_pieces.add(add_image);
            f.getContentPane().add(added);


        }

        for(int i = 0; i < p2_pieces.size(); i++){
            Pieces temp = (Pieces)p2_pieces.get(i);

            ImageIcon t_image = new ImageIcon(this.getClass().getResource("/img/b_" + temp.get_name() + ".png"));
            int  p = (int)col_map.get(temp.getPositionY());
            int q = (int)row_map.get(temp.getPositionX());

            JLabel added = new  JLabel(t_image);
            added.setSize(new Dimension(50,50));
            added.setLocation(p, q);
            Model_image add_image = new Model_image(added, temp.getPositionY(), temp.getPositionX(), temp.getPositionY(), temp.getPositionX());
            labels_2.add(add_image);
            player2_init_pieces.add(add_image);
            f.getContentPane().add(added);
        }



    }

    /**
     * Initialize the Buttons
     * @param f frame that have buttons
     */
    public void init_buttons(JFrame f){

        start_button = new JButton("start");
        start_button.setSize(new Dimension(100, 50));
        start_button.setLocation(200, 600);

        start_button.addActionListener(e -> {
            status_label.setText("GAME START");
            player1 = new Player(true, 'E', 1);
            player2 = new Player(false, 'E', 8);
            is_piece_selected = 0;
            mouse_first_click = 0;
            player1.turn = true;
            game_start = 1;

        });

        restart_button1 = new JButton("Restart1");
        restart_button1.setSize(new Dimension(100, 50));
        restart_button1.setLocation(230, 520);

        restart_button1.addActionListener(e -> {
            restart1 = 1;
            if (restart2 == 1) {
                labels_1 = new ArrayList();
                labels_2 = new ArrayList();
                game_start = 0;

                status_label.setText("RESTARTING");

                for (int i = 0; i < player1_init_pieces.size(); i++) {
                    Model_image temp = (Model_image) player1_init_pieces.get(i);
                    int q = (int) col_map.get(temp.get_init_col());
                    int p = (int) row_map.get(temp.get_init_row());
                    JLabel t_label = temp.get_label();
                    t_label.setLocation(q, p);
                    labels_1.add(temp);

                }

                for (int i = 0; i < player2_init_pieces.size(); i++) {
                    Model_image temp = (Model_image) player2_init_pieces.get(i);
                    int q = (int) col_map.get(temp.get_init_col());
                    int p = (int) row_map.get(temp.get_init_row());
                    JLabel t_label = temp.get_label();
                    t_label.setLocation(q, p);

                    labels_2.add(temp);

                }

                restart1 = 0;
                restart2 = 0;
            }
        });

        restart_button2 = new JButton("Restart2");
        restart_button2.setSize(new Dimension(100, 50));
        restart_button2.setLocation(360, 520);

        restart_button2.addActionListener(e -> {
            restart2 = 1;
            if (restart1 == 1) {
                labels_1 = new ArrayList();
                labels_2 = new ArrayList();
                game_start = 0;

                status_label.setText("RESTARTING");

                for (int i = 0; i < player1_init_pieces.size(); i++) {
                    Model_image temp = (Model_image) player1_init_pieces.get(i);
                    int q = (int) col_map.get(temp.get_init_col());
                    int p = (int) row_map.get(temp.get_init_row());
                    JLabel t_label = temp.get_label();
                    t_label.setLocation(q, p);
                    labels_1.add(temp);

                }

                for (int i = 0; i < player2_init_pieces.size(); i++) {
                    Model_image temp = (Model_image) player2_init_pieces.get(i);
                    int q = (int) col_map.get(temp.get_init_col());
                    int p = (int) row_map.get(temp.get_init_row());
                    JLabel t_label = temp.get_label();
                    t_label.setLocation(q, p);

                    labels_2.add(temp);

                }
                restart1 = 0;
                restart2 = 0;
            }
        });

        forfeit_button = new JButton("Forfeit");
        forfeit_button.setSize(new Dimension(100, 50));
        forfeit_button.setLocation(490, 520);

        forfeit_button.addActionListener(e -> {
            status_label.setText("GAME END");

            if (player1.get_turn()) {
                player2.win = 1;
                status_label.setText("Black Win");
            } else {
                status_label.setText("White Win");
                player1.win = 1;
            }
            game_start = 0;
            game_end = 1;
        });

        undo_button = new JButton("Undo");
        undo_button.setSize(new Dimension(100, 50));
        undo_button.setLocation(100, 600);
        undo_button.addActionListener(e->{

        if(undo_history.size() > 0) {
            if (player1.get_turn()) {
                Model_image temp = (Model_image) labels_2.get(labels_2.size() - 1);
                Model_image u_temp = (Model_image) undo_history.get(undo_history.size() - 1);
                Model_image c_temp;
                if(capturing_happen == 1){
                    c_temp = (Model_image) undo_history.get(undo_history.size()-2);
                    labels_1.add(u_temp);

                    JLabel undo2 = c_temp.get_label();
                    int q = (int) col_map.get(c_temp.get_col());
                    int p = (int) row_map.get(c_temp.get_row());

                    undo2.setLocation(q, p);
                    undo_history.remove(c_temp);

                    labels_2.remove(temp);
                    labels_2.add(c_temp);
                }
                else {
                    labels_2.remove(temp);
                    labels_2.add(u_temp);
                }
                JLabel undo_piece = u_temp.get_label();
                int q = (int) col_map.get(u_temp.get_col());
                int p = (int) row_map.get(u_temp.get_row());



                undo_piece.setLocation(q, p);

                undo_history.remove(u_temp);

                game_count = game_count - 1;
                player1.turn = false;

                if(game_count < 0){
                    game_count = 0;
                }

            }
            else {

                Model_image temp = (Model_image) labels_1.get(labels_1.size() - 1);
                Model_image u_temp = (Model_image) undo_history.get(undo_history.size() - 1);
                Model_image c_temp;
                if(capturing_happen == 1){
                    c_temp = (Model_image) undo_history.get(undo_history.size()-2);
                    labels_1.add(u_temp);

                    JLabel undo2 = c_temp.get_label();
                    int q = (int) col_map.get(c_temp.get_col());
                    int p = (int) row_map.get(c_temp.get_row());

                    undo2.setLocation(q, p);
                    undo_history.remove(c_temp);


                    labels_2.remove(temp);
                    labels_2.add(c_temp);
                }
                else {
                    labels_1.remove(temp);
                    labels_1.add(u_temp);
                }

                JLabel undo_piece = u_temp.get_label();
                int q = (int) col_map.get(u_temp.get_col());
                int p = (int) row_map.get(u_temp.get_row());

                undo_piece.setLocation(q, p);
                undo_history.remove(u_temp);
                game_count = game_count - 1;
                player1.turn = true;

                if(game_count < 0){
                    game_count = 0;
                }

            }

        }

        });


        f.getContentPane().add(start_button);
        f.getContentPane().add(restart_button1);
        f.getContentPane().add(restart_button2);
        f.getContentPane().add(forfeit_button);
        f.getContentPane().add(undo_button);

    }



    /** Make Hashmap for board */
    public void make_map(){
        char c = 'A';
        int r = 8;
        int k = 0;
        for(int i = 100; i<500; i+=50){
            col_map.put((char)(c+k), i);
            row_map.put(r-k, i);
            k++;
        }
    }







}
