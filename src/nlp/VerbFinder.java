package nlp;

import java.util.ArrayList;
import java.util.List;

public class VerbFinder {
	
	/**
	 * Procura um verbo em uma sentença. Retorna true se encontrar
	 * @param s
	 * @return hasVerb
	 */
	public boolean hasVerb(Sentence s){
		boolean hasVerb = false;
		for (int i = 0; i < s.getTokens().length; i++) {
			if (s.getTags()[i].contains("VB")) {
				hasVerb = true;
				break;
			}
		}
		return hasVerb;
	}
	
	/**
	 * Retorna posições dos tokens que correspondem a verbos.
	 * 
	 * @param s
	 * @return posVerbs
	 */
	public List<Integer> returnPosVerbs(Sentence s){
		List<Integer> posVerbs = new ArrayList<Integer>();
		for (int i = 0; i < s.getLemmas().length; i++) {
			System.out.println(s.getLemmas()[i] + "\t:\t" + s.getTags()[i] + 
					"\t:\t" + s.getProbs()[i]);
			if (s.getTags()[i].contains("VB")) {
				posVerbs.add(i);
			}
		}
		return posVerbs;
	}
	
	/**
	 * Procura o principal verbo de uma sentença baseado nas probabilidades
	 * recebidas
	 * @param s
	 * @return mainVerbPosition
	 */
	//receber a lista de posições dos verbos. avaliar a relevância e retornar o principal
	public int returnMainVerb(Sentence s){
		int mainVerbPosition = -1;
		double probAnt = 0;
		for (int i = 0; i < s.getLemmas().length; i++) {
			if (s.getTags()[i].contains("VB")){
				if(s.getProbs()[i] > probAnt){
					probAnt = s.getProbs()[i];
					mainVerbPosition = i;
				}
				
			}
		}
		return mainVerbPosition;
	}

}
