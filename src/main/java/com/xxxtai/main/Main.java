package com.xxxtai.main;

import com.xxxtai.view.DrawingGui;
import com.xxxtai.view.SchedulingGui;
import com.xxxtai.view.SetingGui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SchedulingGui schedulingGui;
	@Autowired
	private SetingGui setingGui;
	@Autowired
	private DrawingGui graphingGui;

	public Main(){
		super("AGV快递分拣系统");
		
		
		
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
		this.addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e){
	        	  //exit();
	          }
	    });
	}
	public void init(){
		graphingGui.getGuiInstance(Main.this, schedulingGui, setingGui, graphingGui);
		setingGui.getGuiInstance(Main.this, schedulingGui, setingGui, graphingGui);
		schedulingGui.getGuiInstance(Main.this, schedulingGui, setingGui, graphingGui);		
		
		this.getContentPane().add(schedulingGui);
		this.repaint();
		this.validate();
	}
	
	public void exit(){
		Object[] option = {"ȷ��", "ȡ��"};
		JOptionPane pane = new JOptionPane("ȷ�Ϲر���", JOptionPane.QUESTION_MESSAGE, 
				JOptionPane.YES_NO_OPTION, null, option, option[1]);
		JDialog dialog = pane.createDialog(this, "����");
		dialog.setVisible(true);
		Object result = pane.getValue();
		if(result == null || result == option[1]){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}else if(result == option[0]){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/beans.xml");
		Main main = context.getBean(Main.class);
		main.init();
	}
}
