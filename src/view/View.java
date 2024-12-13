package view;

import model.AbstractAnimal;

public interface View {
    /*
    Основное меню
    */
    enum MainMenuCommand {
        ADD_ANIMAL ("Добавить животное"),
        SHOW_SKILLS("Смотреть команды"),
        REMOVE_ANIMAL ("Удалить животное"),
        EXIT ("Выйти");

        private String tag;

        MainMenuCommand(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    /*
    Меню обучения командам
    */
    enum AddSkillMenuCommand {
        ADD_SKILL ("Обучить команде"),
        EXIT ("Выйти");

        private String tag;

        AddSkillMenuCommand(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    /*
    Список всех животных в питомнике
    */
    void showNurseryRegistry();

    /*
    Основное меню приложения
    @return - возвращает выбранную команду
    */
    MainMenuCommand showMainMenuWithResult();

    /*
    Диалог добавления животного
    @return возврат true при успешном добавлении, иначе возврат false
    */
    boolean showAddAnimalDialog();

    /*
    Диалог выписки животного из питомника
    @return возврат id выписанного животного или -1 при ошибке
    */
    int showRemoveAnimalDialog();

    /*
    Диалог для представления информации о животном
    */
    void showDetailInfoAnimalDialog();

    /*
    Информация о животном
    */
    void showAnimalInfo(AbstractAnimal animal);

    /*
    Меню добавления команды
    @return возврат выбранной команды
    */
    AddSkillMenuCommand showAddSkillMenu(AbstractAnimal animal);

    /*
    Диалог добавления команды
    @return возврат true в при успешном добавлении, иначе возврат - false
    */
    boolean showAddSkillDialog(AbstractAnimal animal);
}