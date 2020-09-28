import java.util.*;
import java.io.*;
import java.net.MalformedURLException;

public class Client {
    public static void main(String[] args) throws Exception {
        methodPassing();
    }

    public static void methodPassing() throws IOException {
        HttpLib lib = new HttpLib();
        Scanner command = new Scanner(System.in);
        String input = command.nextLine();
        String[] data = input.split(" ");
        String url2= data[data.length-1];
        if (data[0].equalsIgnoreCase("httpc")) {
            if (data[1].equalsIgnoreCase("help")) {
                if (data.length == 2) {
                    System.out.println("httpc is a curl-like application but supports HTTP protocol only. ");
                    System.out.println("Usage: httpc command [arguments]");
                    System.out.println("The commands are:");
                    System.out.println("\t get  executes a HTTP GET request and prints the response.");
                    System.out.println("\t post executes a HTTP POST request and prints the response.");
                    System.out.println("\t help prints this screen.");
                    System.out.println("Use \"httpc help [command]\" for more information about a command.");
                }
                else if (data.length == 3) {
                    if (data[2].equalsIgnoreCase("get")) {
                        System.out.println("usage: httpc get [-v] [-h key:value] URL\n");
                        System.out.println("Get executes a HTTP GET request for a given URL.\n");
                        System.out.println("\t-v              Prints the detail of the response such as protocol, status, and headers.");
                        System.out.println("\t-h key:value    Associates headers to HTTP Request with the format 'key:value'.");
                    }
                    else if (data[2].equalsIgnoreCase("post")) {
                        System.out.println("usage: httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL\n");
                        System.out.println("Post executes a HTTP POST request for a given URL with inline data or from file.\n");
                        System.out.println("\t-v              Prints the detail of the response such as protocol, status, and headers.");
                        System.out.println("\t-h key:value    Associates headers to HTTP Request with the format 'key:value'.");
                        System.out.println("\t-d string       Associates an inline data to the body HTTP POST request.");
                        System.out.println("\t-f file         Associates the content of a file to the body HTTP POST request.\n");
                        System.out.println("Either [-d] or [-f] can be used but not both.");
                    }
                    else {
                        System.out.println("Please Enter The Right Command !!!");
                        System.out.println("Use \"httpc help\" for more information about commands.");
                    }
                }
                else {
                    System.out.println("Please Enter The Right Command !!!");
                    System.out.println("Use \"httpc help\" for more information about commands.");
                }
            }
            else if (data[1].equalsIgnoreCase("get")) {
                if (data[2].equals("-v")) {
                    lib.get(true,url2);
                }
                else {
                    lib.get(false,url2);
                }
//                else if (data[2].equals("-h") || data[2].equals("--h")) {
////                    lib.get("hg", data[data.length - 1]);
//                }
//                else {
//                    System.out.println("Please Enter The Right Command !!!");
//                    System.out.println("Use \"httpc help get\" for more information about commands.");
//                }
            }
            else if (data[1].equalsIgnoreCase("post")) {

                ArrayList<String> header = new ArrayList<String>();
                header.add("Content-Type:application/json");
                lib.post(true,url2,header,null);
                if ((Arrays.asList(data).contains("-f") || Arrays.asList(data).contains("--f")) && (Arrays.asList(data).contains("-d") || Arrays.asList(data).contains("--d"))) {
                    System.out.println("Please Enter The Right Command !!!");
                    System.out.println("Either [-d] or [-f] can be used but not both.");
                    System.out.println("Use \"httpc help\" for more information about commands.");
                }
                else {
                    switch(data[2]) {
//                        case "-v":
//                            lib.post(true, data[data.length - 1]);
//                            break;
//                        case "-h":
//                        case "--h":
//                            lib.get("hp", data[data.length - 1]);
//                            break;
//                        case "-f":
//                        case "--f":
//                            lib.get("f", data[data.length - 1]);
//                            break;
//                        case "-d":
//                        case "--d":
//                            lib.get("d", data[data.length - 1]);
//                            break;
//                        case "-o":
//                        case "--o":
//                            lib.get("o", data[data.length - 1]);
//                            break;
                        default:
                            System.out.println("Please Enter The Right Command !!!");
                            System.out.println("Use \"httpc help post\" for more information about commands.");
                    }
                }
            }
            else {
                System.out.println("Please Enter The Right Command !!!");
                System.out.println("Use \"httpc help\" for more information about commands.");
            }
        }
        else {
            System.out.println("Please Enter The Right Command !!!");
            System.out.println("The command should have \"httpc command [arguments]\" format");
            System.out.println("Use \"httpc help\" for more information about commands.");
        }

    }
}


/*


httpc help

httpc help post

httpc help get

httpc get http://httpbin.org/get?course=networking&assignment=1

httpc get -v http://httpbin.org/get?course=networking&assignment=1

httpc post -h Content-Type:application/json --d {"Assignment":1} http://httpbin.org/post

httpc post -h Content-Type:application/json --d {"Assignment":1} -d {"Assignment":2} --d {"Assignment":3} http://httpbin.org/post

httpc post -h Content-Type:application/json -f {} -d {} http://httpbin.org/post

httpc post -h Content-Type:application/json -f data.txt http://httpbin.org/post

httpc post -h Content-Type:application/json -d {"Assignment":1} -o result.txt http://httpbin.org/post

httpc get -v -h Content-Type:application/json https://httpstat.us/302

 */