import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class PC_Cucina extends Thread{
    int _order = -1;
    List prodottiDisponibili;

    public void setOrder(int ordine, List prodottiDisponibili){
        this._order = ordine;
        this.prodottiDisponibili = prodottiDisponibili;
    }

    public void evadeOrder(){
        if(this._order != -1){
           // magazzino.setItemCount(_order, prodottiDisponibili);
           // this._order = -1;
        }else
            System.out.println("In attesa di un ordine alimentare");
    }

    @Override
    public void run() {
        while (true) {
            Socket skt = null;
            try {
                skt = new Socket("localhost", 4040);
                skt.setSoTimeout(1000);
                DataOutputStream outToServer = new DataOutputStream(skt.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                String input = null;

                System.out.println("\t\t\t----------Client Started----------\nRequesting Order");

                outToServer.writeBytes("req" + "\n");
                System.out.println("Request Sent");
                try {
                    input = inFromServer.readLine();
                } catch (SocketTimeoutException e) {
                    run();
                }

                System.out.println("Received Order:\n" + input);

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
