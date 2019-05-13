package Password;

public class ValidatePassword {
    public static void validatePassword (String pass) throws PasswordException {
        boolean normalLength = pass.length() >= 8 && pass.length() <= 20;
        boolean hasUppercase = pass.matches(".*[A-Z].*");
        boolean hasLowerCase = pass.matches(".*[a-z].*");
        boolean hasDigits = pass.matches(".*\\d.*");
        boolean hasSpecialCharacters = pass.matches(".*[^0-9A-Za-z].*");

        StringBuilder stringBuilder = new StringBuilder("\n");

        if (!normalLength){
            stringBuilder.append("Ваш пароль должен быть не менее 8 символов и не более 20!" + "\n");
        }

        if (!hasUppercase){
            stringBuilder.append("Ваш пароль должен содержать хотя бы одну заглавную букву!" + "\n");
        }

        if (!hasLowerCase){
            stringBuilder.append("Ваш пароль должен содержать хотя бы одну строчную букву!" + "\n");
        }

        if (!hasDigits){
            stringBuilder.append("Ваш пароль должен содержать хотя бы одну цифру!" + "\n");
        }

        if (!hasSpecialCharacters){
            stringBuilder.append("Ваш пароль должен содержать хотя бы один спец символ!" + "\n");
        }

        if (stringBuilder.length() > 1) throw new PasswordException(stringBuilder.toString());
        else System.out.println("Ваш пароль принят!!!");
    }
}

class PasswordException extends Exception {

    public PasswordException(String message) {
        super(message);
    }
}
