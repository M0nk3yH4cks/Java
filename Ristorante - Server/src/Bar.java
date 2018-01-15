import java.util.List;

public class Bar {
    int _order = -1;
    String prodottiDisponibili;

    // SET Section
    synchronized public void set_order(int _order){
        this._order = _order;
    }

    synchronized public void setProdottiDisponibili(String prodottiDisponibili){
        this.prodottiDisponibili = prodottiDisponibili;
    }

    // GET Section
    synchronized public String getProdottiDisponibili(){
        return this.prodottiDisponibili;
    }

    synchronized public int get_order(){
        return this._order;
    }
}
