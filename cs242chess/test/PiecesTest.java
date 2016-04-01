package cs242chess.test;

import cs242chess.chess.main.MainGame;
import cs242chess.chess.players.Player;
import org.junit.Before;
import org.junit.Test;
import cs242chess.chess.pieces.*;

import java.util.ArrayList;

import static cs242chess.chess.rule.Rules.move_piece;
import static org.junit.Assert.*;

/**
 *
 * @author : Yeonsung Kim
 * Class PieceTest
 */
public class PiecesTest extends MainGame {

    /**  init test */
    @Before
    public void init_test(){
        player1 = new Player(true, 'E', 1);
        player2 = new Player(false, 'E', 8);
        game_count = 0;
        game_history.clear();

    }

    /** print out possible move postion */
    public void show_possible_movement(Pieces p){
        ArrayList mov_t = p.get_move_positions();
        for(int i =0; i<mov_t.size();i++){
            Pieces temp = (Pieces)mov_t.get(i);
            System.out.println("Possible movement " + temp.get_name() + " " + temp.getPositionY() + " " + temp.getPositionX());
        }
    }

    private Pieces get_piece(char y, int x, ArrayList p){
        Pieces t = new Pieces();
        for(int i = 0;i<p.size();i++){
            Pieces temp = (Pieces)p.get(i);
            if(temp.getPositionY()==y){
                if(temp.getPositionX()==x){
                    t = temp;
                    break;
                }
            }
        }
        return t;
    }

    /** Test for Pawn initial movement */
    @Test
    public void Pawn_init() {

        Pieces p = get_piece('A', 2, player1.get_pieces());

        ArrayList pawn_positions = p.get_move_positions();

        assertEquals(2, pawn_positions.size());


    }

    /** Test for Rook initial movement */
    @Test
    public void Rook_init(){

        Pieces p = get_piece('A', 1, player1.get_pieces());

        ArrayList rook_positions = p.get_move_positions();

        assertEquals(0, rook_positions.size());
    }

    /** Test for Bishop initial movement */
    @Test
    public void Bishop_init(){

        Pieces p = get_piece('C', 1, player1.get_pieces());

        ArrayList bishop_positions = p.get_move_positions();

        assertEquals(0, bishop_positions.size());
    }

    /** Test for Knight initial movement */
    @Test
    public void Knight_init(){

        Pieces p = get_piece('B', 1, player1.get_pieces());

        ArrayList knight_positions = p.get_move_positions();

        assertEquals(2, knight_positions.size());

    }

    /** Test for Queen initial movement */
    @Test
    public void Queen_init(){

        Pieces p = get_piece('D', 1, player1.get_pieces());

        ArrayList queen_positions = p.get_move_positions();

        assertEquals(0, queen_positions.size());

    }

    /** Test for King initial movement */
    @Test
    public void MoveKing(){

        Pieces p = get_piece('E', 1, player1.get_pieces());

        ArrayList king_positions = p.get_move_positions();

        assertEquals(0, king_positions.size());

    }

    /** Test for piece moving t */
    @Test
    public void Move_One_Pieces(){

        Pieces p = get_piece('A', 2, player1.get_pieces());

        move_piece(p, 4, 'A');

        Pieces t = get_piece('A', 4, player1.get_pieces());
        game_count = 2;

        assertEquals(1, t.get_move_positions().size());



    }

    /** Test for capturing */
    @Test
    public void Capturing_test_Pawn(){

        Pieces p = get_piece('A', 2, player1.get_pieces());

        move_piece(p, 4, 'A');
        game_count = 1;
        player1.turn = false;



        Pieces t= get_piece('B', 7, player2.get_pieces());


        move_piece(t, 5, 'B');

        game_count = 2;

        player1.turn = true;
        Pieces c = get_piece('A', 4, player1.get_pieces());

        move_piece(c, 5, 'B');
        game_count = 3;

        Pieces temp = (Pieces)player1.get_captured_pieces().get(0);

        assertEquals('B', temp.getPositionY());
        assertEquals(5, temp.getPositionX());


    }


    @Test
    public void custom_piece_one(){
        Pieces p = new Custom_Piece_One('H', 4, true);
        player1.add_pieces(p);

        ArrayList t = p.get_move_positions();

        assertTrue(t.size() != 0);
    }


    @Test
    public void custom_piece_two(){
        Pieces p = new Custom_Piece_Two('H', 4, true);
        player1.add_pieces(p);

        ArrayList t = p.get_move_positions();

        assertTrue(t.size() != 0);
    }



}