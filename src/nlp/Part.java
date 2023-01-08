package nlp;

import java.util.ArrayList;
import java.util.List;

public class Part {
	
	private static int sequence = 1;
	private int idPart;
	List<UserStory> userStories = new ArrayList<UserStory>();
	
	public Part(){
		this.idPart = sequence++;
	}
	
	public int getIdPart() {
		return idPart;
	}

	public void setIdPart(int idPart) {
		this.idPart = idPart;
	}
	
	/**
	 * Mostra as USs contidas no incremento.
	 * 
	 */
	public void showPart(){
		System.out.println("\n -> Incremento " + this.idPart + ": ");
		System.out.print("USs: ");
		for(int i = 0; i <  this.userStories.size(); i++){
			System.out.print(this.userStories.get(i).getIdUserStory() + " ");
		}
	}
	
	public String returnPart(){
		String part = "-> Parte " + this.idPart + ": USs: ";
		for(int i = 0; i <  this.userStories.size(); i++){
			part += this.userStories.get(i).getIdUserStory() + " ";
		}
		part += "\n";
		return part;
	}
	
	public String returnUSsFromPart(){
		String uss = "[";
		for(int i = 0; i <  this.userStories.size(); i++){
			uss += this.userStories.get(i).getIdUserStory() + " ";
		}
		uss += "]";
		return uss;
	}
}
