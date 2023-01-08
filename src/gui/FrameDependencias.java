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
import nlp.UserStory;

public class FrameDependencias {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTextArea dependencias;
	
	DependenciesManager dep;

	public FrameDependencias(DependenciesManager dep) {
		super();
		this.dep = dep;
		montaTela();
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}
	
	public void montaTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaTextAreaDependencias();
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

	private void preparaTextAreaDependencias() {
		dependencias = new JTextArea(15, 45);
		dependencias.setLineWrap(true);
		dependencias.setWrapStyleWord(true);
		JScrollPane scrollbar = new JScrollPane(dependencias, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dependencias.append("ID - Entidade. USs: User Stories\n\n");
		for(int i = 0; i < this.dep.userStories.size(); i++){
			UserStory us = this.dep.userStories.get(i);
			dependencias.append("US " +	us.getIdUserStory() + ": Dependencias -> "
					+ us.returnDependencies() + "\n");
		
		}
		painelPrincipal.add(scrollbar);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaJanela() {
		janela = new JFrame("Dependencias Encontradas");
	}
	
	

}
