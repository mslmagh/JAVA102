import java.util.ArrayList;

/**
 * @author Müslüm Agah
 * @since 25.03.2024
 */

public class Assignment02_20210808042 {
    public static void main(String[] args) throws Exception {
        Department cse = new Department("CSE", "Computer Engineering");
        Teacher t = new Teacher("Joseph Ledet", "josephledet@akdeniz.edu.tr", 123L, cse, 1);
        Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
        Course c102 = new Course(cse, 102, "Programming 2", "Object Oriented Programming", 4, t);
        Student s = new Student("Test STUDENT", "me@somewhere.com", 123L, cse);
        s.addCourse(c101, 80);
        s.addCourse(c102, 30);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);
        s = new GradStudent("Test GRADSTUDENT", "me@somewhere.com", 456L, cse, 3, "MDE");
        s.addCourse(c101, 80);
        s.addCourse(c102, 70);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);

        cse.setChair(t);
        Department math = new Department("MATH", "Mathematics");
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());
        t.setDepartment(math);
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());
        t.setDepartment(cse);
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());
    }
}

class Department {
    private String code;
    private String name;
    private Teacher chair;

    public Department(String code, String name) {
        setCode(code);
        setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.length() < 3 || code.length() > 4) {
            throw new IllegalArgumentException("Code must be 3 or 4 characters.");
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getChair() {
        return chair;
    }

    public void setChair(Teacher chair) {
        if (chair != null && !this.equals(chair.getDepartment())) {
            throw new DepartmentMismatchException(this, chair);
        }
        this.chair = chair;
    }
}

class Course {
    private Department department;
    private Teacher teacher;
    private int courseNumber;
    private String title;
    private String description;
    private int AKTS;

    public Course(Department department, int courseNumber, String title, String description, int aKTS,
            Teacher teacher) {
        setDepartment(department);
        setTeacher(teacher);
        setCourseNumber(courseNumber);
        setTitle(title);
        setDescription(description);
        setAKTS(aKTS);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department.getCode().length() < 3 || department.getCode().length() > 4) {
            throw new IllegalArgumentException();
        }
        this.department = department;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if (!teacher.getDepartment().equals(department)) {
            throw new DepartmentMismatchException(department, teacher);
        }
        this.teacher = teacher;
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
        if (AKTS <= 0) {
            throw new IllegalArgumentException();
        } else
            this.AKTS = AKTS;
    }

    String courseCode() {
        return department.getCode() + courseNumber;
    }

    @Override
    public String toString() {
        return department.getCode() + courseNumber + " - " + title + " (" + AKTS + ")";
    }
}

abstract class Person {
    private Department department;
    private String name;
    private String email;
    private long ID;

    public Person(String name, String email, long ID, Department department) {
        setDepartment(department);
        setName(name);
        setEmail(email);
        setID(ID);
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
        int counter1 = 0;
        int indexAt = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                indexAt = i;
                counter1++;
            }
        }

        int counter2 = 0;
        int indexDot = 0;
        if (counter1 == 1 && indexAt != 0) {
            for (int i = indexAt + 1; i < email.length(); i++) {
                if (email.charAt(i) == '.') {
                    counter2++;
                }
            }
            if (counter2 > 2) {
                throw new InvalidValueException(email);
            } else if (counter2 < 2) {
                for (int j = indexAt + 1; j < email.length(); j++) {
                    if (email.charAt(j) == '.') {
                        indexDot = j;
                    }
                }
                if (indexDot != indexAt + 1 && indexDot != email.length() - 1) {
                    this.email = email;
                } else {
                    throw new InvalidValueException(email);
                }
            } else {
                int[] indexDots = new int[2];
                int n = 0;
                for (int j = indexAt + 1; j < email.length(); j++) {
                    if (email.charAt(j) == '.') {
                        indexDots[n] = j;
                        n++;
                    }
                }

                if (indexDots[0] + 1 != indexDots[1] &&
                        indexDots[1] != email.length() - 1) {
                    this.email = email;
                } else {
                    throw new InvalidValueException(email);
                }
            }
        } else {
            throw new InvalidValueException(email);
        }
    }

    // public void setEmail(String email) {
    // String regex = "\\w+@\\w+\\.\\w+";
    // if (email.matches(regex)) {
    // this.email = email;
    // } else {
    // throw new IllegalArgumentException();
    // }
    // }

    public long getID() {
        return ID;
    }

    public void setID(long iD) {
        this.ID = iD;
    }

    @Override
    public String toString() {

        return name + " (" + ID + ") - " + email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department.getCode().length() < 3 || department.getCode().length() > 4) {
            throw new IllegalArgumentException();
        }
        this.department = department;
    }

}

