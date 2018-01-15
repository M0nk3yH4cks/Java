import java.io.*;
import java.net.Socket;

// Dichiarazione Prodotti
public class ServerMagazzino implements Runnable{

    InputStream is = null;
    OutputStream os = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    boolean isDone = false;

    Socket sInThread = null;
    private final Magazzino magazzino;

    public ServerMagazzino(Socket sxxx, Magazzino magazzino) {

        this.sInThread = sxxx;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        if (sInThread.isConnected()) {

            System.out.println("Nuovo Client Connesso");
        }
        while (true) {
            try {
                is = sInThread.getInputStream();
                DataOutputStream outToClient = new DataOutputStream(sInThread.getOutputStream());

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sInThread.getInputStream()));

                try {
                    magazzino.setProdottiDisponibili();
                    isDone = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RuntimeException re) {
                    outToClient.writeBytes("approReq" + "\n");
                    System.out.println("Approvvigionamento Richiesto...");
                }

                String input = String.valueOf(inFromClient.readLine()).trim();
                System.out.println("Stringa in input: " + input);


                String[] testEntity = input.split(",");
                if (testEntity[0].equals("Fornitore")) {
                    magazzino.setAppSize(Integer.parseInt(testEntity[1]));
                } else if (testEntity[0].equals("Tablet")) {
                    if(testEntity[1].equals("productsList")) {
                        System.out.println("Fornendo la lista dei prodotti <-----");
                        outToClient.writeBytes(magazzino.getProdottiDisponibili().toString() + "\n");
                        System.out.println("-----> Lista fornita");
                    }else if (input.equals("getAppro")) {
                        magazzino.getApprovvigionamenti();
                        outToClient.writeBytes(magazzino.getProdottiDisponibili().toString() + "\n");
                    }
                } else if (testEntity[0].equals("Bar")) {
                    System.out.println(">>> Avvio Bar...");
                    magazzino.setItemCount(Integer.parseInt(testEntity[1]), testEntity[2]);
                }

                outToClient.flush();


            } catch (IOException e) {
                System.err.println("Mh..errore " + e.getMessage());
            }
        }
    }
}
