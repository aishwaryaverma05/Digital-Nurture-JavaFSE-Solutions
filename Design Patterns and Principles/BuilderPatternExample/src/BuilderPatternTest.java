public class BuilderPatternTest {
    public static void main(String[] args) {

        Computer gamingComputer = new Computer.Builder()
                .setCpu("Intel i7")
                .setRam("16GB")
                .setStorage("1TB SSD")
                .build();

        Computer officeComputer = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("8GB")
                .setStorage("512GB SSD")
                .build();

        System.out.println("Gaming Computer Configuration:");
        gamingComputer.displayConfiguration();

        System.out.println();

        System.out.println("Office Computer Configuration:");
        officeComputer.displayConfiguration();
    }
}