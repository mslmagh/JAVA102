import java.util.Scanner;

class Game {
    Player player;
    private Scanner scan = new Scanner(System.in);

    void start() {
        System.out.println(player.ANSI_CYAN + player.ANSI_BOLD + "Macera Oyununa Hoşgeldiniz" + player.ANSI_RESET);
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = scan.nextLine();
        Player player = new Player("Muslum");
        System.out.println("Sayın " + playerName + "  bu karanlık ve sisli maceraya hoşgeldiniz");

        System.out.println("Lütfen bir karakter seçin: ");
        player.selectChar();
        while (true) {
            Location location;
            System.out.println(
                    player.ANSI_BLACK + player.ANSI_BOLD + "-----------The zones-----------" + player.ANSI_RESET);
            System.out.println(player.ANSI_GREEN + "1 - Safe House --> There are no enemies here" + player.ANSI_RESET);
            System.out.println(player.ANSI_YELLOW + "2 - Store --> Buy weapons or armor" + player.ANSI_RESET);
            System.out.println(player.ANSI_RED + "3 - Cave --> Award<food>. Be careful, you might run into zombies"
                    + player.ANSI_RESET);
            System.out.println(player.ANSI_RED
                    + "4 - Forest --> Award<firewood> Be careful, you might run into vampires" + player.ANSI_RESET);
            System.out.println(player.ANSI_RED + "5 - River --> Award<water> Be careful, you might run into bears"
                    + player.ANSI_RESET);
            System.out.println(player.ANSI_RED + "6 - Mine --> Award<random item> Be careful, you might run into snakes"
                    + player.ANSI_RESET);
            System.out.println("0 - Out --> Finish the game\n");
            System.out.print("Please choose what location dou you want to go: ");
            int selectLoc = scan.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    location = null;
                    System.out.println("Entered a valid value");
                    continue;
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }
        }

    }
}
