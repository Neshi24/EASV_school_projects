package com.example.tiktakmalicek;

import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private List<Board> boards; //board stash
    private List<Player> players; //player stash

    public GameServer(){ //Constructor for game server
        this.boards = new ArrayList<>(); //init ArrayList of Board(s)
        this.players = new ArrayList<>(); //init ArrayList of Player(s)
    }

    private Board addBoard(){
        Board board = new Board(3, 3); //creates new board of (cols) & (rows)
        this.boards.add(board); //adds the created board to board stash
        return board; //returns the newly created board
    }

    public void addPlayer(String name, String character, long id){
        Player newPlayer = new Player(name, character, id); //creates new player
        Board board; //board to put the new player in

        if(players.size() % 2 == 0){ //how many players we have?
            board = addBoard(); //creates new board for 2 players
        } else {
            board = boards.get(Math.floorDiv(players.size(), 2)); //puts 1st & 2nd player into one board
        }
        board.addPlayer(newPlayer); //adds player to board
        players.add(newPlayer); //adds player to overall player stash

    }

    private class Board implements IGameModel{ //Each Board object is one game

        private List<Square> squares; //Each board has many squares
        private int cols, rows; //Each board has many cols & rows
        private List<Player> players; //Each board has many Players

        public Board(int cols, int rows){ //Constructor for board
            squares = new ArrayList<>(); //init Square stash
            players = new ArrayList<>(); //init Player stash
            this.cols = cols; //set cols
            this.rows = rows; //set rows
            for(int i = 0; i < cols; i++){ //loop cols
                for(int j = 0; j < rows; j++){ //loop rows
                    squares.add(new Square(i, j)); //create square [@col, @row] & add it to stash
                }
            }
        }

        public void addPlayer(Player p){
            players.add(p); //adds new player to stash
        }

        @Override
        public int getNextPlayer() {
            //implement
            return 0;
        }

        @Override
        public boolean play(int col, int row) {
            //implement
            return false;
        }

        @Override
        public boolean isGameOver() {
            //implement
            return false;
        }

        @Override
        public int getWinner() {
            //implement
            return 0;
        }

        @Override
        public void newGame() {
            //implement
        }

        private class Square{ //Each Board has many squares, but they have many things in common
            String sign; //sign == "X" || sign == "O" || sign == "" (read as x or o or blank)
            int col, row; //col & row of square

            public Square(int col, int row){
                this.sign = ""; //square is blank on init
                this.col = col;
                this.row = row;
            }

            public void setSign(String xo){
                this.sign = xo; //sets the sign of square
            }
        }

    }

    private class Player{ //Game server has many players & they have much in common
        private String sign; //the sign of player
        private String name; //name of player
        private long id; //id assigned by socket connection

        public Player(String name, String sign, long id){ //constructor
            setName(name);
            setSign(sign);
            setId(id);
        }

        public String getSign(){
            return this.sign;
        }

        public String getName(){
            return this.name;
        }

        public long getId(){
            return this.id;
        }

        public void setSign(String sign){
            this.sign = sign;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setId(long id){
            this.id = id;
        }
    }
}
