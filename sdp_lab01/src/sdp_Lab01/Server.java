package sdp_Lab01;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server {
    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("primeiro");
        lista.add("segundo");
        lista.add("terceiro");
        try {
            ServerSocket cliente1 = new ServerSocket(9090, 2);
            System.out.println("[SERVER] Esperando pelo cliente");
            Socket client = cliente1.accept();
            System.out.println("[SERVER] Primeiro cliente connectado!");
            Socket client2 = cliente1.accept();
            System.out.println("[SERVER] Segundo cliente connectado!");
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            try {
                while (true) {
                    //String request = in.readLine();
                    switch (in.readLine()) {
                        case "L", "l" -> out.println(lista);
                        case "R", "r" -> {
                            String reque = in.readLine();
                            if (reque.length() > 120) {
                                out.println("O pretendido nao foi adicionado pois ultrapassou 120 carateres");
                            }
                            if (reque.length() <= 120) {
                                lista.add(reque);
                                out.println("O " + reque + " foi adicionado a lista");
                            }
                        }
                        case "Q", "q" -> System.exit(1);

                    }
                    //if (!in.readLine().equals("L") && !in.readLine().equals("R") && !in.readLine().equals("r") && !in.readLine().equals("l")) {
                    //    out.println("O carater " + in.readLine() + " é invalido, digite novamente um caracter valido tendo em conta que L - .... R - .... Q - .....");
                    //}
                    //String request2 = in2.readLine();
                    switch (in2.readLine()) {
                        case "L", "l" -> out2.println(lista);
                        case "R", "r" -> {
                            String reque2 = in2.readLine();
                            if (reque2.length() > 120) {
                                out2.println("////////////////");
                            }
                            if (reque2.length() <= 120) {
                                lista.add(reque2);
                                out2.println("O " + reque2 + " foi adicionado a lista");
                            }
                        }
                        case "Q", "q" -> System.exit(1);
                    }
                    //if (!in2.readLine().equals("L") && !in2.readLine().equals("R") && !in2.readLine().equals("r") && !in2.readLine().equals("l")) {
                    //    out.println("O carater " + in2.readLine() + " é invalido, digite novamente um caracter valido tendo em conta que L - .... R - .... Q - .....");
                    //}
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            in.close();
            client.close();
            cliente1.close();
            out2.close();
            in2.close();
            client2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}