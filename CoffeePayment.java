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

        public void reduceMultiplier() {
            this.multiplier = Math.max(1, this.multiplier / 2);
        }
    }

    public static String selectPerson(List<Person> people, List<String> namePool) {
        // Randomly select a name from the pool
        Random random = new Random();
        String selectedPerson = namePool.get(random.nextInt(namePool.size()));
        System.out.println(selectedPerson + " pays for the team coffee trip today.");
        return selectedPerson;
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
        // Update the name pool with the new person's name and multiplier
        for (int i = 0; i < newPerson.getMultiplier(); i++) {
            namePool.add(newPerson.getName());
        }
    }

    public static void runSequence(List<Person> people, List<String> namePool){
        Scanner scanner = new Scanner(System.in);


        // Interactively add people until the user decides to stop
        String choice;
        do {
            addPerson(people, namePool, scanner);
            System.out.println("Do you want to add another person? (yes/no)");
            choice = scanner.nextLine().toLowerCase();
        } while (choice.equals("yes"));
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<String> namePool = new ArrayList<>();
        
        runSequence(people, namePool);

        for (int day = 1; day <= 5; day++) {
            System.out.println("Day " + day + ":");
            String selectedPerson = selectPerson(people, namePool);

            // Reduce the multiplier for the selected person
            for (Person person : people) {
                if (person.getName().equals(selectedPerson)) {
                    person.reduceMultiplier();
                    break;
                }
            }
        }
    }
}
