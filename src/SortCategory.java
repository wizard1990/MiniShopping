import java.util.Comparator;
import DBModel.Categories;


public class SortCategory implements Comparator<Categories> {

	@Override
	public int compare(Categories arg0, Categories arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else return -1;
	}
}
