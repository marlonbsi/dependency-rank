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

public class FrameEntidades {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTextArea entidades;
	
	DependenciesManager dep;
	
	public FrameEntidades(ReqNlpUI frame){
		this.dep = frame.dep;
		montaTela();
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}
	
	public void montaTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaTextAreaEntidades();
		preparaBotaoSair();
	}
	
	public void preparaJanela(){
		janela = new JFrame("Entidades Encontradas");
	    //janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
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
	
	private void preparaTextAreaEntidades(){
		entidades = new JTextArea(15, 45);
		entidades.setLineWrap(true);
		entidades.setWrapStyleWord(true);
		JScrollPane scrollbar = new JScrollPane(entidades, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		entidades.append("ID - Entidade. USs: User Stories\n\n");
		for(int i = 0; i < this.dep.entities.size(); i++){
			entidades.append(
					this.dep.entities.get(i).getId() + " - " +
					this.dep.entities.get(i).getName() + ". USs: " +
					this.dep.entities.get(i).returnUss().toString() +"\n");
		}
		painelPrincipal.add(scrollbar);
	}

}
