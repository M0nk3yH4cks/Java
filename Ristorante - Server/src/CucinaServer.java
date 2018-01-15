import java.io.*;
import java.net.Socket;

public class CucinaServer implements Runnable{

        InputStream is = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        boolean isDone = false;

        Socket sInThread = null;
        private final Cucina cucina;

        public CucinaServer(Socket sxxx, Cucina bar) {

            this.sInThread = sxxx;
            this.cucina = bar;
        }

        @Override
        public void run() {
            if (sInThread.isConnected()) {

                System.out.println("Nuovo Client Connesso");
            }

                System.out.println("Entered");

        }
}
