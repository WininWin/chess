package cs242chess.test;

import cs242chess.chess.main.MainGame;
import cs242chess.chess.pieces.Pieces;
import cs242chess.chess.players.Player;


import org.junit.Before;
import org.junit.Test;
import cs242chess.chess.pieces.*;

import java.util.ArrayList;

import static cs242chess.chess.rule.Rules.*;
import static org.junit.Assert.*;

/**
 * @author : Yeonsung Kim
 * Test for Rule class
 */
public class RulesTest extends MainGame {


    @Before
    public void init_test(){
        player1 = new Player(true, 'E', 1);
        player2 = new Player(false, 'E', 8);
        game_history.clear();
        game_count = 0;

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

    /*Test for the checkmate Condition */

    @Test
    public void checkmate_Test(){

        player1.remove_all_pieces();
        King k = new King('E', 1, true);
        Rook r = new Rook('A', 1, true);
        player1.add_pieces(k);
        player1.add_pieces(r);
        player1.print_current_pieces_position(game_count);

        player2.remove_all_pieces();
        Pawn f = new Pawn('F', 7, false);
        Pawn g = new Pawn('G', 7, false);
        Pawn h = new Pawn('H', 7, false);
        King c = new King('G', 8, false);
        Rook v = new Rook('H', 8, false);
        player2.add_pieces(f);
        player2.add_pieces(g);
        player2.add_pieces(h);
        player2.add_pieces(c);
        player2.add_pieces(v);
        player2.print_current_pieces_position(game_count);

        Pieces p = get_piece('A', 1, player1.get_pieces());

        move_piece(p, 8, 'A');

        game_count = 1;

        player1.print_current_pieces_position(game_count);

        player1.turn = false;

        print_game_history();

        assertEquals(1, player2.is_check);

        boolean king_move = king_cannot_move();

        boolean can_def = can_def_check();

        boolean check_win = checkmate();

        assertEquals(false, can_def);

        assertEquals(true, king_move);
        assertEquals(true, check_win);

    }

}
