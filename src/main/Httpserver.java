import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Httpserver {

    static int port = 8080;
    static String dir = "src/main/";
    static int clientnumber = 0;
    static boolean verbose = false;
    static int routerport = 3000;
    static long sequencenumber = 0;
    int window = 4;
    static int nop = -1;
    static int MAX = 1013;

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
        boolean part1 = false;
        boolean part2 = false;
        HashMap<Long,Packet> data =  new HashMap<>();


        while (true) {

            byte buffer[] =  new byte[Packet.MAX_LEN];
            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            ss.receive(dp);
            Packet request_packet = Packet.fromBytes(dp.getData());

            if(request_packet.getType()==Packet.datatype.SYN.type){


                Packet synackpacket = new Packet(Packet.datatype.SYNACK.type,sequencenumber,request_packet.getPeerAddress(),request_packet.getPeerPort(),("ACK:"+ request_packet.getSequenceNumber()).getBytes());
                DatagramPacket syndp = new DatagramPacket(synackpacket.toBytes(),synackpacket.toBytes().length,dp.getAddress(),dp.getPort());
                ss.send(syndp);
                sequencenumber++;

                byte temp[] = new byte[Packet.MAX_LEN];
                DatagramPacket recdp = new DatagramPacket(temp,temp.length);
                ss.receive(recdp);

                Packet ack = Packet.fromBytes(recdp.getData());
                if(ack.getType()==Packet.datatype.ACK.type){

                }

            }
            if(request_packet.getType()==Packet.datatype.DATA.type){

                String message="";
                clientnumber++;
                if(verbose){
                    System.out.println("Client:" + clientnumber +" connected");
                }


                Httpserverlib request = new Httpserverlib();
                String output = new String(request_packet.getPayload()).trim();


                if(verbose){
                    System.out.println(output);
                }
                if(output.startsWith("GET")){

                    message=request.getrequest(output);

                }else if(output.startsWith("POST")){

                    message=request.postrequest(output);

                }
//            message+="\r\n";

//                Packet response =  new Packet(Packet.datatype.DATA.type,sequencenumber,InetAddress.getLocalHost(),request_packet.getPeerPort(),message.trim().getBytes());
//                DatagramPacket response_packet = new DatagramPacket(response.toBytes(),response.toBytes().length,InetAddress.getLocalHost(),routerport);

                //Make packets to send
                byte responsebytes[] = message.getBytes();
                if(message.length()>MAX){

                    byte temp[] = new byte[MAX];
                    int j=0;
                    for(int i=0;i<responsebytes.length;i++){

                        temp[j] = responsebytes[i];
                        j++;
                        if(j==MAX){

                            Packet p =  new Packet(Packet.datatype.DATA.type,sequencenumber,InetAddress.getLocalHost(),request_packet.getPeerPort(),temp);
                            data.put(sequencenumber,p);
                            sequencenumber++;
                            temp = new byte[MAX];
                            j=0;

                        }

                    }


                }else{
                    Packet p =  new Packet(Packet.datatype.DATA.type,sequencenumber,InetAddress.getLocalHost(),request_packet.getPeerPort(),responsebytes);
                    data.put(sequencenumber,p);
                    sequencenumber++;

                }
                String msg = "End";
                byte temp[] =  msg.getBytes();
                Packet p =  new Packet(Packet.datatype.DATA.type,sequencenumber,InetAddress.getLocalHost(),request_packet.getPeerPort(),temp);
                data.put(sequencenumber,p);
                sequencenumber++;

//                System.out.println("Printing ds:");
//                for(long i : data.keySet()){
//                    System.out.println("Packet:" + i + ":" + data.get(i).getPayload().length + ":" + Packet.MAX_LEN);
//                    System.out.println(new String(data.get(i).getPayload()));
//                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
//                }

                sendpackets(data);




                if(verbose){

                    System.out.println("Client:" + clientnumber +" disconnected");

                }
            }

        }




    }

    static void sendpackets(HashMap<Long,Packet> data) throws IOException {

        DatagramSocket s = new DatagramSocket();
//        System.out.println();
        for(Long i:data.keySet()){

            System.out.println("Packet:" + i + ":" + data.get(i).getPayload().length + ":" + Packet.MAX_LEN);
            System.out.println(new String(data.get(i).getPayload()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

            DatagramPacket dp = new DatagramPacket(data.get(i).toBytes(),data.get(i).toBytes().length,InetAddress.getLocalHost(),routerport);
            s.send(dp);


        }

        receivepackets(s,data);
    }

    static void receivepackets(DatagramSocket s,HashMap<Long,Packet> data) throws IOException{


        while(data.size()!=0){

            byte temp[] = new byte[Packet.MAX_LEN];
            DatagramPacket dp =new DatagramPacket(temp,temp.length);

//            long timer = System.currentTimeMillis();
//            long timepassed = 0L;

//            while(timepassed<3*1000){
//
//                timepassed = (new Date()).getTime() - timer;
//                System.out.println(timepassed);
//                s.receive(dp);
//            }

            s.receive(dp);


            Packet p = Packet.fromBytes(dp.getData());
            long seqrecv = p.getSequenceNumber();

            if(p.getType()==Packet.datatype.DATAACK.type){
                System.out.println("Received ACK:" + seqrecv);
                System.out.println("Packets in data:" + data.keySet());
                if(data.keySet().contains(seqrecv)){
                    System.out.println("Removing ack");
                    data.remove(seqrecv);
//                    System.out.println(seqrecv);

                }

            }


        }

        System.out.println("All packets ACK'd");

    }


}