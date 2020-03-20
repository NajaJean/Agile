
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
class CreateClient extends JFrame implements ActionListener {
 
    java.awt.Container container = getContentPane();
    
    JLabel newClientTitle = new JLabel("Create a Client");
    
    JLabel clientNameLabel = new JLabel("Client Name");
    JTextField clientNameTextField = new JTextField();
    
    JLabel addressLabel = new JLabel("Address");
    JTextField addressField = new JTextField();
    
    JLabel referenceLabel = new JLabel("Reference Name");
    JTextField referenceField = new JTextField();
    
    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField();
    
    JButton createButton = new JButton("Create Account");
 
    CreateClient() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	newClientTitle.setBounds(139, 50, 92, 30);
    	
        clientNameLabel.setBounds(40, 150, 125, 30);
        clientNameTextField.setBounds(150, 150, 175, 30);
        
        addressLabel.setBounds(40, 200, 125, 30);
        addressField.setBounds(150, 200, 175, 30);
        
        referenceLabel.setBounds(40 , 250, 125, 30);
        referenceField.setBounds(150, 250, 175, 30);
        
        emailLabel.setBounds(40, 300, 125, 30);
        emailField.setBounds(150, 300, 175, 30);
        
        createButton.setBounds(85, 500, 200, 30);
    }
 
    public void addComponentsToContainer() {
    	container.add(newClientTitle);
    	
        container.add(clientNameLabel);
        container.add(clientNameTextField);
        
        container.add(addressLabel);
        container.add(addressField);
        
        container.add(referenceLabel);
        container.add(referenceField);
        
        container.add(emailLabel);
        container.add(emailField);

        container.add(createButton);
    }
 
    public void addActionEvent() {
        createButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == createButton) {
            String clientNameText;
            String addressText;
            String referenceText;
            String emailText;
            
            clientNameText = clientNameTextField.getText();
            addressText = addressField.getText();
            referenceText = referenceField.getText();
            emailText = emailField.getText();
            
            System.out.println(clientNameText);
            System.out.println(addressText);
            System.out.println(referenceText);
            System.out.println(emailText);
            
            Client test = new Client(clientNameText, emailText, addressText);
        }
    }
}
 
public class CreateClientGUI {
    public static void main(String[] a) {
        CreateClient frame = new CreateClient();
        frame.setTitle("Create Client");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
 


