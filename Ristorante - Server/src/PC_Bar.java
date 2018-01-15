import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PC_Bar extends Thread {

    @Override
    public void run() {
        while (true) {
            Socket skt = null;
            try {
                skt = new Socket("localhost", 4321);
                DataOutputStream outToServer = new DataOutputStream(skt.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));

                System.out.println("\t\t\t----------Client Started----------\nRequesting Order");

                outToServer.writeBytes("req" + "\n");
                System.out.println("Request Sent");
                String input = inFromServer.readLine();

                System.out.println("Received Order:\n" + input);
            /*String[] splittedInput = input.split(",");

            int _order = Integer.valueOf(splittedInput[0]);
            List prodottiDisponibili = new ArrayList<String>();

            Collections.addAll(prodottiDisponibili, splittedInput[1]);*/

                skt.close();

                skt = new Socket("localhost", 1234);
                outToServer = new DataOutputStream(skt.getOutputStream());
                inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));

                String outputString = "Bar," + input;
                outToServer.writeBytes(outputString + "\n");
                System.out.println("Transmitted Order:\n" + outputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
