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
    JButton login = new JButton("登录");  
    JButton exit = new JButton("退出");  
    JLabel  name = new JLabel("用户名：");  
    JLabel password = new JLabel("密码：");   
    JTextField JName = new JTextField(10); //明文账号输入  
    JPasswordField JPassword = new JPasswordField(10); // 非明文密码输入；  
      
    public LoginView()   
    {  
        JPanel jp = new JPanel();  
        jp.setLayout(new GridLayout(3,2));  //3行2列的面板jp（网格布局）  
          
        name.setHorizontalAlignment(SwingConstants.RIGHT);  //设置该组件的对齐方式为向右对齐  
        password.setHorizontalAlignment(SwingConstants.RIGHT);  
          
        jp.add(name);   //将内容加到面板jp上  
        jp.add(JName);    
        jp.add(password);  
        jp.add(JPassword);    
        jp.add(login);  
        jp.add(exit);  
          
        login.addActionListener(this); //登录增加事件监听  
        exit.addActionListener(this);   //退出增加事件监听  
          
        this.add(jp,BorderLayout.CENTER);   //将整块面板定义在中间  
          
        this.setTitle("登录窗口");  
        this.setLocation(500,300);  //设置初始位置  
        this.pack();        //表示随着面板自动调整大小  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
    public void actionPerformed(ActionEvent e)  // 对时间进行处理  
    {  
        if(e.getSource() == exit)  
        {  
            int i = JOptionPane.showConfirmDialog(null,"确认要退出吗？", "确认", JOptionPane.YES_NO_OPTION);  
            // 显示选择对话框  
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
                JOptionPane.showMessageDialog(null, "登录成功，欢迎到来！");  
                //显示信息提示框  
                System.exit(0);
                break;
            case 0 :
            	JOptionPane.showMessageDialog(null, "用户或密码错误！请从新登录！");  
                //显示信息提示框  
                JName.setText("");   
                JPassword.setText(""); 
                break;
            case -1: default:
            	JOptionPane.showMessageDialog(null, "加载失败");  
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
