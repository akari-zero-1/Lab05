package hust.soict.hedspi.aims.menu;

public class Menu {
    private static void printMenu(String title, String[] options) {
        System.out.println(title);
        System.out.println("--------------------------------");
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: " + String.join("-", options));
    }

    public static void mainMenu() {
        String[] options = {
            "1. View store",
            "2. Update store",
            "3. See current cart",
            "0. Exit"
        };
        printMenu("AIMS: ", options);
    }

    public static void storeMenu() {
        String[] options = {
            "1. See a media's details",
            "2. Add a media to cart",
            "3. Play a media",
            "4. See current cart",
            "0. Back"
        };
        printMenu("Options: ", options);
    }

    public static void updateStoreOptionMenu() {
        String[] options = {
            "1. Add a media",
            "2. Remove a media",
            "0. Cancel"
        };
        printMenu("Update Store Options:", options);
    }

    public static void cartMenu() {
        String[] options = {
            "1. Filter media in cart",
            "2. Sort media in cart",
            "3. Remove media from cart",
            "4. Play a media",
            "5. Place order",
            "0. Back"
        };
        printMenu("Options: ", options);
    }

    public static void bookDetailsMenu() {
        String[] options = {
            "1. Add to cart",
            "0. Back"
        };
        printMenu("Options: ", options);
    }

    public static void playableDetailsMenu() {
        String[] options = {
            "1. Add to cart",
            "2. Play",
            "0. Back"
        };
        printMenu("Options: ", options);
    }

    public static void sortOptionMenu() {
        String[] options = {
            "1. By title",
            "2. By cost"
        };
        printMenu("Sort Options: ", options);
    }

    public static void filterOptionMenu() {
        String[] options = {
            "1. By id",
            "2. By title"
        };
        printMenu("Filter Options: ", options);
    }

    public static void mediaTypeMenu() {
        String[] options = {
            "1. Book",
            "2. DVD",
            "3. CD"
        };
        printMenu("Media Type: ", options);
    }
}
