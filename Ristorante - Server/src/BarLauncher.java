import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BarLauncher {

    // ///----------------------------------------Instance Variable Fields
    ServerSocket ss = null;
    Socket incoming = null;
    public Bar bar = new Bar();

    // ///----------------------------------------Instance Variable Fields

    // ///---------------------------------------- static Variable Fields
    public static ArrayList<Socket> socList = new ArrayList<Socket>();

    // ///---------------------------------------- static Variable Fields

    public void go() {

        try {

            ss = new ServerSocket(4321);

            System.out.println("Socket Started");

            while (true) {

                incoming = ss.accept();
                System.out.println("Socket Accepted");
                socList.add(incoming);
                System.out.println("Incoming: " + incoming);
                new Thread(new BarServer(incoming, bar)).start();

            }

        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {
                ss.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
