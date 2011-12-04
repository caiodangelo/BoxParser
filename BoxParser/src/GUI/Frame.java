package GUI;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
	private String caminho = null;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() throws HeadlessException {
		super();
		setTitle("Parser de Boxes");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(null);
		setBounds(200, 200, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JButton selectFile = new JButton("Escolhe zip com boxes");
		JButton confirmButton = new JButton("Go Go Go");
		JLabel aviso = new JLabel("Apenas boxes individuais e no formato .zip são suportados");
		
		selectFile.setBounds(0, 220, 175, 40);
		selectFile.setToolTipText("Escolhe arquivo .zip que contém os boxes.");
		selectFile.addActionListener(new ButtonHandler(this));
		confirmButton.setBounds(280,220,100,40);
		confirmButton.setToolTipText("Realiza parsing dos boxes.");
		confirmButton.addActionListener(new ButtonHandler(this));
		aviso.setBounds(50, 0, 300, 50);
		
		this.add(selectFile);
		this.add(confirmButton);
		this.add(aviso);
		
		this.setVisible(true);
	    
	    
		
	}
	
	
	

}
