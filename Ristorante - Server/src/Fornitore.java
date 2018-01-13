import java.io.*;
import java.net.Socket;

public class Fornitore extends Thread{

    public int upgradeAprrov(){
        int aprvsz = 1;
        return aprvsz;
    }

   /* public static void main(String[] args) throws IOException {
        // Dichiarazione variabili e connessione.
        Socket skt = new Socket("localhost", 1234);
        System.out.println("\t\t\t----------Client Started----------");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(skt.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        String outputString;
        String inputString;

        while (true) {
            // Richiedo la lista dei prodotti
            System.out.print("Attendo...");

            inputString = inFromServer.readLine();
            System.out.println("FROM SERVER: " + inputString);

            if(inputString.equals("approReq")){
                outputString = "Fornitore,10";
                outToServer.writeBytes(outputString + "\n");
                System.out.println("Inviato il valore degli approvigionamenti");
            }
        }
    }*/

    @Override
    public void run() {
        // Dichiarazione variabili e connessione.
        Socket skt = null;
        BufferedReader inFromUser = null;
        DataOutputStream outToServer = null;
        BufferedReader inFromServer = null;
        try {
            skt = new Socket("localhost", 1234);
            System.out.println("\t\t\t----------Client Started----------");
            inFromUser = new BufferedReader(new InputStreamReader(System.in));
            outToServer = new DataOutputStream(skt.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputString = null;
        String inputString = null;

        while (true) {
            // Richiedo la lista dei prodotti
            System.out.print("Attendo...");

            try {
                inputString = inFromServer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("FROM SERVER: " + inputString);

            if(inputString.equals("approReq")){
                outputString = "Fornitore,10";
                try {
                    outToServer.writeBytes(outputString + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Inviato il valore degli approvigionamenti");
            }
        }
    }
}
