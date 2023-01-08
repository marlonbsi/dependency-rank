package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import nlp.DependenciesManager;
import nlp.PartManager;

public class FramePartes {
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTextArea partes;
	
	DependenciesManager dm;
	PartManager pm;
	
	public FramePartes(DependenciesManager dep){
		super();
		this.dm = dep;
		pm = new PartManager(dm.dependencies);
		pm.findParts(dm.userStories);
		montaTela();
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}

	private void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaTextAreaPartes();
		preparaBotaoSair();		
	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setPreferredSize(new Dimension(400, 30));
		botaoSair.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    janela.dispose();
		  }
		});
		painelPrincipal.add(botaoSair);
	}

	private void preparaTextAreaPartes() {
		partes = new JTextArea(15, 45);
		partes.setLineWrap(true);
		partes.setWrapStyleWord(true);
		JScrollPane scrollbar = new JScrollPane(partes, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		partes.append("ID - Parte. User Stories contempladas\n\n");
		for(int i = 0; i < this.pm.parts.size(); i++){
			partes.append(this.pm.parts.get(i).returnPart());		
		}
		painelPrincipal.add(scrollbar);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaJanela() {
		janela = new JFrame("Partes de Sofware Encontradas");
	}
	
	public PartManager returnPartManager(){
		return this.pm;
	}
}
