import static Control.RunningApp.Run;


public class Program {
    public static void main(String[] args) {
        try {
            Run();
        } catch (Exception e) {
            System.out.println("Программа завершилась с ошибкой, описание ниже: ");
            System.out.println(e.getMessage());
        }
    }
}
