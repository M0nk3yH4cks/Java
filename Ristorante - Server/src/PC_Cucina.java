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
    public void run(){
        while (true)
            evadeOrder();
    }
}
