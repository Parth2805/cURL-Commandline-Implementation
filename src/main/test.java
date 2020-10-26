import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class test {

    public static void main(String args[]) throws IOException {


        Thread t1 =  new Thread(()->{

            Socket s = null;
            PrintWriter pw = null;
            try {
                s = new Socket(InetAddress.getLocalHost(),8080);
                pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw.write("GET /data.txt\r\n");
                pw.flush();
                String output = br.readLine();
                System.out.println(output);
                br.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread t2 =  new Thread(()->{

            Socket s = null;
            PrintWriter pw = null;
            try {
                s = new Socket(InetAddress.getLocalHost(),8080);
                pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                pw.write("Garbage\r\n");
                pw.flush();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Thread t3 =  new Thread(()->{

            Socket s = null;
            PrintWriter pw = null;
            try {
                s = new Socket(InetAddress.getLocalHost(),8080);
                pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                pw.write("POST\r\n");
                pw.flush();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        t1.start();
//        t2.start();
//        t3.start();





    }

}
