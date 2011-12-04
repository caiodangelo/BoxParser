package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import parser.Parser;

public class ButtonHandler implements ActionListener{
	
	private Frame frame = null;
	private JFileChooser fc = null;

	public ButtonHandler(Frame frame) {
		super();
		this.frame = frame;
		fc = new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Handle open button action.
        if (e.getActionCommand() == "Escolhe zip com boxes") {
            int returnVal = fc.showOpenDialog(this.frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String caminho = fc.getSelectedFile().getPath();
                frame.setCaminho(caminho);
                //This is where a real application would open the file.
                System.out.println("Opening: "+caminho);
            } else {
            	System.out.println("Open command cancelled by user.");
            }

        //Handle save button action.
        } else if (e.getActionCommand() == "Go Go Go") {
            if (frame.getCaminho()!=null) {
            	Boolean isZip = false;
                String caminho = frame.getCaminho();
                if(caminho.contains(".zip")){
                	isZip = true;
                }
                if(Parser.buildParser(caminho, isZip)){
                	System.out.println("OK");
                	System.exit(0);
                }
                else{
                	System.out.println("Erro");
                	System.exit(1);
                }
            } else {
            	System.out.println("Save command cancelled by user.");
            }
        }
		
	}

	
	
}
