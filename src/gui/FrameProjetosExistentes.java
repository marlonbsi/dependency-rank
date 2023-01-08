package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jdbc.Projeto;
import jdbc.ReqNlpDao;
import nlp.DependenciesManager;
import nlp.PartManager;

public class FrameProjetosExistentes {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JButton botaoCarregar;
	private JButton[] botoesProjetos;
	
	File[] files;
	DependenciesManager dep;
	PartManager pm;
	List<Projeto> projetos;
	int idProjeto;
	
	public FrameProjetosExistentes(){
		super();
		ReqNlpDao dao = new ReqNlpDao();
		projetos = dao.listAll();
	}

	public static void main(String[] args) {
		new FrameProjetosExistentes().montatela();
	}

	private void montatela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotoesProjetos();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanelaPrincipal();
	}

	private void mostraJanelaPrincipal() {
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setPreferredSize(new Dimension(400, 30));
		botaoSair.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    System.exit(0);
		  }
		});
		painelPrincipal.add(botaoSair);
	}

	private void preparaBotaoCarregar() {
		// TODO Auto-generated method stub
		
	}

	private void preparaBotoesProjetos() {
		for(int i = 0; i < projetos.size(); i++){
			JButton botaoProjeto = new JButton(projetos.get(i).getTituloProjeto());
			botaoProjeto.setPreferredSize(new Dimension(400,30));
			idProjeto = projetos.get(i).getIdProjeto();
			botaoProjeto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FrameProjetoExistente(idProjeto);
					}
				});
			painelPrincipal.add(botaoProjeto);
		}
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaJanela() {
		janela = new JFrame("ReqNLP");
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
