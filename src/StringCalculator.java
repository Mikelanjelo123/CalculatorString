import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода пользователя
        System.out.println("Введите выражение:"); // Просим пользователя ввести выражение

        String input = scanner.nextLine(); // Читаем введенное выражение
        try {
            String result = calculate(input); // Пытаемся вычислить результат
            if (result.length() > 40) { // Проверяем длину результата
                result = result.substring(0, 40) + "..."; // Если результат длиннее 40 символов, обрезаем его и добавляем "..."
            }
            System.out.println("Output:"); // Выводим результат
            System.out.println(result);
        } catch (Exception e) { // Ловим любые исключения и выводим сообщение об ошибке
            System.err.println(e.getMessage());
        }
    }

    public static String calculate(String input) throws Exception {
        input = input.trim(); // Удаляем лишние пробелы в начале и конце строки
        if (input.matches("\".*\" \\+ \".*\"")) { // Проверяем, соответствует ли выражение формату сложения строк
            String[] parts = input.split(" \\+ ");
            String str1 = parts[0].substring(1, parts[0].length() - 1); // Извлекаем первую строку из кавычек
            String str2 = parts[1].substring(1, parts[1].length() - 1); // Извлекаем вторую строку из кавычек
            return add(str1, str2); // Возвращаем результат сложения строк
        } else if (input.matches("\".*\" - \".*\"")) { // Проверяем, соответствует ли выражение формату вычитания строк
            String[] parts = input.split(" - ");
            String str1 = parts[0].substring(1, parts[0].length() - 1); // Извлекаем первую строку из кавычек
            String str2 = parts[1].substring(1, parts[1].length() - 1); // Извлекаем вторую строку из кавычек
            return subtract(str1, str2); // Возвращаем результат вычитания строк
        } else if (input.matches("\".*\" \\* \\d+")) { // Проверяем, соответствует ли выражение формату умножения строки на число
            String[] parts = input.split(" \\* ");
            String str = parts[0].substring(1, parts[0].length() - 1); // Извлекаем строку из кавычек
            int number = Integer.parseInt(parts[1]); // Преобразуем число из строки в целое число
            validateNumber(number); // Проверяем корректность числа
            return multiply(str, number); // Возвращаем результат умножения строки на число
        } else if (input.matches("\".*\" / \\d+")) { // Проверяем, соответствует ли выражение формату деления строки на число
            String[] parts = input.split(" / ");
            String str = parts[0].substring(1, parts[0].length() - 1); // Извлекаем строку из кавычек
            int number = Integer.parseInt(parts[1]); // Преобразуем число из строки в целое число
            validateNumber(number); // Проверяем корректность числа
            return divide(str, number); // Возвращаем результат деления строки на число
        } else {
            throw new IllegalArgumentException("Некорректное выражение."); // Выбрасываем исключение, если выражение не соответствует ни одному из форматов
        }
    }

    private static String add(String str1, String str2) throws Exception {
        validateString(str1); // Проверяем корректность первой строки
        validateString(str2); // Проверяем корректность второй строки
        return str1 + str2; // Возвращаем результат сложения строк
    }

    private static String subtract(String str1, String str2) throws Exception {
        validateString(str1); // Проверяем корректность первой строки
        validateString(str2); // Проверяем корректность второй строки
        return str1.replace(str2, ""); // Возвращаем результат вычитания строки str2 из строки str1
    }

    private static String multiply(String str, int number) throws Exception {
        validateString(str); // Проверяем корректность строки
        StringBuilder result = new StringBuilder(); // Создаем StringBuilder для эффективного создания результирующей строки
        for (int i = 0; i < number; i++) { // Повторяем строку number раз
            result.append(str);
        }
        return result.toString(); // Возвращаем результирующую строку
    }

    private static String divide(String str, int number) throws Exception {
        validateString(str); // Проверяем корректность строки
        if (number <= 0 || number > str.length()) { // Проверяем корректность числа для деления
            throw new IllegalArgumentException("Некорректное число для деления."); // Выбрасываем исключение, если число некорректно
        }
        return str.substring(0, str.length() / number); // Возвращаем результат деления строки
    }

    private static void validateString(String str) throws Exception {
        if (str.length() > 10) { // Проверяем, что длина строки не превышает 10 символов
            throw new IllegalArgumentException("Строка слишком длинная."); // Выбрасываем исключение, если строка слишком длинная
        }
    }

    private static void validateNumber(int number) throws Exception {
        if (number < 1 || number > 10) { // Проверяем, что число находится в диапазоне от 1 до 10
            throw new IllegalArgumentException("Число должно быть от 1 до 10."); // Выбрасываем исключение, если число некорректно
        }
    }
}