import java.util.List;

public class Bar {
    int _order = -1;
    List prodottiDisponibili;

    // SET Section
    synchronized public void set_order(int _order){
        this._order = _order;
    }

    synchronized public void setProdottiDisponibili(List prodottiDisponibili){
        this.prodottiDisponibili = prodottiDisponibili;
    }

    // GET Section
    synchronized public List getProdottiDisponibili(){
        return this.prodottiDisponibili;
    }

    synchronized public int get_order(){
        return this._order;
    }
}
