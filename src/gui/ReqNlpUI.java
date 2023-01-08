package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nlp.DependenciesManager;
import nlp.PartManager;
import nlp.Relation;
import nlp.Sentence;

public class ReqNlpUI {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JButton botaoCarregar;
	private JButton botaoEntidades;
	private JButton botaoDependencias;
	private JButton botaoPartes;
	private JButton botaoPrioridades;
	
	File[] files;
	DependenciesManager dep;
	PartManager pm;
	
	public static void main(String[] args) {
		new ReqNlpUI().montaTela();
	}
	
	public void montaTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotaoCarregar();
		preparaBotaoEntidades(this);
		preparaBotaoDependencias();
		preparaBotaoPartes();
		preparaBotaoPrioridades();
		preparaBotaoSair();
		mostraJanelaPrincipal();
	}

	private void preparaJanela() {
		janela = new JFrame("ReqNLP");
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}
	
	private void preparaBotaoCarregar() {
		botaoCarregar = new JButton("Carregar US's");
		botaoCarregar.setPreferredSize(new Dimension(400, 30));
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUserStories();
			}});
		painelPrincipal.add(botaoCarregar);
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
	
	private void preparaBotaoEntidades(ReqNlpUI frame){
		botaoEntidades = new JButton("Identificar Entidades");
		botaoEntidades.setPreferredSize(new Dimension(400, 30));
		botaoEntidades.setVisible(false);
		botaoEntidades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameEntidades(frame);
				setDependencies();
			}
		});
		painelPrincipal.add(botaoEntidades);
	}
	
	private void preparaBotaoDependencias(){
		botaoDependencias = new JButton("Identificar Dependencias");
		botaoDependencias.setPreferredSize(new Dimension(400, 30));
		botaoDependencias.setVisible(false);
		botaoDependencias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameDependencias(dep);
				botaoPartes.setVisible(true);
			}
		});
		painelPrincipal.add(botaoDependencias);
	}
	
	private void preparaBotaoPartes(){
		botaoPartes = new JButton("Gerar Incrementos");
		botaoPartes.setPreferredSize(new Dimension(400, 30));
		botaoPartes.setVisible(false);
		botaoPartes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FramePartes fp = new FramePartes(dep);
				pm = fp.pm;
				System.out.println("Parte 0: " + pm.parts.get(0).returnPart());
				botaoPrioridades.setVisible(true);
			}
		});
		painelPrincipal.add(botaoPartes);
	}
	
	private void preparaBotaoPrioridades(){
		botaoPrioridades = new JButton("Definir Prioridades");
		botaoPrioridades.setPreferredSize(new Dimension(400, 30));
		botaoPrioridades.setVisible(false);
		botaoPrioridades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FramePrioridades(pm.parts);
			}
		});
		painelPrincipal.add(botaoPrioridades);
	}
	
	private void mostraJanelaPrincipal() {
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}
	
	public void setUserStories() {
		JFileChooser fileChooser = new JFileChooser(
				"D:\\Docs\\Softwares\\workspace\\OpenNLPTestes\\us");
		fileChooser.setMultiSelectionEnabled(true);
		int retorno = fileChooser.showOpenDialog(null);
		List <String> filesFound = new ArrayList<String>();

		if (retorno == JFileChooser.APPROVE_OPTION) {
			files = fileChooser.getSelectedFiles();
			for (int i = 0; i < files.length; i++) {
				filesFound.add(files[i].getName());
			}
			JOptionPane.showMessageDialog(null, filesFound.toString(),
					"User Stories Carregadas:", JOptionPane.INFORMATION_MESSAGE);
	//		new RelationsFrame();
			setSentences();
		} 
	}
	
	@SuppressWarnings("resource")
	public void setSentences(){
		Sentence s = new Sentence();
		dep = new DependenciesManager(files.length);
		for (int i = 0; i < this.files.length; i++) {
			try {
				Scanner arquivo = new Scanner(new FileReader(this.files[i])).
						useDelimiter(" |\\n");
				s.setS(arquivo.nextLine());
				//insere o nome do arquivo na sentença:
				s.setUs(i+1);
				//text.append("US: "+ s.getS() + "\n");
				System.out.println(s.getS());
				//gera os tokens da sentança:
				s.setTokens();
				//gera as probabilidades e tags da sentença:
				s.setTagsAndProbs();
				//gera os lemas da sentença a partir de tokens, tags e probs:
				s.setLemmas();
				Relation r = new Relation(s.getUs());
				r.findRelation(s);
				//text.append("Relação encontrada: " + r.toString() + "\n");
				r.printRelation();
				System.out.println("\n");
				dep.addEntity(r);
				//frame.setVisible(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		botaoEntidades.setVisible(true);
	}
	
	public void setDependencies(){
		dep.searchCrossDependencies();
		botaoDependencias.setVisible(true);
	}
	
}
