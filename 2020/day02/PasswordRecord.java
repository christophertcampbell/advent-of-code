package day02;

public class PasswordRecord
{
    private int index1;
    private int index2;
    private char validationChar;
    private String password;

    /**
     * Constructs a password record, with information including
     * the password, the validation character and the parameters
     */
    public PasswordRecord(String password, char validationChar, int index1, int index2)
    {
        this.password = password;
        this.validationChar = validationChar;
        this.index1 = index1;
        this.index2 = index2;
    }

    public String getPassword()
    {
        return password;
    }

    public int getIndex1()
    {
        return index1;
    }

    public int getIndex2()
    {
        return index2;
    }

    public char getValidationChar()
    {
        return validationChar;
    }
}