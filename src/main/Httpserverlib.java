import java.io.*;

public class Httpserverlib {

    synchronized String getrequest(String request) throws IOException {

//        System.out.println(request);
        String response = "",file_output = "";
        String data[] = request.split(" ");
//        request = request.substring(5);

        //Get files name
        if(data[1].endsWith("/")){
            File f=null;
            if(data[1].length()!=1){
                String path = Httpserver.dir + data[1].substring(1,data[1].length()-1);
                f = new File(path);
            }else{
                f = new File(Httpserver.dir);
            }

            for(String files:f.list()){

                response += files+"\r\n";
            }

        }else{
            String path = Httpserver.dir + data[1].substring(1);
            System.out.println("Path:" + path);
            File f = new File(path);
            if(f.exists()){
                if(f.isFile()){
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    response = "HTTP 200 Found\r\n";
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
        }
        return response;
    }

    synchronized String  postrequest(String request) throws IOException {

        String response = "";
        String data[] = request.split(" ");
        String path = Httpserver.dir + data[1].substring(1) ;
        File f = new File(path);



        response = "HTTP 200 Post Successful";
        FileWriter fw = new FileWriter(f);
        fw.write(request.substring(request.indexOf("-d")+3));
        fw.close();



        return response;
    }
}
