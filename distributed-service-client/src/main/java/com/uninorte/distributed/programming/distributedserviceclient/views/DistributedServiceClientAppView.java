package com.uninorte.distributed.programming.distributedserviceclient.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

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

@SpringBootApplication
@EnableFeignClients
public class DistributedServiceClientAppView extends JFrame implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(DistributedServiceClientApplication.class);
	private static ConfigurableApplicationContext appCtx;   
	@Autowired
	private DistributedServiceProxy proxy;    
	@Autowired
	private ClientContext context;    
	@Autowired
	private List<TCPService> tcpServices;
	private JPanel contentPane;
	private static DistributedServiceClientAppView frame;

	/**
	 * Launch the application.
	 */
	public static String[] argsvariable;
	
	public static void main(String[] args) {
		//appCtx = SpringApplication.run(DistributedServiceClientAppView.class, argsvariable);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DistributedServiceClientAppView();
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
		setBounds(100, 100, 400, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distributed Programming Client");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(44, 29, 300, 23);
		contentPane.add(lblNewLabel);
		
		Button btnConnectWindow = new Button("Create Client\r\n");
		btnConnectWindow.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DistributedServiceClientAppViewTcpClientConnected connectionFrame = new DistributedServiceClientAppViewTcpClientConnected();
				
				connectionFrame.setVisible(true);
				frame.setVisible(false);
				
								
			}
		});
		
		btnConnectWindow.setBounds(97, 195, 164, 31);
		contentPane.add(btnConnectWindow);
		
		JLabel lblNewLabel_1 = new JLabel("User ID:\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(73, 98, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		TextField UserIdField = new TextField();
		UserIdField.setBounds(165, 98, 127, 22);
		contentPane.add(UserIdField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(73, 123, 74, 14);
		contentPane.add(lblNewLabel_1_1);
		
		TextField PasswordField = new TextField();
		PasswordField.setBounds(165, 123, 127, 22);
		contentPane.add(PasswordField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(73, 148, 64, 14);
		contentPane.add(lblNewLabel_1_2);
		
		TextField FormEmailField = new TextField();
		FormEmailField.setBounds(165, 148, 127, 22);
		contentPane.add(FormEmailField);
	}

	public void connectNewUser(){
		appCtx = SpringApplication.run(DistributedServiceClientAppView.class, argsvariable);
		
	}
	@Override
    public void run(String... args) {
        log.info("Application started");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        
        exec.execute(() -> {
            try {
                Thread.sleep(30000);
                User user = new User("name", "hello123", "name@email.com");
                context.init(user);
                user = context.getUser();
                PostMessage post = new PostMessage("title", "Hello World!!!", user.getUser_id());
                proxy.createPost(context.getToken(), post);
                Thread.sleep(5000);
                
                for (TCPService tcp : this.tcpServices)
                    tcp.stop();
            } catch (Exception ex) {
                log.error("Error", ex);
            }

            log.info("Application finished");
            appCtx.close();
        });
	 }
}
