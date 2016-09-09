import java.util.Scanner;

public class GetAllDishesRestService extends RestService {
    public GetAllDishesRestService() {
    }

    public void run() {
        String json = this.GET("/listalldish");
        Scanner scanner = new Scanner(json);
        scanner.useDelimiter(",");
        while(scanner.hasNext())
        	System.out.println(scanner.next().replace("\"", ""));
        scanner.close();
    }
}
