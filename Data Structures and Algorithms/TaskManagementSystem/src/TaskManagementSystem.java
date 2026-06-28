public class TaskManagementSystem {

    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("Task added successfully.");
    }

    public void searchTask(int taskId) {
        TaskNode current = head;

        while (current != null) {
            if (current.task.taskId == taskId) {
                System.out.println("Task found:");
                current.task.displayTask();
                return;
            }

            current = current.next;
        }

        System.out.println("Task not found.");
    }

    public void traverseTasks() {
        TaskNode current = head;

        while (current != null) {
            current.task.displayTask();
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.task.taskId == taskId) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.task.taskId == taskId) {
                current.next = current.next.next;
                System.out.println("Task deleted successfully.");
                return;
            }

            current = current.next;
        }

        System.out.println("Task not found.");
    }

    public static void main(String[] args) {

        TaskManagementSystem system = new TaskManagementSystem();

        system.addTask(new Task(101, "Complete Java Assignment", "Pending"));
        system.addTask(new Task(102, "Submit Report", "In Progress"));
        system.addTask(new Task(103, "Prepare Presentation", "Completed"));

        System.out.println("\nAll Tasks:");
        system.traverseTasks();

        System.out.println("\nSearch Task:");
        system.searchTask(102);

        System.out.println("\nDelete Task:");
        system.deleteTask(101);

        System.out.println("\nTasks After Deletion:");
        system.traverseTasks();
    }
}