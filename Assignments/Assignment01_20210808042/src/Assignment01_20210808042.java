/**
 * @author Müslüm Agah
 * @since 11.03.2024
 */

public class Assignment01_20210808042 {
    public static void main(String[] args) throws Exception {
        System.out.println(Math.pow(8, 0));
        Course c = new Course("CSE", 102, "Programming 2", "Introduction to OOP", 6);
        Course course = new Course("CSE", 101, "Computer Programming 1", "Introduction to Programming", 6);
        Student student = new Student("Can DO", "cando@akdenizedu.tr", 123L, "CSE");
        GradStudent gradStudent = new GradStudent(null, "me@somewhere.com", 3, "cse", 0, "try");
        System.out.println(c.courseCode() + " - " + c.getTitle());
        System.out.println(c.toString());
        Teacher t = new Teacher("Joseph LEDET", "josephledet@akdenizedu.tr", 123L, "CSE", 1);
        System.out.println(t);
        Student s = new Student("Test STUDENT", "me@somewhere.com", 456L, "CSE");
        System.out.println(s);
        s.passCourse(c);
        System.out.println(s.getAKTS());
        System.out.println("------");
        t.setEmail("22@ogr.com");
        System.out.println(t.getEmail());
        gradStudent.setRank(3);
        gradStudent.getLevel();
        student.passCourse(course);
        course.setCourseNumber(course.getCourseNumber() + 10);
        System.out.println(student);
        System.out.println(course);
        student.passCourse(course);
        course.setCourseNumber(course.getCourseNumber() - 10);
        System.out.println(course);
        System.out.println(student);
    }
}

class Course {
    private String departmentCode;
    private int courseNumber;
    private String title;
    private String description;
    private int AKTS;

    public Course(String departmentCode, int courseNumber, String title, String description, int AKTS) {
        setDepartmentCode(departmentCode);
        setCourseNumber(courseNumber);
        setTitle(title);
        setDescription(description);
        setAKTS(AKTS); 
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        if ((departmentCode.length() == 3) || (departmentCode.length() == 4))
            this.departmentCode = departmentCode;
        else
            throw new IllegalArgumentException();
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int number) {
        if ((number >= 100 && number <= 999) || (number >= 5000 && number <= 5999)
                || (number >= 7000 && number <= 7999))
            this.courseNumber = number;
        else
            throw new IllegalArgumentException();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setAKTS(int AKTS) {
        if(AKTS > 0)
        this.AKTS = AKTS;
        else throw new IllegalArgumentException();
    }

    String courseCode() {
        return departmentCode + courseNumber;
    }

    @Override
    public String toString() {
        return departmentCode + courseNumber + " - " + title + " (" + AKTS + ")";
    }
}

class Person {
    private String name;
    private String email;
    private long ID;
    private String departmentCode;

    public Person(String name, String email, long ID, String departmentCode) {
        setName(name);
        setEmail(email);
        setID(ID);
        setDepartmentCode(departmentCode);
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
        String regex = "\\w+@\\w+\\.\\w+";
        if (email.matches(regex)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException();
        }
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
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {

        return name + " (" + ID + ") - " + email;
    }

}

class Teacher extends Person {
    private int rank;

    public Teacher(String name, String email, long ID, String departmentCode, int rank) {
        super(name, email, ID, departmentCode);
        setRank(rank);
    }

    public void setRank(int rank) {
        if (rank >= 1 && rank <= 4)
            this.rank = rank;
        else
            throw new IllegalArgumentException();
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
            return "Undefined";
    }

    public void promote() {
        if (rank < 4)
            rank++;
        else
            throw new IllegalArgumentException();
    }

    public void demote() {
        if (rank > 1)
            rank--;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return getTittle() + super.toString();
    }
}

class Student extends Person {
    private int AKTS;

    public Student(String name, String email, long number, String departmentCode) {
        super(name, email, number, departmentCode);
        this.AKTS = 0;
    }

    public int getAKTS() {
        return this.AKTS;
    }

    public void passCourse(Course course) {
        this.AKTS += course.getAKTS();
    }
}

class GradStudent extends Student {
    private int rank;
    private String thesisTopic;

    public GradStudent(String name, String email, long number, String departmentCode, int rank, String thesisTopic) {
        super(name, email, number, departmentCode);
        this.rank = rank;
        this.thesisTopic = thesisTopic;
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public void setRank(int rank) {
        if (rank > 0 && rank < 4)
            this.rank = rank;
        else
            throw new IllegalArgumentException();
    }

    public String getLevel() {
        switch (this.rank) {
            case 1:
                return "Master's Student";
            case 2:
                return "Doctoral Student";
            case 3:
                return "Doctoral Candidate";
            default:
                return "Undefined";
        }
    }
}
