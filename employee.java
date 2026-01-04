public class Employee extends Person {
    private static int nextEmployeeId = 1;
    private final int id;
    private String position;
    private double salary;

    public Employee() {
        super();
        this.id = nextEmployeeId++;
        this.position = "";
        this.salary = 0.0;
    }

    public Employee(String name, String surname, String position, double salary) {
        super(name, surname);
        this.id = nextEmployeeId++;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getPaymentAmount() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee: " + super.toString();
    }

    @Override
    public String getPosition() {
        return position;
    }
}


\\