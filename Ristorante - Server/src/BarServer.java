import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarServer implements Runnable{

        InputStream is = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        boolean isDone = false;

        Socket sInThread = null;
        private final Bar bar;

        public BarServer(Socket sxxx, Bar bar) {

            this.sInThread = sxxx;
            this.bar = bar;
        }

        @Override
        public void run() {
            if (sInThread.isConnected()) {

                System.out.println("Nuovo Client Connesso");
            }

            System.out.println("Entered");
            try {
                is = sInThread.getInputStream();

                DataOutputStream outToClient = new DataOutputStream(sInThread.getOutputStream());
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sInThread.getInputStream()));

                String input = String.valueOf(inFromClient.readLine()).trim();
                String[] splittedInput = input.split(",");

                int _order = Integer.valueOf(splittedInput[0]);
                List prodottiDisponibili = new ArrayList<String>();;

                Collections.addAll(prodottiDisponibili, splittedInput[1]);

                bar.set_order(_order);
                bar.setProdottiDisponibili(prodottiDisponibili);

                outToClient.writeBytes("Operation Completed" + "\n");
                outToClient.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
}
