package com.school;

import com.school.model.Course;
import com.school.model.Department;
import com.school.model.Generation;
import com.school.model.Leave;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.model.Teacher;
import com.school.model.Timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Department> departments = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Generation> generations = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<SchoolClass> schoolClasses = new ArrayList<>();
    private static final List<Leave> leaves = new ArrayList<>();
    private static final List<Timetable> timetables = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==================================================");
        System.out.println("   WELCOME TO SCHOOL MANAGEMENT SYSTEM CLI");
        System.out.println("==================================================");

        while (running) {
            System.out.println("\nSelect a Class to Input info or View Data:");
            System.out.println("1. Department");
            System.out.println("2. Teacher");
            System.out.println("3. Course");
            System.out.println("4. Generation");
            System.out.println("5. Student");
            System.out.println("6. SchoolClass");
            System.out.println("7. Leave");
            System.out.println("8. Timetable");
            System.out.println("9. View All Connected Data");
            System.out.println("10. Add Quick Sample Data");
            System.out.println("11. Exit");
            System.out.print("Enter choice (1-11): ");

            String inputChoice = scanner.nextLine().trim();
            switch (inputChoice) {
                case "1":
                    inputDepartmentInfo();
                    break;
                case "2":
                    inputTeacherInfo();
                    break;
                case "3":
                    inputCourseInfo();
                    break;
                case "4":
                    inputGenerationInfo();
                    break;
                case "5":
                    inputStudentInfo();
                    break;
                case "6":
                    inputSchoolClassInfo();
                    break;
                case "7":
                    inputLeaveInfo();
                    break;
                case "8":
                    inputTimetableInfo();
                    break;
                case "9":
                    displayAllConnectedData();
                    break;
                case "10":
                    addSampleData();
                    break;
                case "11":
                    running = false;
                    System.out.println("\nExiting School Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 11.");
            }
        }
    }

    // --- 1. Department ---
    private static Department inputDepartmentInfo() {
        System.out.println("\n--- 1. INPUT DEPARTMENT INFO ---");
        int id = readInt("Enter Department Id: ");
        System.out.print("Enter Department Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Department Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine().trim();
        int managerId = readInt("Enter ManagerId: ");

        Department dept = new Department(id, name, code, location, 50000, managerId, 1, "2026-09-13");
        departments.add(dept);
        System.out.println("\n[SUCCESS] Department added successfully!");
        printDepartmentCard(dept);
        return dept;
    }

    // --- 2. Teacher ---
    private static Teacher inputTeacherInfo() {
        System.out.println("\n--- 2. INPUT TEACHER INFO ---");
        int teacherId = readInt("Enter TeacherId: ");
        System.out.print("Enter TeacherName: ");
        String teacherName = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();
        double salary = readDouble("Enter Salary: ");

        Department dept = selectOrCreateDepartment();

        Teacher teacher = new Teacher(teacherId, teacherName, email, phone, salary, dept);
        teachers.add(teacher);
        System.out.println("\n[SUCCESS] Teacher added successfully!");
        printTeacherCard(teacher);
        return teacher;
    }

    // --- 3. Course ---
    private static Course inputCourseInfo() {
        System.out.println("\n--- 3. INPUT COURSE INFO ---");
        int courseId = readInt("Enter CourseId: ");
        System.out.print("Enter CourseName: ");
        String courseName = scanner.nextLine().trim();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        int credit = readInt("Enter Credit: ");

        System.out.println("\n-> Select Department for Course:");
        Department dept = selectOrCreateDepartment();

        System.out.println("\n-> Select Teacher for Course:");
        Teacher teacher = selectOrCreateTeacher();

        Course course = new Course(courseId, courseName, description, credit, dept, teacher);
        courses.add(course);
        System.out.println("\n[SUCCESS] Course added successfully!");
        printCourseCard(course);
        return course;
    }

    // --- 4. Generation ---
    private static Generation inputGenerationInfo() {
        System.out.println("\n--- 4. INPUT GENERATION INFO ---");
        int id = readInt("Enter Generation Id: ");
        System.out.print("Enter Generation Name (e.g. Gen 10): ");
        String name = scanner.nextLine().trim();
        int startYear = readInt("Enter Start Year: ");
        int endYear = readInt("Enter End Year: ");
        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        int studentCount = readInt("Enter Student Count: ");
        System.out.print("Enter Academic Year (e.g. 2026-2027): ");
        String academicYear = scanner.nextLine().trim();

        Generation gen = new Generation(id, name, startYear, endYear, description, studentCount, academicYear);
        generations.add(gen);
        System.out.println("\n[SUCCESS] Generation added successfully!");
        printGenerationCard(gen);
        return gen;
    }

    // --- 5. Student ---
    private static Student inputStudentInfo() {
        System.out.println("\n--- 5. INPUT STUDENT INFO ---");
        int studentId = readInt("Enter StudentId: ");
        System.out.print("Enter StudentName: ");
        String studentName = scanner.nextLine().trim();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine().trim();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("\n-> Select Generation for Student:");
        Generation generation = selectOrCreateGeneration();

        Student student = new Student(studentId, studentName, gender, age, phone, email, generation);
        students.add(student);
        System.out.println("\n[SUCCESS] Student added successfully!");
        printStudentCard(student);
        return student;
    }

    // --- 6. SchoolClass ---
    private static SchoolClass inputSchoolClassInfo() {
        System.out.println("\n--- 6. INPUT SCHOOLCLASS INFO ---");
        int classId = readInt("Enter ClassId: ");
        System.out.print("Enter ClassName: ");
        String className = scanner.nextLine().trim();
        System.out.print("Enter Room: ");
        String room = scanner.nextLine().trim();
        int capacity = readInt("Enter Capacity: ");

        System.out.println("\n-> Select Course for Class:");
        Course course = selectOrCreateCourse();

        System.out.println("\n-> Select Teacher for Class:");
        Teacher teacher = selectOrCreateTeacher();

        System.out.println("\n-> Select Generation for Class:");
        Generation generation = selectOrCreateGeneration();

        SchoolClass schoolClass = new SchoolClass(classId, className, room, capacity, course, teacher, generation);
        schoolClasses.add(schoolClass);
        System.out.println("\n[SUCCESS] SchoolClass added successfully!");
        printSchoolClassCard(schoolClass);
        return schoolClass;
    }

    // --- 7. Leave ---
    private static Leave inputLeaveInfo() {
        System.out.println("\n--- 7. INPUT LEAVE INFO ---");
        int leaveId = readInt("Enter LeaveId: ");
        System.out.print("Enter Reason: ");
        String reason = scanner.nextLine().trim();
        System.out.print("Enter StartDate (YYYY-MM-DD): ");
        String startDate = scanner.nextLine().trim();
        System.out.print("Enter EndDate (YYYY-MM-DD): ");
        String endDate = scanner.nextLine().trim();
        System.out.print("Enter Status (Pending/Approved/Rejected) [default: Pending]: ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) status = "Pending";

        System.out.println("\n-> Select Student for Leave:");
        Student student = selectOrCreateStudent();

        System.out.println("\n-> Select SchoolClass for Leave:");
        SchoolClass sClass = selectOrCreateSchoolClass();

        Leave leave = new Leave(leaveId, reason, startDate, endDate, status, student, sClass);
        leaves.add(leave);
        System.out.println("\n[SUCCESS] Leave request added successfully!");
        printLeaveCard(leave);
        return leave;
    }

    // --- 8. Timetable ---
    private static Timetable inputTimetableInfo() {
        System.out.println("\n--- 8. INPUT TIMETABLE INFO ---");
        int timetableId = readInt("Enter TimetableId: ");
        System.out.print("Enter Day (e.g. Monday): ");
        String day = scanner.nextLine().trim();
        System.out.print("Enter StartTime (e.g. 08:00 AM): ");
        String startTime = scanner.nextLine().trim();
        System.out.print("Enter EndTime (e.g. 10:00 AM): ");
        String endTime = scanner.nextLine().trim();
        System.out.print("Enter Room: ");
        String room = scanner.nextLine().trim();

        System.out.println("\n-> Select Course for Timetable:");
        Course course = selectOrCreateCourse();

        System.out.println("\n-> Select SchoolClass for Timetable:");
        SchoolClass sClass = selectOrCreateSchoolClass();

        Timetable timetable = new Timetable(timetableId, day, startTime, endTime, room, course, sClass);
        timetables.add(timetable);
        System.out.println("\n[SUCCESS] Timetable added successfully!");
        printTimetableCard(timetable);
        return timetable;
    }

    // --- Helpers for selecting or creating parent entities ---
    private static Department selectOrCreateDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No Department found. Creating a new Department...");
            return inputDepartmentInfo();
        }
        System.out.println("Available Departments:");
        for (int i = 0; i < departments.size(); i++) {
            Department d = departments.get(i);
            System.out.printf("  %d. %s (Code: %s, Id: %d)\n", i + 1, d.getName(), d.getCode(), d.getId());
        }
        System.out.printf("  %d. [Create New Department]\n", departments.size() + 1);
        int choice = readInt("Select department (1-" + (departments.size() + 1) + "): ");
        if (choice >= 1 && choice <= departments.size()) {
            return departments.get(choice - 1);
        } else {
            return inputDepartmentInfo();
        }
    }

    private static Teacher selectOrCreateTeacher() {
        if (teachers.isEmpty()) {
            System.out.println("No Teacher found. Creating a new Teacher...");
            return inputTeacherInfo();
        }
        System.out.println("Available Teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            Teacher t = teachers.get(i);
            System.out.printf("  %d. %s (Id: %d)\n", i + 1, t.getTeacherName(), t.getTeacherId());
        }
        System.out.printf("  %d. [Create New Teacher]\n", teachers.size() + 1);
        int choice = readInt("Select teacher (1-" + (teachers.size() + 1) + "): ");
        if (choice >= 1 && choice <= teachers.size()) {
            return teachers.get(choice - 1);
        } else {
            return inputTeacherInfo();
        }
    }

    private static Course selectOrCreateCourse() {
        if (courses.isEmpty()) {
            System.out.println("No Course found. Creating a new Course...");
            return inputCourseInfo();
        }
        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.printf("  %d. %s (Id: %d)\n", i + 1, c.getCourseName(), c.getCourseId());
        }
        System.out.printf("  %d. [Create New Course]\n", courses.size() + 1);
        int choice = readInt("Select course (1-" + (courses.size() + 1) + "): ");
        if (choice >= 1 && choice <= courses.size()) {
            return courses.get(choice - 1);
        } else {
            return inputCourseInfo();
        }
    }

    private static Generation selectOrCreateGeneration() {
        if (generations.isEmpty()) {
            System.out.println("No Generation found. Creating a new Generation...");
            return inputGenerationInfo();
        }
        System.out.println("Available Generations:");
        for (int i = 0; i < generations.size(); i++) {
            Generation g = generations.get(i);
            System.out.printf("  %d. %s (Academic Year: %s, Id: %d)\n", i + 1, g.getName(), g.getAcademicYear(), g.getId());
        }
        System.out.printf("  %d. [Create New Generation]\n", generations.size() + 1);
        int choice = readInt("Select generation (1-" + (generations.size() + 1) + "): ");
        if (choice >= 1 && choice <= generations.size()) {
            return generations.get(choice - 1);
        } else {
            return inputGenerationInfo();
        }
    }

    private static Student selectOrCreateStudent() {
        if (students.isEmpty()) {
            System.out.println("No Student found. Creating a new Student...");
            return inputStudentInfo();
        }
        System.out.println("Available Students:");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("  %d. %s (Id: %d)\n", i + 1, s.getStudentName(), s.getStudentId());
        }
        System.out.printf("  %d. [Create New Student]\n", students.size() + 1);
        int choice = readInt("Select student (1-" + (students.size() + 1) + "): ");
        if (choice >= 1 && choice <= students.size()) {
            return students.get(choice - 1);
        } else {
            return inputStudentInfo();
        }
    }

    private static SchoolClass selectOrCreateSchoolClass() {
        if (schoolClasses.isEmpty()) {
            System.out.println("No SchoolClass found. Creating a new SchoolClass...");
            return inputSchoolClassInfo();
        }
        System.out.println("Available Classes:");
        for (int i = 0; i < schoolClasses.size(); i++) {
            SchoolClass sc = schoolClasses.get(i);
            System.out.printf("  %d. %s (Room: %s, Id: %d)\n", i + 1, sc.getClassName(), sc.getRoom(), sc.getClassId());
        }
        System.out.printf("  %d. [Create New Class]\n", schoolClasses.size() + 1);
        int choice = readInt("Select class (1-" + (schoolClasses.size() + 1) + "): ");
        if (choice >= 1 && choice <= schoolClasses.size()) {
            return schoolClasses.get(choice - 1);
        } else {
            return inputSchoolClassInfo();
        }
    }

    // --- Displaying Cards ---
    private static void printDepartmentCard(Department dept) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| Department Id : %-30d |\n", dept.getId());
        System.out.printf("| Name          : %-30s |\n", dept.getName());
        System.out.printf("| Code          : %-30s |\n", dept.getCode());
        System.out.printf("| Location      : %-30s |\n", dept.getLocation());
        System.out.printf("| ManagerId     : %-30d |\n", dept.getManagerId());
        System.out.println("+------------------------------------------------+");
    }

    private static void printTeacherCard(Teacher teacher) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| TeacherId     : %-30d |\n", teacher.getTeacherId());
        System.out.printf("| TeacherName   : %-30s |\n", teacher.getTeacherName());
        System.out.printf("| Email         : %-30s |\n", teacher.getEmail());
        System.out.printf("| Phone         : %-30s |\n", teacher.getPhone());
        System.out.printf("| Salary        : $%-29.2f |\n", teacher.getSalary());
        System.out.printf("| Department    : %-30s |\n", (teacher.getDepartment() != null ? teacher.getDepartment().getName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printCourseCard(Course course) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| CourseId      : %-30d |\n", course.getCourseId());
        System.out.printf("| CourseName    : %-30s |\n", course.getCourseName());
        System.out.printf("| Description   : %-30s |\n", course.getDescription());
        System.out.printf("| Credit        : %-30d |\n", course.getCredit());
        System.out.printf("| Department    : %-30s |\n", (course.getDepartment() != null ? course.getDepartment().getName() : "None"));
        System.out.printf("| Teacher       : %-30s |\n", (course.getTeacher() != null ? course.getTeacher().getTeacherName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printGenerationCard(Generation gen) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| Generation Id : %-30d |\n", gen.getId());
        System.out.printf("| Name          : %-30s |\n", gen.getName());
        System.out.printf("| Period        : %-30s |\n", gen.getStartYear() + " - " + gen.getEndYear());
        System.out.printf("| Description   : %-30s |\n", gen.getDescription());
        System.out.printf("| StudentCount  : %-30d |\n", gen.getStudentCount());
        System.out.printf("| AcademicYear  : %-30s |\n", gen.getAcademicYear());
        System.out.println("+------------------------------------------------+");
    }

    private static void printStudentCard(Student student) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| StudentId     : %-30d |\n", student.getStudentId());
        System.out.printf("| StudentName   : %-30s |\n", student.getStudentName());
        System.out.printf("| Gender        : %-30s |\n", student.getGender());
        System.out.printf("| Age           : %-30d |\n", student.getAge());
        System.out.printf("| Phone         : %-30s |\n", student.getPhone());
        System.out.printf("| Email         : %-30s |\n", student.getEmail());
        System.out.printf("| Generation    : %-30s |\n", (student.getGeneration() != null ? student.getGeneration().getName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printSchoolClassCard(SchoolClass sc) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| ClassId       : %-30d |\n", sc.getClassId());
        System.out.printf("| ClassName     : %-30s |\n", sc.getClassName());
        System.out.printf("| Room          : %-30s |\n", sc.getRoom());
        System.out.printf("| Capacity      : %-30d |\n", sc.getCapacity());
        System.out.printf("| Course        : %-30s |\n", (sc.getCourse() != null ? sc.getCourse().getCourseName() : "None"));
        System.out.printf("| Teacher       : %-30s |\n", (sc.getTeacher() != null ? sc.getTeacher().getTeacherName() : "None"));
        System.out.printf("| Generation    : %-30s |\n", (sc.getGeneration() != null ? sc.getGeneration().getName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printLeaveCard(Leave leave) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| LeaveId       : %-30d |\n", leave.getLeaveId());
        System.out.printf("| Reason        : %-30s |\n", leave.getReason());
        System.out.printf("| Period        : %-30s |\n", leave.getStartDate() + " to " + leave.getEndDate());
        System.out.printf("| Status        : %-30s |\n", leave.getStatus());
        System.out.printf("| Student       : %-30s |\n", (leave.getStudent() != null ? leave.getStudent().getStudentName() : "None"));
        System.out.printf("| SchoolClass   : %-30s |\n", (leave.getSchoolClass() != null ? leave.getSchoolClass().getClassName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printTimetableCard(Timetable tt) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| TimetableId   : %-30d |\n", tt.getTimetableId());
        System.out.printf("| Day           : %-30s |\n", tt.getDay());
        System.out.printf("| Time          : %-30s |\n", tt.getStartTime() + " - " + tt.getEndTime());
        System.out.printf("| Room          : %-30s |\n", tt.getRoom());
        System.out.printf("| Course        : %-30s |\n", (tt.getCourse() != null ? tt.getCourse().getCourseName() : "None"));
        System.out.printf("| SchoolClass   : %-30s |\n", (tt.getSchoolClass() != null ? tt.getSchoolClass().getClassName() : "None"));
        System.out.println("+------------------------------------------------+");
    }

    // --- View All Connected Data ---
    private static void displayAllConnectedData() {
        System.out.println("\n==================================================");
        System.out.println("           ALL CONNECTED SYSTEM DATA             ");
        System.out.println("==================================================");

        System.out.println("\n[1] DEPARTMENTS (" + departments.size() + ")");
        for (Department d : departments) printDepartmentCard(d);

        System.out.println("\n[2] TEACHERS (" + teachers.size() + ")");
        for (Teacher t : teachers) printTeacherCard(t);

        System.out.println("\n[3] COURSES (" + courses.size() + ")");
        for (Course c : courses) printCourseCard(c);

        System.out.println("\n[4] GENERATIONS (" + generations.size() + ")");
        for (Generation g : generations) printGenerationCard(g);

        System.out.println("\n[5] STUDENTS (" + students.size() + ")");
        for (Student s : students) printStudentCard(s);

        System.out.println("\n[6] SCHOOL CLASSES (" + schoolClasses.size() + ")");
        for (SchoolClass sc : schoolClasses) printSchoolClassCard(sc);

        System.out.println("\n[7] LEAVE REQUESTS (" + leaves.size() + ")");
        for (Leave l : leaves) printLeaveCard(l);

        System.out.println("\n[8] TIMETABLES (" + timetables.size() + ")");
        for (Timetable tt : timetables) printTimetableCard(tt);
    }

    // --- Sample Data Pre-population ---
    private static void addSampleData() {
        Department deptCS = new Department(1, "Computer Science", "CS", "Building A", 50000, 101, 1, "2026-01-01");
        departments.add(deptCS);

        Teacher sampleTeacher = new Teacher(101, "Dr. Alice Smith", "alice.smith@school.edu", "+1234567890", 4500.0, deptCS);
        teachers.add(sampleTeacher);

        Course sampleCourse = new Course(301, "Java Programming", "OOP and Data Structures", 4, deptCS, sampleTeacher);
        courses.add(sampleCourse);

        Generation sampleGen = new Generation(1, "Generation 8", 2024, 2028, "Software Engineering Track", 120, "2026-2027");
        generations.add(sampleGen);

        Student sampleStudent = new Student(201, "John Doe", "Male", 20, "+0987654321", "john.doe@student.edu", sampleGen);
        students.add(sampleStudent);

        SchoolClass sampleClass = new SchoolClass(501, "Class CS-8A", "Lab 204", 30, sampleCourse, sampleTeacher, sampleGen);
        schoolClasses.add(sampleClass);

        Leave sampleLeave = new Leave(701, "Medical Leave", "2026-10-01", "2026-10-03", "Approved", sampleStudent, sampleClass);
        leaves.add(sampleLeave);

        Timetable sampleTimetable = new Timetable(801, "Monday", "08:00 AM", "11:00 AM", "Lab 204", sampleCourse, sampleClass);
        timetables.add(sampleTimetable);

        System.out.println("\n[INFO] Sample data populated for all 8 diagram classes successfully!");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String val = scanner.nextLine().trim();
                return Integer.parseInt(val);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer format. Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String val = scanner.nextLine().trim();
                return Double.parseDouble(val);
            } catch (NumberFormatException e) {
                System.out.println("Invalid decimal format. Please enter a valid number.");
            }
        }
    }
}