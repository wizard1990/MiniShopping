package DBModel;

import java.io.Serializable;

public class ProductListElement implements Serializable {
	private Integer id;
	private String name;
	private Integer profit;
	/** default constructor */
	public ProductListElement() {
	}

	/** full constructor */
	public ProductListElement(Integer id, String name, Integer profit) {
		this.id = id;
		this.name = name;
		this.setProfit(profit);
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}
}
