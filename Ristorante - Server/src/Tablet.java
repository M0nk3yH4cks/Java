import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tablet extends Thread{

    @Override
    public void run() {

        while (true) {
            // Dichiarazione variabili e connessione.
            Socket skt = null;
            try {
                skt = new Socket("localhost", 1234);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\t\t\t----------Client Started----------");

            try {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(skt.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                String outputString;
                String inputString;

                Scanner scanf = new Scanner(System.in);     // Instanziamo un'ogetto per l'input da linea di comando

                // Richiedo la lista dei prodotti
                System.out.println("----> Richiedo la lista dei prodotti");
                outputString = "Tablet,productsList,1,null";
                outToServer.writeBytes(outputString + '\n');
                System.out.println("----> Stringa inviata");

                inputString = inFromServer.readLine();
                System.out.println("FROM SERVER: " + inputString);

                inputString = inputString.substring(1, inputString.length() - 1).trim();
                String[] stringList = inputString.split(", ");
                List<String> tempProductsList = new ArrayList<String>();

                Collections.addAll(tempProductsList, stringList);

                String _order = "-1";
                // Verifichiamo che vi siano effettivamente prodotti in magazzino
                if (tempProductsList.size() == 0) {
                    outputString = "getAppro";
                    outToServer.writeBytes(outputString + '\n');

                    inputString = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + inputString);

                    inputString = inputString.substring(1, inputString.length() - 1).trim();
                    stringList = inputString.split(", ");
                    Collections.addAll(tempProductsList, stringList);
                }

                // Stampiamo la lista delle pietanze disponibili
                System.out.println("Inserire:");
                for (int i = 0; i < tempProductsList.size(); i++) {
                    System.out.println(i + " - " + tempProductsList.get(i));
                }

                // Prendiamo l'input con un controllo sullo stesso
                do {
                    System.out.print("Inserire il numero corrispondente alla pietanza da lei desiderata\n>>>");
                    try {
                        _order = scanf.nextLine();
                        Integer.parseInt(_order);
                    } catch (NumberFormatException e) {
                        System.err.println("Inserire carattere valido");
                        _order = "-1";
                    }
                }while (Integer.parseInt(_order) > tempProductsList.size() || Integer.parseInt(_order) < 0 || Integer.parseInt(_order) == -1);

                // Individuiamo l'indice iniziale delle bevande
                int drinkIndex = 0;

                for (int i = 0; i < tempProductsList.size(); i++) {
                    if (tempProductsList.get(i).equals("Acqua") || tempProductsList.get(i).equals("Birra") || tempProductsList.get(i).equals("CocaCola") || tempProductsList.get(i).equals("Aranciata") || tempProductsList.get(i).equals("Succo")) {
                        drinkIndex = i;
                        break;
                    }
                }

                //outToServer.flush();

                // Dividiamo gli ordini in base all'ordine che dobbiamo fare
                if(drinkIndex != 0 && Integer.parseInt(_order) >= drinkIndex){
                    skt.close();
                    skt = new Socket("localhost", 1423);

                    outToServer = new DataOutputStream(skt.getOutputStream());
                    inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));

                    //outputString = _order + "," + tempProductsList.toString().substring(1, inputString.length() - 1);

                    outToServer.writeBytes( _order + "," + tempProductsList.toString().substring(1, tempProductsList.toString().length() - 1) + "\n");
                    System.out.println(inFromServer.readLine());

                    skt.close();
                }else {
                    skt.close();
                    skt = new Socket("localhost", 4321);

                    outToServer = new DataOutputStream(skt.getOutputStream());
                    inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));

                    //outputString = _order + "," + tempProductsList.toString().substring(1, inputString.length() - 1);

                    outToServer.writeBytes( _order + "," + tempProductsList.toString().substring(1, inputString.length() - 1) + "\n");
                    System.out.println(inFromServer.readLine());
                }

                outToServer.flush();
            } catch (Exception e) {
                try {
                    skt.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.print("Whoops! It didn't work!\n");
            }
        }
    }
}
