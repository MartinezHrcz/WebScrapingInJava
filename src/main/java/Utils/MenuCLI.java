package Utils;

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
    String StartMenuOptions = """
            1.Show Scraped data
            2.Average price for products
            3.Minimum / Maximum price for products
            4.
            """;


    public static void StartMenu() {
        System.out.println(StartMenuBanner.toString());
    }
}
