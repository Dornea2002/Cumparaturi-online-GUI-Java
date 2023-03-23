import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Produs> favouriteList=new ArrayList<Produs>();
        ArrayList<Produs> cartList=new ArrayList<Produs>();
        MainFrame mainFrame=new MainFrame(favouriteList, cartList);
    }
}