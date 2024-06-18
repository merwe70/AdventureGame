import java.util.Random;
import java.util.Scanner;

public class Game {

    public Scanner input = new Scanner(System.in);
    public void start(){
        // Kullanıcıdan isim istenir
        System.out.print("Nick girin: ");
        String playerName = input.nextLine();
        System.out.print("\nMacera adasına hoşgeldin " + playerName);
        System.out.println(", bu ürkütücü ve ıssız adada yaşanılan herşey gerçek !!! \n");
        System.out.println(playerName +  ", böyle bir deneyimi hangi karakter ile tecrübe etmek istersin ?");
        Player player = new Player(playerName);
        player.selectChar();//Karakter seçimi

        Random random = new Random();
        int firstMove = random.nextInt(2);// İlk hamle durumu rastgele belirlenir.

        if(firstMove == 0){
            System.out.println("İlk hamleyi "+ playerName +" yapıyor ! ");
        }else{
            System.out.println("İlk hamleyi canavar yapıyor !");
        }

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println("---------Bölgeler---------");

            System.out.println("1 - Güvenli ev");
            System.out.println("2 - Dükkan");
            System.out.println("3 - Mağara ---> Savaş bölgesi - Ödül: Yemek     -       Düşman: Zombi");
            System.out.println("4 - Orman  ---> Savaş bölgesi - Ödül: Odun      -       Düşman: Vampir");
            System.out.println("5 - Nehir  ---> Savaş bölgesi - Ödül: Su        -       Düşman: Ayı");
            System.out.println("6 - Maden  ---> Savaş bölgesi - Ödül: Silah-Zırh-Para - Düşman: Yılan");
            System.out.println("0 - Oyundan çık");
            System.out.print("Konum ID seçin: ");
            int selectLog = input.nextInt();
            switch (selectLog){
                case 0 :
                    location = null;
                    break;
                case 1 :
                    location = new SafeHouse(player);
                    break;
                case 2 :
                    location = new ToolStore(player);
                    break;
                case 3 :
                    if (player.getInventory().isFood()){
                        System.out.println("Mağaradaki bütün ödülleri topladın. Başka bölgede savaş.");
                        continue;
                    }else {
                        location = new Cave(player);
                        break;
                    }

                case 4 :
                    if (player.getInventory().isFirewood()){
                        System.out.println("Ormandaki bütün ödülleri topladın. Başka bölgede savaş.");
                        continue;
                    }else {
                        location = new Forest(player);
                        break;
                    }

                case 5 :
                    if (player.getInventory().isWater()){
                        System.out.println("Nehirdeki bütün ödülleri topladın. Başka bölgede savaş.");
                        continue;
                    }else {
                        location = new River(player);
                        break;
                    }
                case 6 :
                    location = new Mine(player);
                    break;


                default:
                    System.out.println("Lütfen geçerli bir bölge girin.");
            }

            if (location == null){
                System.out.println("Masaüstüne dönülüyor.");
                break;
            }
            if (location.getName().equals("Güvenli Ev")) {
                if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                    System.out.println("TEBRİKLER OYUNU KAZANDINIZ.");
                    break;

                }
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER!!");
                break;
            }


        }

    }
}