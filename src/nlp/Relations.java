package nlp;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Relations {

	public List<String[]> relations = new ArrayList<String[]>();
	public int usNumber;

	public Relations(int us) {
		this.usNumber = us;
	}

	/**
	 * Busca as relações entre entidades no texto.
	 * 
	 * @param tokens
	 * @param tags
	 * @param probs
	 */
	public void findRelation(Sentence s) {

		String subs1 = null, subs2 = null;
		String[] relation = new String[3];
		int pMainVerb = -1;
		VerbFinder vf = new VerbFinder();

		pMainVerb = vf.returnMainVerb(s);

		if (pMainVerb > -1) {
			int s1 = -1, s2 = -1;
			for (int j = 0; j < s.getLemmas().length; j++) {
				if (j < pMainVerb) {
					if (subs1 == null) {
						if (s.getTags()[j].contains("NN")) {
							s1 = j;
							subs1 = s.getLemmas()[j];
						}
					} else {
						if ((j - 1) == s1) {
							if (s.getTags()[j].contains("NN")) {
								subs1 = subs1 + "_" + s.getLemmas()[j];
							}
						}
					}
				} else {
					if (subs2 == null) {
						if (s.getTags()[j].contains("NN")) {
							s2 = j;
							subs2 = s.getLemmas()[j];
						}
					} else {
						// if ((j - 1) == s2) {
						if ((s.getTags()[j].contains("NN")) && (j - 1) == s2) {
							subs2 = subs2 + "_" + s.getLemmas()[j];
						}
						// }
					}
				}
			}
			relation[0] = s.getLemmas()[pMainVerb];
			relation[1] = subs1;
			relation[2] = subs2;
			this.relations.add(relation);
		} else {
			this.defineNewRelation(s);
		}
	}

	/**
	 * Define uma relação em sentenças em que não foi encontrado um verbo.
	 * Analisa a maior probabilidade de verbo e os respectivos substantivos que
	 * compõem a relação.
	 * 
	 * @param s
	 */
	@SuppressWarnings("unused")
	public void defineNewRelation(Sentence s) {

		String[] relation = new String[3];
		boolean hasVerb = false;
		double biggestProb = -1;
		String verb, s1, s2;

		for (int i = 0; i < s.getTags().length; i++) {
			// testa token como verbo
			if (s.getTokens().length > 1) {
				String next = "to " + s.getLemmas()[i];
				Sentence nextS = new Sentence();
				nextS.setS(next);

				// processa a tokenização
				nextS.setTokens();

				// realiza o POS tagging
				nextS.setTagsAndProbs();

				// verifica se é o token de maior probabilidade de ser verbo
				if ((nextS.getTags()[0].contains("VB")) && (nextS.getProbs()[0] > biggestProb)) {
					biggestProb = nextS.getProbs()[0];
					verb = nextS.getLemmas()[0];
					hasVerb = true;
					System.out.println(" -> Verbo de maior probabilidade: " + nextS.getLemmas()[0]);
				}
			}
		}
	}

	/**
	 * Imprime as relações encontradas
	 */
	public void printRelations() {
		for (int i = 0; i < this.relations.size(); i++) {
			System.out.print("UC" + this.usNumber + ": " + 
					this.relations.get(i)[0] + "(" + this.relations.get(i)[1] +
					", " + this.relations.get(i)[2] + ")");
			System.out.println();
		}
	}
}
