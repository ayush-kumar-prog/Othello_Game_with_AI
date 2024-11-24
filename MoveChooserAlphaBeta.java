/**
 * Solution code for Comp24011 Reversi lab
 *
 * @author r36859ak
 */

public class MoveChooserAlphaBeta extends MoveChooser {
    /**
     * MoveCooser implementation MoveChooserAlphaBeta(int)
     *
     * @param   searchDepth The requested depth for minimax search
     */
    public MoveChooserAlphaBeta(int searchDepth) {
        // Add object initialisation code...
        super("HiyaTheGreatBillo", searchDepth);
    }

    /**
     * Need to implement chooseMove(BoardState,Move)
     *
     * @param   boardState  The current state of the game board
     *
     * @param   hint        Skip move or board location clicked on the UI
     *                      This parameter should be ignored!
     *
     * @return  The move chosen by alpha-beta pruning as discussed in the course
     */

    public Move chooseMove(BoardState boardState, Move hint) {
        // Add main alpha-beta pruning code... ***DONE***
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : boardState.getLegalMoves()){
            BoardState copy = boardState.deepCopy();
            copy.makeLegalMove(move);
            int moveValue = alphabeta(copy, searchDepth - 1, alpha, beta, false);
            if (moveValue > bestValue){
                bestValue = moveValue;
                bestMove = move;
            }
            alpha = Math.max(alpha, moveValue);
        }
        return bestMove;
    }

    private int alphabeta (BoardState boardState, int depth, int alpha, int beta, boolean maximizingPlayer){
        //base case
        if (depth == 0 || boardState.gameOver()){
            return boardEval(boardState);
        }
        
        //if max's turn
        if (maximizingPlayer){
            int max_eval = Integer.MIN_VALUE;
            for (Move move : boardState.getLegalMoves()){
                if (depth == searchDepth - 1) {
                    // Use save and restore at the first depth
                    boardState.save();
                    boardState.makeLegalMove(move);
                    int eval = alphabeta(boardState, depth - 1, alpha, beta, false);
                    boardState.restore();
                    max_eval = Math.max(eval, max_eval);
                    alpha = Math.max(max_eval, alpha);
                    if (beta <= alpha){
                        break;
                    }
                } else {
                    // Use deepCopy for deeper levels
                    BoardState copy = boardState.deepCopy();
                    copy.makeLegalMove(move);
                    int eval = alphabeta(copy, depth - 1, alpha, beta, false);
                    max_eval = Math.max(eval, max_eval);
                    alpha = Math.max(max_eval, alpha);
                    if (beta <= alpha){
                        break;
                    } 
                }
            }
            return max_eval;
        }

        //if min's turn
        else {
            int min_eval = Integer.MAX_VALUE;
            for (Move move : boardState.getLegalMoves()){
                if (depth == searchDepth - 1) {
                    // Use save and restore at the first depth
                    boardState.save();
                    boardState.makeLegalMove(move);
                    int eval = alphabeta(boardState, depth - 1, alpha, beta, true);
                    boardState.restore();
                    min_eval = Math.min(eval, min_eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha){
                        break;
                    }
                } else {
                    // Use deepCopy for deeper levels
                    BoardState move_copy = boardState.deepCopy();
                    move_copy.makeLegalMove(move);
                    int eval = alphabeta(move_copy, depth - 1, alpha, beta, true);
                    min_eval = Math.min(eval, min_eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha){
                        break;
                    }
                }
            }
            return min_eval;
        }
    }

    /**
     *
     * @param   boardState  The current state of the game board
     *
     * @return  The value of the board using Norvig's weighting of squares
     */
    private static final int[][] WEIGHTS = 
    {
                        {120,-20,20,5,5,20,-20,120},
                        {-20,-40,-5,-5,-5,-5,-40,-20},
                        {20,-5,15,3,3,15,-5,20},
                        {5,-5,3,3,3,3,-5,5},
                        {5, -5, 3, 3, 3, 3, -5, 5},
                        {20, -5, 15, 3, 3, 15, -5, 20},
                        {-20, -40, -5, -5, -5, -5, -40, -20},
                        {120, -20, 20, 5, 5, 20, -20, 120}
    };

    public int boardEval(BoardState boardState) {
        // Add board evaluation code...
        int score = 0;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                int piece = boardState.getContents(i, j);
                if (piece == 1) {
                    score += WEIGHTS[i][j];
                }
                else if (piece == -1){
                    score -= WEIGHTS[i][j];
                }
            }
        }

        return score;
    }
}

/* vim:set et ts=4 sw=4: */
