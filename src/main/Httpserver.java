import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Httpserver {

    static File f = new File("src/");
    static int port = 8080;
    static String dir = "src/main";
    static int clientnumber = 0;

    public static void main(String args[]) throws IOException {


        boolean verbose = false;

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String words[] = command.split(" ");

        if (words[0].equals("httpfs") && words.length > 1) {

            for (int i = 0; i < words.length; i++) {

                switch (words[i]) {

                    case "-p":
                        port = Integer.parseInt(words[i + 1]);
                        break;
                    case "-v":
                        verbose = true;
                        break;
                    case "-d":
                        dir = words[i + 1];
                        break;
                    default:
                        break;
                }
            }

            if (verbose) {

                System.out.println("Server Started at Port:" + port);
            } else {

                System.out.println("Server Started");
            }
            serverrunning();

        } else {

            System.out.println("Type https [-v] [-p PORT] [-d PATH-TO-DIR]");
        }
    }

    static void serverrunning() throws IOException {

        ServerSocket ss = new ServerSocket(port);


        while (true) {
            Socket s1 = ss.accept();
            clientnumber++;
            System.out.println("Client:" + clientnumber +"connected");

            Httpserverlib request = new Httpserverlib();
            request.receive(s1);
        }


    }
}