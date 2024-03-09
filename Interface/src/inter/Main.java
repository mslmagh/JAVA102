package inter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Tutar Giriniz: ");
        double price = scan.nextDouble();

        System.out.print("Kart no giriniz: ");
        String cardNumber = scan.next();

        System.out.print("Son kullanım tarihi giriniz: ");
        String date = scan.next();

        System.out.print("Güvenlik kodu giriniz: ");
        String cvc = scan.next();

        System.out.println("1. A Bankası");
        System.out.println("2. B Bankası");
        System.out.println("3. C Bankası");
        System.out.print("Banka Seçiniz: ");
        int selectBank = scan.nextInt();

        switch (selectBank) {
            case 1:
                ABankasi aPos = new ABankasi("A Bankası", "4543535435", "545");
                aPos.connect("127.1.1.1");
                aPos.payment(price, cardNumber, date, cvc);
                break;
            case 2:
                ABankasi bPos = new ABankasi("B Bankası", "4543535435", "545");
                bPos.connect("127.1.1.1");
                bPos.payment(price, cardNumber, date, cvc);
                break;
            default:
                System.out.println("Geçerli banka giriniz.");
        }
    }
}
