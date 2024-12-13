
import controller.NurseryAccounting;
import storage.NurseryStorage;
import view.ConsoleView;
import view.View;

public class App {
    /*
    Основной метод приложения - обработка основных команд
    (добавить животное, показать команды животного, удалить/выписать животное)
    */
    public static void run() {
        NurseryAccounting nurseryAccounting = new NurseryAccounting(new NurseryStorage());
        View view = new ConsoleView(nurseryAccounting);

        while (true) {
            view.showNurseryRegistry();
            View.MainMenuCommand code = view.showMainMenuWithResult();
            switch (code) {
                case ADD_ANIMAL -> {
                    boolean result = view.showAddAnimalDialog();
                    String resMessage = result ? "Животное добавлено в реестр" : "Не удалось добавить животное в реестр";
                    System.out.println(resMessage);
                }
                case SHOW_SKILLS -> {
                    view.showDetailInfoAnimalDialog();
                }
                case REMOVE_ANIMAL -> {
                    int removeId = view.showRemoveAnimalDialog();
                    String resMessage = removeId > -1  ? "Выписано животное" + removeId : "Не удалось  выписать животное";
                    System.out.println(resMessage);
                }
                case EXIT -> {
                    System.out.println("Спасибо за работу, до встречи... )");
                    return;
                }
            }
        }
    }
}