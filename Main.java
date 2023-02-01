package Calculator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        System.out.println("Введите арифметическую операцию с целыми числами: ");
        String mathEx = scann.nextLine().replaceAll("\\s", "");
        Main.calc(mathEx);
    }
    public static String calc(String input) {

        Converter converter = new Converter();
        String[] operation1 = {"\\+", "-", "/", "\\*"};
        String[] operation = {"+", "-", "/", "*"};
        int operationIndex = 0;

        try {
            for (int i = 0; i < operation.length; i++) {
                if (input.contains(operation[i])) {
                    operationIndex = i;
                    break;
                } else {
                    operationIndex -= 5;
                }
            }
            String[] digits = input.split(operation1[operationIndex]);
            if (digits.length > 2 | digits.length < 2) {
                throw new InputExeption("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (converter.isRoman(digits[1]) == converter.isRoman(digits[1])) {
                int num1, num2;
                boolean isRoman = converter.isRoman(digits[0]);
                if (isRoman) {
                    num1 = converter.romanToArabic(digits[0]);
                    num2 = converter.romanToArabic(digits[1]);
                } else {
                    num1 = Integer.parseInt(digits[0]);
                    num2 = Integer.parseInt(digits[1]);
                }
                if (num1 > 10 | num2 > 10 | num1 < 1 | num2 < 1){
                    throw new NumberInputExeption("Exeption");
                }
                switch (operation[operationIndex]) {
                    case "+":
                        input = String.valueOf(ArithmeticOperation.addition(num1, num2));
                        break;
                    case "-":
                        input = String.valueOf(ArithmeticOperation.subtract(num1, num2));
                        break;
                    case "*":
                        input = String.valueOf(ArithmeticOperation.multiply(num1, num2));
                        break;
                    case "/":
                        input = String.valueOf(ArithmeticOperation.divine(num1, num2));
                        break;
                }
//                if (isRoman) {
//                    System.out.println(converter.arabicToRoman(Integer.parseInt(input)));
//                } else {
//                    System.out.println(input);
//                }
            }
        }
        // При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
        catch (NumberFormatException e) {
            System.err.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
        }

        // Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
        catch (NullPointerException e) {
            System.err.println("Результатом работы калькулятора с римскими числами могут быть только положительные числа");}

        // Если длина массива с числами меньше 2 или больше 2 выбрасываем исключение ("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)")
        catch (InputExeption e) {
            System.err.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}

        // Комментарий ментора:"Не выбрасывается исключение при вводе чисел меньше 1 или больше 10". Исправлено.
        catch (NumberInputExeption e) {
            System.err.println("Принимаются только числа от 1 до 10 включительно");}
        return input;
    }
}






