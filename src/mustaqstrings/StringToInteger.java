package mustaqstrings;

public class StringToInteger {

    public int St_to_I(String input) {
        Integer index = 0, sign = 1, parsedInt = null;

        //Handle the Empty or NULL string
        if (input == null || input.length() == 0)
            throw new IllegalArgumentException("Empty String");

        //skip the leading spaces "     45"
        while (index < input.length() && Character.isWhitespace(input.charAt(index)))
            index++;

        //handle the sign + or -
        if (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
            sign = input.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        //Expectation is that all the succeeding characters should be digits from now on
        while (index < input.length()) {
            if (!Character.isDigit(input.charAt(index)))
                throw new IllegalArgumentException("String contains invalid characters");
            int currentDigit = input.charAt(index) - '0';

            if (parsedInt == null)
                parsedInt = 0;
            if (Integer.MIN_VALUE / 10 < parsedInt || (Integer.MAX_VALUE / 10 == parsedInt && Integer.MAX_VALUE % 10 < currentDigit))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            parsedInt = 10 * parsedInt + currentDigit;
            index++;

        }


        if (parsedInt == null)
            throw new IllegalArgumentException("Invalid string");

        return parsedInt * sign;

    }


    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();

        System.out.println(stringToInteger.St_to_I("   -45"));
        System.out.println(stringToInteger.St_to_I("+12345"));
        System.out.println(stringToInteger.St_to_I("000001"));
        System.out.println(stringToInteger.St_to_I("2147836471212"));
        System.out.println(stringToInteger.St_to_I("-21111111111111111111111111111111111111111111111"));
    }

}
