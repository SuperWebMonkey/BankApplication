package linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import enums.LinkedListStatus;

public class LinkedListMain {
    private final static Logger LOGGER = LogManager.getLogger(LinkedListMain.class);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Double> list = new LinkedList<Double>();

        int input;

        do {
            showMenu();
            input = scan.nextInt();

            switch (input) {
                case 0:
                    LOGGER.info("You are quitting the program.");
                    break;
                case 1:
                    LOGGER.info("Add to the linked list:");
                    double choice = scan.nextDouble();

                    list.add(choice);
                    break;
                case 2:
                    LOGGER.info("You are deleting from the linked list.");

                    try {
                        list.remove();
                    } catch (Exception e) {
                        LOGGER.info("List is empty");
                    }

                    break;
                case 3:
                    list.print();
                    break;
                case 4:
                    LOGGER.info("Enter in the value:");
                    double addValue = scan.nextDouble();
                    LOGGER.info("Enter the position:");
                    int addPos = scan.nextInt();

                    list.addInPos(addValue, addPos);
                    break;
                case 5:
                    LOGGER.info("Enter the position you want to delete:");
                    int delPos = scan.nextInt();

                    list.removeInPos(delPos);
                    break;
                default:
                    LOGGER.info("You have selected an invalid option");
                    break;
            }
        } while (input != 0);
    }

    public static void showMenu() {
        LOGGER.info("0) Quit the menu");
        LOGGER.info("1) Add to node");
        LOGGER.info("2) Delete a node");
        LOGGER.info("3) Print the linked list");
        LOGGER.info("4) Add node at index");
        LOGGER.info("5) Delete node at index");
    }
}
