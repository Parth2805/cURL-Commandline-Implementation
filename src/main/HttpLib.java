import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;


public class HttpLib {
    public String host;
    public int port;
    public String path;
    public boolean v;
    public String httpreq = "";
    String output;
    String query;

    public void get(Boolean verbose,String url1) throws IOException {

        URL url = new URL(url1);
        path = url.getPath();
        host = url.getHost();
        port = 80;
        query = url.getQuery();
        httpreq = "GET " + path + "?" +query;

        Socket s = new Socket(host, port);
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

//        System.out.println("URL data:"+ url.getQuery());

        //Data Adding
            //TO DO

        //Add protocol
        httpreq = httpreq + " HTTP/1.0\r\n" + "Host:" + host +"\r\n" + "\r\n";
//        System.out.println("Request:" + httpreq );
        pw.write(httpreq);
        pw.flush();

        //Response
        output = br.readLine();

        //If verbose not enabled
        if(verbose==false){

            while(output!=null){

                if(output.length()==0){

                    break;
                }
                output=br.readLine();

            }
        }

        while((output=br.readLine())!=null){

            System.out.println(output);

        }





//        switch (com) {
//            case "vg":
//                System.out.println("vg" + url);
//                break;
//            case "vp":
//                System.out.println("vp" + url);
//                break;
//            case "hg":
//                System.out.println("hg" + url);
//                break;
//            case "hp":
//                System.out.println("hp" + url);
//                break;
//            case "f":
//                System.out.println("f" + url);
//                break;
//            case "d":
//                System.out.println("d" + url);
//                break;
//            case "o":
//                System.out.println("o" + url);
//                break;
//            default:
//                System.out.println("Please Enter The Right Command !!!");
//                System.out.println("Use \"httpc help\" for more information about commands.");

//        }
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
