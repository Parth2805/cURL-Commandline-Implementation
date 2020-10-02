import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

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
        if (data[0].equalsIgnoreCase("httpc")) {
            if(data.length>1){
                String url1= data[data.length-1];
                String url2;
                if(url1.startsWith("'")) url2 = url1.substring(1, url1.length() - 1);
                else url2 = url1;
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
                    if(data.length>2){
                        boolean command1=true;
                        boolean v1 =false;
                        ArrayList<String> h1 = new ArrayList<>();
                        for(int i=2; i<(data.length-1);i++){
                            if (data[i].equals("-v")) {
                                v1=true;
                            }
                            else if (data[i].equalsIgnoreCase("-h") || data[i].equalsIgnoreCase("--h")) {
                                String temp1= data[i+1];
                                if(temp1.startsWith("'")){
                                    String temp2= temp1.substring(1,temp1.length()-1);
                                    h1.add(temp2);
                                }
                                else h1.add(temp1);
                                i+=1;
                            }
                            else command1=false;
                        }
                        if(command1) {
                            try {
                                List<String> header1 = h1.stream().distinct().collect(Collectors.toList());
                                lib.get(v1,"output.txt", (ArrayList<String>) header1, url2);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.out.println("Please Enter The Right Command !!!");
                            System.out.println("Use \"httpc help get\" for more information about commands.");
                        }
                    }
                    else{
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
                    else{
                        if(data.length>2){
                            boolean command2 = true;
                            boolean v2 = false;
                            ArrayList<String> h2 = new ArrayList<>();
                            ArrayList<String> d2 = new ArrayList<>();
                            ArrayList<String> f2 = new ArrayList<>();
                            for(int i=2;i<(data.length-1);i++) {
                                if (data[i].equalsIgnoreCase("-v")) {
                                    v2 = true;
                                }
                                else if (data[i].equalsIgnoreCase("-h") || data[i].equalsIgnoreCase("--h")) {
                                    String temp1= data[i+1];
                                    if(temp1.startsWith("'")){
                                        String temp2= temp1.substring(1,temp1.length()-1);
                                        h2.add(temp2);
                                    }
                                    else h2.add(temp1);
                                    i+=1;
                                }
                                else if (data[i].equalsIgnoreCase("-d") || data[i].equalsIgnoreCase("--d")) {
                                    String temp1= data[i+1];
                                    if(temp1.startsWith("'")){
                                        String temp2= temp1.substring(1,temp1.length()-1);
                                        d2.add(temp2);
                                    }
                                    else d2.add(temp1);
                                    i+=1;
                                }
                                else if (data[i].equalsIgnoreCase("-f") || data[i].equalsIgnoreCase("--f")) {
                                    String temp1= data[i+1];
                                    if(temp1.startsWith("'")){
                                        String temp2= temp1.substring(1,temp1.length()-1);
                                        f2.add(temp2);
                                    }
                                    else f2.add(temp1);
                                    i+=1;
                                }
                                else command2=false;
                            }
                            if(command2) {
                                try {
                                    List<String> header2 = h2.stream().distinct().collect(Collectors.toList());
                                    lib.post(v2,"output.txt", (ArrayList<String>) header2,d2,f2,url2);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                System.out.println("Please Enter The Right Command !!!");
                                System.out.println("Use \"httpc help post\" for more information about commands.");
                            }
                        }
                        else{
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
            else{
                System.out.println("Please Enter The Right Command !!!");
                System.out.println("The command should have \"httpc command [arguments]\" format");
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

