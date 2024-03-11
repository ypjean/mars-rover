package com.mars.rover;

import com.mars.rover.impl.CommandCentre;
import com.mars.rover.impl.CommandCentreImpl;
import com.mars.rover.model.Rover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@SpringBootApplication
@ComponentScan(basePackages = "com.mars.rover")
public class MarsRoverApp {

    private static CommandCentre commandCentre;
    private static Scanner scanner = new Scanner(in);

    @Autowired
    public MarsRoverApp(CommandCentreImpl commandCentre) {
        this.commandCentre = commandCentre;
    }

    public static void main(String[] args) {
        SpringApplication.run(MarsRoverApp.class, args);

        out.println("\n\nWelcome to Mars Rover Program!");
        while (true) {
            printMenu();
            String selection = scanner.nextLine().strip();
            executeSelection(selection);
        }
    }

    private static void executeSelection(String selection) {
        switch (selection) {
            case "1" -> viewRovers();
            case "2" -> deployRover();
            case "3" -> moveRover();
            case "4" -> {
                out.println("\nExiting program");
                System.exit(0);
            }
            default -> out.println("\nInvalid selection");
        }
    }

    private static void moveRover() {
        out.print("Enter Id of rover: ");
        String id = scanner.nextLine();
        out.print("Enter your command: ");
        String command = scanner.nextLine();
        try {
            Rover rover = commandCentre.executeCommand(Long.valueOf(id), command);
            printRoverLocation(rover);
        } catch (Exception ex) {
            out.println("\nCannot move rover, " + ex.getMessage());
        }
    }

    private static void deployRover() {
        out.print("Enter name of rover: ");
        String name = scanner.nextLine();
        out.print("Enter location of rover: ");
        String location = scanner.nextLine();

        try {
            Rover rover = commandCentre.deployRover(name, location);
            out.println("\nRover Deployed Successfully!");
            printRoverLocation(rover);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        }

    }

    private static void viewRovers() {
        try {
            List<Rover> rovers = commandCentre.getRovers();
            if (rovers.isEmpty()) {
                out.println("\nNo rovers deployed");
                return;
            }
            out.println("\nDeployed Rovers:");
            for (Rover r : rovers) {
                out.println("Id: " + r.getId() + ", Name: " + r.getName() + ", Coordinates: "
                        + r.coordinates() + ", Direction: " + r.getDirection());
            }
        } catch (Exception ex) {
            out.println("\nUnable to obtain rovers due to " + ex.getMessage());
        }
    }

    private static void printMenu() {
        out.println("\nPlease select what you would like to do");
        out.println("1   View rovers");
        out.println("2   Deploy new rover");
        out.println("3   Move rover");
        out.println("4   Exit program");
        out.print("Enter the action number: ");
    }

    private static void printRoverLocation(Rover rover) {
        out.println("Id: " + rover.getId());
        out.println("Rover: "+ rover.getName());
        out.println("Coordinates: " + rover.coordinates());
        out.println("Direction: " + rover.getDirection().toString());
    }

}
