import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Httpserver {

    static int port = 8080;
    static String dir = "src/main/";
    static int clientnumber = 0;
    static boolean verbose = false;
    static int routerport = 3000;
    static int sequencenumber = 100;

    public static void main(String args[]) throws IOException {

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String words[] = command.split(" ");

        if (words[0].equals("httpfs")) {

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

            System.out.println("Type httpfs [-v] [-p PORT] [-d PATH-TO-DIR]");
        }
    }

    static void serverrunning() throws IOException {

        DatagramSocket ss = new DatagramSocket(port);


        while (true) {

            byte buffer[] =  new byte[Packet.MAX_LEN];
            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            ss.receive(dp);
            Packet request_packet = Packet.fromBytes(dp.getData());
            String message="";
            clientnumber++;
            if(verbose){

                System.out.println("Client:" + clientnumber +" connected");
            }


            Httpserverlib request = new Httpserverlib();
            String output = new String(request_packet.getPayload());

//            BufferedReader br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s1.getOutputStream()));
//            String output = br.readLine();

            if(verbose){
                System.out.println(output);
            }
            if(output.startsWith("GET")){

                message=request.getrequest(output);

            }else if(output.startsWith("POST")){

                message=request.postrequest(output);

            }
//            message+="\r\n";

            Packet response =  new Packet(Packet.datatype.DATA.type,sequencenumber,InetAddress.getLocalHost(),request_packet.getPeerPort(),message.trim().getBytes());
            DatagramPacket response_packet = new DatagramPacket(response.toBytes(),response.toBytes().length,InetAddress.getLocalHost(),routerport);

            DatagramSocket s = new DatagramSocket();
            s.send(response_packet);
//            pw.write(message+"\r\n");
//            pw.flush();
//            pw.close();
//            s1.close();
            s.close();

            if(verbose){

                System.out.println("Client:" + clientnumber +" disconnected");

            }
        }




    }
}