class Teacher extends Person {
    private int rank;

    public Teacher(String name, String email, long ID, Department department, int rank) {
        super(name, email, ID, department);
        this.rank = rank;
    }

    public void setDepartment(Department department) {
        if (department.getChair() != null && department.getChair().equals(this)) {
            department.setChair(null);
        }
        super.setDepartment(department);
    }

    public String getTitle() {
        if (rank == 1)
            return "Lecturer";
        else if (rank == 2)
            return "Adjunct Instructor";

        else if (rank == 3)
            return "Assistant Professor";
        else if (rank == 4)
            return "Associate Professor";
        else if (rank == 4)
            return "Professor";
        else
            return "Undefined";
    }

    public void promote() {
        if (rank < 1 || rank > 5) {
            throw new InvalidRankException(rank);
        }
        rank++;
    }

    public void demote() {
        if (rank < 1 || rank > 5) {
            throw new InvalidRankException(rank);
        }
        rank--;
    }

    @Override
    public String toString() {
        return getTitle() + super.toString();
    }
}

class Student extends Person {
    public ArrayList<CourseGrade> courseGrades = new ArrayList<>();

    public Student(String name, String email, long ID, Department department) {
        super(name, email, ID, department);
    }

    public int getAKTS() {
        int totalAKTS = 0;
        for (CourseGrade grade : courseGrades) {
            if (grade.getResult().equals("Passed")) { // Sadece başarılı olan derslerin AKTS değerlerini topla
                totalAKTS += grade.getCourse().getAKTS();
            }
        }
        return totalAKTS;
    }

    public int getAttemptedAKTS() {
        int totalAttemptedAKTS = 0;
        for (CourseGrade grade : courseGrades) {
            totalAttemptedAKTS += grade.getCourse().getAKTS();
        }
        return totalAttemptedAKTS;
    }

    public void addCourse(Course course, double grade) {
        for (CourseGrade courseGrade : courseGrades) {
            if (courseGrade.getCourse().equals(course)) {
                courseGrade.setGrade(grade);
                return;
            }
        }
        courseGrades.add(new CourseGrade(course, grade));
    }

    public double courseGPAPoints(Course course) {
        for (CourseGrade grade : courseGrades) {
            if (grade.getCourse().equals(course)) {
                return grade.getGPAPoints();
            }
        }
        throw new CourseNotFoundException(this, course);
    }

    public String courseGradeLetter(Course course) {
        for (CourseGrade grade : courseGrades) {
            if (grade.getCourse().equals(course)) {
                return grade.getGradeLetter();
            }
        }
        throw new CourseNotFoundException(this, course);
    }

    public String courseResult(Course course) {
        for (CourseGrade grade : courseGrades) {
            if (grade.getCourse().equals(course)) {
                return grade.getResult();
            }
        }
        throw new CourseNotFoundException(this, course);
    }

    public double getGPA() {
        double totalPoints = 0;
        int totalAKTS = 0;
        for (CourseGrade grade : courseGrades) {
            totalPoints += grade.getGPAPoints() * grade.getCourse().getAKTS();
            totalAKTS += grade.getCourse().getAKTS();
        }
        return totalPoints / totalAKTS;
    }

    public String toString() {
        return super.toString() + " - GPA: " + getGPA();
    }
}

class GradStudent extends Student {
    private int rank;
    private String thesisTopic;

    public GradStudent(String name, String email, long ID, Department department, int rank, String thesisTopic) {
        super(name, email, ID, department);
        this.rank = rank;
        this.thesisTopic = thesisTopic;
    }

    public void setRank(int rank) {
        if (rank < 1 || rank > 5) {
            throw new InvalidRankException(rank);
        }
        this.rank = rank;
    }

