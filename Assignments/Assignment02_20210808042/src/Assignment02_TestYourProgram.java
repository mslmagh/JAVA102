import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment02_TestYourProgram {
    public Assignment02_TestYourProgram() {

    }

    public static void main(String[] args) {
        try {
            Scanner inp = new Scanner(System.in);
            System.out.print("Enter your student number: ");
            String id = inp.nextLine();
            String filename = "Assignment02_" + id;
            Process p = Runtime.getRuntime().exec("javac " + filename + ".java");
            p.waitFor();
            if (p.exitValue() != 0) {
                System.out.println("fewfewfhcewkckwf");
                System.out.println("ERROR: Something is wrong in your java program or filename.");
            } else {
                System.out.println("Your program seems to compile and your filename is correct.");
                PrintWriter out = new PrintWriter("Assignment02_CompileMe.java");
                out.print("public class Assignment02_CompileMe { \n   public static void main(String[] args) {\n      String s;\n      boolean b;\n      double d;\n      int i;\n      Department de = new Department(\"\", \"\");\n      Teacher t = new Teacher(\"\", \"\", 0L, de, 0);\n      Course c = new Course(de, 0, \"\", \"\", 0, t);\n      Student st = new Student(\"\", \"\", 0L, de);\n      GradStudent gs = new GradStudent(\"\", \"\", 0L, de, 0, \"\");\n      Person p = new Teacher(\"\", \"\", 0L, de, 0);\n      p = new Student(\"\", \"\", 0L, de);\n      p = new GradStudent(\"\", \"\", 0L, de, 0, \"\");\n      s = de.getCode();\n      s = de.getName();\n      t = de.getChair();\n      de.setCode(\"\");\n      de.setName(\"\");\n      de.setChair(t);\n      de = c.getDepartment();\n      c.setDepartment(de);\n      t = c.getTeacher();\n      c.setTeacher(t);\n      s = c.courseCode();\n      s = c.toString();\n      t.setDepartment(de);\n      t.promote();\n      t.demote();\n      s = t.getTitle();\n      i = st.getAKTS();\n      i = st.getAttemptedAKTS();\n      st.addCourse(c, 0);\n      d = st.courseGPAPoints(c);\n      s = st.courseGradeLetter(c);\n      s = st.courseResult(c);\n      d = st.getGPA();\n      s = st.toString();\n      gs.setRank(0);\n      d = gs.courseGPAPoints(c);\n      s = gs.courseGradeLetter(c);\n      s = gs.courseResult(c);\n      d = gs.getGPA();\n      s = gs.toString();\n      CourseNotFoundException ex1 = new CourseNotFoundException(st, c);\n      DepartmentMismatchException ex2 = new DepartmentMismatchException(c, t);\n      ex2 = new DepartmentMismatchException(de, t);\n      InvalidGradeException ex3 = new InvalidGradeException(d);\n      InvalidRankException ex4 = new InvalidRankException(i);\n   }\n}");
                out.close();
                p = Runtime.getRuntime().exec("javac Assignment02_CompileMe.java");
                p.waitFor();
                if (p.exitValue() != 0) {
                    System.out.println("dsfdngbdlkv222222");
                    System.out.println("ERROR: Something is wrong in your java program, classes or method names.");
                } else {
                    System.out.println("Your program seems to have correct class and method names.");
                }
                File myFile = new File("Assignment02_CompileMe.java");
                myFile.delete();
                myFile = new File("Assignmen02_CompileMe.class");
                myFile.delete();
            }
        } catch (Exception var7) {
            System.out.println("dsfdngbdlkvwefww");
            System.out.println("ERROR: Something is wrong in your java program or filename.");
        }
    }
}

