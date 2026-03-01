import java.util.Scanner;
import java.util.function.Predicate;

public class SimpleLogin_refactored2
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

                int[] score = evaluate(
                    pass,
                    p -> p.length() >= 6,
                    p -> !p.contains(" "),
                    p ->
                    {
                        for (int i = 0; i < p.length(); i++)
                        {
                            if (Character.isLetter(p.charAt(i)))
                            {
                                return true;
                            }
                        }
                        return false;
                    },
                    
                    p ->
                    {
                        for (int i = 0; i < p.length(); i++)
                        {
                            if (Character.isDigit(p.charAt(i)))
                            {
                                return true;
                            }
                        }
                        return false;
                    }
                );
                
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

    private static boolean authCheck(String user, String pass)
    {
        for (int i = 0; i < USERNAMES.length; i++)
        {
            if (USERNAMES[i].equals(user) && PASSWORDS[i].equals(pass)) 
                return true;
        }
        
        return false;
    }

    private static int[] evaluate(String pass, Predicate<String>... rules)
    {
        int n = 0;
        int m = rules.length;

        for (int i = 0; i < rules.length; i++)
        {
            if (rules[i].test(pass)) n++;
        }

        return new int[] { n, m };
    }
}
