import java.util.Scanner;

public class simpleLogin
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
    
        int percobaan = 0;
        boolean loginBerhasil = false;

        while (percobaan < 3 && !loginBerhasil)
        {
            System.out.print("User: ");
            String user = sc.nextLine();
            
            System.out.print("Pass: ");
            String pass = sc.nextLine();

            if (user.equals("admin") && pass.equals("password"))
            {
                loginBerhasil = true;

                boolean panjangMinimal = pass.length() >= 6;
                boolean adaHuruf = false;
                boolean adaAngka = false;
                boolean tanpaSpasi = !pass.contains(" ");

                for (int i = 0; i < pass.length(); i++)
                {
                    char c = pass.charAt(i);

                    if (Character.isLetter(c)) adaHuruf = true;
                    if (Character.isDigit(c)) adaAngka = true;
                }

                int skor = 0;

                if (panjangMinimal) skor++;
                if (adaHuruf) skor++;
                if (adaAngka) skor++;
                if (tanpaSpasi) skor++;

                System.out.println("Login sukses! Selamat datang Admin.");

                if (skor == 4) System.out.println("Skor: " + skor + "/4");
                else
                {
                    System.out.println("Skor: " + skor + "/4");

                    if (!panjangMinimal) System.out.println("- minimal 6 karakter");
                    if (!adaHuruf) System.out.println("- harus punya minimal 1 huruf");
                    if (!adaAngka) System.out.println("- harus punya minimal 1 angka");
                }
            }

            else
            {
                percobaan++;
                System.out.println("Login Gagal! Sisa percobaan: " + (3 - percobaan));
            }
        }

        if (percobaan == 3) System.out.println("Akun terkunci");
        sc.close();
    }
}
