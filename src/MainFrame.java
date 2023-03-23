import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JLabel titleLabel;
    private JButton addFavouriteProductButton;
    private JList<Produs> favouriteProductList;
    private JList<Produs> cartList;
    private JButton addToCartButton;
    private JButton deleteFavouriteProductButton;
    private JCheckBox useDiscountCheckBox;
    private JButton totalPriceButton;
    private JLabel totalPriceLabel;
    private JList<Produs> cartJList;

    public MainFrame(ArrayList<Produs> favouriteList, ArrayList<Produs> cartList) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        titleLabel = new JLabel();
        titleLabel.setText("Cumparaturi online");
        titleLabel.setFont(new Font("Georgia", Font.ITALIC + Font.BOLD, 20));
        titleLabel.setBounds(250, 100, 500, 20);
        getContentPane().add(titleLabel);

        addFavouriteProductButton = new JButton();
        addFavouriteProductButton.setText("Adauga produs favorit");
        addFavouriteProductButton.setBounds(250, 130, 250, 20);
        getContentPane().add(addFavouriteProductButton);
        addFavouriteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                SecondFrame secondFrame = new SecondFrame();
                secondFrame.registerProduct(favouriteList, cartList);
                secondFrame.setVisible(true);
                rewriteFrom(favouriteList, favouriteProductList);
                rewriteTo(cartList, cartJList);
            }
        });

        JLabel titleFavouriteList = new JLabel();
        titleFavouriteList.setText("Produse favorite");
        titleFavouriteList.setBounds(20, 380, 100, 20);
        getContentPane().add(titleFavouriteList);

        favouriteProductList = new JList<Produs>();
        favouriteProductList.setBounds(20, 400, 250, 300);
        getContentPane().add(favouriteProductList);

        JLabel titleCartList = new JLabel();
        titleCartList.setText("Cos cumparaturi");
        titleCartList.setBounds(510, 380, 100, 20);
        getContentPane().add(titleCartList);

        cartJList = new JList<Produs>();
        cartJList.setBounds(510, 400, 250, 300);
        getContentPane().add(cartJList);

        addToCartButton = new JButton();
        addToCartButton.setText("Adauga in cos");
        addToCartButton.setBounds(300, 450, 180, 20);
        getContentPane().add(addToCartButton);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = favouriteProductList.getSelectedIndex();
                if (index >= 0) {
                    Produs produs = favouriteProductList.getModel().getElementAt(index);
                    cartList.add(produs);
                    rewriteTo(cartList, cartJList);
                    favouriteList.remove(index);
                    rewriteTo(favouriteList, favouriteProductList);
                } else {
                    String id = JOptionPane.showInputDialog(null, "Introduceti ID-ul produsului pe care doriti sa il mutati:");
                    try {
                        int idSearch = Integer.parseInt(id);
                        Produs produs = null;
                        int newIndex = -1;
                        for (Produs p : favouriteList) {
                            newIndex++;
                            if (p.getId() == idSearch) {
                                produs = p;
                                break;
                            }
                        }
                        if (produs != null) {
                            cartList.add(produs);
                            rewriteTo(cartList, cartJList);
                            favouriteList.remove(newIndex);
                            rewriteTo(favouriteList, favouriteProductList);
                        } else throw new Exception("Acest produs nu exista!");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
            }
        });

        deleteFavouriteProductButton = new JButton();
        deleteFavouriteProductButton.setText("Sterge produs favorit");
        deleteFavouriteProductButton.setBounds(300, 480, 180, 20);
        getContentPane().add(deleteFavouriteProductButton);
        deleteFavouriteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = favouriteProductList.getSelectedIndex();
                if (index >= 0) {
                    Produs produs = favouriteProductList.getModel().getElementAt(index);
                    favouriteList.remove(index);
                    rewriteTo(favouriteList, favouriteProductList);
                } else {
                    String id = JOptionPane.showInputDialog(null, "Introduceti ID-ul produsului pe care doriti sa il stergeti:");
                    try {
                        int idSearch = Integer.parseInt(id);
                        Produs produs = null;
                        int newIndex = -1;
                        for (Produs p : favouriteList) {
                            newIndex++;
                            if (p.getId() == idSearch) {
                                produs = p;
                                break;
                            }
                        }
                        if (produs != null) {
                            favouriteList.remove(newIndex);
                            rewriteTo(favouriteList, favouriteProductList);
                        } else throw new Exception("Acest produs nu exista!");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
            }
        });

        useDiscountCheckBox = new JCheckBox();
        useDiscountCheckBox.setText("Foloseste discount premium");
        useDiscountCheckBox.setBounds(300, 620, 200, 20);
        getContentPane().add(useDiscountCheckBox);
/*        useDiscountCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs = null;
                for (Produs p : favouriteList) {
                    if (p.getPrice() > produs.getPrice()) {
                        produs = p;
                    }
                }
                if (produs != null)
                    produs.setPrice(produs.getPrice() + produs.getPrice() * 0.2);
                rewriteFrom(favouriteList, favouriteProductList);
            }
        });*/

        totalPriceButton = new JButton();
        totalPriceButton.setText("Calculeaza total cos");
        totalPriceButton.setBounds(300, 650, 180, 20);
        totalPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double suma = 0;
                if(!useDiscountCheckBox.isSelected()) {
                    for (Produs p : cartList) {
                        suma += p.getPrice();
                    }
                    totalPriceLabel.setText("Total cos: " + suma + " lei");
                }
                else{
                    Produs produs=new Produs();
                    produs.setPrice(0);
                    for (Produs p : cartList) {
                        if (p.getPrice() > produs.getPrice() && p.getType()==Tip.PREMIUM) {
                            produs = p;
                        }
                    }
                    suma+=produs.getPrice()-produs.getPrice()*0.2;
                    for (Produs p : cartList) {
                        if (p!=produs) {
                            suma+=p.getPrice();
                        }
                    }
                }
            }
        });

        getContentPane().add(totalPriceButton);

        totalPriceLabel = new JLabel();
        totalPriceLabel.setText("Total cos: X lei");
        totalPriceLabel.setBounds(350, 680, 180, 20);

        getContentPane().add(totalPriceLabel);

        rewriteFrom(favouriteList, favouriteProductList);

        rewriteTo(cartList, cartJList);

        this.setSize(800, 800);
        this.setVisible(true);

    }

    public void rewriteFrom(ArrayList<Produs> produse, JList list) {
        DefaultListModel<Produs> fromList = new DefaultListModel<Produs>();
        for (Produs p : produse) {
            fromList.addElement(p);
        }
        list.setModel(fromList);
    }

    public void rewriteTo(ArrayList<Produs> produse, JList list) {
        DefaultListModel<Produs> toList = new DefaultListModel<Produs>();
        for (Produs p : produse) {
            toList.addElement(p);
        }
        list.setModel(toList);
    }
}
