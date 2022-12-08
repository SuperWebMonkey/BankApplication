package LinkedList;
import java.util.Scanner;

public class LinkedListMain {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        LinkedList<Double> list = new LinkedList<Double>();

        int input;

        do {
            menu();
            input = scan.nextInt();

            switch(input) {
                case 0:
                    System.out.println("You are quitting the program.");
                    break;
                case 1:
                    System.out.println("Add to the linked list:");
                    double choice = scan.nextDouble();

                    list.add(choice);
                    break;
                case 2:
                    System.out.println("You are deleting from the linked list.");
                    try {
                        list.remove();
                    } catch(Exception e){
                        System.out.println("List is empty");
                    }
                    break;
                case 3:
                    list.print();
                    break;
                default:
                    System.out.println("You have selected an invalid option");
            }
        } while(input != 0);
    }
    public static void menu(){
        System.out.println("0) Quit the menu");
        System.out.println("1) Add to node");
        System.out.println("2) Delete a node");
        System.out.println("3) Print the linked list");
    }
}
