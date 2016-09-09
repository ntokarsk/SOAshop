import java.util.Scanner;

public class GetDishByCategory extends RestService {
	int id;
    public GetDishByCategory(int id) {
    	this.id = id;
    }

    public void run() {
        String json = this.GET("/getDishByCategory/"+id);
        Scanner scanner = new Scanner(json);
        scanner.useDelimiter(",");
        while(scanner.hasNext())
        	System.out.println(scanner.next().replace("\"", ""));
        scanner.close();
    }
}
