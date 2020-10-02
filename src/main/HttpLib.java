import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;


public class HttpLib {
    public String host;
    public int port;
    public String path;
    public boolean v;
    public String httpreq = "";
    String output;
    String query;
    int content_length= 0;
    String response="";

    public void get(Boolean verbose,String save_to_file,ArrayList<String> header,String url1) throws IOException {

        URL url = new URL(url1);
        path = url.getPath();
        host = url.getHost();
        port = 80;
        query = url.getQuery();

        Socket s = new Socket(host, port);
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //Request Forming
        httpreq = "GET " + path;

        if(query!=null){

            httpreq += "?" + query;
        }
        System.out.println("URL data:"+ url.getQuery());

        //Add protocol
        httpreq = httpreq + " HTTP/1.0\r\n" + "Host:" + host +"\r\n";

        //Adding headers
        for(String headers : header){
            if(!headers.equals("Host:"+host)){

                httpreq += headers +"\r\n";
            }
        }
        httpreq += "\r\n";

        System.out.println("Request:" + httpreq );
        pw.write(httpreq);
        pw.flush();

        //Response
        output = br.readLine();
        response += response +"\r\n";

        //If verbose not enabled
        if(!verbose){

            while(output!=null){

                if(output.length()==0){

                    break;
                }
                output=br.readLine();
                response += output +"\r\n";
            }
        }

        while(output!=null){

            System.out.println(output);
            output = br.readLine();
            response += output + "\r\n";
        }

        //If -o used
        if(save_to_file!=null){
            writeToFile(save_to_file,response);
        }

        s.close();
        pw.close();
        br.close();

    }


    void post(boolean verbose,String save_to_file, ArrayList<String> header, ArrayList<String> data, ArrayList<String> file, String url1) throws IOException {

        URL url = new URL(url1);

        path = url.getPath();
        host = url.getHost();
        port = 80;
        query = url.getQuery();

        Socket s = new Socket(host, port);
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        httpreq = "POST " + path;

        if(query!=null){
            httpreq += "?" + query;
        }

        //Add protocol
        httpreq = httpreq + " HTTP/1.0\r\n" + "Host:" + host +"\r\n";


        //If datafile present
        if(file != null){

            for(String files:file){

                File data_file =  new File("src/main/"+files);

                if(data_file.exists()){

                    BufferedReader br1 = new BufferedReader(new FileReader(data_file));
                    String file_output;
                    while((file_output=br1.readLine())!=null){

                        data.add(file_output);
                    }

                }else{

                    System.out.println("File does not exist");
                    return;

                }
            }
        }


        //Add Content-Length
        for(String d :data){
            content_length += d.length();
        }
        httpreq += "Content-Length:" + content_length +"\r\n";

        //Adding headers
        for(String headers : header){
            if(!headers.equals("Host:"+host)){

                httpreq += headers +"\r\n";
            }
        }


        //Data Adding
        if(data.size()!=0){

            for(int i=0;i<data.size();i++){

                httpreq = httpreq + "\r\n" + data.get(i);

            }
        }else{

            httpreq += "\r\n";
        }

        System.out.println("Request:" + httpreq );
        System.out.println("---------------------------------");
        pw.write(httpreq);
        pw.flush();

        //Response
        output = br.readLine();
        response += output+"\r\n";

        //If verbose not enabled
        if(!verbose){

            while(output!=null){

                if(output.length()==0){

                    break;
                }
                output=br.readLine();
                response += output +"\r\n";

            }
        }

        while(output!=null){

            System.out.println(output);
            output = br.readLine();
            response += output + "\r\n";

        }

        System.out.println("Response:" + response);
        //If -o used
        if(save_to_file!=null){
            writeToFile(save_to_file,response);
        }

        s.close();
        pw.close();
        br.close();
    }

    void writeToFile(String file_name,String response) throws IOException {

        File file = new File(file_name);
        FileWriter fw = new FileWriter(file);
        System.out.println("Response:"+response);
        fw.write(response);
        fw.close();

    }

}

//String input_url1 = "GET /get?course=networking&assignment=1 HTTP/1.0\r\n" + "Host:httpbin.org\r\n" +"\r\n";

