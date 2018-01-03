//package Kalah;
//package edu.lmu.cs.networking;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//public class Server {
//    /**
//     * Runs the application. Pairs up clients that connect.
//     */
//    public static void main(String[] args) throws Exception {
//        ServerSocket listener = new ServerSocket(8901);
//        System.out.println("Server is running");
//        try {
//            while (true) {
//                Game game = new Game();
//                Game.Player player1 = game.new Player(listener.accept(), '');
//                Game.Player player2 = game.new Player(listener.accept(), '');
//                player1.setOpponent(player2);
//                player2.setOpponent(player1);
//                game.currentPlayer = player1;
//                player1.start();
//                player2.start();
//            }
//        } finally {
//            listener.close();
//        }
//    }
//}
