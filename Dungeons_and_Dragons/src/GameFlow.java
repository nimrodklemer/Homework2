public class GameFlow {

    private static GameBoard[] boards;
    private static int currentBoard = 0;

    private static void tick(){
        Tile[][] board = boards[currentBoard].board;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j].onTick();
            }
        }
    }

    public static void main(String[] args) {
        boards = new GameBoard[1];
        Tile[][] newBoard = new Tile[3][3];
        //Upper walls.
        newBoard[0][0] = new Wall(0,0);
        newBoard[0][1] = new Wall(0,1);
        newBoard[0][2] = new Wall(0,2);
        //Lower walls.
        newBoard[2][0] = new Wall(2,0);
        newBoard[2][1] = new Wall(2,1);
        newBoard[2][2] = new Wall(2,2);
        //Right-side wall.
        newBoard[1][2] = new Wall(1,2);
        //Empty-space.
        newBoard[1][1] = new EmptySpace(1,1);
        //Player.
        newBoard[1][0] = new Warrior(1,0, "Julius Caesar", 1);

        boards[0] = new GameBoard(newBoard);
        boolean winLevel = false, death = false;
        while(!winLevel && !death){
            tick();
            // for testing Imma break it now.
            break;
        }
    }
}
