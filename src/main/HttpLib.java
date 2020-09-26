import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;


public class HttpLib {

    boolean verbose = false;
    int port = 80;
    PrintWriter pw;
    BufferedReader br;
    Socket s;
    String host;



    public HttpLib() throws IOException {
    }

    void makeConnection(String input_url) throws IOException {

        URL url = new URL(input_url);
        host = url.getHost();
        System.out.println(host);
        s = new Socket(host,port);
        System.out.println("Connection made succesfully");
    }

    void get(String input_url) throws IOException {

        makeConnection(input_url);

        pw = new PrintWriter(s.getOutputStream());
        String input_url1 = "GET /get?course=networking&assignment=1 HTTP/1.0\r\n" + "Host:httpbin.org\r\n" +"\r\n";
        pw.write(input_url1);
        pw.flush();
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String output = br.readLine();
        System.out.println("Output:"+output);
        while(output !=null){
            System.out.println(output);
            output = br.readLine();

        }

    }

}
