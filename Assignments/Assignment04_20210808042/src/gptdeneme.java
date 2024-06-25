import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Salih Çimen
 * @since 25.03.2024
 */
public class gptdeneme {
    public static void main(String[] args) {
        Department cse = new Department("CSE", "Computer Engineering");
        Teacher teacher = new Teacher("Joseph LEDET", "josephledet@akdeniz.edu.tr", 123L, cse, 3);
        System.out.println(teacher);
        Student stu = new Student("Assignment 4 STUDENT", "me@somewhere.com", 456L, cse);
        Semester s1 = new Semester(1, 2020);
        Course c101 = new Course(cse, 101, "Programming 1", "Introduction", 6, teacher);
        Semester s2 = new Semester(2, 2021);
        Course c102 = new Course(cse, 102, "Programming 2", "OOP", 4, teacher);
        Course c204 = new Course(cse, 204, "Database Systems", "DBMS", 6, teacher);

        stu.addCourse(c101, s1, 80);
        stu.addCourse(c102, s2, 30);
        stu.addCourse(c204, s2, 70);
        System.out.println("List Grades for CSEL01:\n" + stu.listGrades(c101));
        System.out.println("List Grades for Spring 2021: \n" + stu.listGrades(s2));
        System.out.println("Student Transcript: \n" + stu.transcript());

        GradStudent gs = new GradStudent("Assignment 4 GRADSTUDENT", "me@somewhere.com", 789L, cse, 2, "MDE");
        gs.addCourse(c101, s1, 85);
        gs.addCourse(c102, s1, 40);
        // gs.setTeachingAssistant(c101);
        // System.out.println("Teaching Assistant: \n" + gs.getTeachingAssistant());
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

    Course(Department department, int courseNumber, String title, String description, int AKTS, Teacher teacher) {
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
        switch (rank) {
            case 1:
                return "Lecturer";
            case 2:
                return "Adjunct Instructor";
            case 3:
                return "Assistant Professor";
            case 4:
                return "Associate Professor";
            case 5:
                return "Professor";
            default:
                return "ERROR: Invalid rank number.";
        }
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
    private Map<Course, Map<Semester, Double>> courseRecords = new HashMap<>();

    Student(String name, String email, long ID, Department department) {
        super(name, email, ID, department);
    }

    private Map<Course, Map<Semester, Double>> courseGrades = new HashMap<>();

    public void addCourse(Course course, Semester semester, double grade) {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException(grade);
        }

        // Kursun daha önce eklenip eklenmediğini kontrol et
        if (courseGrades.containsKey(course)) {
            Map<Semester, Double> semesterGradeMap = courseGrades.get(course);

            // Kursun bu dönemde daha önce alınıp alınmadığını kontrol et
            if (semesterGradeMap.containsKey(semester)) {
                // Kursun notunu güncelle
                semesterGradeMap.put(semester, grade);
            } else {
                // Yeni dönem ve not ekle
                semesterGradeMap.put(semester, grade);
            }
        } else {
            // Yeni kurs ve dönem oluştur
            Map<Semester, Double> semesterGradeMap = new HashMap<>();
            semesterGradeMap.put(semester, grade);
            courseGrades.put(course, semesterGradeMap);
        }
    }

    public int getAKTS() {
        int totalAKTS = 0;
        for (Map<Semester, Double> semesterGradeMap : courseGrades.values()) {
            for (Map.Entry<Semester, Double> entry : semesterGradeMap.entrySet()) {
                if (entry.getValue() >= 60) {
                    totalAKTS += entry.getKey().getAKTS();
                }
            }
        }
        return totalAKTS;
    }

    public int getAttemptedAKTS() {
        int totalAKTS = 0;
        for (Map<Semester, Double> semesterGradeMap : courseGrades.values()) {
            totalAKTS += semesterGradeMap.keySet().stream().mapToInt(Semester::getAKTS).sum();
        }
        return totalAKTS;
    }

    public double getGPA() {
        int totalPoints = 0;
        int totalAKTS = getAttemptedAKTS();
        for (Map<Semester, Double> semesterGradeMap : courseGrades.values()) {
            for (Map.Entry<Semester, Double> entry : semesterGradeMap.entrySet()) {
                double points = courseGPAPoints(entry.getValue());
                totalPoints += points * entry.getKey().getAKTS();
            }
        }
        return totalPoints / (double) totalAKTS;
    }

    public String listGrades(Semester semester) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (Map.Entry<Course, Map<Semester, Double>> entry : courseGrades.entrySet()) {
            if (entry.getValue().containsKey(semester)) {
                found = true;
                result.append(entry.getKey().getTitle()).append(": ").append(entry.getValue().get(semester)).append("\n");
            }
        }
        if (!found) {
            throw new SemesterNotFoundException(this, semester);
        }
        return result.toString();
    }

