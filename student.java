public class Student extends Person {
    private static int nextStudentId = 1;
    private final int id;
    private double gpa;

    public Student() {
        super();
        this.id = nextStudentId++;
        this.gpa = 0.0;
    }

    public Student(String name, String surname, double gpa) {
        super(name, surname);
        this.id = nextStudentId++;
        this.gpa = gpa;
    }

    @Override
    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public double getPaymentAmount() {
        return (gpa > 2.67) ? 36660.00 : 0.0;
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }

    @Override
    public String getPosition() {
        return "Student";
    }
}