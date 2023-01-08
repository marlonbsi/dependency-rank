package examples;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import nlp.DependenciesManager;
import nlp.PartManager;
import nlp.Relation;
import nlp.Sentence;

public class TestesUS {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		File[] arqs;
		File dir = new File("us");
		arqs = dir.listFiles();
		Sentence s = new Sentence();
		DependenciesManager dep = new DependenciesManager(arqs.length);
//		System.out.println(ent.usAmount);
		
		for (int i = 0; i < arqs.length; i++) {
			try {
				Scanner arquivo = new Scanner(new FileReader(arqs[i])).
						useDelimiter(" |\\n");
				s.setS(arquivo.nextLine());
				//insere o nome do arquivo na sentença:
				s.setUs(i+1);
				System.out.println(s.getS());
				//gera os tokens da sentança:
				s.setTokens();
				//gera as probabilidades e tags da sentença:
				s.setTagsAndProbs();
				//gera os lemas da sentença a partir de tokens, tags e probs:
				s.setLemmas();
//				Relations rel = new Relations(s.getUs());
//				rel.findRelation(s);
//				rel.printRelations();
//				System.out.println("\n");
//				dep.fillEntities(rel);
				Relation r = new Relation(s.getUs());
				r.findRelation(s);
				r.printRelation();
				System.out.println("\n");
				dep.addEntity(r);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dep.printEntities();
		dep.searchCrossDependencies();
		dep.printUserStories();
//		dep.generateMatrix();
//		dep.printMatrix();
//		dep.findDependencies();
//		dep.showDependencies();
		PartManager pm = new PartManager(dep.dependencies);
		pm.findParts(dep.userStories);
		pm.showParts();
//		pm.showOrderedDependencies();
	}

}
