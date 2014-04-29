import java.util.Comparator;

import DBModel.Product;


public class SortTransactions implements Comparator<Product> {

	@Override
	public int compare(Product arg0, Product arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else return -1;
	}

}
