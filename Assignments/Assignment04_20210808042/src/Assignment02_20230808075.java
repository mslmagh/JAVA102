import java.util.HashMap;
import java.util.Map;

public class Assignment02_20230808075 {
    public static void main(String[] args) {

        Department cse = new Department("CSE", "Computer Engineering");
        Teacher teacher = new Teacher("Joseph LEDET", "josephledet@akdeniz.edu.tr", 123L, cse, 3);
        System.out.println(teacher);
        Student stu = new Student("Assignment 4 STUDENT", "me@somewhere.com", 456L, cse);
        Semester s1 = new Semester(1, 2020);
        Course c101 = new Course(cse, 101, "Programming 1", "Introduction", 6, teacher);
        Semester s2 = new Semester(2, 2021);
        Course c102 = new Course(cse, 102, "Programming 2", "O0P", 4, teacher);
        Course c204 = new Course(cse, 204, "Database Systems", "DBMS", 6, teacher);
        System.out.println(stu.getAKTS());
        stu.addCourse(c101, s1, 80);
        stu.addCourse(c102, s2, 30);
        stu.addCourse(c204, s2, 70);
        System.out.println("List Grades for CSE101:\n" + stu.listGrades(c101));
        // System.out.println("List Grades for Spring 2021:\n" + stu.listGrades(s2));
        // System.out.println("Student Transcript:\n" + stu.transcript());
        System.out.println();
        GradStudent gs = new GradStudent("Assignment 4 GRADSTUDENT", "me@somewhere.com", 789L, cse, 2, "MDE");
        // gs.addCourse(c101, s1, 85);
        // gs.addCourse(c102, s1, 40);
        // gs.setTeachingAssistant(c101);
        // System.out.println("Teaching Assistant:\n" + gs.getTeachingAssistant());
        // gs.setTeachingAssistant(c102);
        // System.out.println("Teaching Assistant:\n" + gs.getTeachingAssistant());

    }
}

class Department {
    private String code, name;
    private Teacher chair;

    Department(String code, String name) {
        setCode(code);
        setName(name);
    }

    public void setCode(String code) {
        if (code.length() == 3 || code.length() == 4) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Department code must be 3 or 4 characters long.");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setChair(Teacher chair) {
        if (chair == null) {
            this.chair = null;
        } else {
            if (chair.getDepartment() != this) {
                throw new DepartmentMismatchException(this, chair);
            }
            this.chair = chair;
        }

    }

    public Teacher getChair() {
        return chair;
    }
}

class Course {
    private Department department;
    private Teacher teacher;
    private int courseNumber, AKTS;
    private String title, description;

    Course(Department department, int courseNumber, String title,
            String description, int AKTS, Teacher teacher) {
        setDepartment(department);
        setCourseNumber(courseNumber);
        setTitle(title);
        setDescription(description);
        setAKTS(AKTS);
        setTeacher(teacher);
    }

