package view;

import controller.NurseryAccounting;
import model.AbstractAnimal;
import model.AnimalGenius;
import model.Skill;
import util.Counter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {
    public static final int SIZE_LINE = 80;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ROW_FORMAT = "%-6d%-16s%-20s%-20d%-20s";
    public static final String COLUMN_HEADER_FORMAT = "%-6s%-16s%-20s%-20s%-20s";
    public static final String RED_COLOR = "\u001B[31m";
    public static final String GREEN_COLOR = "\u001B[32m";
    public static final String YELLOW_COLOR = "\u001B[33m";
    private final NurseryAccounting nurseryAccounting;
    private Scanner scanner;

    public ConsoleView(NurseryAccounting nurseryAccounting) {
        this.nurseryAccounting = nurseryAccounting;
        scanner = new Scanner(System.in);
    }

    record AnimalData(String name, LocalDate birthDate, AnimalGenius animalGenius){}

    @Override
    public void showNurseryRegistry() {
        clearConsole();
        printLineWithSymbol("=", SIZE_LINE);
        printCaption("Питомник животных, версия 2024.01. ", " ");
        printLineWithSymbol("=", SIZE_LINE);
        printRegistryHeader();
        printLineWithSymbol("-", SIZE_LINE);

        List<AbstractAnimal> animals = nurseryAccounting.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            var item = animals.get(i);
            String row = String.format(ROW_FORMAT, i + 1, item.getName(), item.getBurthDateAsString(), item.getAge(),
                    item.getAnimalGenius().getTitle());
            printLine(row);
        }
        printLineWithSymbol("-", SIZE_LINE);
    }

    @Override
    public MainMenuCommand showMainMenuWithResult() {
        String menu = String.format(
                "%d-[%s]\t%d-[%s]\t%d-[%s]\t%d-[%s]\n",
                MainMenuCommand.ADD_ANIMAL.ordinal(),
                MainMenuCommand.ADD_ANIMAL.getTag(),
                MainMenuCommand.SHOW_SKILLS.ordinal(),
                MainMenuCommand.SHOW_SKILLS.getTag(),
                MainMenuCommand.REMOVE_ANIMAL.ordinal(),
                MainMenuCommand.REMOVE_ANIMAL.getTag(),
                MainMenuCommand.EXIT.ordinal(),
                MainMenuCommand.EXIT.getTag());
        printLineWithSymbol("=", SIZE_LINE);
        printColorLine("Доступные операции (выберите):", YELLOW_COLOR);
        printColorLine(menu, GREEN_COLOR);
        while (true) {
            try {
                System.out.printf("%s (%d - %d): ", "Выберите действие (укажите число): ", MainMenuCommand.ADD_ANIMAL.ordinal(),
                        MainMenuCommand.EXIT.ordinal());
                scanner = new Scanner(System.in);
                return MainMenuCommand.values()[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                printColorLine("Некорректные данные!", RED_COLOR);
            }
        }
    }

    @Override
    public boolean showAddAnimalDialog() {
        String infoMessage = "Введите данные нового животного в виде: \"имя день_рождения тип_животного\"\n" +
                "день_рождения имеет вид: dd-mm-yyyy (12-03-2022): \n" +
                "тип_животного может принимать значения из списка: " + Arrays.asList(AnimalGenius.values());
        System.out.println(infoMessage);
        while (true) {
            Counter counter = new Counter();
            try (counter){
                System.out.print("Ввод: ");
                scanner = new Scanner(System.in);
                String inputData = scanner.nextLine();
                AnimalData parsedData = parceAnimalData(inputData);
                counter.add();
                return nurseryAccounting.createAnimal(parsedData.name, parsedData.birthDate, parsedData.animalGenius);
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты рождения");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showDetailInfoAnimalDialog() {
        String infoMessage = String.format("Введите учетный номер/Id животного (1 - %d)", nurseryAccounting.getAnimals().size());
        System.out.println(infoMessage);
        while (true) {
            try {
                scanner = new Scanner(System.in);
                int animalNumber = scanner.nextInt();
                AbstractAnimal animal = nurseryAccounting.getAnimals().get(animalNumber - 1);
                showAnimalInfo(animal);

                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Животное с таким номером отсутствует");
            }
        }
    }

    @Override
    public void showAnimalInfo(AbstractAnimal animal) {
        while (true) {
            printAnimalInfo(animal);
            AddSkillMenuCommand code = showAddSkillMenu(animal);
            switch (code) {
                case ADD_SKILL -> {
                    boolean result = showAddSkillDialog(animal);
                    String resMessage = result ? "Команда добавлена" : "Ошибка при добавлении команды";
                    System.out.println(resMessage);
                    if (!result) return;
                }
                case EXIT -> {
                    return;
                }
            }
        }
    }

    @Override
    public AddSkillMenuCommand showAddSkillMenu(AbstractAnimal animal) {
        String menu = String.format(
                "%d-[%s]\t%d-[%s]\n",
                AddSkillMenuCommand.ADD_SKILL.ordinal(), AddSkillMenuCommand.ADD_SKILL.getTag(),
                AddSkillMenuCommand.EXIT.ordinal(), AddSkillMenuCommand.EXIT.getTag()) ;
        printLineWithSymbol("=", SIZE_LINE);
        printColorLine("Доступные действия:", GREEN_COLOR);
        printColorLine(menu, GREEN_COLOR);
        while (true) {
            try {
                System.out.printf("%s (%d - %d): ", "Выберите действие (укажите число): ", AddSkillMenuCommand.ADD_SKILL.ordinal(),
                        AddSkillMenuCommand.EXIT.ordinal());
                scanner = new Scanner(System.in);
                return AddSkillMenuCommand.values()[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                printColorLine("Некорректное значение!", RED_COLOR);
            }
        }
    }

    @Override
    public boolean showAddSkillDialog(AbstractAnimal animal) {
        System.out.println("Введите данные в виде \"Команда (:описание)\"");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isBlank()) return false;
        String[] skillData = input.split(":");
        if (skillData.length == 1) {
            animal.learnSkill(new Skill(skillData[0]));
        } else if (skillData.length == 2) {
            animal.learnSkill(new Skill(skillData[0], skillData[1]));
        } else {
            System.out.println("Избыточный объем данных/параметров");
            return false;
        }
        return true;
    }

    @Override
    public int showRemoveAnimalDialog() {
        String infoMessage = String.format("Введите номер животного (1 - %d)", nurseryAccounting.getAnimals().size());
        System.out.println(infoMessage);
        while (true) {
            try {
                scanner = new Scanner(System.in);
                int animalNumber = scanner.nextInt();
                AbstractAnimal animal = nurseryAccounting.getAnimals().get(animalNumber - 1);

                return nurseryAccounting.removeAnimal(animal);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Животное с таким учетным номером/Id отсутствует");
            }
        }
    }
    /**
     * Парсим входные данные (добавлении животного)
     * @param inputData Входная строка имеет вид "имя_животного дата_рождения тип_животного":
     *        имя_животного - не содержит пробелов
     *        дата_рождения - в формате dd-мм-yyyy (15-07-2022)
     *        тип_животного - имеет значение из перечисления AnimalGenius
     * @return возвращает record AnimalData, с валидными данными животного
     */
    private AnimalData parceAnimalData(String inputData) {
        String[] input = inputData.split(" ");

        if (input.length < 3) {
            throw new IllegalArgumentException("Данные не полные (некорректные), необходимо повторить");
        }
        if (input.length > 3) {
            throw new IllegalArgumentException("Избыточный (некорректный) объем данных, необходимо повторить");
        }
        String animalName = input[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDay = LocalDate.parse(input[1], formatter);
        AnimalGenius genius = AnimalGenius.valueOf(input[2].toUpperCase());
        return new AnimalData(animalName, birthDay, genius);
    }
    private void clearConsole() {
        System.out.print("\033[H\033[J");
    }

    private void printLineWithSymbol(String symbol, int sizeLine) {
        System.out.println(symbol.repeat(sizeLine));
    }

    private void printCaption(String caption, String padSymb) {
        int spaceSize = (SIZE_LINE - caption.length()) / 2;
        String captionLine = padSymb.repeat(spaceSize) + caption + padSymb.repeat(spaceSize);
        System.out.println(captionLine);
    }

    private void printRegistryHeader() {
        String header = String.format(COLUMN_HEADER_FORMAT, "№", "Имя/кличка", "Дата рождения", "Возраст(месяцы)",
                "Тип животного");
        System.out.println(header);
    }

    private void printLine(String row) {
        System.out.printf("%s\n", row);
    }

    private void printColorLine(String row, String displayColor) {
        System.out.printf("%s%s%s\n", displayColor, row, ANSI_RESET);
    }

    private void printAnimalInfo(AbstractAnimal animal) {
        clearConsole();
        printCaption("Детальная информация", "~");
        System.out.printf("Тип животного: %s\n", animal.getAnimalGenius().getTitle());
        System.out.printf("Имя: %s\n", animal.getName());
        System.out.printf("Дата рождения: %s\n", animal.getBurthDateAsString());
        System.out.printf("Возраст (месяцы): %d\n", animal.getAge());
        System.out.printf("Команды: %s\n", animal.getAnimalSkills());
        printLineWithSymbol("~", SIZE_LINE);
    }
}