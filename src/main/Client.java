import java.util.*;
import java.io.*;


public class Client {

    public static void main(String args[]) throws IOException {


        System.out.println("Welcome to cURL command-Line");


        testlib();



    }

    public static void testlib() throws IOException {

        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String user_req = sc.nextLine();
        String input[] = user_req.split(" ");
        HttpLib lib = new HttpLib();


        //if first word httpc
        if(input[0].equals("httpc")){

            //
            if(input[1].equals("help")){

                System.out.println("TO DO");
                //Add the instructions
                String input_url = "http://httpbin.org/get?course=networking&assignment=1";
                lib.get(input_url);




            }




        }

        //if first word not httpc
        else{

            System.out.println("httpc is the only recognized library");


        }







    }


}
