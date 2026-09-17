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
            System.out.println("\nSelect a Feature to Manage:");
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
                case "1": manageDepartmentMenu(); break;
                case "2": manageTeacherMenu(); break;
                case "3": manageCourseMenu(); break;
                case "4": manageGenerationMenu(); break;
                case "5": manageStudentMenu(); break;
                case "6": manageSchoolClassMenu(); break;
                case "7": manageLeaveMenu(); break;
                case "8": manageTimetableMenu(); break;
                case "9": displayAllConnectedData(); break;
                case "10": addSampleData(); break;
                case "11":
                    running = false;
                    System.out.println("\nExiting School Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 11.");
            }
        }
    }

    // ===================================================================
    // Shared helpers
    // ===================================================================

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

    /** Reads a String, keeping the current value if the user just presses Enter. */
    private static String readStringOrKeep(String label, String currentValue) {
        System.out.print(label + " [current: " + currentValue + "] (Enter to keep): ");
        String val = scanner.nextLine().trim();
        return val.isEmpty() ? currentValue : val;
    }

    /** Reads an int, keeping the current value if the user just presses Enter or types invalid input. */
    private static int readIntOrKeep(String label, int currentValue) {
        System.out.print(label + " [current: " + currentValue + "] (Enter to keep): ");
        String val = scanner.nextLine().trim();
        if (val.isEmpty()) return currentValue;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, keeping current value.");
            return currentValue;
        }
    }

    /** Reads a double, keeping the current value if the user just presses Enter or types invalid input. */
    private static double readDoubleOrKeep(String label, double currentValue) {
        System.out.print(label + " [current: " + currentValue + "] (Enter to keep): ");
        String val = scanner.nextLine().trim();
        if (val.isEmpty()) return currentValue;
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, keeping current value.");
            return currentValue;
        }
    }

    /** Asks a yes/no question. Returns true only for "y" or "yes" (case-insensitive). */
    private static boolean askYesNo(String prompt) {
        System.out.print(prompt + " (y/n): ");
        String val = scanner.nextLine().trim().toLowerCase();
        return val.equals("y") || val.equals("yes");
    }

    /** Formats a related object as "Name (ID: x)" so relations always show both. */
    private static String formatRelation(String name, int id) {
        return name + " (ID: " + id + ")";
    }

    private static boolean confirmDelete(String entityLabel) {
        return askYesNo("Are you sure you want to delete this " + entityLabel + "?");
    }

    // ===================================================================
    // 1. DEPARTMENT
    // ===================================================================

    private static void manageDepartmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- DEPARTMENT MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllDepartments(); break;
                case 2: inputDepartmentInfo(); break;
                case 3: updateDepartment(); break;
                case 4: deleteDepartment(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Department inputDepartmentInfo() {
        System.out.println("\n--- ADD DEPARTMENT ---");
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

    private static void viewAllDepartments() {
        System.out.println("\n--- ALL DEPARTMENTS (" + departments.size() + ") ---");
        if (departments.isEmpty()) {
            System.out.println("No departments yet.");
            return;
        }
        for (int i = 0; i < departments.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printDepartmentCard(departments.get(i));
        }
    }

    private static int findDepartmentIndexById(int id) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId() == id) return i;
        }
        return -1;
    }

    private static void updateDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No departments to update.");
            return;
        }
        viewAllDepartments();
        int id = readInt("\nEnter the Department Id to update: ");
        int index = findDepartmentIndexById(id);
        if (index == -1) {
            System.out.println("Department with Id " + id + " not found.");
            return;
        }
        Department dept = departments.get(index);
        dept.setName(readStringOrKeep("Name", dept.getName()));
        dept.setCode(readStringOrKeep("Code", dept.getCode()));
        dept.setLocation(readStringOrKeep("Location", dept.getLocation()));
        dept.setManagerId(readIntOrKeep("ManagerId", dept.getManagerId()));
        System.out.println("\n[SUCCESS] Department updated successfully!");
        printDepartmentCard(dept);
    }

    private static void deleteDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No departments to delete.");
            return;
        }
        viewAllDepartments();
        int id = readInt("\nEnter the Department Id to delete: ");
        int index = findDepartmentIndexById(id);
        if (index == -1) {
            System.out.println("Department with Id " + id + " not found.");
            return;
        }
        printDepartmentCard(departments.get(index));
        if (confirmDelete("Department")) {
            departments.remove(index);
            System.out.println("[SUCCESS] Department deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 2. TEACHER
    // ===================================================================

    private static void manageTeacherMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- TEACHER MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllTeachers(); break;
                case 2: inputTeacherInfo(); break;
                case 3: updateTeacher(); break;
                case 4: deleteTeacher(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Teacher inputTeacherInfo() {
        System.out.println("\n--- ADD TEACHER ---");
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

    private static void viewAllTeachers() {
        System.out.println("\n--- ALL TEACHERS (" + teachers.size() + ") ---");
        if (teachers.isEmpty()) {
            System.out.println("No teachers yet.");
            return;
        }
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printTeacherCard(teachers.get(i));
        }
    }

    private static int findTeacherIndexById(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getTeacherId() == id) return i;
        }
        return -1;
    }

    private static void updateTeacher() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers to update.");
            return;
        }
        viewAllTeachers();
        int id = readInt("\nEnter the TeacherId to update: ");
        int index = findTeacherIndexById(id);
        if (index == -1) {
            System.out.println("Teacher with Id " + id + " not found.");
            return;
        }
        Teacher teacher = teachers.get(index);
        teacher.setTeacherName(readStringOrKeep("TeacherName", teacher.getTeacherName()));
        teacher.setEmail(readStringOrKeep("Email", teacher.getEmail()));
        teacher.setPhone(readStringOrKeep("Phone", teacher.getPhone()));
        teacher.setSalary(readDoubleOrKeep("Salary", teacher.getSalary()));
        if (askYesNo("Change Department?")) {
            teacher.setDepartment(selectOrCreateDepartment());
        }
        System.out.println("\n[SUCCESS] Teacher updated successfully!");
        printTeacherCard(teacher);
    }

    private static void deleteTeacher() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers to delete.");
            return;
        }
        viewAllTeachers();
        int id = readInt("\nEnter the TeacherId to delete: ");
        int index = findTeacherIndexById(id);
        if (index == -1) {
            System.out.println("Teacher with Id " + id + " not found.");
            return;
        }
        printTeacherCard(teachers.get(index));
        if (confirmDelete("Teacher")) {
            teachers.remove(index);
            System.out.println("[SUCCESS] Teacher deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 3. COURSE
    // ===================================================================

    private static void manageCourseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- COURSE MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllCourses(); break;
                case 2: inputCourseInfo(); break;
                case 3: updateCourse(); break;
                case 4: deleteCourse(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Course inputCourseInfo() {
        System.out.println("\n--- ADD COURSE ---");
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

    private static void viewAllCourses() {
        System.out.println("\n--- ALL COURSES (" + courses.size() + ") ---");
        if (courses.isEmpty()) {
            System.out.println("No courses yet.");
            return;
        }
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printCourseCard(courses.get(i));
        }
    }

    private static int findCourseIndexById(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId() == id) return i;
        }
        return -1;
    }

    private static void updateCourse() {
        if (courses.isEmpty()) {
            System.out.println("No courses to update.");
            return;
        }
        viewAllCourses();
        int id = readInt("\nEnter the CourseId to update: ");
        int index = findCourseIndexById(id);
        if (index == -1) {
            System.out.println("Course with Id " + id + " not found.");
            return;
        }
        Course course = courses.get(index);
        course.setCourseName(readStringOrKeep("CourseName", course.getCourseName()));
        course.setDescription(readStringOrKeep("Description", course.getDescription()));
        course.setCredit(readIntOrKeep("Credit", course.getCredit()));
        if (askYesNo("Change Department?")) {
            course.setDepartment(selectOrCreateDepartment());
        }
        if (askYesNo("Change Teacher?")) {
            course.setTeacher(selectOrCreateTeacher());
        }
        System.out.println("\n[SUCCESS] Course updated successfully!");
        printCourseCard(course);
    }

    private static void deleteCourse() {
        if (courses.isEmpty()) {
            System.out.println("No courses to delete.");
            return;
        }
        viewAllCourses();
        int id = readInt("\nEnter the CourseId to delete: ");
        int index = findCourseIndexById(id);
        if (index == -1) {
            System.out.println("Course with Id " + id + " not found.");
            return;
        }
        printCourseCard(courses.get(index));
        if (confirmDelete("Course")) {
            courses.remove(index);
            System.out.println("[SUCCESS] Course deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 4. GENERATION
    // ===================================================================

    private static void manageGenerationMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GENERATION MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllGenerations(); break;
                case 2: inputGenerationInfo(); break;
                case 3: updateGeneration(); break;
                case 4: deleteGeneration(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Generation inputGenerationInfo() {
        System.out.println("\n--- ADD GENERATION ---");
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

    private static void viewAllGenerations() {
        System.out.println("\n--- ALL GENERATIONS (" + generations.size() + ") ---");
        if (generations.isEmpty()) {
            System.out.println("No generations yet.");
            return;
        }
        for (int i = 0; i < generations.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printGenerationCard(generations.get(i));
        }
    }

    private static int findGenerationIndexById(int id) {
        for (int i = 0; i < generations.size(); i++) {
            if (generations.get(i).getId() == id) return i;
        }
        return -1;
    }

    private static void updateGeneration() {
        if (generations.isEmpty()) {
            System.out.println("No generations to update.");
            return;
        }
        viewAllGenerations();
        int id = readInt("\nEnter the Generation Id to update: ");
        int index = findGenerationIndexById(id);
        if (index == -1) {
            System.out.println("Generation with Id " + id + " not found.");
            return;
        }
        Generation gen = generations.get(index);
        gen.setName(readStringOrKeep("Name", gen.getName()));
        gen.setStartYear(readIntOrKeep("Start Year", gen.getStartYear()));
        gen.setEndYear(readIntOrKeep("End Year", gen.getEndYear()));
        gen.setDescription(readStringOrKeep("Description", gen.getDescription()));
        gen.setStudentCount(readIntOrKeep("Student Count", gen.getStudentCount()));
        gen.setAcademicYear(readStringOrKeep("Academic Year", gen.getAcademicYear()));
        System.out.println("\n[SUCCESS] Generation updated successfully!");
        printGenerationCard(gen);
    }

    private static void deleteGeneration() {
        if (generations.isEmpty()) {
            System.out.println("No generations to delete.");
            return;
        }
        viewAllGenerations();
        int id = readInt("\nEnter the Generation Id to delete: ");
        int index = findGenerationIndexById(id);
        if (index == -1) {
            System.out.println("Generation with Id " + id + " not found.");
            return;
        }
        printGenerationCard(generations.get(index));
        if (confirmDelete("Generation")) {
            generations.remove(index);
            System.out.println("[SUCCESS] Generation deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 5. STUDENT
    // ===================================================================

    private static void manageStudentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllStudents(); break;
                case 2: inputStudentInfo(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Student inputStudentInfo() {
        System.out.println("\n--- ADD STUDENT ---");
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

    private static void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS (" + students.size() + ") ---");
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printStudentCard(students.get(i));
        }
    }

    private static int findStudentIndexById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == id) return i;
        }
        return -1;
    }

    private static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to update.");
            return;
        }
        viewAllStudents();
        int id = readInt("\nEnter the StudentId to update: ");
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Student with Id " + id + " not found.");
            return;
        }
        Student student = students.get(index);
        student.setStudentName(readStringOrKeep("StudentName", student.getStudentName()));
        student.setGender(readStringOrKeep("Gender", student.getGender()));
        student.setAge(readIntOrKeep("Age", student.getAge()));
        student.setPhone(readStringOrKeep("Phone", student.getPhone()));
        student.setEmail(readStringOrKeep("Email", student.getEmail()));
        if (askYesNo("Change Generation?")) {
            student.setGeneration(selectOrCreateGeneration());
        }
        System.out.println("\n[SUCCESS] Student updated successfully!");
        printStudentCard(student);
    }

    private static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to delete.");
            return;
        }
        viewAllStudents();
        int id = readInt("\nEnter the StudentId to delete: ");
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Student with Id " + id + " not found.");
            return;
        }
        printStudentCard(students.get(index));
        if (confirmDelete("Student")) {
            students.remove(index);
            System.out.println("[SUCCESS] Student deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 6. SCHOOLCLASS
    // ===================================================================

    private static void manageSchoolClassMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- SCHOOLCLASS MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllSchoolClasses(); break;
                case 2: inputSchoolClassInfo(); break;
                case 3: updateSchoolClass(); break;
                case 4: deleteSchoolClass(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static SchoolClass inputSchoolClassInfo() {
        System.out.println("\n--- ADD SCHOOLCLASS ---");
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

    private static void viewAllSchoolClasses() {
        System.out.println("\n--- ALL SCHOOL CLASSES (" + schoolClasses.size() + ") ---");
        if (schoolClasses.isEmpty()) {
            System.out.println("No school classes yet.");
            return;
        }
        for (int i = 0; i < schoolClasses.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printSchoolClassCard(schoolClasses.get(i));
        }
    }

    private static int findSchoolClassIndexById(int id) {
        for (int i = 0; i < schoolClasses.size(); i++) {
            if (schoolClasses.get(i).getClassId() == id) return i;
        }
        return -1;
    }

    private static void updateSchoolClass() {
        if (schoolClasses.isEmpty()) {
            System.out.println("No school classes to update.");
            return;
        }
        viewAllSchoolClasses();
        int id = readInt("\nEnter the ClassId to update: ");
        int index = findSchoolClassIndexById(id);
        if (index == -1) {
            System.out.println("SchoolClass with Id " + id + " not found.");
            return;
        }
        SchoolClass sc = schoolClasses.get(index);
        sc.setClassName(readStringOrKeep("ClassName", sc.getClassName()));
        sc.setRoom(readStringOrKeep("Room", sc.getRoom()));
        sc.setCapacity(readIntOrKeep("Capacity", sc.getCapacity()));
        if (askYesNo("Change Course?")) {
            sc.setCourse(selectOrCreateCourse());
        }
        if (askYesNo("Change Teacher?")) {
            sc.setTeacher(selectOrCreateTeacher());
        }
        if (askYesNo("Change Generation?")) {
            sc.setGeneration(selectOrCreateGeneration());
        }
        System.out.println("\n[SUCCESS] SchoolClass updated successfully!");
        printSchoolClassCard(sc);
    }

    private static void deleteSchoolClass() {
        if (schoolClasses.isEmpty()) {
            System.out.println("No school classes to delete.");
            return;
        }
        viewAllSchoolClasses();
        int id = readInt("\nEnter the ClassId to delete: ");
        int index = findSchoolClassIndexById(id);
        if (index == -1) {
            System.out.println("SchoolClass with Id " + id + " not found.");
            return;
        }
        printSchoolClassCard(schoolClasses.get(index));
        if (confirmDelete("SchoolClass")) {
            schoolClasses.remove(index);
            System.out.println("[SUCCESS] SchoolClass deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 7. LEAVE
    // ===================================================================

    private static void manageLeaveMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- LEAVE MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllLeaves(); break;
                case 2: inputLeaveInfo(); break;
                case 3: updateLeave(); break;
                case 4: deleteLeave(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Leave inputLeaveInfo() {
        System.out.println("\n--- ADD LEAVE ---");
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

        System.out.println("\nWho is requesting this leave?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        int requesterChoice = readInt("Enter choice (1-2): ");

        Leave leave;
        if (requesterChoice == 2) {
            System.out.println("\n-> Select Teacher requesting leave:");
            Teacher teacher = selectOrCreateTeacher();
            leave = new Leave(leaveId, reason, startDate, endDate, status, teacher);
        } else {
            System.out.println("\n-> Select Student requesting leave:");
            Student student = selectOrCreateStudent();

            System.out.println("\n-> Select SchoolClass for Leave (optional context):");
            SchoolClass sClass = selectOrCreateSchoolClass();

            leave = new Leave(leaveId, reason, startDate, endDate, status, student, sClass);
        }

        leaves.add(leave);
        System.out.println("\n[SUCCESS] Leave request added successfully!");
        printLeaveCard(leave);
        return leave;
    }

    private static void viewAllLeaves() {
        System.out.println("\n--- ALL LEAVE REQUESTS (" + leaves.size() + ") ---");
        if (leaves.isEmpty()) {
            System.out.println("No leave requests yet.");
            return;
        }
        for (int i = 0; i < leaves.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printLeaveCard(leaves.get(i));
        }
    }

    private static int findLeaveIndexById(int id) {
        for (int i = 0; i < leaves.size(); i++) {
            if (leaves.get(i).getLeaveId() == id) return i;
        }
        return -1;
    }

    private static void updateLeave() {
        if (leaves.isEmpty()) {
            System.out.println("No leave requests to update.");
            return;
        }
        viewAllLeaves();
        int id = readInt("\nEnter the LeaveId to update: ");
        int index = findLeaveIndexById(id);
        if (index == -1) {
            System.out.println("Leave with Id " + id + " not found.");
            return;
        }
        Leave leave = leaves.get(index);
        leave.setReason(readStringOrKeep("Reason", leave.getReason()));
        leave.setStartDate(readStringOrKeep("StartDate", leave.getStartDate()));
        leave.setEndDate(readStringOrKeep("EndDate", leave.getEndDate()));
        leave.setStatus(readStringOrKeep("Status", leave.getStatus()));

        System.out.println("Current requester: " + leave.getRequesterType() + " - " + leave.getRequesterName());
        if (askYesNo("Change requester entirely (switch between Student/Teacher)?")) {
            System.out.println("Who is the new requester?");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            int requesterChoice = readInt("Enter choice (1-2): ");
            if (requesterChoice == 2) {
                leave.setTeacher(selectOrCreateTeacher());
                leave.setSchoolClass(null);
            } else {
                leave.setStudent(selectOrCreateStudent());
                if (askYesNo("Set a SchoolClass for this leave?")) {
                    leave.setSchoolClass(selectOrCreateSchoolClass());
                }
            }
        } else if (leave.isTeacherLeave() && askYesNo("Change Teacher?")) {
            leave.setTeacher(selectOrCreateTeacher());
        } else if (leave.isStudentLeave() && askYesNo("Change Student?")) {
            leave.setStudent(selectOrCreateStudent());
        }

        if (leave.isStudentLeave() && askYesNo("Change SchoolClass?")) {
            leave.setSchoolClass(selectOrCreateSchoolClass());
        }

        System.out.println("\n[SUCCESS] Leave updated successfully!");
        printLeaveCard(leave);
    }

    private static void deleteLeave() {
        if (leaves.isEmpty()) {
            System.out.println("No leave requests to delete.");
            return;
        }
        viewAllLeaves();
        int id = readInt("\nEnter the LeaveId to delete: ");
        int index = findLeaveIndexById(id);
        if (index == -1) {
            System.out.println("Leave with Id " + id + " not found.");
            return;
        }
        Leave leave = leaves.get(index);
        printLeaveCard(leave);

        if ("Approved".equalsIgnoreCase(leave.getStatus())) {
            System.out.println("[BLOCKED] This leave is already Approved and cannot be deleted.");
            System.out.println("Only Pending or Rejected leave requests can be deleted.");
            return;
        }

        if (confirmDelete("Leave request")) {
            leaves.remove(index);
            System.out.println("[SUCCESS] Leave deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // 8. TIMETABLE
    // ===================================================================

    private static void manageTimetableMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- TIMETABLE MENU ---");
            System.out.println("1. View All");
            System.out.println("2. Add New");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to Main Menu");
            int choice = readInt("Enter choice (1-5): ");
            switch (choice) {
                case 1: viewAllTimetables(); break;
                case 2: inputTimetableInfo(); break;
                case 3: updateTimetable(); break;
                case 4: deleteTimetable(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static Timetable inputTimetableInfo() {
        System.out.println("\n--- ADD TIMETABLE ---");
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

    private static void viewAllTimetables() {
        System.out.println("\n--- ALL TIMETABLES (" + timetables.size() + ") ---");
        if (timetables.isEmpty()) {
            System.out.println("No timetable entries yet.");
            return;
        }
        for (int i = 0; i < timetables.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printTimetableCard(timetables.get(i));
        }
    }

    private static int findTimetableIndexById(int id) {
        for (int i = 0; i < timetables.size(); i++) {
            if (timetables.get(i).getTimetableId() == id) return i;
        }
        return -1;
    }

    private static void updateTimetable() {
        if (timetables.isEmpty()) {
            System.out.println("No timetable entries to update.");
            return;
        }
        viewAllTimetables();
        int id = readInt("\nEnter the TimetableId to update: ");
        int index = findTimetableIndexById(id);
        if (index == -1) {
            System.out.println("Timetable with Id " + id + " not found.");
            return;
        }
        Timetable tt = timetables.get(index);
        tt.setDay(readStringOrKeep("Day", tt.getDay()));
        tt.setStartTime(readStringOrKeep("StartTime", tt.getStartTime()));
        tt.setEndTime(readStringOrKeep("EndTime", tt.getEndTime()));
        tt.setRoom(readStringOrKeep("Room", tt.getRoom()));
        if (askYesNo("Change Course?")) {
            tt.setCourse(selectOrCreateCourse());
        }
        if (askYesNo("Change SchoolClass?")) {
            tt.setSchoolClass(selectOrCreateSchoolClass());
        }
        System.out.println("\n[SUCCESS] Timetable updated successfully!");
        printTimetableCard(tt);
    }

    private static void deleteTimetable() {
        if (timetables.isEmpty()) {
            System.out.println("No timetable entries to delete.");
            return;
        }
        viewAllTimetables();
        int id = readInt("\nEnter the TimetableId to delete: ");
        int index = findTimetableIndexById(id);
        if (index == -1) {
            System.out.println("Timetable with Id " + id + " not found.");
            return;
        }
        printTimetableCard(timetables.get(index));
        if (confirmDelete("Timetable entry")) {
            timetables.remove(index);
            System.out.println("[SUCCESS] Timetable deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ===================================================================
    // Helpers for selecting or creating parent entities (used by Add/Update)
    // ===================================================================

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

    // ===================================================================
    // Displaying Cards — relations now show "Name (ID: x)"
    // ===================================================================

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
        System.out.printf("| Department    : %-30s |\n",
                (teacher.getDepartment() != null
                        ? formatRelation(teacher.getDepartment().getName(), teacher.getDepartment().getId())
                        : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printCourseCard(Course course) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| CourseId      : %-30d |\n", course.getCourseId());
        System.out.printf("| CourseName    : %-30s |\n", course.getCourseName());
        System.out.printf("| Description   : %-30s |\n", course.getDescription());
        System.out.printf("| Credit        : %-30d |\n", course.getCredit());
        System.out.printf("| Department    : %-30s |\n",
                (course.getDepartment() != null
                        ? formatRelation(course.getDepartment().getName(), course.getDepartment().getId())
                        : "None"));
        System.out.printf("| Teacher       : %-30s |\n",
                (course.getTeacher() != null
                        ? formatRelation(course.getTeacher().getTeacherName(), course.getTeacher().getTeacherId())
                        : "None"));
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
        System.out.printf("| Generation    : %-30s |\n",
                (student.getGeneration() != null
                        ? formatRelation(student.getGeneration().getName(), student.getGeneration().getId())
                        : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printSchoolClassCard(SchoolClass sc) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| ClassId       : %-30d |\n", sc.getClassId());
        System.out.printf("| ClassName     : %-30s |\n", sc.getClassName());
        System.out.printf("| Room          : %-30s |\n", sc.getRoom());
        System.out.printf("| Capacity      : %-30d |\n", sc.getCapacity());
        System.out.printf("| Course        : %-30s |\n",
                (sc.getCourse() != null
                        ? formatRelation(sc.getCourse().getCourseName(), sc.getCourse().getCourseId())
                        : "None"));
        System.out.printf("| Teacher       : %-30s |\n",
                (sc.getTeacher() != null
                        ? formatRelation(sc.getTeacher().getTeacherName(), sc.getTeacher().getTeacherId())
                        : "None"));
        System.out.printf("| Generation    : %-30s |\n",
                (sc.getGeneration() != null
                        ? formatRelation(sc.getGeneration().getName(), sc.getGeneration().getId())
                        : "None"));
        System.out.println("+------------------------------------------------+");
    }

    private static void printLeaveCard(Leave leave) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| LeaveId       : %-30d |\n", leave.getLeaveId());
        System.out.printf("| Reason        : %-30s |\n", leave.getReason());
        System.out.printf("| Period        : %-30s |\n", leave.getStartDate() + " to " + leave.getEndDate());
        System.out.printf("| Status        : %-30s |\n", leave.getStatus());
        System.out.printf("| Requester Type: %-30s |\n", leave.getRequesterType());
        if (leave.isTeacherLeave()) {
            System.out.printf("| Teacher       : %-30s |\n",
                    formatRelation(leave.getTeacher().getTeacherName(), leave.getTeacher().getTeacherId()));
        } else if (leave.isStudentLeave()) {
            System.out.printf("| Student       : %-30s |\n",
                    formatRelation(leave.getStudent().getStudentName(), leave.getStudent().getStudentId()));
            System.out.printf("| SchoolClass   : %-30s |\n",
                    (leave.getSchoolClass() != null
                            ? formatRelation(leave.getSchoolClass().getClassName(), leave.getSchoolClass().getClassId())
                            : "None"));
        } else {
            System.out.printf("| Requester     : %-30s |\n", "None");
        }
        System.out.println("+------------------------------------------------+");
    }

    private static void printTimetableCard(Timetable tt) {
        System.out.println("+------------------------------------------------+");
        System.out.printf("| TimetableId   : %-30d |\n", tt.getTimetableId());
        System.out.printf("| Day           : %-30s |\n", tt.getDay());
        System.out.printf("| Time          : %-30s |\n", tt.getStartTime() + " - " + tt.getEndTime());
        System.out.printf("| Room          : %-30s |\n", tt.getRoom());
        System.out.printf("| Course        : %-30s |\n",
                (tt.getCourse() != null
                        ? formatRelation(tt.getCourse().getCourseName(), tt.getCourse().getCourseId())
                        : "None"));
        System.out.printf("| SchoolClass   : %-30s |\n",
                (tt.getSchoolClass() != null
                        ? formatRelation(tt.getSchoolClass().getClassName(), tt.getSchoolClass().getClassId())
                        : "None"));
        System.out.println("+------------------------------------------------+");
    }

    // ===================================================================
    // View All Connected Data / Sample Data
    // ===================================================================

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
}