import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

// Dichiarazione Prodotti
public class Magazzino {
    int Pizza = 0;
    int Pasta = 0;
    int Pomodoro = 0;
    int Aglio = 0;
    int Zucca = 0;
    int Olio = 0;
    int Peperoncino = 0;
    int Ricotta = 0;
    int Torta = 0;
    int Sorbetto = 0;
    int Insalata = 0;
    int Acqua = 0;
    int Birra = 0;
    int CocaCola = 0;
    int Aranciata = 0;
    int Succo = 0;

    List prodottiDisponibili;                       // Inizializzazione Lista Prodotti
    Fornitore approvvigiona = new Fornitore();      // Dichiarazione del Fornitore
    int appSize = 0;

    synchronized public void setAppSize(int appSize){
        this.appSize = appSize;
    }

    synchronized public void setProdottiDisponibili() throws InterruptedException {
        List<String> prodottiDisponibili = new ArrayList<String>();     // Dichiarazione Lista d'appoggio Temporanea
        if (this.Pizza > 0)
            prodottiDisponibili.add("Pizza");
        if(this.Pasta > 0)
            prodottiDisponibili.add("Pasta in bianco");
        if(this.Pasta > 0 && this.Zucca > 0)
            prodottiDisponibili.add("Pasta alla Zucca");
        if(this.Pasta > 0 && this.Ricotta > 0)
            prodottiDisponibili.add("Pasta alla Ricotta");
        if(this.Pasta > 0 && this.Pomodoro > 0)
            prodottiDisponibili.add("Pasta Asciutta");
        if(this.Pasta > 0 && this.Aglio > 0 && this.Olio > 0 && this.Peperoncino > 0)
            prodottiDisponibili.add("Pasta Aglio Olio e Peperoncino");
        if(this.Torta > 0)
            prodottiDisponibili.add("Torta");
        if(this.Sorbetto > 0)
            prodottiDisponibili.add("Sorbetto");
        if(this.Insalata > 0)
            prodottiDisponibili.add("Insalata");
        if(this.Acqua > 0)
            prodottiDisponibili.add("Acqua");
        if(this.CocaCola > 0)
            prodottiDisponibili.add("CocaCola");
        if(this.Aranciata > 0)
            prodottiDisponibili.add("Aranciata");
        if(this.Birra > 0)
            prodottiDisponibili.add("Birra");
        if(this.Succo > 0)
            prodottiDisponibili.add("Succo");

        // Controllo sulla disponibilità dei prodotti in magazzino
        if(prodottiDisponibili.size() != 0)
            this.prodottiDisponibili = prodottiDisponibili;
        else{
            System.err.println("Errore nell'approvvigionamento delle risorse\nStiamo lavorando per ripristinare le scorte");
            sleep(100);
            getApprovvigionamenti();
            setProdottiDisponibili();
            System.out.println("Integrità magazzino ripristinata");
        }
    }

    synchronized  List getProdottiDisponibili() {
        return prodottiDisponibili;
    }

    synchronized public void getApprovvigionamenti(){
        if (appSize == 0)
            throw new RuntimeException("This is thrown intentionally");
        try {
            this.Pizza = appSize;
            this.Pasta = appSize + 5;     // Aggiungo 5 per verificare il funzionamento dei tipi
            this.Pomodoro = appSize;
            this.Aglio = appSize;
            this.Zucca = appSize;
            this.Olio = appSize;
            this.Peperoncino = appSize;
            this.Ricotta = appSize;
            this.Torta = appSize;
            this.Sorbetto = appSize;
            this.Insalata = appSize;
            this.Succo = appSize;
            this.Birra = appSize;
            this.Aranciata = appSize;
            this.CocaCola = appSize;
            this.Acqua = appSize;
            System.out.println("Approvvigionamenti Ripristinati");
        }catch (Exception e){
            System.err.println("Impossibile richiedere approvvigionamenti:\n"+e);
            System.exit(1);
        }
    }

    synchronized public void setItemCount(int option,String itemList){
        String item = itemList;
        if(item.equals("Pizza"))
            this.Pizza--;
        else if(item.equals("Pasta in bianco"))
            this.Pasta--;
        else if(item.equals("Pasta alla Zucca")){
            this.Pasta--;
            this.Zucca--;
        }else if(item.equals("Pasta alla Ricotta")){
            this.Pasta--;
            this.Ricotta--;
        }else if(item.equals("Pasta Asciutta")){
            this.Pasta--;
            this.Pomodoro--;
        }else if(item.equals("Pasta Aglio Olio e Peperoncino")){
            this.Pasta--;
            this.Aglio--;
            this.Olio--;
            this.Peperoncino--;
        }else if(item.equals("Torta"))
            this.Torta--;
        else if(item.equals("Sorbetto"))
            this.Sorbetto--;
        else if(item.equals("Insalata"))
            this.Insalata--;
        else if(item.equals("Acqua"))
            this.Acqua--;
        else if(item.equals("CocaCola"))
            this.CocaCola--;
        else if(item.equals("Aranciata"))
            this.Aranciata--;
        else if(item.equals("Birra"))
            this.Birra--;
        else this.Succo--;

        System.err.println("FUCKIN UPDATED");
    }
}
