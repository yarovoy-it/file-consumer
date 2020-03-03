package by.home.fileConsumer.component;

/**
 * The type Util.
 */

public class Util {

    /**
     * Validate.
     *
     * @param expression   the expression
     * @param errorMessage the error message
     */
    public static void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * Add String to position and wrap it in brackets
     *
     * @param str       base string
     * @param injectStr string for inject
     * @param position  position
     * @return refactored string
     */
    public static String addString(String str, String injectStr, int position) {
        return str.substring(0, position) + "(" + injectStr + ")" + str.substring(position);
    }

}
