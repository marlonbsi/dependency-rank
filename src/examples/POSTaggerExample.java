package examples;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import nlp.Relations;
import nlp.Sentence;
import nlp.VerbFinder;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * www.tutorialkart.com POS Tagger Example in Apache OpenNLP using Java
 */
@SuppressWarnings("resource")
public class POSTaggerExample {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//
//		InputStream tokenModelIn = null;
//		InputStream posModelIn = null;
//
//		try {
//			String sentence = null;
//			Sentence s = new Sentence();
//			Relations rel = new Relations();
//			VerbFinder vf = new VerbFinder();
//			// últimos índices recebidos em s1 e s2
//			Scanner arquivo = new Scanner(new FileReader("uc_exemplo")).useDelimiter(" |\\n");
//			int i = 0;
//			while (arquivo.hasNextLine()) {
//				sentence = arquivo.nextLine();
//				System.out.println(sentence);
//
//				// tokenize the sentence
//				tokenModelIn = new FileInputStream("en-token.bin");
//				TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
//				Tokenizer tokenizer = new TokenizerME(tokenModel);
//				String tokens[] = tokenizer.tokenize(sentence);
//			//	s.setTokens(tokens);
//
//				// Parts-Of-Speech Tagging
//				// reading parts-of-speech model to a stream
//				posModelIn = new FileInputStream("en-pos-maxent.bin");
//				// loading the parts-of-speech model from stream
//				POSModel posModel = new POSModel(posModelIn);
//				// initializing the parts-of-speech tagger with model
//				POSTaggerME posTagger = new POSTaggerME(posModel);
//				// Tagger tagging the tokens
//				String tags[] = posTagger.tag(tokens);
//			//	s.setTags(tags);
//				// Getting the probabilities of the tags given to the tokens
//				double probs[] = posTagger.probs();
//			//	s.setProbs(probs);
//
//				rel.findRelation(s);
//				
//				/*
//				List <Integer> verbos = vf.returnPosVerbs(s);
//				System.out.println("Verbos na iteração " +i +": ");
//				for (int j = 0; j < verbos.size(); j++) {
//					System.out.print(verbos.get(j) + " ");
//				}
//				System.out.println();
//				*/
//
//				i++;
//			}
//			
//			System.out.println("Tamanho do rel: " + rel.relations.size());
//			System.out.println("-- Relações encontradas --");
//			rel.printRelations();
//			
//			
//			System.out.println();
//			arquivo.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (tokenModelIn != null) {
//				try {
//					tokenModelIn.close();
//				} catch (IOException e) {
//				}
//			}
//			if (posModelIn != null) {
//				try {
//					posModelIn.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//		/*
//		 * try { String sentence = "John is 27 years old."; // tokenize the
//		 * sentence tokenModelIn = new FileInputStream("en-token.bin");
//		 * TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
//		 * Tokenizer tokenizer = new TokenizerME(tokenModel); String tokens[] =
//		 * tokenizer.tokenize(sentence);
//		 * 
//		 * // Parts-Of-Speech Tagging // reading parts-of-speech model to a
//		 * stream posModelIn = new FileInputStream("en-pos-maxent.bin"); //
//		 * loading the parts-of-speech model from stream POSModel posModel = new
//		 * POSModel(posModelIn); // initializing the parts-of-speech tagger with
//		 * model POSTaggerME posTagger = new POSTaggerME(posModel); // Tagger
//		 * tagging the tokens String tags[] = posTagger.tag(tokens); // Getting
//		 * the probabilities of the tags given to the tokens double probs[] =
//		 * posTagger.probs();
//		 * 
//		 * System.out.println(
//		 * "Token\t:\tTag\t:\tProbability\n---------------------------------------------"
//		 * ); for(int i=0;i<tokens.length;i++){
//		 * System.out.println(tokens[i]+"\t:\t"+tags[i]+"\t:\t"+probs[i]); }
//		 * 
//		 * } catch (IOException e) { // Model loading failed, handle the error
//		 * e.printStackTrace(); } finally { if (tokenModelIn != null) { try {
//		 * tokenModelIn.close(); } catch (IOException e) { } } if (posModelIn !=
//		 * null) { try { posModelIn.close(); } catch (IOException e) { } } }
//		 */
	}
}