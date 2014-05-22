import java.util.Comparator;

import DBModel.Carts;


public class SortTransactions implements Comparator<Carts> {

	@Override
	public int compare(Carts arg0, Carts arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else return -1;
	}
}
