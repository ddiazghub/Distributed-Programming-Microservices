package com.uninorte.distributed.programming.distributedserviceclient.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class DistributedServiceClientAppView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistributedServiceClientAppView frame = new DistributedServiceClientAppView();
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
	public DistributedServiceClientAppView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(70, 45, 300, 23);
		contentPane.add(lblNewLabel);
		
		Button btnConnectWindow = new Button("Connect");
		btnConnectWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConnectWindow.setBounds(87, 100, 87, 31);
		contentPane.add(btnConnectWindow);
		
		Button btnSignUpWindow = new Button("Sign Up");
		btnSignUpWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignUpWindow.setBounds(220, 100, 87, 31);
		contentPane.add(btnSignUpWindow);
	}
}
