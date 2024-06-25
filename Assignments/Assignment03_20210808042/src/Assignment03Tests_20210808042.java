import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class Assignment03Tests_20210808042 {
  @Test
  public void testDepartmentCreation() {
    try {
      Department cse = new Department("CSE", "Computer Engineering");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void getCodeTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    Assert.assertEquals("CSE", cse.getCode());
  }

  @Test
  public void getNameTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    Assert.assertEquals("Computer Enginering", cse.getName());
  }

  @Test
  public void getChairTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Assert.assertEquals(null, cse.getChair());
  }

  @Test
  public void setChairTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    cse.setChair(t);
    Assert.assertEquals(t, cse.getChair());
  }

  @Test
  public void setCodeTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    cse.setCode("103");
    Assert.assertEquals("103", cse.getCode());
  }

  @Test
  public void setNameTestInDepartment() {
    Department cse = new Department("CSE", "Computer Enginering");
    cse.setName("EEE");
    Assert.assertEquals("EEE", cse.getName());
  }

  @Test
  public void setCodeWithInvalidValueTestInDepartment() {
    try {
      Department cse = new Department("CSE", "Computer Enginering");
      cse.setCode("103");
      cse.setCode("1");
      Assert.assertEquals("103", cse.getCode());
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // @Test
  // public void setChairWithInvalidValueTestInDepartment() {
  // try {
  // Department department = new Department("102", "CSE");
  // Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L,
  // department, 1);
  // department.setChair(t);
  // Teacher t2 = new Teacher("Berk Ercin", "berkercin@akdeniz.tr", 123L,
  // department, 1);
  // department.setChair(t2);
  // Assert.assertEquals(t, department.getChair());
  // } catch (DepartmentMismatchException e) {
  // e.getMessage();
  // }
  // }

  @Test
  public void testCourseCreation() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    try {
      Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void getAKTSTestINCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals(6, course.getAKTS());
  }

  @Test
  public void getCourseNumberTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals(102, course.getCourseNumber());
  }

  @Test
  public void getDepartmentTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals(cse, course.getDepartment());
  }

  @Test
  public void getDescriptionTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals("Introduction to Programing", course.getDescription());
  }

  @Test
  public void getTeacherTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals(t, course.getTeacher());
  }

  @Test
  public void getTitleTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals("Programming 1", course.getTitle());
  }

  @Test
  public void setAKTSTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);

    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setAKTS(5);
    Assert.assertEquals(5, course.getAKTS());
  }

  @Test
  public void setCourseNumberTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);

    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setCourseNumber(103);
    Assert.assertEquals(103, course.getCourseNumber());
  }

  @Test
  public void setDepartmentTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);

    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setCourseNumber(103);
    Assert.assertEquals(103, course.getCourseNumber());
  }

  @Test
  public void setDescriptionTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);

    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setDescription("Inheritance");
    Assert.assertEquals("Inheritance", course.getDescription());
  }

  @Test
  public void setTeacherTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 2);
    Teacher t2 = new Teacher("Berk Ercin", "berkercin@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setTeacher(t2);
    Assert.assertEquals(t2, course.getTeacher());
  }

  @Test
  public void setTitleTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);

    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    course.setTitle("Inheritance");
    Assert.assertEquals("Inheritance", course.getTitle());
  }

  @Test
  public void courseCodeTestInCourse() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    Course course = new Course(cse, 102, "Programming 1", "Introduction to Programing", 6, t);
    Assert.assertEquals("CSE102", course.courseCode());
  }

  @Test
  public void setDepartmentTestInTeacher() {
    Department cse = new Department("CSE", "Computer Enginering");
    Department cse2 = new Department("CSEE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 1);
    t.setDepartment(cse2);
    Assert.assertEquals(cse2, t.getDepartment());
  }

  @Test // Title ları Assignment 2 de güncellemediğim için hata alabilirim
  public void getTitleTestInTeacher() {
    Department cse = new Department("CSE", "Computer Enginering");
    Teacher t = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, cse, 2);
    Assert.assertEquals("Lecturer", t.getTitle());
  }

  // Assignment2 de getRank yazmamışım
  @Test
  public void getRankTestInTeacher() {
    Department department = new Department("CSE", "Computer Engineering");
    Teacher teacher = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, department, 1);
    assertEquals(1, teacher.getRank());
  }

  @Test
  public void promoteTestInTeacher() {
    Department department = new Department("CSE", "Computer Engineering");
    Teacher teacher = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, department, 2);
    teacher.promote();
    assertEquals(3, teacher.getRank());
  }

  @Test
  public void demoteTestInTeacher() {
    Department department = new Department("CSE", "Computer Engineering");
    Teacher teacher = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, department, 2);
    teacher.demote();
    assertEquals(1, teacher.getRank());
  }

  @Test
  public void promoteTestShouldThrowException() {
    try {
      Department department = new Department("CSE", "Computer Engineering");
      Teacher teacher = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, department, 5);
      teacher.promote();
      Assert.assertEquals(false, true);
    } catch (RuntimeException e) {
      Assert.assertEquals(e instanceof InvalidRankException, true);
    }
  }

  @Test
  public void demoteTestShouldThrowException() {
    try {
      Department department = new Department("CSE", "Computer Engineering");
      Teacher teacher = new Teacher("Joseph Ledet", "josepledet@akdeniz.tr", 123L, department, 1);
      teacher.demote();
    } catch (RuntimeException e) {
      Assert.assertEquals(e instanceof InvalidRankException,
          true);
    }
  }

  
}