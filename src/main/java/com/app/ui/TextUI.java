package com.app.ui;

import com.app.domain.guest.Gender;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.Reservation;
import com.app.domain.reservation.ReservationService;
import com.app.domain.room.BedType;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.exceptions.OnlyNumberException;
import com.app.exceptions.PersistenceToFileException;
import com.app.exceptions.WrongOptionException;
import com.app.utils.Utils;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();
    private final ReservationService reservationService = new ReservationService();

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
        Gender[] genders = Gender.values();
        for (Gender gender : genders) {
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


    public void showSystemInfo() {
        System.out.print("Welcome to the reservation system for the ");
        System.out.println(Utils.HOTEL_NAME);
        System.out.print("Current system version: ");
        System.out.println(Utils.SYSTEM_VERSION);
        System.out.print("Development version: ");
        System.out.println(Utils.IS_DEVELOPER_VERSION);
        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {
        System.out.println("Loading data in progress...");
        this.guestService.readAllGuestsFromFile();
        this.roomService.readAllRoomsFromFile();
//        this.reservationService.readAllReservationsFromFile();
        Scanner input = new Scanner(System.in);
        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException | PersistenceToFileException e) {
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


    private void editGuestData(Scanner input) {
        System.out.println("Enter the guest ID number to edit.");
        try {
            int guestId = input.nextInt();
            System.out.print("Please enter a first name: ");
            String firstName = input.next();
            System.out.print("Please enter a last name: ");
            String lastName = input.next();
            System.out.print("Please enter the age: ");
            int age = input.nextInt();
            System.out.println("Please, specify your gender: ");
            showAvailableGenders();
            int gender = input.nextInt();
            guestService.editGuestFromList(guestId, firstName, lastName, age, gender);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use the numbers when editing guest.");
        }
    }

    private void editRoomData(Scanner input) {
        System.out.println("Enter the room ID number to edit.");
        try {
            int roomId = input.nextInt();
            System.out.println("Room number: ");
            int numberRoom = input.nextInt();
            int[] bedType = chooseBedType(input);
            roomService.editGuestFromList(roomId, numberRoom, bedType);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room.");
        }
    }

    private void removeGuest(Scanner input) {
        System.out.println("Enter the guest ID number to delete.");
        try {
            int guestId = input.nextInt();
            guestService.removeGuestFromList(guestId);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use the numbers when inserting ID.");
        }
    }

    private void removeRoom(Scanner input) {
        System.out.println("Enter the guest ID number to delete.");
        try {
            int guestId = input.nextInt();
            roomService.removeRoomFromList(guestId);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use the numbers when inserting ID.");
        }
    }


    private void showRoomList() {
        List<Room> rooms = this.roomService.getAllRooms();

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private void showGuestList() {
        List<Guest> guests = this.guestService.getAllGuests();

        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }


    private static int getActionFromUser(Scanner in) {
        System.out.println();
        System.out.println("GUEST");
        System.out.println("1. Add new guest.");
        System.out.println("2. Edit guest.");
        System.out.println("3. Remove guest.");
        System.out.println("4. Show guest list.");
        System.out.println("ROOM");
        System.out.println("5. Add new room.");
        System.out.println("6. Edit room.");
        System.out.println("7. Remove room.");
        System.out.println("8. Show room list.");
        System.out.println("RESERVATION");
        System.out.println("9. Create a reservation.");
        System.out.println();
        System.out.println("0. Exit from the program.");
        System.out.println();
        System.out.println("Select the option: ");
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu.");
        }
        return option;
    }

    private void performAction(Scanner input) {
        int option;
        do {
            option = getActionFromUser(input);
            switch (option) {
                case 0 -> {
                    System.out.println("I am leaving the application. Saves the data to a file.");
                    this.guestService.writeAllGuestsToFile();
                    this.roomService.writeAllRoomsToFile();
//                    this.reservationService.writeAllReservationsToFile();
                }
                case 1 -> readNewGuestData(input);
                case 2 -> editGuestData(input);
                case 3 -> removeGuest(input);
                case 4 -> showGuestList();
                case 5 -> readNewRoomData(input);
                case 6 -> editRoomData(input);
                case 7 -> removeRoom(input);
                case 8 -> showRoomList();
                case 9 -> createReservation(input);
                default -> throw new WrongOptionException("Wrong option in main menu.");
            }
        } while (option != 0);
    }

    private void createReservation(Scanner input) {
        System.out.println("From when the reservation(DD.MM.YYYY):");
        String fromAsString = input.next();
        LocalDate from = LocalDate.parse(fromAsString, Utils.DATE_TIME_FORMATTER);
        System.out.println("Until when the reservation(DD.MM.YYYY):");
        String toAsString = input.next();
        LocalDate to = LocalDate.parse(toAsString, Utils.DATE_TIME_FORMATTER);
        System.out.println("Please enter the room id");
        int roomId = input.nextInt();
        System.out.println("Please enter the guest id");
        int guestId = input.nextInt();
        Reservation reservation = reservationService.createNewReservation(from, to, roomId, guestId);
        if (reservation != null) {
            System.out.println("It was possible to create a reservation.");
        }
    }


    public static Gender chooseGenderFromNumberValue(int userValue) {
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            if (gender.getValue() == userValue) {
                return gender;
            }
        }
        throw new WrongOptionException("Wrong option when selecting gender");
    }

    public static BedType chooseBedTypeFromNumberValue(int userValue) {
        BedType[] values = BedType.values();
        for (BedType bedType : values) {
            if (bedType.getValue() == userValue)
                return bedType;
        }
        throw new WrongOptionException("Wrong option when selecting bed type");
    }


}
