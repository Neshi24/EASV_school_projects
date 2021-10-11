package com.example.tiktakmalicek;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private int row, col;
    private String name = "PLAYER";
    private String sign;

    public Move(int row, int col, String sign){
        this.row = row;
        this.col = col;
        this.sign = sign;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {return this.col;}

    public String getName() {
        return this.name;
    }

    public String getSign() {
        return this.sign;
    }
    public void setSign(String sign){
        this.sign = sign;
    }

    public void altSign(){
        this.setSign(this.getSign().equals("X") ? "O" : "X");
    }

    //for Client
    public static Move stringToMove(String input){
        String[] commands = input.split(",");
        if(commands.length > 2){
            return new Move(Integer.parseInt(commands[0]) , Integer.parseInt(commands[1]), commands[2]);
        }
        return null;
    }

    //for Client
    public static List<Move> solveMoves(String input){
        List<Move> moves = new ArrayList<Move>();
        for (String move :
                input.split(";")) {
            moves.add(stringToMove(move));
        }
        return moves;
    }

    //for Server
    public static String solveMoves(List<Move> moves){
        String out = "";
        for (Move move :
                moves) {
            out += move.getRow() + "," + move.getCol() + "," + move.getSign() + ";";
        }
        return out;
    }

    //for Client
    public String toString(){
        return this.row + "," + this.col + "," + this.sign;
    }

    public static Move mousePointToMove(double mX, double mY, int blockSize, String sign){
        int col = (int) mX / blockSize;
        int row = (int) mY / blockSize;
        //System.out.println(col + " " + row);
        return new Move(col, row, sign);
    }





}
