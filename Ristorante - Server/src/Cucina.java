import java.util.List;

public class Cucina {
    int _order;
    List prodottiDisponibili;

    // SET Section
    public void set_order(int _order){
        this._order = _order;
    }

    public void setProdottiDisponibili(List prodottiDisponibili){
        this.prodottiDisponibili = prodottiDisponibili;
    }

    // GET Section
    public List getProdottiDisponibili(){
        return this.prodottiDisponibili;
    }

    public int get_order(){
        return this._order;
    }
}
