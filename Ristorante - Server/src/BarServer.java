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
                if(input.equals("req")){
                    System.out.println("Client Bar Connesso...<<<");
                    if(bar.get_order() != -1) {
                        System.out.println("Fornendo Dati:\nOrdine : " + bar.get_order() + "\nLista: " + bar.getProdottiDisponibili());
                        outToClient.writeBytes(bar.get_order() + "," + bar.getProdottiDisponibili() + "\n");
                        System.out.println(">>>Soddifatta Richieseta Client Bar...");
                        bar.set_order(-1);
                    }
                }else {
                    System.out.println("Client Tablet Connesso <<<");
                    String[] splittedInput = input.split(",");

                    System.err.println(input);
                    int _order = Integer.valueOf(splittedInput[0]);



                    bar.set_order(_order);
                    bar.setProdottiDisponibili(splittedInput[1]);
                    System.out.println("Operazioni Completate\nFile Inviati : " + _order + " || " + splittedInput[1]);
                    outToClient.writeBytes("Operation Completed" + "\n");
                }
                outToClient.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
}
