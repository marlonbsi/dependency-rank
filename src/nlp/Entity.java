package nlp;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	
	private static int sequence = 1;
	private int id;
	private String name;
	public List<UserStory> us = new ArrayList<UserStory>();	
	
	//Construtor
	public Entity(String n){
		this.id = sequence++;
		this.setName(n);
	}
	
	//Getters e setters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Integer> returnUss(){
		List<Integer> ussNumbers = new ArrayList<Integer>();
		for (int i = 0; i < this.us.size(); i++) {
			ussNumbers.add(this.us.get(i).getIdUserStory());
		}
		return ussNumbers;
	}
}
