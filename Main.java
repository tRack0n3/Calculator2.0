package Calculator;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Main.calc();
    }
    public static void calc(){
        System.out.println("Введите арифметическую операцию с целыми числами: ");
        Scanner scann = new Scanner(System.in);
        Converter converter = new Converter();
        String mathEx = scann.nextLine().replaceAll("\\s", "");
        String[] operation1 = {"\\+", "-", "/", "\\*"};
        String[] operation = {"+", "-", "/", "*"};
        int operationIndex = 0;

        try {
            for (int i = 0; i < operation.length; i++) {
                if (mathEx.contains(operation[i])) {
                    operationIndex = i;
                    break;
                } else {
                    operationIndex -= 5;
                }
            }
            String[] digits = mathEx.split(operation1[operationIndex]);
            if (digits.length>2 | digits.length<2){
                throw new InputExeption("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}
            if (converter.isRoman(digits[1]) == converter.isRoman(digits[1])) {
                int num1, num2;
                boolean isRoman = converter.isRoman(digits[0]);
                if (isRoman) {
                    num1 = converter.romanToArabic(digits[0]);
                    num2 = converter.romanToArabic(digits[1]);}
                else {
                    num1 = Integer.parseInt(digits[0]);
                    num2 = Integer.parseInt(digits[1]);}

                int result = 0;

                switch (operation[operationIndex]) {
                    case "+":
                        result = ArithmeticOperation.addition(num1, num2);
                        break;
                    case "-":
                        result = ArithmeticOperation.subtract(num1, num2);
                        break;
                    case "*":
                        result = ArithmeticOperation.multiply(num1, num2);
                        break;
                    case "/":
                        result = ArithmeticOperation.divine(num1, num2);
                        break;}
                if (isRoman) {
                    System.out.println(converter.arabicToRoman(result));}
                else {
                    System.out.println(Integer.toString(result));}
            }
       }
       //При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
       catch(ArrayIndexOutOfBoundsException e){
           System.err.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}

       //Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
       catch(NumberFormatException e){
           System.err.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");}

        //Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
       catch(NullPointerException e){
           System.err.println("Результатом работы калькулятора с римскими числами могут быть только положительные числа");}

        // Если длина массива с числами меньше 2 или больше 2 выбрасываем исключение ("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)")
       catch (InputExeption e) {
           System.err.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}
    }
}






