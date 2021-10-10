package com.example.tiktakmalicek;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private int x, y;
    private String name = "PLAYER";
    private String sign;

    public Move(int x, int y, String sign){
        this.x = x;
        this.y = y;
        this.sign = sign;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    public String getSign() {
        return sign;
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
            out += move.getX() + "," + move.getY() + "," + move.getSign() + ";";
        }
        return out;
    }

    //for Client
    public String toString(){
        return this.x + "," + this.y + "," + this.sign;
    }
}
