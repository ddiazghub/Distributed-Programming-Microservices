package com.uninorte.distributed.programming.distributedserviceclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import static javax.swing.JOptionPane.showMessageDialog;

import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.FileService;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.JScrollPane;

public class DistributedServiceClientAppView extends JFrame {

    private DistributedServiceProxy proxy;
    private ClientContext context;
    private List<TCPService> tcpServices;
    private FileService files;
    private JPanel contentPane;
    private JTextField UserIdField;
    private JTextField PasswordField;
    private JTextField FormEmailField;
    private Logger log = LoggerFactory.getLogger(DistributedServiceClientAppView.class);

    /**
     * Create the frame.
     */
    public DistributedServiceClientAppView(DistributedServiceProxy proxy, ClientContext context, List<TCPService> tcpServices, FileService files) {
        this.proxy = proxy;
        this.context = context;
        this.tcpServices = tcpServices;
        this.files = files;
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
        DistributedServiceClientAppView view = this;

        Button btnConnectWindow = new Button("Create Client\r\n");
        
        btnConnectWindow.addActionListener((ActionEvent e) -> {
            //create user
            if(!"".equals(UserIdField.getText()) && !"".equals(PasswordField.getText()) && !"".equals(FormEmailField.getText())) {
                User user = new User(UserIdField.getText(), PasswordField.getText(), FormEmailField.getText());
        
                try {
                    context.init(user);
                
                    //String filename = "C:\\Users\\david\\Downloads\\linealfor.c";
                    //log.info("Uploading file \"" + filename + "\":");

                    /*try {
                        files.upload(new File(filename));
                        log.info("File uploaded");
                    } catch (Exception ex) {
                        log.error("Error", ex);
                    }*/
                
                    EventQueue.invokeLater(() -> {
                        DistributedServiceClientConnectedView frame1 = new DistributedServiceClientConnectedView(proxy, context, tcpServices, files);
                        frame1.setVisible(true);
                        view.dispose();
                    });

                    showMessageDialog(null, "Connected");
                } catch (Exception ex) {
                    showMessageDialog(null, "Connection to server failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                showMessageDialog(null, "There are empty fields, please write info");
            }
        });

        btnConnectWindow.setBounds(97, 195, 164, 31);
        contentPane.add(btnConnectWindow);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                for (TCPService tcp : view.tcpServices)
                    tcp.stop();
                
                DistributedServiceClientApplication.appCtx.close();
            }
        });
    }
}
