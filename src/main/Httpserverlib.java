import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Httpserverlib {

    public void receive(Socket s) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));






    }

}
