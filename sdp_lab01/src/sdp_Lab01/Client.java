package sdp_Lab01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("O IP e a porta do servidor de ambos os clientes tem de ser igual para alem disso a unica porta disponivel no servidor é 9090");
        System.out.println("sugestão para IP: 127.0.0.1");
        System.out.println("sugestao para porta: 9090");
        System.out.println("Indique a IP do servidor:");
        String p = scan.next();
        System.out.println("Indique a porta do servidor:");
        int k=scan.nextInt();
        try {
            Socket socket = new Socket(p, k);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try {
                System.out.println("O que voce deseja fazer:\nQ - Sair \nL - Ver a lista.\nR - Adicionar algo a lista.");
                while (true) {
                    System.out.println(">");
                    String command = keyboard.readLine();
                    out.println(command);
                    switch (command) {
                        case "R", "r" -> {
                            System.out.println("Indique o que pretende adicionar tendo em conta que limite maximo de carateres é 120");
                            String paraLista = keyboard.readLine();
                            out.println(paraLista);
                        }
                        case "Q", "q" -> {
                            out.println(command);
                            System.exit(1);
                        }
                    }
                    String serverResponse = input.readLine();
                    System.out.println("A resposta do servidor: " + serverResponse);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            scan.close();
            socket.close();
            input.close();
            keyboard.close();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}