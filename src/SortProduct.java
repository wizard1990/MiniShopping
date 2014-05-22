import java.util.Comparator;
import DBModel.Products;

public class SortProduct implements Comparator<Products>{
	@Override
	public int compare(Products arg0, Products arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else return -1;
	}
}
