package Utils;

import java.util.Scanner;

public class MenuCLI {
    private static final String StartMenuBanner = """
  _  _       __  __                             
 | || |     |  \\/  |                            
 | __ |  _  | |\\/| |  _                         
 |_||_| (_) |_|  |_| (_)                        
 \\ \\    / /__| |__ _____ _ _ __ _ _ __  ___ _ _ 
  \\ \\/\\/ / -_) '_ (_-< _| '_/ _` | '_ \\/ -_) '_|
   \\_/\\_/\\___|_.__/__|__|_| \\__,_| .__/\\___|_|  
                                 |_|            
 """;
    private final static String StartMenuOptions = """
            1.Show Scraped data
            2.Average price for products
            3.Minimum / Maximum price for products
            4.Exit
            """;


    public static void StartMenu() {
        System.out.println(StartMenuBanner.toString());
        System.out.println(StartMenuOptions.toString());
        System.out.print("Options: ");
        Scanner sr = new Scanner(System.in);
        String input ="";

        while (input !="4" ){
            input = sr.nextLine();
            switch (input){
                case "1":
                        DataWriter.writeDataToCLI(ProductUtils.getProducts());
                    break;
                case "2":
                    System.out.println("Average price for products: ");
                    System.out.println(ProductUtils.AvgPrice());
                    break;
                case "3":
                    System.out.println("Minimum price for products: " + ProductUtils.MinPrice());
                    System.out.println("Maximum price for products: " + ProductUtils.MaxPrice());
                    break;
                case "4":
                    System.out.println("Exiting application!");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
            System.out.println("Press Enter to continue...");
            sr.nextLine();
            System.out.println(StartMenuOptions.toString());
            System.out.print("Option: ");
        }
    }
}
