# WMC_Fan-in Calculations

## MainComponent

- ```java
  public MainComponent(String componentName, int componentWeight, ArrayList<SubComponent> subComponents)
  ```

  - CourseMgr.enterCourseWorkComponentWeightage()
  - FILEMgr.loadCourses()
  - FILEMgr.loadStudentMarks()

- ```java
  public ArrayList<SubComponent> getSubComponents()
  ```

  - CourseMgr.enterCourseWorkComponentWeightage()
  - FILEMgr.writeCourseIntoFile()
  - FILEMgr.backUpCourse()
  - FILEMgr.updateStudentMarks()
  - FILEMgr.backUpMarks()
  - Mark.setMainCourseWorkMarks()
  - Mark.setSubCourseWorkMarks()
  - MarkMgr.initializeMark()
  - MarkMgr.setCourseWorkMark()
  - MarkMgr.printCourseStatistics()
  - MarkMgr.printStudentTranscript()

- ```java
  public void printComponentType()
  ```




Total: 3 + 11 + 0 = 14

## Mark

- ```java
  public Mark(Student student, Course course, HashMap<CourseworkComponent, Double> courseWorkMarks, double totalMark)
  ```

  - FILEMgr.loadStudentMarks()
  - MarkMgr.initializeMark()

- ```java
  public Student getStudent()
  ```

  - FILEMgr.updateStudentMarks()
  - FILEMgr.backUpMarks()
  - MarkMgr.setCourseWorkMark()
  - MarkMgr.printStudentTranscript()

- ```java
  public Course getCourse()
  ```

  - FILEMgr.updateStudentMarks()
  - FILEMgr.backUpMarks()
  - MarkMgr.setCourseWorkMark()
  - MarkMgr.printCourseStatistics()
  - MarkMgr.printStudentTranscript()

- ```java
  public HashMap<CourseworkComponent, Double> getCourseWorkMarks()
  ```

  - FILEMgr.updateStudentMarks()
  - FILEMgr.backUpMarks()
  - MarkMgr.setCourseWorkMark()
  - MarkMgr.computeMark()
  - MarkMgr.printCourseStatistics()
  - MarkMgr.printStudentTranscript()

- ```java
  public double getTotalMark()
  ```

  - FILEMgr.updateStudentMarks()
  - FILEMgr.backUpMarks()
  - MarkMgr.printCourseStatistics()
  - MarkMgr.printStudentTranscript()

- ```java
  public void setMainCourseWorkMarks(String courseWorkName, double result)
  ```

  - MarkMgr.setCourseWorkMark()

- ```java
  public void setSubCourseWorkMarks(String courseWorkName, double result)
  ```

  - MarkMgr.setCourseWorkMark()

Total: 2 + 4 + 5 + 6 + 4 + 1 = 22

## MarkMgr*

- ```java
  public static Mark initializeMark(Student student, Course course)
  ```

  - CourseRegistrationMgr.registerCourse()

- ```java
  public static void setCourseWorkMark(boolean isExam)
  ```

  - Main.main()

- ```java
  public static double computeMark(ArrayList<Mark> thisCourseMark, String thisComponentName)
  ```

  - MarkMgr.printCourseStatistics()

- ```java
  public static void printCourseStatistics()
  ```

  - Main.main()

- ```java
  public static void  printStudentTranscript()
  ```

  - Main.main()

- ```java
  public static double gpaCalcualtor(double result)
  ```

  - MarkMgr.printStudentTranscript()

Total: 1 + 1 + 1 + 1 + 1 + 1 = 6

## Professor

- ```java
  public Professor(String profID, String profName)
  ```

  - FILEMgr.loadProfessors()
  - ProfessorMgr.addProfessor()

- ```java
  public String getProfID()
  ```

  - FILEMgr.writeCourseIntoFile()
  - FILEMgr.loadCourses()
  - FILEMgr.backUpCourse()
  - FILEMgr.writeProfIntoFile()
  - HelpInfoMgr.printProfInDepartment()
  - ValidationMgr.checkProfExists()

- ```java
  public String getProfName()
  ```

  - CourseMgr.printCourses()
  - CourseRegistrationMgr.registerCourse()
  - FILEMgr.writeProfIntoFile()

- ```java
  public String getProfDepartment()
  ```

  - FILEMgr.writeProfIntoFile()
  - HelpInfoMgr.printProfInDepartment()

- ```java
  public void setProfDepartment(String profDepartment)
  ```

  - FILEMgr.loadProfessors()
  - ProfessorMgr.addProfessor()

