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
            try {
                is = sInThread.getInputStream();

                DataOutputStream outToClient = new DataOutputStream(sInThread.getOutputStream());
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sInThread.getInputStream()));

                String input = String.valueOf(inFromClient.readLine()).trim();
                if(input.equals("req")){
                    System.out.println("Client Cucina Connesso...<<<");
                    if(cucina.get_order() != -1) {
                        System.out.println("Fornendo Dati:\nOrdine : " + cucina.get_order() + "\nLista: " + cucina.getProdottiDisponibili());
                        outToClient.writeBytes(cucina.get_order() + "," + cucina.getProdottiDisponibili() + "\n");
                        System.out.println(">>>Soddifatta Richieseta Client Cucina...");
                        cucina.set_order(-1);
                    }
                }else {
                    System.out.println("Client Tablet Connesso <<<");
                    String[] splittedInput = input.split(",");

                    System.err.println(input);
                    int _order = Integer.valueOf(splittedInput[0]);



                    cucina.set_order(_order);
                    cucina.setProdottiDisponibili(splittedInput[1]);
                    System.out.println("Operazioni Completate\nFile Inviati : " + _order + " || " + splittedInput[1]);
                    outToClient.writeBytes("Operation Completed" + "\n");
                }
                outToClient.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