    public String getLevel() {
        switch (rank) {
            case 1:
                return "Master’s Student";
            case 2:
                return "Doctoral Student";
            case 3:
                return "Doctoral Candidate";
            default:
                throw new InvalidRankException(rank);
        }
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    @Override
    public String toString() {
        return getLevel() + " " + super.toString();
    }

    @Override
    public double courseGPAPoints(Course course) {
        if (courseGrades.isEmpty()) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = courseGrades.get(courseGrades.size() - 1).getGrade();
        if (grade >= 90)
            return 4.0;
        else if (grade >= 85)
            return 3.5;
        else if (grade >= 80)
            return 3.0;
        else if (grade >= 75)
            return 2.5;
        else if (grade >= 70)
            return 2.0;
        else
            return 0.0;
    }

    @Override
    public String courseGradeLetter(Course course) {
        if (courseGrades.isEmpty()) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = courseGrades.get(courseGrades.size() - 1).getGrade();
        if (grade >= 90)
            return "AA";
        else if (grade >= 85)
            return "BA";
        else if (grade >= 80)
            return "BB";
        else if (grade >= 75)
            return "CB";
        else if (grade >= 70)
            return "CC";
        else
            return "FF";
    }
}

class CourseGrade {
    private Course course;
    private double grade;

    public CourseGrade(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getGPAPoints() {
        if (grade >= 88)
            return 4.0;
        else if (grade >= 81)
            return 3.5;
        else if (grade >= 74)
            return 3.0;
        else if (grade >= 67)
            return 2.5;
        else if (grade >= 60)
            return 2.0;
        else if (grade >= 53)
            return 1.5;
        else if (grade >= 46)
            return 1.0;
        else if (grade >= 35)
            return 0.5;
        else
            return 0.0;
    }

    public String getGradeLetter() {
        if (grade >= 88)
            return "AA";
        else if (grade >= 81)
            return "BA";
        else if (grade >= 74)
            return "BB";
        else if (grade >= 67)
            return "CB";
        else if (grade >= 60)
            return "CC";
        else if (grade >= 53)
            return "DC";
        else if (grade >= 46)
            return "DD";
        else if (grade >= 35)
            return "FD";
        else
            return "FF";
    }

    public String getResult() {
        if (grade >= 35)
            return "Passed";
        else
            return "Failed";
    }
}

class Semester {
    private int season = 2;
    private int year = 2024;
    
    public Semester(int season, int year) {
        this.season = season;
        this.year = year;
    }

    public String getSeason() {

        if (season == 1)
            return "Fall";
        else if (season == 2)
            return "Spring";
        else if (season == 3)
            return "Summer";
        else
            throw new IllegalArgumentException("Invalid season");

    }

    public int getYear() {
        return year;
    }

}

class CourseNotFoundException extends RuntimeException {
    private Student student;
    private Course course;

    public CourseNotFoundException(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseNotFoundException: " + student.getID() + " has not yet taken " + course.courseCode();
    }
}

class DepartmentMismatchException extends RuntimeException {
    private Department department;
    private Person person;
    private Course course;

    public DepartmentMismatchException(Person person, Course course) {
        this.person = person;
        this.course = course;
        this.department = null;
    }

    public DepartmentMismatchException(Department department, Person person) {
        this.department = department;
        this.person = person;
        this.course = null;
    }

    @Override
    public String toString() {
        if (course != null) {
            return "DepartmentMismatchException: " + person.getName() + "(" + person.getID() + ") cannot teach "
                    + course.courseCode() + " because he/she is currently assigned to "
                    + person.getDepartment().getCode();
        } else {
            return "DepartmentMismatchException: " + person.getName() + "(" + person.getID() + ") cannot be chair of "
                    + department.getCode() + " because he/she is currently assigned to "
                    + person.getDepartment().getCode();
        }
    }
}

class InvalidGradeException extends RuntimeException {
    private double grade;

    public InvalidGradeException(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "InvalidGradeException: " + grade;
    }
}

class InvalidValueException extends RuntimeException {
    private String email;

    public InvalidValueException(String grade) {
        this.email = grade;
    }

    @Override
    public String toString() {
        return "InvalidValueException: Email cannot be " + this.email
                + ", it must be like 'username@universityname.domain";
    }
}

class InvalidRankException extends RuntimeException {
    private int rank;

    public InvalidRankException(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "InvalidRankException: " + rank;
    }
}

class SemesterNotFoundException extends RuntimeException {
    private Student student;
    private Semester semester;

    public SemesterNotFoundException(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "SemesterNotFoundException: " + student.getID() + " has not yet taken any courses in ";
    }
}