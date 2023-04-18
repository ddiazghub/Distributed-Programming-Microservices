package com.uninorte.distributed.programming.distributedserviceclient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.FileService;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserFile;

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
import java.io.IOException;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public class DistributedServiceClientConnectedView extends JFrame {

    private DefaultTreeModel model;
    private JTree filesTree;
    private JPanel contentPane;
    private JTextField sendMessageField;
    private JTextField tokenIdField;
    private DistributedServiceProxy proxy;
    private ClientContext context;
    private List<TCPService> tcpServices;
    private FileService files;
    private JTextArea postsTextArea;
    private JFileChooser fc;
    private Logger log = LoggerFactory.getLogger(DistributedServiceClientAppView.class);
    
    /**
     * Create the frame.
     */
    public DistributedServiceClientConnectedView(DistributedServiceProxy proxy, ClientContext context, List<TCPService> tcpServices, FileService files) {
        this.proxy = proxy;
        this.context = context;
        this.tcpServices = tcpServices;
        this.files = files;
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
        
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Upload & Download", null, panel_3, null);
        panel_3.setLayout(null);
        
        Button downloadBtn = new Button("Download File");
        downloadBtn.setBounds(192, 205, 82, 22);
        panel_3.add(downloadBtn);
        
        Button uploadBtn = new Button("Upload File");
        uploadBtn.addActionListener(e -> {
            fc = new JFileChooser(Paths.get(System.getProperty("user.home")).resolve("Downloads").toFile());
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setAcceptAllFileFilterUsed(true);
            int option = fc.showOpenDialog(this);

            if(option == JFileChooser.APPROVE_OPTION) {
                try {
                    int opt = showConfirm("Encrypt file?");
                    files.upload(fc.getSelectedFile(), opt == JOptionPane.YES_OPTION);
                    log.info("File uploaded");
                } catch (HeadlessException | IOException ex) {
                    showError("Upload fail");
                    log.error("Error", ex);
                }
            } else if (option == JFileChooser.ERROR_OPTION){
                showError("Upload fail");
            }
        });
        
        uploadBtn.setBounds(77, 205, 82, 22);
        panel_3.add(uploadBtn);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(38, 0, 275, 199);
        panel_3.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        JScrollPane scrollPane_1 = new JScrollPane();
        panel_4.add(scrollPane_1);
        
        JTree filesTree = new JTree();
        scrollPane_1.setViewportView(filesTree);
        
        filesTree.addTreeSelectionListener(e -> {
            if (filesTree.getSelectionPath() != null && !((DefaultMutableTreeNode) filesTree.getSelectionPath().getLastPathComponent()).isLeaf())
                filesTree.setSelectionPath(null);
        });
        
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
            
            if (this.context.getFilesChanged()) {
                this.context.setFilesChanged(false);
                this.printFiles();
            }
        });
        
        timer.setRepeats(true);
        timer.start();
        
        this.filesTree = filesTree;
        this.model = new DefaultTreeModel(null);
        this.filesTree.setModel(model);
        this.printFiles();
        filesTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        downloadBtn.addActionListener(e -> {
            TreePath path = filesTree.getSelectionPath();

            if (path == null) {
                showError("No file selected");
            } else {
                try {
                    DefaultMutableTreeNode selected = ((DefaultMutableTreeNode) path.getLastPathComponent());
                    UserFile file = (UserFile) selected.getUserObject();
                    String key = "";
                    boolean decrypt = false;
                    
                    if (Objects.equals(file.getUserId(), context.getUser().getUser_id())) {
                        key = context.getUser().getUser_password();
                    } else {
                        if (showConfirm("Encrypt file?") == JOptionPane.YES_OPTION) {
                            key = JOptionPane.showInputDialog("Input the decryption key:");
                            decrypt = true;
                        }
                    }
                    
                    Path filepath = files.download(file.getFilename(), key, decrypt);
                    showMessage("File downloaded to path: " + filepath.toAbsolutePath().toString());
                } catch (IOException ex) {
                    log.warn("Error", ex);
                    showError("Download Failed");
                } catch (RuntimeException ex) {
                    showError("Decryption failed. The encrypted file was still downloaded to the downloads folder");
                }
            }
        });
        
    }
    
    public void printPosts() {
        StringBuilder text = new StringBuilder();
        
        for (PostMessage post : context.getPosts())
            text.append(post.toString()).append('\n');
        
        this.postsTextArea.setText(text.toString());
    }
    
    public void printFiles() {  	
    	this.filesTree.removeAll();
    	DefaultMutableTreeNode node = new DefaultMutableTreeNode("files");
    	
    	for (UserFile file: this.context.getFiles()) {
            node.add(new DefaultMutableTreeNode(file));
    	}

    	this.model.setRoot(node);
    }
    
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
    }
}
