package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nlp.Part;
import nlp.Priorities;

public class FramePrioridades {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private List<JLabel> labels = new ArrayList<JLabel>();
	private List<JTextField> fields = new ArrayList<JTextField>();
	private List<JLabel> legendas = new ArrayList<JLabel>();
	
	Priorities priorities;
	
	List<Part> parts = new ArrayList<Part>();
	
	public static void main(String[] args) {
		new FramePrioridades();
	}
	
	public FramePrioridades(){
		super();
		montaTela();
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}
	
	public FramePrioridades(List<Part> parts) {
		super();
		this.parts = parts;
		priorities = new Priorities();
		priorities.parts = parts;
		montaTela();
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}

	private void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaLabels();
	//	montaLabels();
		preparaBotaoVotar();
		preparaBotaoSair();
	}

	private void preparaBotaoVotar() {
		JButton botaoVotar = new JButton("Votar");
		botaoVotar.setPreferredSize(new Dimension(400, 30));
		List<Integer> valoresVotacao = new ArrayList<Integer>();
		botaoVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    for(int i = 0; i <  fields.size(); i++){
			    	String fieldEmString = fields.get(i).getText();
			    	System.out.println("Valor em a: " + fieldEmString);
			    	valoresVotacao.add(new Integer(fieldEmString));
			    }
			    
			    priorities.points = valoresVotacao;
			    JOptionPane.showMessageDialog(null, exibeVotacao(),
						"User Stories Carregadas:", JOptionPane.INFORMATION_MESSAGE);
			    janela.dispose();
			  }
			});
		painelPrincipal.add(botaoVotar);
	}
	
	public String exibeVotacao(){
		String votacao = "Votação Computada: \n";
		for(int i = 0; i < priorities.parts.size(); i++){
			votacao += "Parte " + priorities.parts.get(i).getIdPart() + " -> " + priorities.points.get(i) + " pontos.\n";
		}
		return votacao;
	}

	private void preparaLabels() {
		JLabel titulo = new JLabel();
		titulo.setPreferredSize(new Dimension(450, 50));
		titulo.setText("Priorizacao por votacao cumulativa de 1000 pontos:");
		painelPrincipal.add(titulo);
		
		for (int i = 0; i < this.parts.size(); i++) {
			labels.add(new JLabel());
			labels.get(i).setText("Parte " + this.parts.get(i).getIdPart() + ". User Stories: " 
					+ this.parts.get(i).returnUSsFromPart() + ". ");
			labels.get(i).setPreferredSize(new Dimension(370, 20));
			
			fields.add(new JTextField());
			fields.get(i).setPreferredSize(new Dimension(30, 20));
			
			legendas.add(new JLabel());
			legendas.get(i).setPreferredSize(new Dimension(450, 10));
			
			painelPrincipal.add(labels.get(i));
			painelPrincipal.add(fields.get(i));
			painelPrincipal.add(legendas.get(i));
		}
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

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaJanela() {
		janela = new JFrame("Definir Prioridades por Votação Cumulativa");
	}
	
	

}
