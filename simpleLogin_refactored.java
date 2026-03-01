import java.util.Scanner;

public class SimpleLogin_refactored
{
    private static final int MAX_ATTEMPTS = 3;

    private static final String[] USERNAMES =
    {
        "admin",
        "burhan",
        "akiyama_mio"
    };

    private static final String[] PASSWORDS =
    {
        "password",
        "burunghantu123",
        "cute"
    };

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        loginHandler(sc);
        sc.close();
    }

    private static void loginHandler(Scanner sc)
    {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS)
        {
            String user = inputHandler(sc, "User: ");
            String pass = inputHandler(sc, "Pass: ");

            if (authCheck(user, pass))
            {
                System.out.println("Login sukses! Selamat datang " + user + ".");

                int[] score = evaluate(pass);
                System.out.println("Skor: " + score[0] + "/" + score[1]);
                
                return;
            }

            attempts++;
            System.out.println("Login Gagal! Sisa percobaan: " + (MAX_ATTEMPTS - attempts));
        }

        System.out.println("Akun terkunci");
    }

    private static String inputHandler(Scanner sc, String message)
    {
        System.out.print(message);
        
        return sc.nextLine();
    }

    public static boolean authCheck(String user, String pass)
    {
        for (int i = 0; i < USERNAMES.length; i++)
        {
            if (USERNAMES[i].equals(user) && PASSWORDS[i].equals(pass)) 
                return true;
        }
        
        return false;
    }

    public static int[] evaluate(String pass)
    {
        int n = 0;
        int m = 4;

        if (pass.length() >= 6)  n++;
        if (!pass.contains(" ")) n++;
        
        boolean adaHuruf = false;
        boolean adaAngka = false;

        for (int i = 0; i < pass.length(); i++)
        {
            if (adaHuruf && adaAngka) break;
        
            char c = pass.charAt(i);

            if (Character.isLetter(c)) adaHuruf = true;
            if (Character.isDigit(c))  adaAngka = true;
        }

        if (adaHuruf) n++;
        if (adaAngka) n++;

        return new int[] { n, m };
    }
}
