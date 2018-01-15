import java.util.List;

public class PC_Bar extends Thread{
    int _order = -1;

    public void evadeOrder(){
        if(this._order != -1) {
            this._order = -1;
        }else
            System.out.println("In attesa di un ordine alimentare");
    }

    @Override
    public void run() {

        evadeOrder();
    }
}
