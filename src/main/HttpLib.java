import java.io.IOException;
import java.net.*;

public class HttpLib {
    public String host;
    public String port;
    public String path;

    public void methodPassing1(String com, String url1) throws IOException {
        URL url = new URL(url1);
        switch(com) {
            case "g":
                System.out.println("g "+url);
                break;
            case "vg":
                System.out.println("vg "+url);
                break;
            case "vp":
                System.out.println("vp "+url);
                break;
            case "hg":
                System.out.println("hg "+url);
                break;
            case "hp":
                System.out.println("hp "+url);
                break;
            case "f":
                System.out.println("f "+url);
                break;
            case "d":
                System.out.println("d "+url);
                break;
            case "o":
                System.out.println("o "+url);
                break;
            default:
                System.out.println("Please Enter The Right Command !!!");
                System.out.println("Use \"httpc help\" for more information about commands.");

        }
    }
}
