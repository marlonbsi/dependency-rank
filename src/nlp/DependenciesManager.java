package nlp;

import java.util.ArrayList;
import java.util.List;

public class DependenciesManager {

	public List<Entity> entities = new ArrayList<Entity>();
	public List<UserStory> userStories = new ArrayList<UserStory>();
	public List<Dependency> dependencies = new ArrayList<Dependency>();
	@SuppressWarnings("unused")
	private int usAmount;
	public int[][] dependenciesMatrix;

	/**
	 * Construtor da classe. Recebe a quantidade de US avaliadas para facilitar
	 * a geração da matriz.
	 * 
	 * @param usAmount
	 */
	public DependenciesManager(int usAmount) {
		this.usAmount = usAmount;
	}

	/**
	 * Preenche as entidades com base nas relações.
	 * 
	 * @param r (Relação)
	 *            
	 */
	public void addEntity(Relation r) {
		UserStory us = new UserStory(r.usNumber);
		int position = posEntity(r.e1);
		if (position < 0) {
			// Verdadeiro quando a entidade ainda não está na lista
			Entity e1 = new Entity(r.e1);
			e1.us.add(us);
			this.entities.add(e1);
			us.entities.add(e1);
		} else {
			this.entities.get(position).us.add(us);
			us.entities.add(this.entities.get(position));
		}
		position = posEntity(r.e2);
		if (position < 0) {
			Entity e2 = new Entity(r.e2);
			e2.us.add(us);
			this.entities.add(e2);
			us.entities.add(e2);
		} else {
			this.entities.get(position).us.add(us);
			us.entities.add(this.entities.get(position));
		}
		this.userStories.add(us);
	}

	/**
	 * Imprime as entidades encontradas.
	 * 
	 */
	public void printEntities() {
		System.out.print(" -> ");
		for (int i = 0; i < this.entities.size(); i++) {
			if ((i + 1) % 4 == 0) {
				System.out.println();
				System.out.print(" -> ");
			}
			System.out.print("|" + this.entities.get(i).getId() + " | " + this.entities.get(i).getName() + " - "
					+ this.entities.get(i).returnUss().toString() + " |  ");
		}
		System.out.println("\n");
	}
	
	/**
	 * Imprime as User Stories encontradas.
	 * 
	 */
	public void printUserStories() {
		System.out.println();
		for (int i = 0; i < this.userStories.size(); i++) {
			System.out.println();
			System.out.print("US " + this.userStories.get(i).getIdUserStory() 
					+ " : " + " Entidades -> ");
			for (int j = 0; j < this.userStories.get(i).entities.size(); j++) {
				System.out.print(" " +this.userStories.get(i).entities.get(j).getId());
			}
		}
		System.out.println("\n");
	}
	
	public String entitiesToString(){
		String ents = "[";
		for(int i = 0; i < this.entities.size(); i++){
			ents += this.entities.get(i).getId() + " ";
		}
		ents += "]";
		return ents;
	}
	
	/**
	 * Gera a matriz de associações entre entidade e US.
	 * 
	 
	public void generateMatrix() {
		this.dependenciesMatrix = new int[this.entities.size()][this.usAmount + 1];
		for (int i = 0; i < this.dependenciesMatrix.length; i++) {
			for (int j = 0; j < this.dependenciesMatrix[i].length; j++) {
				if (j == 0) {
					this.dependenciesMatrix[i][j] = i + 1;
				} else {
					this.dependenciesMatrix[i][j] = -1;
				}
			}
		}
		for (int i = 0; i < this.entities.size(); i++) {
			for (int j = 0; j < this.entities.get(i).us.size(); j++) {
				this.dependenciesMatrix[i][this.entities.get(i).us.get(j)] = 0;
			}
		}
	}*/

	/**
	 * Imprime a matriz gerada.
	 * 
	 */
	public void printMatrix() {
		for (int i = 0; i < this.dependenciesMatrix.length; i++) {
			System.out.println();
			for (int j = 0; j < dependenciesMatrix[i].length; j++) {
				System.out.print(this.dependenciesMatrix[i][j] + " ");
			}
		}
		System.out.println();
	}

	/**
	 * Procura uma entidade na lista, caso já exista, retorna a posição.
	 * 
	 * @param e (nome da Entidade)
	 * @return
	 */
	public int posEntity(String e) {
		int pos = -1;
		for (int i = 0; i < this.entities.size(); i++) {
			if (this.entities.get(i).getName().equals(e)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	/**
	 * Exibe as dependências entre USs encontradas.
	 * 
	 */
	public void showDependencies() {
		System.out.println();
		for (int i = 0; i < this.dependencies.size(); i++) {
			System.out.print("Dependencia id " + 
					this.dependencies.get(i).getId() + ": ");
			System.out.print(this.dependencies.get(i).
					userStories.toString());
			System.out.println();
		}
	}
	
	/**
	 * Percorre as USs adicionando as dependências cruzdas.
	 * 
	 */
	public void searchCrossDependencies(){
		for (int i = 0; i < this.userStories.size(); i++) {
			addUssFromEntities(this.userStories.get(i));
		}
		System.out.println(" -- ");
		printDependencies();
		System.out.println(" -- ");
	}
	
	/**
	 * Retorna se há alguma Dependência com a US passada.
	 * 
	 * @param us
	 * @return
	 */
	public boolean hasDependencyUS(UserStory us){
		boolean has = false;
		for (int i = 0; i < this.dependencies.size(); i++) {
			if(this.dependencies.get(i).userStories.contains(us)){
				has = true;
				break;
			}
		}
		return has;
	}
	
	/**
	 * Pesquisa dependências de primeiro nível entre as USs, adicionando as
	 * USs constantes na lista de cada entidade manipulada.
	 * 
	 * @param us
	 */
	public void addUssFromEntities(UserStory us){
		for(int i = 0; i < us.entities.size(); i++){
			for (int j = 0; j < us.entities.get(i).us.size(); j++) {
				if(us.entities.get(i).us.get(j) != us){
					if(!us.dependencies.contains(us.entities.get(i).us.get(j))){
						us.dependencies.add(us.entities.get(i).us.get(j));
//						System.out.println("Adicionou US " + 
//								us.entities.get(i).us.get(j).getIdUserStory() + 
//								" às deps de " + us.getIdUserStory());
					}
				}
			}
		}
	}
	
	/**
	 * Exibe as dependências da lista de cada US.
	 * 
	 */
	public void printDependencies(){
		for (int i = 0; i < this.userStories.size(); i++) {
			this.userStories.get(i).showDependencies();
		}
	}
	
	
	/**
	 * Retorna as UserStories que manipulam a mesma entidade "e" como 
	 * dependentes.
	 * 
	 * @param e (Entity)
	 * @return
	 */
	public void returnCrossUSs(Entity e, int idUS){
		List<UserStory> uSsFound = new ArrayList<UserStory>();
		for (int i = 0; i < this.userStories.size(); i++) {
			if(this.userStories.get(i).getIdUserStory() != idUS){
				if(this.userStories.get(i).entities.contains(e)){
					uSsFound.add(this.userStories.get(i));
					System.out.println("Entidade " +e.getName() + 
							" encontrada na US " + 
							this.userStories.get(i).getIdUserStory());
				}
			}
		}
	}
	
}
