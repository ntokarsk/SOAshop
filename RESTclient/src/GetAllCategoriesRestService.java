import java.util.Scanner;

public class GetAllCategoriesRestService extends RestService {
    public GetAllCategoriesRestService() {
    }

    public void run() {
        String json = this.GET("/listallcategories");
        Scanner scanner = new Scanner(json);
        scanner.useDelimiter(",");
        while(scanner.hasNext())
        	System.out.println(scanner.next().replace("\"", ""));
        scanner.close();
    }
}
