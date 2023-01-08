package nlp;

import java.util.ArrayList;
import java.util.List;

public class UserStory {
	
	private int idUserStory;
	List<Entity> entities = new ArrayList<Entity>();
	List<UserStory> dependencies = new ArrayList<UserStory>();
	
	public UserStory(int idUs){
		this.idUserStory = idUs;
	}
	
	public int getIdUserStory() {
		return idUserStory;
	}
	public void setIdUserStory(int idUserStory) {
		this.idUserStory = idUserStory;
	}
	
	public void showDependencies(){
		System.out.print("Dependencias de " + this.getIdUserStory() + ": ");
		for (int i = 0; i < this.dependencies.size(); i++) {
			System.out.print(this.dependencies.get(i).idUserStory + " , ");
		}
		System.out.println();
	}
	
	public String returnDependencies(){
		String dependencies = "[";
		for (int i = 0; i < this.dependencies.size(); i++) {
			dependencies += this.dependencies.get(i).idUserStory + " ";
		}
		dependencies += "]";
		return dependencies;
	}
	
}