    public String listGrades(Course course) {
        Map<Semester, Double> semesterGradeMap = courseGrades.get(course);
        if (semesterGradeMap == null) {
            throw new CourseNotFoundException(this, course);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Semester, Double> entry : semesterGradeMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public String transcript() {
        StringBuilder transcript = new StringBuilder();
        Map<Semester, ArrayList<String>> semesterCourses = new HashMap<>();
        for (Map.Entry<Course, Map<Semester, Double>> entry : courseGrades.entrySet()) {
            for (Map.Entry<Semester, Double> innerEntry : entry.getValue().entrySet()) {
                if (!semesterCourses.containsKey(innerEntry.getKey())) {
                    semesterCourses.put(innerEntry.getKey(), new ArrayList<>());
                }
                semesterCourses.get(innerEntry.getKey()).add(entry.getKey().getTitle() + ": " + innerEntry.getValue());
            }
        }
        for (Map.Entry<Semester, ArrayList<String>> entry : semesterCourses.entrySet()) {
            transcript.append(entry.getKey()).append("\n");
            for (String courseGrade : entry.getValue()) {
                transcript.append(courseGrade).append("\n");
            }
            double semesterGPA = calculateSemesterGPA(entry.getKey());
            transcript.append("Semester GPA: ").append(semesterGPA).append("\n");
        }
        return transcript.toString();
    }

    private double courseGPAPoints(double grade) {
        if (grade >= 87.5) {
            return 4.0;
        } else if (grade >= 80.5) {
            return 3.5;
        } else if (grade >= 73.5) {
            return 3.0;
        } else if (grade >= 66.5) {
            return 2.5;
        } else if (grade >= 59.5) {
            return 2.0;
        } else if (grade >= 52.5) {
            return 1.5;
        } else if (grade >= 45.5) {
            return 1.0;
        } else if (grade >= 34.5) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    private double calculateSemesterGPA(Semester semester) {
        double totalPoints = 0;
        int totalAKTS = 0;
        for (Map.Entry<Course, Map<Semester, Double>> entry : courseGrades.entrySet()) {
            Map<Semester, Double> semesterGradeMap = entry.getValue();
            for (Map.Entry<Semester, Double> innerEntry : semesterGradeMap.entrySet()) {
                if (innerEntry.getKey().equals(semester)) {
                    totalPoints += courseGPAPoints(innerEntry.getValue()) * entry.getKey().getAKTS();
                    totalAKTS += entry.getKey().getAKTS();
                }
            }
        }
        return totalPoints / totalAKTS;
    }
}

    @Override
    public String toString() {
        return String.format(super.toString() + " - AKTS: " + getAKTS() + ", GPA: " + getGPA());
    }
}

class GradStudent extends Student {
    private int level;
    private String thesisTitle;

    GradStudent(String name, String email, long ID, Department department, int level, String thesisTitle) {
        super(name, email, ID, department);
        this.level = level;
        this.thesisTitle = thesisTitle;
    }

    public int getLevel() {
        return level;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + " - Level: " + level + ", Thesis: " + thesisTitle);
    }
}

class Semester {
    private int semesterNo;
    private int year;

    Semester(int semesterNo, int year) {
        this.semesterNo = semesterNo;
        this.year = year;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Semester " + semesterNo + " " + year);
    }
}

class DepartmentMismatchException extends RuntimeException {
    DepartmentMismatchException(Department d, Teacher t) {
        super("Department mismatch between " + d.getName() + " and " + t.getName());
    }
}

class InvalidValueException extends RuntimeException {
    InvalidValueException(String entity, String providedValue, String propertyName, String requirement) {
        super("Invalid " + entity + " value: " + providedValue + ". " + propertyName + " " + requirement);
    }
}

class InvalidRankException extends RuntimeException {
    InvalidRankException(int rank) {
        super("Invalid rank: " + rank + ". Rank must be between 1 and 5.");
    }
}

class SemesterNotFoundException extends RuntimeException {
    public SemesterNotFoundException(Student student, Semester semester) {
        super("Semester not found for student: " + student + " in semester: " + semester);
    }
}

class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Student student, Course course) {
        super("Course not found for student: " + student + " in course: " + course);
    }
}
