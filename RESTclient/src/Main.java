import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            printMenu();
            input = scanner.nextLine();
            byte var4 = -1;
            switch(input.hashCode()) {
                case 48:
                    if(input.equals("0")) {
                        var4 = 0;
                    }
                    break;
                case 49:
                    if(input.equals("1")) {
                        var4 = 1;
                    }
                    break;
                case 50:
                    if(input.equals("2")) {
                        var4 = 2;
                    }
                    break;
                case 51:
                    if(input.equals("3")) {
                        var4 = 3;
                    }
            }

            switch(var4) {
                case 0:
                	scanner.close();
                    break;
                case 1:
                    (new GetAllCategoriesRestService()).run();
                    break;
                case 2:
                    (new GetAllDishesRestService()).run();
                    break;
                case 3:
                	/*input2 = scanner.nextLine();
                    (new GetDishByCategory(new Integer(input2))).run();*/
                    break;
                default:
                    System.out.println("Nieprawidlowa operacja");
            }
        } while(!input.equalsIgnoreCase("0"));

    }

    private static void printMenu() {
        System.out.println("\n----\nAplikacja kliencka serwisu REST");
        System.out.println("[1] - pobierz wszystkie kategorie [JSON]");
        System.out.println("[2] - pobierz wszystkie posilki [JSON]");
        //System.out.println("[3] - dodaj pozycje w wybranej kategorii");
        System.out.println("[0] - zakoncz");
        System.out.println("Wybor: ");
    }
}
