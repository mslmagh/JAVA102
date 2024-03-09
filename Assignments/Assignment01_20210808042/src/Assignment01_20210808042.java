public class Assignment01_20210808042 {
    public static void main(String[] args) throws Exception {
        System.out.println("deneme");
        Course course = new Course("CSEtrry", 102, "Computer", "sjsj", 2);
        System.out.println("test");
        Course course2 = new Course("CSE455", 102, "Computer", "sjsj", 2);
        System.out.println(course.toString());
        Person person = new Person("Muslum", "mslmagh@gmail.com", 33247683422L, "CSEefew");
        System.out.println(person.toString());
        person.setDepartmentCode("fjkfdsjf");
        System.out.println(person.getDepartmentCode());
    }
}

class Course {
    private String departmentCode;
    private int courseNumber;
    private String tittle;
    private String description;
    private final int AKTS;

    public Course(String departmentCode, int courseNumber, String tittle, String description, int AKTS) {
        this.departmentCode = departmentCode;
        this.courseNumber = courseNumber;
        this.tittle = tittle;
        this.description = description;
        this.AKTS = AKTS;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        if ((departmentCode.length() == 3) || (departmentCode.length() == 4))
            this.departmentCode = departmentCode;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int number) {
        if ((number >= 100 && number <= 999) || (number >= 5000 && number <= 5999)
                || (number >= 7000 && number <= 7999))
            this.courseNumber = number;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAKTS() {
        return AKTS;
    }

    String courseCode() {
        return departmentCode + courseNumber;
    }

    @Override
    public String toString() {
        return "{" + departmentCode + "}" + "{" + courseNumber + "} - " + "{" + tittle + "} " + "({" + AKTS + "})";
    }
}

class Person {
    private String name;
    private String email;
    private long ID;
    private String departmentCode;

    public Person(String name, String email, long ID, String departmentCode) {
        this.name = name;
        this.email = email;
        this.ID = ID;
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long iD) {
        this.ID = iD;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        if ((departmentCode.length() == 3) || (departmentCode.length() == 4))
            this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {

        return "{" + name + "}" + "({" + ID + "}) -" + " {" + email + "}";
    }

}

class Teacher extends Person {
    private int rank;

    public Teacher(String name, String email, long ID, String departmentCode, int rank) {
        super(name, email, ID, departmentCode);
        this.rank = rank;
    }

    public void setRank(int rank) {
        if (rank >= 1 && rank <= 4)
            this.rank = rank;
    }

    public String getTittle() {
        if (rank == 1)
            return "Lecturer";
        else if (rank == 2)
            return "Assistant Professor";

        else if (rank == 3)
            return "Associate Professor";
        else if (rank == 4)
            return "Professor";
        else
            return "";
    }

    public void promote() {
        if (rank < 4)
            rank++;
    }

    public void demote() {
        if (rank > 1)
            rank--;
    }

    @Override
    public String toString() {
        return getTittle() + super.toString();
    }
}

class Student extends Person {

    private int AKTS;

    public Student(String name, String email, long ID, String departmentCode, int AKTS) {
        super(name, email, ID, departmentCode);
        AKTS = 0;
        this.AKTS = AKTS;
    }

    public int getAKTS() {
        return AKTS;
    }

    public void passCourse(Course course) {
        this.AKTS += course.getAKTS();
    }
}

class GradStudent extends Student {
    private int rank;

    private String thesisTopic;

    public GradStudent(String name, String email, long ID, String departmentCode, int AKTS, String thesisTopic) {
        super(name, email, ID, departmentCode, AKTS);
        this.thesisTopic = thesisTopic;
    }

    public void setRank(int rank) {
        if (rank == 1 || rank == 2 || rank == 3)
            this.rank = rank;
        else
            System.out.println("ERROR: Invali value.");
    }

    public String getLevel() {
        if (rank == 1)
            return "Master's Student";
        else if (rank == 2)
            return "Doctoral Student";

        else if (rank == 3)
            return "Doctoral Candidate";
        else
            return "";
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
