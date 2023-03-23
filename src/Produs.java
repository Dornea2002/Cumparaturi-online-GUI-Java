import java.awt.*;

public class Produs {
    private int id;
    private String name;
    private String producer;
    private double price;
    private int guarantee;
    private Tip type;

    public Produs(){};

    public Produs(int id, String name, String producer, double price, int guarantee, Tip type) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.guarantee = guarantee;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public Tip getType() {
        return type;
    }

    public void setType(Tip type) {
        this.type =type;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", price=" + price +
                ", guarantee=" + guarantee +
                ", type=" + type +
                '}';
    }

}
