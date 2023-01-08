package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Sentence {
	
	private String s;
	private int us;

	private String[] tokens, tags, lemmas;
	private double[] probs;
	
	//Getter e Setters:
	
	public String getS() {
		return s;
	}
	
	public void setS(String s) {
		this.s = s;
	}
	
	public int getUs() {
		return us;
	}

	public void setUs(int us) {
		this.us = us;
	}
	
	public String[] getLemmas() {
		return lemmas;
	}
	
	public void setLemmas() {
		InputStream dictLemmatizer;
		if((this.tokens != null) && (this.tags != null)){
			try {
				dictLemmatizer = new FileInputStream("en-lemmatizer.txt");
				DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);
				this.lemmas = lemmatizer.lemmatize(tokens, tags);
				for (int i = 0; i < this.lemmas.length; i++) {
					if(this.lemmas[i].equals("O")){
						this.lemmas[i] = tokens[i];
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Erro: File not found -> en-lemmatizer.txt");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erro: IOException -> en-lemmatizer.txt");
				e.printStackTrace();
			}
		} else{
			System.out.println("Ainda não possui tokens e/ou tags!");
		}
	}	
	
	public String[] getTokens() {
		return tokens;
	}
	
	public void setTokens() {
		InputStream tokenModelIn = null;
		try {
			tokenModelIn = new FileInputStream("en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
			Tokenizer tokenizer = new TokenizerME(tokenModel);
			this.tokens = tokenizer.tokenize(this.s);
		} catch (FileNotFoundException e) {
			System.out.println("Erro: File not found -> en-token.bin");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro: IOException -> en-token.bin");
			e.printStackTrace();
		}
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public double[] getProbs() {
		return probs;
	}
	
	public void setTagsAndProbs() {
		InputStream posModelIn = null;
		if(this.tokens != null){
			try {
				posModelIn = new FileInputStream("en-pos-maxent.bin");
				POSModel posModel = new POSModel(posModelIn);
				POSTaggerME posTagger = new POSTaggerME(posModel);
				this.tags = posTagger.tag(this.tokens);
				this.probs = posTagger.probs();
			} catch (FileNotFoundException e) {
				System.out.println("Erro: File not found -> en-pos-maxent.bin");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erro: IOExeption -> en-pos-maxent.bin");
				e.printStackTrace();
			}
		} else{
			System.out.println("Ainda não possui tokens!");
		}
	}
	
}
