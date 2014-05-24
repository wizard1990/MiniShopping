package DBModel;
import java.io.Serializable;

public class StateListElement implements Serializable {
	private String name;
	private Integer cost;
	
	public StateListElement(String name, Integer cost) {
		this.name = name;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
