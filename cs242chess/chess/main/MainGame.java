package cs242chess.chess.main;



import cs242chess.chess.control.MouseControl;
import cs242chess.chess.model.Model_image;
import cs242chess.chess.pieces.*;
import cs242chess.chess.players.Player;
import cs242chess.chess.rule.Rules;
import cs242chess.chess.view.Display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainGame class
 */
public class MainGame extends JPanel{

    /** Main game player1 */
    protected static Player player1 = new Player(true, 'E', 1);

    /** Main game player1 */
    protected static Player player2 = new Player(false, 'E', 8);

    protected static Rules rule = new Rules();

    /** Game count */
    protected static int game_count = 0;

    /** Game history */
    protected static ArrayList game_history = new ArrayList();

    /** Game piece images */
    protected static ArrayList labels_1 = new ArrayList();
    protected static ArrayList labels_2 = new ArrayList();

    /**
     * check the mouse click to piece
     */
    protected static int mouse_first_click = 0;



    /** frame that all components are added */
    public static JFrame frame;


    /** Map for col */
    public static HashMap col_map = new HashMap();

    /** Map for row */
    public static HashMap row_map = new HashMap();


    /** Game Display */
    protected static Display display = new Display();

    /** Check the piece is selected */
    protected static int is_piece_selected = 0;


    /** status of the players want to restart or not*/
    protected static int restart1 = 0;
    protected static int restart2 = 0;


    /** label of piece selected */
    public static JLabel label;

    /** Game status */
    public static JLabel status_label;

    /** Game status */
    protected static int game_start = 0;
    protected static int game_end = 0;


    /** selected pieces and images */
    protected static Pieces selected_piece;
    protected static Model_image selected_label;

    /** Game reset setting */
    protected static ArrayList player1_init_pieces = new ArrayList();
    protected static ArrayList player2_init_pieces = new ArrayList();

    /** Undo histoty */
    protected static ArrayList undo_history = new ArrayList();

    protected static int capturing_happen = 0;


    /** all the capturing pieces */
    protected static int prison_location = 100;

    /**
     *  change check condition of player1
     *  @param p Piece need to be checked
     *  */
    public void is_check_player1(Pieces p){
        if(p.get_name() == "King"){
            player1.is_check = 1;
        }
    }

    /**
     *  change check condition of player2
     *   @param p Piece need to be checked
     *   */
    public void is_check_player2(Pieces p){
        if(p.get_name() == "King"){
            player2.is_check = 1;
        }
    }

    /**
     * Print current game histroy
     * */
    public void print_game_history(){
        System.out.println("---------------game history--------------------");
        for(int i=0; i < game_history.size();i++){
            Pieces t = (Pieces)game_history.get(i);
            System.out.println(t.get_name() + " " + t.getPositionY() + " " + t.getPositionX());
        }
    }


    /** Display size 600 * 600, Board size 400 * 400
     * @param g Graphics object
     * */
    public void paint(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(100, 100, 400, 400);  //100~500 , 100~500
        display.draw_board(g);

    }


    /** print out possible move postion */
    public void show_possible_movement(Pieces p){
        ArrayList mov_t = p.get_move_positions();
        for(int i =0; i<mov_t.size();i++){
            Pieces temp = (Pieces)mov_t.get(i);
            System.out.println("Possible movement " + temp.get_name() + " " + temp.getPositionY() + " " + temp.getPositionX());
        }
    }



    /** Main Game system */
    public static void main(String[] args){

        frame = new JFrame();
        frame.setSize(600, 900);

        display.make_map();
        display.init_buttons(frame);
        display.draw_init_pieces(frame);
        label = new JLabel("Mouse Event");
        label.setSize(new Dimension(150, 50));
        label.setLocation(100, 520);
        frame.getContentPane().add(label);
        status_label = new JLabel("Status");
        status_label.setSize(new Dimension(150, 50));
        status_label.setLocation(400, 620);
        frame.getContentPane().add(status_label);
        frame.getContentPane().addMouseListener(new MouseControl());
        frame.getContentPane().add(new MainGame());
        frame.setLocationRelativeTo(null);
       // frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);








    }


}
