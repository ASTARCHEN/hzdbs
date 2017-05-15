package com.snowland.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.snowland.client.ClientJsonCreater;  
  
public class LoginView extends JFrame implements ActionListener  
{  
    JButton login = new JButton("��¼");  
    JButton exit = new JButton("�˳�");  
    JLabel  name = new JLabel("�û�����");  
    JLabel password = new JLabel("���룺");   
    JTextField JName = new JTextField(10); //�����˺�����  
    JPasswordField JPassword = new JPasswordField(10); // �������������룻  
      
    public LoginView()   
    {  
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
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
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	//socket.
        	int res = 1;
//        	User res = login.getLogin(username, password);
            switch(res)  
            {
            case 1 :
                JOptionPane.showMessageDialog(null, "��¼�ɹ�����ӭ������");  
                //��ʾ��Ϣ��ʾ��  
                System.exit(0);
                break;
            case 0 :
            	JOptionPane.showMessageDialog(null, "�û��������������µ�¼��");  
                //��ʾ��Ϣ��ʾ��  
                JName.setText("");   
                JPassword.setText(""); 
                break;
            case -1: default:
            	JOptionPane.showMessageDialog(null, "����ʧ��");  
            	JName.setText("");   
                JPassword.setText(""); 
            	break;
            }  
            
        }  
    }  
    public static void main(String[] args)  
    {  
        JFrame.setDefaultLookAndFeelDecorated(true);  
        new LoginView();  
    }  
}  
