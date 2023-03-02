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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class DistributedServiceClientAppViewTcpClientConnected extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField postMessageField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistributedServiceClientAppViewTcpClientConnected frame = new DistributedServiceClientAppViewTcpClientConnected();
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
	public DistributedServiceClientAppViewTcpClientConnected() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(122, 11, 316, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("My Token ID:\r\n");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(45, 105, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(146, 102, 338, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Post Message:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(45, 187, 92, 14);
		contentPane.add(lblNewLabel_1_1);
		
		postMessageField = new JTextField();
		postMessageField.setColumns(10);
		postMessageField.setBounds(146, 184, 338, 20);
		contentPane.add(postMessageField);
		
		Button btnSendPost = new Button("Send Message\r\n");
		btnSendPost.setBounds(174, 263, 240, 22);
		contentPane.add(btnSendPost);
	}
}
