import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("John", "Lennon", "Manager", 27045.78);
        Employee employee2 = new Employee("George", "Harrison", "Developer", 50000.00);
        Student student1 = new Student("Ringo", "Starr", 2.5); // GPA ≤ 2.67 - не получает стипендию
        Student student2 = new Student("Paul", "McCartney", 3.5); // GPA > 2.67 - получает стипендию
        Student student3 = new Student("Mick", "Jagger", 2.0); // Не получает стипендию
        Student student4 = new Student("Keith", "Richards", 3.8); // Получает стипендию
        ArrayList<Person> people = new ArrayList<>();
        people.add(employee1);
        people.add(employee2);
        people.add(student1);
        people.add(student2);
        people.add(student3);
        people.add(student4);
        Collections.sort(people);
        printData(people);
    }
    public static void printData(Iterable<Person> people) {
        for (Person person : people) {
            String personType = person instanceof Employee ? "Employee" : "Student";
            System.out.printf("%s: %s earns %.2f tenge%n",
                    personType,
                    person.toString(),
                    person.getPaymentAmount());
        }
    }
}