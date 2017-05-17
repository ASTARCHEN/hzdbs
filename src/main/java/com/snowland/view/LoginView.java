package com.snowland.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.User;
import com.snowland.client.Client;
import com.snowland.client.ClientJsonCreater;  
  
public class LoginView extends JFrame implements ActionListener  
{  
	private Client client;
    JButton login = new JButton("��¼");  
    JButton exit = new JButton("�˳�");  
    JLabel  name = new JLabel("�û�����");
    JLabel password = new JLabel("���룺");   
    JTextField JName = new JTextField(10); //�����˺�����  
    JPasswordField JPassword = new JPasswordField(10); // �������������룻  
      
    public LoginView() throws UnknownHostException, IOException   
    {  
    	client = new Client();
        JPanel jp = new JPanel();  
        jp.setLayout(new GridLayout(3,2));  //3��2�е����jp�����񲼾֣�  
          
        name.setHorizontalAlignment(SwingConstants.RIGHT);  //���ø�����Ķ��뷽ʽΪ���Ҷ���  
        password.setHorizontalAlignment(SwingConstants.RIGHT);  
          
        jp.add(name);   //�����ݼӵ����jp��  
        jp.add(JName);    
        jp.add(password);  
        jp.add(JPassword);    
        jp.add(login);  
        jp.add(exit);  
          
        login.addActionListener(this); //��¼�����¼�����  
        exit.addActionListener(this);   //�˳������¼�����  
          
        this.add(jp,BorderLayout.CENTER);   //��������嶨�����м�  
          
        this.setTitle("��¼����");  
        this.setLocation(500,300);  //���ó�ʼλ��  
        this.pack();        //��ʾ��������Զ�������С  
        this.setVisible(true);  
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        	client.exit();
	        super.windowClosing(e);
	        }
        });
    }  
    public void actionPerformed(ActionEvent e)  // ��ʱ����д���  
    {  
        if(e.getSource() == exit)  
        {  
            int i = JOptionPane.showConfirmDialog(null,"ȷ��Ҫ�˳���", "ȷ��", JOptionPane.YES_NO_OPTION);  
            // ��ʾѡ��Ի���  
            if(i == JOptionPane.YES_OPTION);  
            System.exit(0);  
        }  
        else  
        {  
        	String username = JName.getText();
        	String password = String.valueOf(JPassword.getPassword());
        	JSONObject part = new JSONObject();
        	part.put("password", password);
        	ClientJsonCreater jsonCreater = new ClientJsonCreater(username, null, "login", part);
        	try {
				Socket socket = new Socket(Common.HOST, Common.PORT);
				client.setSocket(socket);
				User user = new User(username,password);
				this.client.setUser(user);
				client.login(password);
				JSONObject responseJSONObject = client.getResponse();
				JSONObject resultJSONObject = new JSONObject((String)responseJSONObject.get("response"));
				resultJSONObject.get("tag");
				
        	} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	//socket.
//        	int res = 1;
//        	
//            switch(res)  
//            {
//            case 1 :
//                JOptionPane.showMessageDialog(null, "��¼�ɹ�����ӭ������");  
//                //��ʾ��Ϣ��ʾ��  
//                System.exit(0);
//                break;
//            case 0 :
//            	JOptionPane.showMessageDialog(null, "�û��������������µ�¼��");  
//                //��ʾ��Ϣ��ʾ��  
//                JName.setText("");   
//                JPassword.setText(""); 
//                break;
//            case -1: default:
//            	JOptionPane.showMessageDialog(null, "����ʧ��");  
//            	JName.setText("");   
//                JPassword.setText(""); 
//            	break;
//            }  
            
        }  
    }  
    public static void main(String[] args) throws UnknownHostException, IOException {  
        JFrame.setDefaultLookAndFeelDecorated(true);  
        new LoginView();  
    }  
    
    public Client getClient(){
    	return client;
    }
}  
