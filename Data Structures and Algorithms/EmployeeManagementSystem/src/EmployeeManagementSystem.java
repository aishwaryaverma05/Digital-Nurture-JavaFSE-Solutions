public class EmployeeManagementSystem {

    static Employee[] employees = new Employee[10];
    static int count = 0;

    public static void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Employee array is full.");
        }
    }

    public static void searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                System.out.println("Employee found:");
                employees[i].displayEmployee();
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            employees[i].displayEmployee();
        }
    }

    public static void deleteEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null;
                count--;
                System.out.println("Employee deleted successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {

        addEmployee(new Employee(101, "Aishwarya", "Developer", 50000));
        addEmployee(new Employee(102, "Rahul", "Tester", 40000));
        addEmployee(new Employee(103, "Priya", "Manager", 70000));

        System.out.println("\nAll Employees:");
        traverseEmployees();

        System.out.println("\nSearch Employee:");
        searchEmployee(102);

        System.out.println("\nDelete Employee:");
        deleteEmployee(101);

        System.out.println("\nEmployees After Deletion:");
        traverseEmployees();
    }
}