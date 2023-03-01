package com.uninorte.distributed.programming.distributedserviceclient.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DistributedServiceClienteAppViewSignup extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistributedServiceClienteAppViewSignup frame = new DistributedServiceClienteAppViewSignup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DistributedServiceClienteAppViewSignup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User ID:\r\n");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(106, 53, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client Sign Up");
		lblNewLabel.setBounds(39, 11, 366, 23);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(106, 78, 74, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(106, 103, 64, 14);
		contentPane.add(lblNewLabel_1_2);
		
		TextField signupFormUserIdField = new TextField();
		signupFormUserIdField.setBounds(198, 53, 127, 22);
		contentPane.add(signupFormUserIdField);
		
		TextField signupFormPasswordField = new TextField();
		signupFormPasswordField.setBounds(198, 78, 127, 22);
		contentPane.add(signupFormPasswordField);
		
		TextField signupFormEmailField = new TextField();
		signupFormEmailField.setBounds(198, 103, 127, 22);
		contentPane.add(signupFormEmailField);
		
		Button btnSignUpTcpClient = new Button("Sign Up");
		btnSignUpTcpClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignUpTcpClient.setBounds(117, 148, 208, 35);
		contentPane.add(btnSignUpTcpClient);
	}
}
