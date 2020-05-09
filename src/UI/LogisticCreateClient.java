package UI;

import javax.swing.*;

public class LogisticCreateClient extends JFrame {
    java.awt.Container container = getContentPane();
    JLabel insertUserNameLabel = new JLabel ("Insert Username:");
	JLabel jcomp2 = new JLabel ("Insert Password:");
	JLabel insertNameLabel = new JLabel ("Insert Name:");
	JLabel insertEmailLabel = new JLabel ("Insert Email:");
	JLabel insertAddressLabel = new JLabel ("Insert Address:");
	JTextField userNameTextField = new JTextField (5);
	JTextField passwordTextField = new JTextField (5);
	JTextField nameTextField = new JTextField (5);
	JTextField emailTextField = new JTextField (5);
	JTextField addressTextField = new JTextField (5);
	JButton createButton = new JButton ("Create ");
	JButton returnButton = new JButton ("Return");
	
	public LogisticCreateClient() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        
        setTitle("Create Client");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	insertUserNameLabel.setBounds (20, 20, 120, 25);
        jcomp2.setBounds (20, 50, 115, 25);
        insertNameLabel.setBounds (20, 80, 120, 25);
        insertEmailLabel.setBounds (20, 110, 120, 25);
        insertAddressLabel.setBounds (20, 140, 120, 25);
        userNameTextField.setBounds (140, 20, 100, 25);
        passwordTextField.setBounds (140, 50, 100, 25);
        nameTextField.setBounds (140, 80, 100, 25);
        emailTextField.setBounds (140, 110, 100, 25);
        addressTextField.setBounds (140, 140, 100, 25);
        createButton.setBounds (75, 175, 100, 25);
        returnButton.setBounds (60, 210, 135, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(insertUserNameLabel);
    	container.add(jcomp2);
    	container.add(insertNameLabel);
    	container.add(insertEmailLabel);
    	container.add(insertAddressLabel);
    	container.add(userNameTextField);
    	container.add(passwordTextField);
    	container.add(nameTextField);
    	container.add(emailTextField);
    	container.add(addressTextField);
    	container.add(createButton);
    	container.add(returnButton);
    }
    
    public JButton getCreateButton() {
		return createButton;
	}
	public JButton getReturnButton() {
		return returnButton;
	}
	public JTextField getUserNameTextField() {
		return userNameTextField;
	}
	public JTextField getPasswordTextField() {
		return passwordTextField;
	}
	public JTextField getNameTextField() {
		return nameTextField;
	}
	public JTextField getEmailTextField() {
		return emailTextField;
	}
	public JTextField getAddressTextField() {
		return addressTextField;
	}
}
