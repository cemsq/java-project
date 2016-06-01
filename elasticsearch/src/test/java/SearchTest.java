import cem.es.Search;
import org.testng.annotations.Test;

public class SearchTest {

    @Test
    public void hibc_test() {
        Search item = new Search();

        item.search("00123456", "HIBC");
    }
}
