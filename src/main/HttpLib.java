import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import java.io.IOException;
import java.net.*;

public class HttpLib {
    public String host;
    public String port;
    public String path;

    public void methodPassing1(String com, String url1) throws IOException {
        URL url = new URL(url1);
        switch (com) {
            case "vg":
                System.out.println("vg" + url);
                break;
            case "vp":
                System.out.println("vp" + url);
                break;
            case "hg":
                System.out.println("hg" + url);
                break;
            case "hp":
                System.out.println("hp" + url);
                break;
            case "f":
                System.out.println("f" + url);
                break;
            case "d":
                System.out.println("d" + url);
                break;
            case "o":
                System.out.println("o" + url);
                break;
            default:
                System.out.println("Please Enter The Right Command !!!");
                System.out.println("Use \"httpc help\" for more information about commands.");

        }
    }
}

    //Library other
//    boolean verbose = false;
//    int port = 80;
//    PrintWriter pw;
//    BufferedReader br;
//    Socket s;
//    String host;
//
//
//
//    public HttpLib() throws IOException {
//    }
//
//    void makeConnection(String input_url) throws IOException {
//
//        URL url = new URL(input_url);
//        host = url.getHost();
//        System.out.println(host);
//        s = new Socket(host,port);
//        System.out.println("Connection made succesfully");
//    }
//
//    void get(String input_url) throws IOException {
//
//        makeConnection(input_url);
//
//        pw = new PrintWriter(s.getOutputStream());
//        String input_url1 = "GET /get?course=networking&assignment=1 HTTP/1.0\r\n" + "Host:httpbin.org\r\n" +"\r\n";
//        pw.write(input_url1);
//        pw.flush();
//        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        String output = br.readLine();
//        System.out.println("Output:"+output);
//        while(output !=null){
//            System.out.println(output);
//            output = br.readLine();
//
//        }
//
//    }
//
//}