    public int getAKTS() {
        return AKTS;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setAKTS(int aKTS) {
        AKTS = aKTS;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacher(Teacher teacher) {
        if (teacher.getDepartment() != this.department)
            throw new DepartmentMismatchException(department, teacher);
        this.teacher = teacher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String courseCode() {
        return department.getCode() + this.getCourseNumber();
    }

    @Override
    public String toString() {
        return String.format(courseCode() + " - " + title + "(" + AKTS + ")");
    }
}

abstract class Person {
    private Department department;
    private String name;
    private String email;
    private long ID;

    Person(String name, String email, long ID, Department department) {
        setDepartment(department);
        setEmail(email);
        setID(ID);
        setName(name);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        int index = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                index = i;
                break;
            }
        }

        if (index == 0) {
            throw new InvalidValueException("Person", email, this.getEmail(),
                    "must include @ and . in the right places");
        }

        for (int j = index; j < email.length(); j++) {
            if (j == email.length() - 1)
                throw new InvalidValueException("Person", email, this.getEmail(),
                        "must include @ and . in right places");
            if (email.charAt(j) == '.') {
                this.email = email;
                break;
            }
        }
    }

    public void setID(long iD) {
        ID = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmail(String email) {
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                for (int j = 0; j < email.length(); j++) {
                    if (email.charAt(j) == '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(name + " " + "(" + ID + ")" + " - " + email);
    }
}

class Teacher extends Person {
    private int rank;

    Teacher(String name, String email, long ID, Department department, int rank) {
        super(name, email, ID, department);
        this.rank = rank;
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

    public void setDepartment(Department department) {
        if (this.getDepartment() == null) {
            super.setDepartment(department);
        } else if (this.getDepartment().getChair() == this) {
            this.getDepartment().setChair(null);
            super.setDepartment(department);
        } else {
            super.setDepartment(department);
        }
    }

    public void promote() {
        if (rank < 5 && rank >= 1)
            rank++;
        else
            throw new InvalidRankException(rank);
    }

    public int getRank() {
        return rank;
    }

    public void demote() {
        if (rank <= 5 && rank > 1)
            rank--;
        else
            throw new InvalidRankException(rank);
    }

    @Override
    public String toString() {
        return String.format(getTitle() + " " + super.toString());
    }
}

class Student extends Person {
    private int AKTS;

    HashMap<Course, HashMap<Semester, Double>> courses = new HashMap<>();

    Student(String name, String email, long ID, Department department) {
        super(name, email, ID, department);
    }

    public void addCourse(Course course, Semester semester, double grade) {

        if (courses.containsKey(course)) {
            for (Map.Entry m : courses.get(course).entrySet()) {
                courses.get(course).remove((int) m.getKey());
                courses.get(course).put(semester, grade);
            }

        } else {
            HashMap<Semester, Double> semesterGrades = new HashMap<>();
            semesterGrades.put(semester, grade);
            courses.put(course, semesterGrades);
            semesterGrades.remove(semester);
        }
    }

    public String listGrades(Course course) {

        return courses.toString();
    }

    public int getAKTS() {
        return AKTS;
    }

    public int getAttemptedAKTS() {
        return 0;
    }

    // public String courseResult(Course course) {
    // double GPA = courseGPAPoints(course);
    // if (!Courses.contains(course)) {
    // throw new CourseNotFoundException(this, course);
    // } else if (GPA >= 2.0) {
    // return "Passed";
    // } else if (GPA >= 1.0) {
    // return "Conditionally Passed";
    // } else {
    // return "Failed";
    // }
    // }

    // public String gradeLetter(Course course) {
    // double GPA = courseGPAPoints(course);
    // if (!Courses.contains(course)) {
    // throw new CourseNotFoundException(this, course);
    // } else if (GPA == 4) {
    // return "AA";
    // } else if (GPA == 3.5) {
    // return "BA";
    // } else if (GPA == 3.0) {
    // return "BB";
    // } else if (GPA == 2.5) {
    // return "CB";
    // } else if (GPA == 2.0) {
    // return "CC";
    // } else if (GPA == 1.5) {
    // return "DC";
    // } else if (GPA == 1.0) {
    // return "DD";
    // } else if (GPA == 0.5) {
    // return "FD";
    // } else {
    // return "FF";
    // }
    // }

    // public double courseGPAPoints(Course course) {
    // double gpa;
    // // for (Map.Entry m : courses.get(course).entrySet()) {

    // // gpa = m.getValue();

    // // }
    // HashMap<Semester, Double> gpaa = courses.get(course);
    // Semester semester = new Semester(1, 2);
    // gpaa.get(semester);
    // int index = -1;
    // index = Courses.indexOf(course);
    // double point = Grades.get(index);
    // if (index == -1) {
    // throw new CourseNotFoundException(this, course);
    // } else if (point >= 87.5 && point <= 100) {
    // return 4.0;
    // } else if (point >= 80.5) {
    // return 3.5;
    // } else if (point >= 73.5) {
    // return 3.0;
    // } else if (point >= 66.5) {
    // return 2.5;
    // } else if (point >= 59.5) {
    // return 2.0;
    // } else if (point >= 52.5) {
    // return 1.5;
    // } else if (point >= 45.5) {
    // return 1.0;
    // } else if (point >= 34.5) {
    // return 0.5;
    // } else {
    // return 0.0;
    // }
    // }

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

class GradStudent extends Student {
    private int rank;
    private String thesisTopic;
    HashMap<Course, Double> courses = new HashMap<>();
    // private ArrayList<Course> Courses = getCourses();
    // private ArrayList<Double> Grades = getGrades();

    GradStudent(String name, String email, long ID, Department department,
            int rank, String thesisTopic) {
        super(name, email, ID, department);
        setRank(rank);
        setThesisTopic(thesisTopic);
    }

    // @Override
    // public String courseResult(Course course) {

    // if (courses.get(course) >= 89.5) {
    // return "AA";
    // } else if (courses.get(course) >= 84.5) {
    // return "BA";
    // } else if (courses.get(course) >= 79.5) {
    // return "BB";
    // } else if (courses.get(course) >= 74.5) {
    // return "CB";
    // } else if (courses.get(course) >= 69.5) {
    // return "CC";
    // } else {
    // return "FF";
    // }
    // }

    // @Override
    // public double courseGPAPoints(Course course) {
    // int i = Courses.indexOf(course);
    // double grade = Grades.get(i);
    // if (grade >= 89.5) {
    // return 4.0;
    // } else if (grade >= 84.5) {
    // return 3.5;
    // } else if (grade >= 79.5) {
    // return 3.0;
    // } else if (grade >= 74.5) {
    // return 2.5;
    // } else if (grade >= 69.5) {
    // return 2.0;
    // } else {
    // return 0.0;
    // }
    // }

    // @Override
    // public String gradeLetter(Course course) {
    // int index = Courses.indexOf(course);
    // double grade = Grades.get(index);
    // if (grade >= 87.5) {
    // return "AA";
    // } else if (grade >= 84.5) {
    // return "BA";
    // } else if (grade >= 79.5) {
    // return "BB";
    // } else if (grade >= 74.5) {
    // return "CB";
    // } else if (grade >= 69.5) {
    // return "CC";
    // } else {
    // return "FF";
    // }
    // }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public void setRank(int rank) {
        if (rank < 1 || rank > 5) {
            throw new InvalidRankException(rank);
        }
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getThesisTopic() {
        return thesisTopic;
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
                return "ERROR: Rank must be bettwen 1-3";
        }
    }
}

class CourseNotFoundException extends RuntimeException {
    private Student student;
    private Course course;

    public CourseNotFoundException(Student student, Course course) {
        super();
        this.student = student;
        this.course = course;

    }

    @Override
    public String toString() {
        return "CourseNotFoundException: " + student.getID() +
                " has not yet taken " + course.courseCode();
    }
}

class DepartmentMismatchException extends RuntimeException {
    Department department;
    Teacher person;
    Course course;

    DepartmentMismatchException(Course course, Teacher person) {
        super();
        this.course = course;
        this.person = person;
        this.department = null;
    }

    DepartmentMismatchException(Department department, Teacher person) {
        super();
        this.department = department;
        this.person = person;
        this.course = null;
    }

    @Override
    public String toString() {
        if (this.course == null)
            return String.format("DepartmentMismatchException: " +
                    person.getName() + "(" + person.getID() + ") " +
                    "cannot be chair of " + department.getCode() +
                    " because he/she is currently assigned to " +
                    person.getDepartment().getCode());
        else
            return String.format("DepartmentMismatchException: " +
                    person.getName() + "(" + person.getID() + ") " +
                    "cannot teach " + course.getCourseNumber() +
                    " because he/she is currently assigned to " +
                    person.getDepartment().getCode());
    }
}

class InvalidGradeException extends RuntimeException {
    private double grade;

    InvalidGradeException(double grade) {
        super();
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("InvalidGradeException: " + grade);
    }

}

class InvalidRankException extends RuntimeException {
    private double rank;

    public InvalidRankException(double rank) {
        super("Invalid radius " + rank);
        this.rank = rank;
    }

    public double getrank() {
        return rank;
    }
}

class InvalidValueException extends RuntimeException {
    String className;
    String attributeName;
    String invalidVal;
    String validVal;

    InvalidValueException(String className, String attributeName,
            String invalidVal, String validVal) {
        super();
        this.className = className;
        this.attributeName = attributeName;
        this.invalidVal = invalidVal;
        this.validVal = validVal;
    }

    @Override
    public String toString() {
        return String.format("InvalidValueException: %s  %s  %s %s",
                className, attributeName, invalidVal, validVal);
    }
}