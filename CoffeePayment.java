import java.util.*;

public class CoffeePayment {
    static class Person {
        private String name;
        private double drinkPrice;
        private int multiplier;

        public Person(String name, double drinkPrice) {
            this.name = name;
            this.drinkPrice = drinkPrice;
            this.multiplier = (int) (drinkPrice * 10);
        }

        public String getName() {
            return name;
        }

        public double getDrinkPrice() {
            return drinkPrice;
        }

        public int getMultiplier() {
            return multiplier;
        }
    }

    public static void selectPerson(List<Person> people, List<String> namePool) {

        Random random = new Random();
        String selectedPerson = namePool.get(random.nextInt(namePool.size()));
        System.out.println(selectedPerson + " pays for the team coffee trip today.");
    }
    public static void addPerson(List<Person> people, List<String> namePool, Scanner scanner) {
        System.out.println("Enter the name and price of the person's preferred drink separated by a comma:");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        if (parts.length != 2) {
            System.out.println("Invalid input. Please enter the name and price separated by a comma.");
            return;
        }
        String name = parts[0].trim();
        double drinkPrice;
        try {
            drinkPrice = Double.parseDouble(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for drink price. Please enter a valid number.");
            return;
        }
        Person newPerson = new Person(name, drinkPrice);
        people.add(newPerson);
        for (int i = 0; i < newPerson.getMultiplier(); i++) {
            namePool.add(newPerson.getName());
        }
    }

    public static void runSequence(List<Person> people, List<String> namePool){
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("done")) {
            addPerson(people, namePool, scanner);
            System.out.println("Enter 'done' if you've finished adding people, or press Enter to add another person:");
            choice = scanner.nextLine().trim().toLowerCase();
        }
    
        // If the choice is "done", select a person
        if (choice.equalsIgnoreCase("done")) {
            selectPerson(people, namePool);
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<String> namePool = new ArrayList<>();
        
        runSequence(people, namePool);
    }
}
