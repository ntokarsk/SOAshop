
import java.util.Scanner;

public class GetAllOrdersRestService extends RestService {
	public GetAllOrdersRestService(){}


    public void run() {
        String json = this.GET("/listallorders");
        Scanner scanner = new Scanner(json);
        scanner.useDelimiter(",");
        while(scanner.hasNext())
        	System.out.println(scanner.next().replace("\"", ""));
        scanner.close();
    }
}
