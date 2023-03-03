package com.uninorte.distributed.programming.distributedserviceclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import static javax.swing.JOptionPane.showMessageDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.uninorte.distributed.programming.distributedserviceclient.DistributedServiceClientApplication;
import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.TextField;
import javax.swing.JScrollPane;


public class DistributedServiceClientAppView extends JFrame {

	private Logger log = LoggerFactory.getLogger(DistributedServiceClientApplication.class); 
	
	private DistributedServiceProxy proxy;
	private ClientContext context;
	private List<TCPService> tcpServices;
	
	private JPanel contentPane;
	private static DistributedServiceClientAppView frame;
	private JTextField UserIdField;
	private JTextField PasswordField;
	private JTextField FormEmailField;
	
	/**
	 * Create the frame.
	 */
	public DistributedServiceClientAppView(DistributedServiceProxy proxy, ClientContext context, List<TCPService> tcpServices) {
		this.proxy = proxy;
		this.context = context;
		this.tcpServices = tcpServices;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(44, 29, 300, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(73, 98, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextField UserIdField = new JTextField();
		UserIdField.setBounds(165, 98, 127, 22);
		contentPane.add(UserIdField);
		this.UserIdField = UserIdField;
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(73, 123, 74, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JTextField PasswordField = new JTextField();
		PasswordField.setBounds(165, 123, 127, 22);
		contentPane.add(PasswordField);
		this.PasswordField = PasswordField;
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(73, 148, 64, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JTextField FormEmailField = new JTextField();
		FormEmailField.setBounds(165, 148, 127, 22);
		contentPane.add(FormEmailField);
		this.FormEmailField = FormEmailField;
		JFrame view = this;
		
		Button btnConnectWindow = new Button("Create Client\r\n");
		btnConnectWindow.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//create user
				if(UserIdField.getText()!= "" && PasswordField.getText() != "" && FormEmailField.getText()!="") {
					
					User user = new User(UserIdField.getText(), PasswordField.getText(), FormEmailField.getText());
		            context.init(user);
		            user = context.getUser();
		            
		            EventQueue.invokeLater(new Runnable() {
		    			public void run() {
		    				DistributedServiceClientConnectedView frame = new DistributedServiceClientConnectedView(proxy, context, tcpServices);
		    				frame.setVisible(true);
		    				view.dispose();
		    			}
		    		});		          
		            showMessageDialog(null, "Connected");
		            
				}else{
					showMessageDialog(null, "There are empty fields, please write info");
				}				           								
			}
		});
		
		btnConnectWindow.setBounds(97, 195, 164, 31);
		contentPane.add(btnConnectWindow);
		
	}
}
