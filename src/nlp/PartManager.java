package nlp;

import java.util.ArrayList;
import java.util.List;

public class PartManager {

	Dependency[] orderedDependencies;
	public List<Part> parts = new ArrayList<Part>();

	/**
	 * Cria o gerenciador de incrementos com base nas dependências
	 * identificadas, no final ordena da maior lista para a menor.
	 * 
	 * @param deps
	 */
	public PartManager(List<Dependency> deps) {
		this.orderedDependencies = new Dependency[deps.size()];
		for (int i = 0; i < deps.size(); i++) {
			this.orderedDependencies[i] = deps.get(i);
		}
		sortDeps();
	}

	/**
	 * Método que ordena as dependências priorizando as com mais user stories
	 * envolvidas
	 */
	public void sortDeps() {
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < this.orderedDependencies.length - 1; i++) {
				if (this.orderedDependencies[i].userStories.size() < this.orderedDependencies[i + 1].userStories
						.size()) {
					Dependency temp = this.orderedDependencies[i];
					this.orderedDependencies[i] = this.orderedDependencies[i + 1];
					this.orderedDependencies[i + 1] = temp;
					swap = true;
				}
			}
		}
	}

	/**
	 * Verifica todos os incrementos a partir de uma lista de USs.
	 * 
	 * @param uss
	 */
	public void findParts(List<UserStory> uss) {
		// provavelmente tenha que mudar pra while variando o tamanho de uss
		for (int i = 0; i < uss.size(); i++) {
			if (!hasPart(uss.get(i))) {
				findPartsFromUS(uss.get(i));
			}
		}
	}

	/**
	 * Verifica se já existe um incremento com a US informada.
	 * 
	 * @param us
	 * @return
	 */
	public boolean hasPart(UserStory us) {
		boolean has = false;
		for (int i = 0; i < this.parts.size(); i++) {
			if (parts.get(i).userStories.contains(us)) {
				has = true;
				break;
			}
		}
		return has;
	}

	/**
	 * Adiciona as USs dependentes do parâmetro ao incremento.
	 * 
	 * @param us
	 */
	public void findPartsFromUS(UserStory us) {
		Part part = new Part();
		if(!hasPart(us)){
			// Adiciona us ao incremento.
			System.out.println("Montando incremento " + part.getIdPart() + ": ");
			if (!part.userStories.contains(us)) {
				part.userStories.add(us);
				System.out.println("Adicionou: " + us.getIdUserStory());
			}
		}
		// Adiciona Dependências de primeiro nível:
		for (int i = 0; i < us.dependencies.size(); i++) {
			if (!hasPart(us.dependencies.get(i))) {
				part.userStories.add(us.dependencies.get(i));
				System.out.println("Adicionou: " + us.dependencies.get(i).getIdUserStory() + " por dep. de "
						+ us.getIdUserStory());
			}
		}
		this.addCrossDependencies(part);
		this.parts.add(part);
	}

	/**
	 * Método que procura e adiciona as dependências cruzadas entre USs.
	 * 
	 * @param p
	 */
	public void addCrossDependencies(Part p){
		UserStory u;
		int j = 0, t = p.userStories.size();
		while(j < t){
			u = p.userStories.get(j);
			addSubDependencies(p, u);
			j++;
			t = p.userStories.size();
		}
	}
	
	/**
	 * Método que adiciona as subdependências ao incremento.
	 * 
	 * @param p
	 * @param u
	 */
	public void addSubDependencies(Part p, UserStory u){
		for(int i = 0; i < u.dependencies.size(); i++){
			if(!p.userStories.contains(u.dependencies.get(i))){
				p.userStories.add(u.dependencies.get(i));
				System.out.println("Adicionou " 
					+u.dependencies.get(i).getIdUserStory() + " em addDependencies");
			}
		}
	}
	
	/**
	 * Percorre os incrementos exibindo suas USs.
	 * 
	 */
	public void showParts(){
		for(int i = 0; i <  this.parts.size(); i++){
			this.parts.get(i).showPart();
		}
	}
}
