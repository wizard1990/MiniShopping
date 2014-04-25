import java.util.Comparator;
import DBModel.Category;


public class SortCategory implements Comparator<Category> {

	@Override
	public int compare(Category arg0, Category arg1) {
		if (arg0.getId() < arg1.getId()) {
			return 1;
		}
		else return -1;
	}
}
