import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Httpserverlib {


    synchronized String getrequest(String request){

        String response = "";
        request = request.substring(5);
        String path = "src/main/" + request;
        File f = new File(path);
        System.out.println(request+ " " + path);
        if(!request.contains("/")){

            if(f.exists()){

                response = "Yes file exists";

            }
            else{
                response = "File does not exist";
            }
            
        }else{

            System.out.println("error");

        }
        System.out.println(response);
        return response;
    }

    synchronized String  postrequest(String request){

//        System.out.println("Got POst");

    return null;
    }
}
