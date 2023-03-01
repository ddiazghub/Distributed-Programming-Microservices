package com.uninorte.distributed.programming.distributedserviceclient.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Button;

public class DistributedServiceClientAppViewConnect extends JFrame {

	private JPanel contentPane;
	private JTextField connectFormUserIdField;
	private JTextField connectFormPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistributedServiceClientAppViewConnect frame = new DistributedServiceClientAppViewConnect();
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
	public DistributedServiceClientAppViewConnect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client Connect");
		lblNewLabel.setBounds(25, 10, 373, 23);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Password:\r\n");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(65, 105, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("User ID:\r\n");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(87, 63, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		connectFormUserIdField = new JTextField();
		connectFormUserIdField.setBounds(162, 60, 132, 20);
		contentPane.add(connectFormUserIdField);
		connectFormUserIdField.setColumns(10);
		
		connectFormPasswordField = new JTextField();
		connectFormPasswordField.setBounds(162, 102, 132, 20);
		contentPane.add(connectFormPasswordField);
		connectFormPasswordField.setColumns(10);
		
		Button btnConnectTcpClient = new Button("Connect");
		btnConnectTcpClient.setBounds(87, 150, 207, 34);
		contentPane.add(btnConnectTcpClient);
	}

}
