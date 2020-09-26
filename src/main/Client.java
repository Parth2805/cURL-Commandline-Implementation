import java.util.*;
import java.io.*;
import java.net.MalformedURLException;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to cURL Application!!!");
        methodPassing();
        System.out.println("\nThank you!!!");
    }

    public static void methodPassing() throws IOException {
        HttpLib lib = new HttpLib();
        Scanner command = new Scanner(System.in);
        System.out.print(">");
        String input = command.nextLine();
        String[] data = input.split(" ");
        String url2= data[data.length-1];
        String url1= url2.substring(1,url2.length()-1);
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
                    lib.methodPassing1("vg", url1);
                }
                else if (data[2].equals("-h") || data[2].equals("--h")) {
                    lib.methodPassing1("hg", url1);
                }
                else if(data[2].contains("http")){
                    lib.methodPassing1("g",url1);
                }
                else {
                    System.out.println("Please Enter The Right Command !!!");
                    System.out.println("Use \"httpc help get\" for more information about commands.");
                }
            }
            else if (data[1].equalsIgnoreCase("post")) {
                if ((Arrays.asList(data).contains("-f") || Arrays.asList(data).contains("--f")) && (Arrays.asList(data).contains("-d") || Arrays.asList(data).contains("--d"))) {
                    System.out.println("Please Enter The Right Command !!!");
                    System.out.println("Either [-d] or [-f] can be used but not both.");
                    System.out.println("Use \"httpc help\" for more information about commands.");
                }

                else {
                    switch(data[2]) {
                        case "-v":
                            lib.methodPassing1("vp", url1);
                            break;
                        case "-h":
                        case "--h":
                            lib.methodPassing1("hp", url1);
                            break;
                        case "-f":
                        case "--f":
                            lib.methodPassing1("f", url1);
                            break;
                        case "-d":
                        case "--d":
                            lib.methodPassing1("d", url1);
                            break;
                        case "-o":
                        case "--o":
                            lib.methodPassing1("o", url1);
                            break;
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
        System.out.println("\nPress Y/y to CONTINUE or any key to EXIT...");
        String continue_command = command.nextLine();
        if(continue_command.equalsIgnoreCase("Y")){
            methodPassing();
        }
    }
}

