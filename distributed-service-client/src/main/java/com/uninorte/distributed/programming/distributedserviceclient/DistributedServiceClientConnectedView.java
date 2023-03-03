package com.uninorte.distributed.programming.distributedserviceclient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Button;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class DistributedServiceClientConnectedView extends JFrame {

    private JPanel contentPane;
    private JTextField sendMessageField;
    private JTextField tokenIdField;
    private DistributedServiceProxy proxy;
    private ClientContext context;
    private List<TCPService> tcpServices;

    private JTextArea postsTextArea;
    
    /**
     * Create the frame.
     */
    public DistributedServiceClientConnectedView(DistributedServiceProxy proxy, ClientContext context, List<TCPService> tcpServices) {
        this.proxy = proxy;
        this.context = context;
        this.tcpServices = tcpServices;
        
        DistributedServiceClientConnectedView view = this;
        
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
        tabbedPane.addTab("Create post", null, panel, null);
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

        tokenIdField = new JTextField(context.getToken());
        tokenIdField.setEditable(false);
        tokenIdField.setColumns(10);
        tokenIdField.setBounds(116, 53, 169, 20);
        panel.add(tokenIdField);

        Button sendPostMessage = new Button("Send Message");
        
        sendPostMessage.addActionListener((ActionEvent e) -> {
            if(!sendMessageField.getText().equals("")) {
                try {
                    PostMessage post = new PostMessage(sendMessageField.getText(), context.getUser().getUser_id());
                    proxy.createPost(context.getToken(), post);
                    showMessageDialog(null, "Post Sent");
                } catch (Exception ex) {
                    showMessageDialog(null, "Failed to post message", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                
                showMessageDialog(null, "There are empty fields, please write info");
            }
        });
        
        sendPostMessage.setBounds(51, 169, 234, 22);
        panel.add(sendPostMessage);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Posts", null, panel_1, null);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane);

        JPanel panel_2 = new JPanel();
        scrollPane.setViewportView(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        this.postsTextArea = new JTextArea();
        this.printPosts();
        postsTextArea.setEditable(false);
        panel_2.add(postsTextArea);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                for (TCPService tcp : view.tcpServices)
                    tcp.stop();
                
                DistributedServiceClientApplication.appCtx.close();
            }
        });
        
        Timer timer = new Timer(100, e -> {
            if (this.context.getPostsChanged()) {
                this.context.setPostsChanged(false);
                this.printPosts();
            }
        });
        
        timer.setRepeats(true);
        timer.start();
    }
    
    public void printPosts() {
        StringBuilder text = new StringBuilder();
        
        for (PostMessage post : context.getPosts())
            text.append(post.toString()).append('\n');
        
        this.postsTextArea.setText(text.toString());
    }
}
