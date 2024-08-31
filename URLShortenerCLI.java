import java.util.Scanner;

public class URLShortenerCLI {
    public static void main(String[] args) {
        URLManager manager = new URLManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("URL Shortener CLI");
        System.out.println("Commands: shorten [URL], expand [shortURL], exit");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.startsWith("shorten ")) {
                String longURL = command.substring(8);
                String shortURL = manager.shortenURL(longURL);
                System.out.println("Short URL: " + shortURL);
            } else if (command.startsWith("expand ")) {
                String shortURL = command.substring(7);
                String longURL = manager.expandURL(shortURL);
                if (longURL != null) {
                    System.out.println("Long URL: " + longURL);
                } else {
                    System.out.println("Invalid short URL.");
                }
            } else if (command.equals("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}
