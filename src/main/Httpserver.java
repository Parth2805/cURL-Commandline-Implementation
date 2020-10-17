import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Httpserver {

    static File f = new File("/src/main/");


    public static void main(String args[]) throws IOException {


        System.out.println("Server Started");

        serverrunning();
    }



    static void serverrunning() throws IOException {

        ServerSocket ss = new ServerSocket();


        while(true){

            Socket s1 = ss.accept();
//            System.out.println("Client connected to:" + s1.getLocalAddress());


            BufferedReader br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
//            OutputStream os = s.getOutputStream();
            String request;
            String input="";
            while((request =br.readLine()).length()!=0){

                System.out.println(request);
                input += request;
            }

            String data[] = input.split(" ");

//            for(String d: data){
//                System.out.println(d);
//            }

            if(data[0].equals("GET")){
                get(data);
            }
            if(data[0].equals("POST")){
                post(data);
            }
       }
    }

    static void get(String data[]){

        if(data[1].equals("/")){

            System.out.println(f.list());

        }


    }

    static void post(String data[]){


    }
}
