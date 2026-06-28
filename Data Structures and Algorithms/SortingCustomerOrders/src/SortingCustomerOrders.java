public class SortingCustomerOrders {

    public static void bubbleSort(Order[] orders) {
        for (int i = 0; i < orders.length - 1; i++) {
            for (int j = 0; j < orders.length - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(orders, low, high);
            quickSort(orders, low, partitionIndex - 1);
            quickSort(orders, partitionIndex + 1, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void displayOrders(Order[] orders) {
        for (Order order : orders) {
            order.displayOrder();
        }
    }

    public static void main(String[] args) {

        Order[] bubbleOrders = {
                new Order(101, "Aishwarya", 2500),
                new Order(102, "Rahul", 1200),
                new Order(103, "Priya", 4500),
                new Order(104, "Aman", 800)
        };

        Order[] quickOrders = {
                new Order(101, "Aishwarya", 2500),
                new Order(102, "Rahul", 1200),
                new Order(103, "Priya", 4500),
                new Order(104, "Aman", 800)
        };

        System.out.println("Orders sorted using Bubble Sort:");
        bubbleSort(bubbleOrders);
        displayOrders(bubbleOrders);

        System.out.println();

        System.out.println("Orders sorted using Quick Sort:");
        quickSort(quickOrders, 0, quickOrders.length - 1);
        displayOrders(quickOrders);
    }
}