Total: 2 + 6 + 3 + 2 + 2 = 15

## ProfessorMgr*

- ```java
  public Professor addProfessor()
  ```

Total: 0

??? ProfessorMgr isnt used by anything???

## Student

- ```java
  public Student(String studentName)
  ```

  - StudentMgr.addStudent()

- ```java
  public Student(String studentID, String studentName)
  ```

  - FILEMgr.loadStudents()

- ```java
  public String getStudentID()
  ```

  - CourseRegistrationMgr
    registerCourse()
    printStudents()
  - FILEMgr
    writeStudentsIntoFile(Student)
    writeCourseRegistrationIntoFile(CourseRegistration)
    loadCourseRegistration()
    updateStudentMarks(Mark)
    loadStudentMarks()
    backUpMarks(ArrayList<Mark>)
  - HelpInfoMgr
    printAllStudents()
  - MarkMgr
    setCourseWorkMark(boolean)
    printStudentTranscript()
  - Student
    generateStudentID()
  - StudentMgr
    addStudent()
  - ValidationMgr
    checkStudentExists(String)
    checkCourseRegistrationExists(String, String)

- ```java
  public String getStudentName()
  ```

  - CourseRegistrationMgr
    registerCourse()
    printStudents()
  - FILEMgr
    writeStudentsIntoFile(Student)
  - MarkMgr
    printStudentTranscript()
  - StudentMgr
    addStudent()

- ```java
  public String getStudentSchool()
  ```

  - FILEMgr
    writeStudentsIntoFile(Student)
  - StudentMgr
    addStudent()

- ```java
  public String getGender()
  ```

  - FILEMgr
    writeStudentsIntoFile(Student)
  - StudentMgr
    addStudent()

- ```java
  public double getGPA()
  ```

  - FILEMgr
    writeStudentsIntoFile(Student)
  - StudentMgr
    addStudent()

- ```java
  public int getStudentYear()
  ```

  - FILEMgr
    writeStudentsIntoFile(Student)
  - StudentMgr
    addStudent()

- ```java
  public static void setIdNumber(int idNumber)
  ```

  - FILEMgr
    loadStudents()

- ```java
  public void setStudentID(String studentID)
  ```

  - StudentMgr
    addStudent()

- ```java
  public void setStudentSchool(String studentSchool)
  ```

  - FILEMgr
    loadStudents()
  - StudentMgr
    addStudent()

- ```java
  public void setGender(String gender)
  ```

  - FILEMgr
    loadStudents()
  - StudentMgr
    addStudent()

- ```java
  public void setGPA(double GPA)
  ```
  - FILEMgr
    loadStudents()

- ```java
  public void  setStudentYear(int studentYear)
  ```

  - FILEMgr
    loadStudents()
  - StudentMgr
    addStudent()

- ```java
  private String generateStudentID()
  ```

  - Student
    Student(String)

Total: 1 + 1 + 15 + 5 + 2 + 2 + 2 + 2 + 1 + 1 +2 + 1 + 2 + 1 = 38

## StudentMgr*

- ```java
  public static void addStudent()
  ```

  - Main
    main(String[])

Total: 1

## SubComponent

- ```java
  public SubComponent(String componentName, int componentWeight)
  ```

  - CourseMgr
    enterCourseWorkComponentWeightage(Course)
  - FILEMgr
    loadCourses()
    loadStudentMarks()

- ```java
  public void printComponentType()
  ```

Total: 3

## TutorialGroup

- ```java
  public TutorialGroup(String groupName, int availableVacancies, int totalSeats)
  ```

  - CourseMgr
    addCourse()
  - FILEMgr
    loadCourses()

Total: 2

## ValidationMgr*

- ```java
  public static boolean checkDepartmentValidation(String department)
  ```

  - CourseMgr
    addCourse()
  - HelpInfoMgr
    printProfInDepartment(String, boolean)
  - ProfessorMgr
    addProfessor()
  - StudentMgr
    addStudent()
  - ValidationMgr
    checkCourseDepartmentExists()

- ```java
  public static boolean checkGenderValidation(String gender)
  ```

  - StudentMgr
    addStudent()

- ```java
  public static boolean checkCourseTypeValidation(String courseType)
  ```

  - CourseMgr
    addCourse()

- ```JAVA
  public static boolean checkValidStudentIDInput(String studentID)
  ```

  - addStudent()
    StudentMgr

Total: 5 + 1 + 1 + 1 = 8

