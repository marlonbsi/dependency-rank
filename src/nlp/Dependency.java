package nlp;

import java.util.ArrayList;
import java.util.List;

public class Dependency {
	
	private static int sequence = 1;
	private int id;
	private int entity;
	List<UserStory> userStories = new ArrayList<UserStory>();
	
	public Dependency(){
		this.id = sequence++;
	}
	public int getId(){
		return this.id;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
