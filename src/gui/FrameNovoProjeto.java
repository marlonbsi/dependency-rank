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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdbc.Projeto;
import jdbc.ReqNlpDao;
import nlp.DependenciesManager;
import nlp.PartManager;
import nlp.Relation;
import nlp.Sentence;

public class FrameNovoProjeto {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JButton botaoCarregar;
	private JTextField textTituloProjeto;
	private String titulo;
	
	File[] files;
	DependenciesManager dep;
	PartManager pm;

	public static void main(String[] args) {
		new FrameNovoProjeto().montaTela();
	}

	private void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaTextFieldTitulo();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanelaPrincipal();
	}

	private void mostraJanelaPrincipal() {
		janela.pack();
	    janela.setSize(540, 540);
	    janela.setVisible(true);
	}
	
	private void preparaTextFieldTitulo(){
		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("Título do Projeto: ");
		labelTitulo.setPreferredSize(new Dimension(150, 20));
		textTituloProjeto = new JTextField();
		textTituloProjeto.setPreferredSize(new Dimension(250, 20));
		labelTitulo.setLabelFor(textTituloProjeto);
		painelPrincipal.add(labelTitulo);
		painelPrincipal.add(textTituloProjeto);
	}

	private void preparaBotaoCarregar() {
		botaoCarregar = new JButton("Carregar US's");
		botaoCarregar.setPreferredSize(new Dimension(400, 30));
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titulo = textTituloProjeto.getText();
				if(insereProjeto(titulo)){
					//Carrega e interpreta USs
					setUserStories();
					//Encontra Dependências
					setDependencies();
					//Define partes
					pm = new PartManager(dep.dependencies);
					pm.findParts(dep.userStories);
					new FramePrioridades(pm.parts);
				}
			}
		});
		painelPrincipal.add(botaoCarregar);
	}
	
	private boolean insereProjeto(String titulo) {
		if(titulo.length() > 60 || titulo.length() < 3){
			JOptionPane.showMessageDialog(null, "O campo título deve ter entre 3 e 60 caracteres",
					"Erro de validação!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else{
			ReqNlpDao dao = new ReqNlpDao();
			dao.insereProjeto(titulo);
			JOptionPane.showMessageDialog(null, "Projeto inserido com sucesso!",
					"OK", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
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

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaJanela() {
		janela = new JFrame("ReqNLP");
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				//insere o nome do arquivo na sentenÃ§a:
				s.setUs(i+1);
				//text.append("US: "+ s.getS() + "\n");
				System.out.println(s.getS());
				//gera os tokens da sentanÃ§a:
				s.setTokens();
				//gera as probabilidades e tags da sentenÃ§a:
				s.setTagsAndProbs();
				//gera os lemas da sentenÃ§a a partir de tokens, tags e probs:
				s.setLemmas();
				Relation r = new Relation(s.getUs());
				r.findRelation(s);
				//text.append("RelaÃ§Ã£o encontrada: " + r.toString() + "\n");
				r.printRelation();
				System.out.println("\n");
				dep.addEntity(r);
				//frame.setVisible(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setDependencies(){
		dep.searchCrossDependencies();
	}

}
