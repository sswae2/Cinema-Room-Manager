package cinema;

import java.util.Scanner;

public class Cinema {

    private int rows, seats;
    private String arr[][];
    private int totalTickets;
    private int ticketsPurchased;
    private int currentIncome = 0;
    private int totalIncome = 0;

    public Cinema(int rows, int seats){
        this.rows = rows;
        this.seats = seats;
        totalTickets = rows * seats;

        if(totalTickets > 60) totalIncome = (rows/2) * 10 * seats + (rows - rows/2) * seats * 8;
        else totalIncome = rows * seats * 10;

        arr = new String[rows+1][seats+1];
        for(int i =0;i<=rows;i++){
            for(int j = 0;j<=seats;j++){
                if(i==0 && j== 0) {
                    arr[i][j] = " ";
                    continue;
                }
                if(i == 0){
                    arr[i][j] = Integer.toString(j);
                    continue;
                }
                if(j == 0){
                    arr[i][j] = Integer.toString(i);
                    continue;
                }
                arr[i][j] = "S";
            }
        }
    }


  boolean evaluate(int row, int seat){

        try{
            if(arr[row][seat] == "B"){
                System.out.println("That ticket has already been purchased!");
                return false;
            }
            arr[row][seat] = "B";
            this.ticketsPurchased++;
            if(rows * seats <=60 || row <= rows/2)
            {
                currentIncome += 10;
                System.out.println("Ticket price: $10");
                return true;
            }
            currentIncome += 8;
            System.out.println("Ticket price: $8");
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong input!");
            return false;
        }



    }
    void printSeats(){

//        for(int i =0;i<=rows;i++){
//            for(int j =0;j<=seats;j++){
//                if(i == 0 && j == 0) System.out.print("  ");
//                if(i == 0){
//                    System.out.print(j);
//                    if(j!=seats)
//                    System.out.print(" ");
//                    else{
//                        System.out.println();
//                    }
//                    continue;
//                }
//                if(j == 0){
//                    System.out.print(i + " ");
//                }
//
//                System.out.print("S");
//                if(j!=seats) System.out.print(" ");
//                else{
//                    System.out.println();
//                }
//            }
//        }
        System.out.println("\nCinema:");
        for(int i = 0;i<=rows;i++){
            for(int j = 0;j<=seats;j++){
                System.out.print(arr[i][j]);
                if(j!=seats) System.out.print(" ");
                else System.out.println();
            }
        }

    }

    private void showStat(){
        System.out.println("\nNumber of purchased tickets: "+this.ticketsPurchased);
        System.out.printf("Percentage: %.2f%%%n", ((double)this.ticketsPurchased/this.totalTickets) * 100);
        System.out.println("Current income: $"+this.currentIncome);
        System.out.println("Total income: $"+ this.totalIncome);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();
        Cinema c = new Cinema(rows,seats);
        int ans = 0;

        do{
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
             ans = scan.nextInt();
             switch(ans){
                 case 1:  c.printSeats();
                 break;
                 case 2:
                     int row , seat;
                     do {
                         System.out.println("Enter a row number:");
                          row = scan.nextInt();
                         System.out.println("Enter a seat number in that row:");
                          seat = scan.nextInt();
                     }while(c.evaluate(row,seat) != true);
                break;
                 case 3:
                     c.showStat();
                     break;

             }
        }while(ans != 0);



    }
}