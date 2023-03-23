import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class SecondFrame extends JFrame {
    private JLabel titleLabel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel producerLabel;
    private JTextField producerTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JLabel guaranteeLabel;
    private JTextField guaranteeTextField;
    private JLabel typeLabel;
    private JComboBox<Tip> typeComboBox;

    private JButton saveFavouriteProductButton;

    public ArrayList<Produs> productSecondList=new ArrayList<Produs>();

    public void registerProduct(ArrayList<Produs> favouriteList, ArrayList<Produs> cartList) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
        titleLabel.setText("Adauga produs favorit");
        titleLabel.setBounds(250, 100, 500, 20);
        getContentPane().add(titleLabel);

        idLabel = new JLabel();
        idLabel.setText("ID: ");
        idLabel.setBounds(20, 200, 100, 20);
        getContentPane().add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(150, 200, 100, 20);
        getContentPane().add(idTextField);

        nameLabel = new JLabel();
        nameLabel.setText("Nume produs: ");
        nameLabel.setBounds(20, 230, 100, 20);
        getContentPane().add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(150, 230, 100, 20);
        getContentPane().add(nameTextField);

        producerLabel = new JLabel();
        producerLabel.setText("Nume producator: ");
        producerLabel.setBounds(20, 260, 150, 20);
        getContentPane().add(producerLabel);

        producerTextField = new JTextField();
        producerTextField.setBounds(150, 260, 100, 20);
        getContentPane().add(producerTextField);

        priceLabel = new JLabel();
        priceLabel.setText("Pret: ");
        priceLabel.setBounds(20, 290, 150, 20);
        getContentPane().add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(150, 290, 100, 20);
        getContentPane().add(priceTextField);

        guaranteeLabel = new JLabel();
        guaranteeLabel.setText("Garantie(ani): ");
        guaranteeLabel.setBounds(20, 320, 150, 20);
        getContentPane().add(guaranteeLabel);

        guaranteeTextField = new JTextField();
        guaranteeTextField.setBounds(150, 320, 100, 20);
        getContentPane().add(guaranteeTextField);

        typeLabel = new JLabel();
        typeLabel.setText("Tip: ");
        typeLabel.setBounds(500, 260, 150, 20);
        getContentPane().add(typeLabel);

        typeComboBox=new JComboBox<Tip>();
        typeComboBox.setBounds(530, 260, 150, 20);
        typeComboBox.addItem(Tip.NORMAL);
        typeComboBox.addItem(Tip.PREMIUM);
        getContentPane().add(typeComboBox);

        saveFavouriteProductButton=new JButton();
        saveFavouriteProductButton.setText("Salveaza produs favorit");
        saveFavouriteProductButton.setBounds(500, 500, 250, 20);
        getContentPane().add(saveFavouriteProductButton);
        saveFavouriteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Produs produs = new Produs();
                    produs.setId(Integer.parseInt(idTextField.getText()));
                    produs.setName(nameTextField.getText());
                    if(produs.getName().isEmpty()==true){
                        throw new InvalidDataException("Error! Name is empty");
                    }
                    produs.setProducer(producerTextField.getText());
                    if(produs.getProducer().isEmpty()==true){
                        throw new InvalidDataException("Error! Producer is empty");
                    }
                    produs.setPrice(Double.parseDouble(priceTextField.getText()));
                        if(produs.getPrice()<0)
                            throw new InvalidDataException("Error! Price is negative");
                    produs.setGuarantee(Integer.parseInt(guaranteeTextField.getText()));
                    if(produs.getGuarantee()<0)
                        throw new InvalidDataException("Error! Guarantee is negative");
                    int index = typeComboBox.getSelectedIndex();
                    if (index == 0) {
                        produs.setType(Tip.NORMAL);
                    } else {
                        produs.setType(Tip.PREMIUM);
                    }
                    favouriteList.add(produs);
                    setVisible(false);
                    MainFrame mainFrame = new MainFrame(favouriteList, cartList);
                    mainFrame.setVisible(true);
                }
                catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
            }
        });

        setSize(800, 600);
        setVisible(true);
    }

}
