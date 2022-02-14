package com.app.ui;

import com.app.domain.guest.Gender;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.BedType;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.exceptions.OnlyNumberException;
import com.app.exceptions.WrongOptionException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    private void readNewGuestData(Scanner input) {
        System.out.println("We create a new guest.");
        try {
            System.out.print("Please enter a first name: ");
            String firstName = input.next();
            System.out.print("Please enter a last name: ");
            String lastName = input.next();
            System.out.print("Please enter the age: ");
            int age = input.nextInt();
            System.out.println("Please, specify your gender: ");
            showAvailableGenders();
            int gender = input.nextInt();
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, gender);
            System.out.println("New visitors added: " + newGuest);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender.");
        }
    }

    private void showAvailableGenders() {
        Gender[]  genders = Gender.values();
        for(Gender gender : genders) {
            System.out.println(gender + " - choose: " + gender.getValue());
        }
    }


    private void readNewRoomData(Scanner input) {
        System.out.println("Creating a new room.");
        try {
            System.out.println("Room number: ");
            int numberRoom = input.nextInt();
            int[] bedType = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(numberRoom, bedType);
            System.out.println("A new room has been created:\n" + newRoom);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room.");
        }
    }



    private int[] chooseBedType(Scanner input) {
        System.out.println("Enter the number of beds in the room");
        int bedNumber = input.nextInt();
        int[] bedTypes = new int[bedNumber];
        for (int i = 0; i < bedTypes.length; i++) {
            availableBedTypes();
            int bedTypeOption = input.nextInt();
            bedTypes[i] = bedTypeOption;
        }
        return bedTypes;
    }

    private void availableBedTypes() {
        System.out.println("Available bed types. Select a number.");
        BedType[] availableBedTypes = BedType.values();
        for (BedType bedType : availableBedTypes) {
            System.out.println(bedType + "- choose: " + bedType.getValue());
        }
    }


    public void showSystemInfo(String hotelName, int currentSystemVersion, boolean isDeveloperVersion) {
        System.out.print("Welcome to the reservation system for the ");
        System.out.println(hotelName);
        System.out.print("Current system version: ");
        System.out.println(currentSystemVersion);
        System.out.print("Development version: ");
        System.out.println(isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {
        Scanner input = new Scanner(System.in);
        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("An unexpected error has occurred.");
            System.out.println("Error code: " + e.getCode());
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred.");
            System.out.println("Unknown error code.");
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void performAction(Scanner input) {
        int option;
        do {
            option = getActionFromUser(input);
            switch (option) {
                case 0 -> System.out.println("I am leaving the application");
                case 1 -> readNewGuestData(input);
                case 2 -> readNewRoomData(input);
                case 3 -> showGuestList();
                case 4 -> showRoomList();
                default -> throw new WrongOptionException("Wrong option in main menu.");
            }
        } while (option != 0);
    }

    private void showRoomList() {
        List<Room> rooms = roomService.getAllRooms();

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private void showGuestList() {
        List<Guest> guests = guestService.getAllGuests();

        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }


    private static int getActionFromUser(Scanner in) {
        System.out.println();
        System.out.println("1. Add new guest.");
        System.out.println("2. Add new room.");
        System.out.println("3. Show guest list.");
        System.out.println("4. Show room list.");
        System.out.println("Select the option: ");
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu.");
        }
        return option;
    }


}
