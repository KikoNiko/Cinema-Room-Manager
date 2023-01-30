package cinema;

import java.util.Scanner;

public class Operations {

    static boolean isPurchased = false;
    private static int ticketCount = 0;
    private static int currentIncome = 0;

    static Scanner scanner = new Scanner(System.in);

    static CinemaRoom cr = new CinemaRoom();
    static int rows = cr.getRows();
    static int seats = cr.getSeats();
    static int rowNum = cr.getRowNum();
    static int seatNum = cr.getSeatNum();
    static char[][] cinemaArray = cr.getCinemaArray();


    public void createCinema() {
        System.out.println("Enter the number of rows:");
        System.out.print('>');
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print('>');
        seats = scanner.nextInt();
        System.out.println();

        //Populate two-dimensional array with empty seats ('S')
        cinemaArray = new char[rows][seats];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaArray[i][j] = 'S';
            }
        }
    }

    public void chooseFromMenu() {
        printMenu();
        int choice;
        do {
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please enter 1, 2,or 0");
                    break;
            }
            printMenu();
        } while (scanner.hasNextInt());
    }

    private static void statistics() {
        //I keep track of purchased tickets in buyTicket method and here it just prints the count
        System.out.println("Number of purchased tickets: "+ ticketCount);

        //Calculate percentage
        double ticketsPurchased = ticketCount;
        double percentValue = (ticketsPurchased / (rows * seats)) * 100;
        System.out.printf("Percentage: %.2f%%", percentValue);
        System.out.println();

        //Calculate current income. I do that in buyTicket method and display it here
        System.out.println("Current income: $"+ currentIncome);

        //Calculate total income
        int maxIncome;
        if (rows * seats <= 60) {
            maxIncome = rows * seats * 10;
            System.out.println("Total income: $"+ maxIncome);
        }
        else if (rows * seats > 60) {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            maxIncome = frontRows * seats * 10 + backRows * seats * 8;
            System.out.println("Total income: $"+ maxIncome);
        }
        System.out.println();

    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print('>');
    }

    private static void buyTicket() {
        /*
        A bit self-explanatory this method is used to buy a ticket.
        It checks if the seat is available (free seats are represented by 'S' and taken seats are 'B')
        It makes sure of correct input.
        It makes sure same ticket cannot be bought twice.
         */
        do {
            try {
                System.out.println("Enter a row number:");
                System.out.print('>');
                rowNum = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                System.out.print('>');
                seatNum = scanner.nextInt();

                if (cinemaArray[rowNum - 1][seatNum - 1] == 'B') {
                    isPurchased = true;
                    System.out.println();
                    System.out.println("That ticket has already been purchased!");
                    System.out.println();
                    buyTicket();
                } else {
                    System.out.println();
                    int price;
                    if (rowNum <= 4) {
                        price = 10;
                        System.out.println("Ticket price: $" + price);
                    } else {
                        price = 8;
                        System.out.println("Ticket price: $" + price);
                    }
                    currentIncome += price;
                    cinemaArray[rowNum - 1][seatNum - 1] = 'B';
                    ticketCount++;
                    System.out.println();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
                System.out.println();
            }
        } while (rowNum > rows || seatNum > seats);
    }

    private static void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int n = 1; n <= seats; n++) {
            System.out.print(n + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinemaArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
