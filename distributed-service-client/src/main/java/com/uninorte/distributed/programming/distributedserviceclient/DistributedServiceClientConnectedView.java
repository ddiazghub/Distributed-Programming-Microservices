package com.uninorte.distributed.programming.distributedserviceclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class DistributedServiceClientConnectedView extends JFrame {

	private JPanel contentPane;
	private JTextField sendMessageField;
	private JTextField tokenIdField;
	private DistributedServiceProxy proxy;
	private ClientContext context;
	private List<TCPService> tcpServices;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistributedServiceClientConnectedView frame = new DistributedServiceClientConnectedView();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public DistributedServiceClientConnectedView(DistributedServiceProxy proxy, ClientContext context, List<TCPService> tcpServices) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 363, 265);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TokenId:\r\n");
		lblNewLabel.setBounds(51, 56, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Send Post");
		lblNewLabel_1.setBounds(51, 117, 70, 14);
		panel.add(lblNewLabel_1);
		
		sendMessageField = new JTextField();
		sendMessageField.setColumns(10);
		sendMessageField.setBounds(116, 114, 169, 20);
		panel.add(sendMessageField);
		this.sendMessageField = sendMessageField;
		
		tokenIdField = new JTextField();
		tokenIdField.setEditable(false);
		tokenIdField.setColumns(10);
		tokenIdField.setBounds(116, 53, 169, 20);
		panel.add(tokenIdField);
		
		Button sendPostMessage = new Button("Send Message");
		sendPostMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		sendPostMessage.setBounds(51, 169, 234, 22);
		panel.add(sendPostMessage);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JTextArea textArea = new JTextArea();
		panel_2.add(textArea);
	}
}
