import java.util.Comparator;

import DBModel.Transaction;


public class SortTransactions implements Comparator<Transaction> {

	@Override
	public int compare(Transaction arg0, Transaction arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else return -1;
	}

}
