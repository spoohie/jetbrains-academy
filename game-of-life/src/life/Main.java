package life;

public class Main {
    public static void main(String[] args) {
        UniverseView universeView = new UniverseView();
        Universe universeModel = new Universe();
        UniverseController universeController = new UniverseController(universeModel, universeView);

        universeController.start();
    }
}