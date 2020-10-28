import java.io.*;

public class Httpserverlib {


    synchronized String getrequest(String request) throws IOException {

        String response = "",file_output = "";
        String data[] = request.split(" ");
//        request = request.substring(5);
        String path = "src/main/" + data[1].substring(1);
        File f = new File(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

        if(f.exists()){
            if(f.isFile()){
                response = "HTTP Found\r\n";
                while((file_output=br.readLine())!=null){

                    response += file_output+"\r\n";
                }
                br.close();
            }else{
                response = "Not a file, its a directory.";
            }

        }
        else{
            response = "HTTP 404 Not Found";
        }

        if(data[1].equals("/")){


        }

        return response;
    }

    synchronized String  postrequest(String request) throws IOException {

        String response = "";
        String data[] = request.split(" ");
        String path = "src/main/" + data[1].substring(1) ;
        File f = new File(path);


        if(f.exists()){
            response = "POST Complete";
            FileWriter fw = new FileWriter(f);
            fw.write(data[3]);
            fw.close();
        }
        else{
            response = "HTTP 404 Not Found";

        }

        return response;
    }
}
