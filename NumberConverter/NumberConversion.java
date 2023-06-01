import java.util.Scanner;
import java.lang.Math;

public class NumberConversion {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String type = "";

        do {
            System.out.println("Enter a type of value to be generated or quit to exit the program (hex, binary, decimal, quit): ");
            type = input.nextLine().toLowerCase();

            int randomNum = (int)(Math.random()*100) + 1; //random number between 1 and 10000
            String hexVal, hexGuess, resultHex, binaryVal, binaryGuess, resultBinary;
            int decimalGuess, resultDecimal;

            switch (type) {
                case "hex":
                    hexVal = convertDecToHex(randomNum);
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("Here is your random hex value: " + hexVal);
                    System.out.println("-------------------------------------------------------------------------");

                    System.out.println("Please enter your guess for a decimal value: ");
                    decimalGuess = input.nextInt();

                    System.out.println("Please enter your guess for a binary value: ");
                    binaryGuess = input.next();
                    System.out.println("-------------------------------------------------------------------------");

                    resultDecimal = convertHexToDec(hexVal);
                    resultBinary = convertHexToBinary(hexVal);

                    if(decimalGuess == resultDecimal) {
                        System.out.println("Your guess for the decimal value was correct!");
                    }
                    if(resultBinary.equals(binaryGuess) || stripLeadZeros(resultBinary).equals(binaryGuess)) {
                        System.out.println("Your guess for the binary value was correct!");
                    }

                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("The decimal equivalent of " + hexVal + " is " + resultDecimal);
                    System.out.println("The binary equivalent of " + hexVal + " is " + resultBinary + " or " + stripLeadZeros(resultBinary));
                    System.out.println("-------------------------------------------------------------------------");
                    break;
                case "binary":
                    binaryVal = convertDecToBinary(randomNum);
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("Here is your random binary value: " + binaryVal);
                    System.out.println("-------------------------------------------------------------------------");

                    System.out.println("Please enter your guess for a decimal value: ");
                    decimalGuess = input.nextInt();

                    System.out.println("Please enter your guess for a hex value: ");
                    hexGuess = input.next().toLowerCase();
                    System.out.println("-------------------------------------------------------------------------");

                    resultDecimal = convertBinaryToDec(binaryVal);
                    resultHex = convertBinaryToHex(binaryVal);

                    if(decimalGuess == resultDecimal) {
                        System.out.println("Your guess for the decimal value was correct!");
                    }
                    if(resultHex.equals(hexGuess)) {
                        System.out.println("Your guess for the hex value was correct!");
                    }

                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("The decimal equivalent of " + binaryVal + " is " + resultDecimal);
                    System.out.println("The hex equivalent of " + binaryVal + " is " + resultHex);
                    System.out.println("-------------------------------------------------------------------------");
                    break;
                case "decimal":
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("Here is your random decimal value: " + randomNum);
                    System.out.println("-------------------------------------------------------------------------");

                    System.out.println("Please enter your guess for a binary value: ");
                    binaryGuess = input.next();

                    System.out.println("Please enter your guess for a hex value: ");
                    hexGuess = input.next().toLowerCase();
                    System.out.println("-------------------------------------------------------------------------");

                    resultBinary = convertDecToBinary(randomNum);
                    resultHex = convertDecToHex(randomNum);

                    if(resultBinary.equals(binaryGuess) || stripLeadZeros(resultBinary).equals(binaryGuess)) {
                        System.out.println("Your guess for the binary value was correct!");
                    }
                    if(resultHex.equals(hexGuess)) {
                        System.out.println("Your guess for the hex value was correct!");
                    }

                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("The binary equivalent of " + randomNum + " is " + resultBinary + " or " + stripLeadZeros(resultBinary));
                    System.out.println("The hex equivalent of " + randomNum + " is " + resultHex);
                    System.out.println("-------------------------------------------------------------------------");
                    break;
                case "quit": break;
                default:
                    System.out.println("Wrong Type, Please Try Again.");
                    break;
            }
            input.nextLine();
        } while(!type.equals("quit"));
        input.close();

    }

    public static int convertHexToDec(String input) {
        int result = 0;
        for(int i = 0; i < input.length(); i++) {
            int char0 = (int) input.charAt( (input.length()-1) - i); // moves through the input backwards
            if(char0 <= 102 && char0 >= 97) { // if the input is a character between 'a' to 'f'
                char0 -= 87; //convert to decimal value
            } else if(char0 <= 57 && char0 >= 48) { // if the input is a character between '0' to '9'
                char0 -= 48; //convert to decimal value
            }
            result += ((int) Math.pow(16.0, (double) i)) * ((int) char0); // 16^i * char0
        }
        return result;
    }

    public static String convertHexToBinary(String input) {
        String resultStr = "";
        for(int i = 0; i < input.length(); i++) {
            int char0 = (int) input.charAt( (input.length()-1) - i); // moves through the input backwards
            if(char0 <= 102 && char0 >= 97) { // if the input is a character between 'a' to 'f'
                char0 -= 87; //convert to decimal value
            } else if(char0 <= 57 && char0 >= 48) { // if the input is a character between '0' to '9'
                char0 -= 48; //convert to decimal value
            }
            for(int j = 0; j < 4; j++) {
                resultStr = (char0 % 2) + resultStr;
                char0 /= 2;
            }
        }
        return resultStr;
    }

    public static int convertBinaryToDec(String input) {
        int result = 0;
        for(int i = 0; i < input.length(); i++) {
            int char0 = (int) input.charAt( (input.length()-1) - i); // moves through the input backwards
            if(char0 < 48 || char0 > 49) {
                System.out.println("Invalid binary string.");
                return -1;
            }
            char0 -= 48;
            result += ((int) Math.pow(2.0, (double) i)) * ((int) char0); // 16^i * char0
        }
        return result;
    }

    public static String convertBinaryToHex(String input) {
        int result = 0;
        String resultStr = "";
        for(int i = 0; i < input.length(); i++) {
            if(i%4 == 0 && i > 0) {
                char c;
                if(result <= 15 && result >= 10) { // if the input is a character between 'a' to 'f'
                    c = (char)(result + 55); //convert to appropriate Hex Value
                    resultStr = c + resultStr;
                } else resultStr = "" + result + resultStr;
                result = 0;
            }
            int char0 = (int) input.charAt( (input.length()-1) - i); // moves through the input backwards
            if(char0 < 48 || char0 > 49) {
                System.out.println("Invalid binary string.");
                result = -1;
                break;
            }
            char0 -= 48;
            result += ((int) Math.pow(2.0, (double) (i%4))) * ((int) char0); // 16^i * char0
        }
        if(result != 0) {
            char c;
            if(result <= 15 && result >= 10) { // if the input is a character between 'a' to 'f'
                c = (char)(result + 55); //convert to appropriate Hex Value
                resultStr = c + resultStr;
            } else resultStr = "" + result + resultStr;
        }

        return resultStr;
    }

    public static String convertDecToHex(int input) {
        String resultStr = "";
        while(input > 0) {
            int c = input % 16;
            if(c >= 10) { // if the input is a character between 'a' to 'f'
                resultStr = (char)(c+87) + resultStr; //convert to decimal value
            } else { // if the input is a character between '0' to '9'
            resultStr = (char)(c+48) + resultStr;; //convert to decimal value
            }
            input /= 16;
        }
        return resultStr;
    }

    public static String convertDecToBinary(int input) {
        String resultStr = convertDecToHex(input);
        return convertHexToBinary(resultStr);
    }

    public static String stripLeadZeros(String input) {
        int i;
        for(i = 0; i < input.length(); i++) {
            if(input.charAt(i) != '0') {
                break;
            }
        }
        return input.substring(i, input.length());
    }
}
