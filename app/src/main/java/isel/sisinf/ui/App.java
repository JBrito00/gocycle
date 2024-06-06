/*
MIT License

Copyright (c) 2024, Nuno Datia, Matilde Pato, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.ui;

import isel.sisinf.model.Bicycle;
import isel.sisinf.model.Customer;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.procedures.Service;
import isel.sisinf.model.procedures.ServiceWithSQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker {
    void doWork();
}

class UI {
    private enum Option {
        // DO NOT CHANGE ANYTHING!
        Unknown,
        Exit,
        createCostumer,
        listExistingBikes,
        checkBikeAvailability,
        obtainBookings,
        makeBooking,
        cancelBooking,
        about
    }

    private static UI __instance = null;

    private HashMap<Option, DbWorker> __dbMethods;

    private UI() {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option, DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> UI.this.createCustomer());
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes());
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {
            public void doWork() {
                UI.this.obtainBookings();
            }
        });
        __dbMethods.put(Option.makeBooking, new DbWorker() {
            public void doWork() {
                UI.this.makeBooking();
            }
        });
        __dbMethods.put(Option.cancelBooking, new DbWorker() {
            public void doWork() {
                UI.this.cancelBooking();
            }
        });
        __dbMethods.put(Option.about, new DbWorker() {
            public void doWork() {
                UI.this.about();
            }
        });

    }

    public static UI getInstance() {
        // DO NOT CHANGE ANYTHING!
        if (__instance == null) {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu() {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try {
            // DO NOT CHANGE ANYTHING!
            System.out.println("Bicycle reservation");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Create Costumer");
            System.out.println("3. List Existing Bikes");
            System.out.println("4. Check Bike Availability");
            System.out.println("5. Current Bookings");
            System.out.println("6. Make a booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. About");
            System.out.print(">");
            int result = s.nextInt();
            option = Option.values()[result];
        } catch (RuntimeException ex) {
            //nothing to do.
        }
        return option;
    }

    private static void clearConsole() throws Exception {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception {
        // DO NOT CHANGE ANYTHING!
        Option userInput;
        do {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            } catch (NullPointerException ex) {
                //Nothing to do. The option was not a valid one. Read another.
            }

        } while (userInput != Option.Exit);
    }

    /**
     * To implement from this point forward. Do not need to change the code above.
     * -------------------------------------------------------------------------------
     * IMPORTANT:
     * --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
     * --- Other Methods and properties can be added to support implementation -------
     * -------------------------------------------------------------------------------
     */

    private void createCustomer() {
        System.out.println("createCustomer()");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Name:");
            String name = scanner.nextLine();
            System.out.println("Address:");
            String address = scanner.nextLine();
            System.out.println("Email:");
            String email = scanner.nextLine();
            System.out.println("Phone:");
            String phone = scanner.nextLine();
            System.out.println("CC:");
            String cc = scanner.nextLine();
            System.out.println("Nationality:");
            String nationality = scanner.nextLine();
            new ServiceWithSQL().createCustomer(name, address, email, phone, cc, nationality);
            System.out.println("Customer created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Melhor para depuração do que System.out.println(e.getMessage());
        }
    }

    private void listExistingBikes() {
        System.out.println("listExistingBikes()");

        try {
            ServiceWithSQL service = new ServiceWithSQL();
            service.listExistingBikes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkBikeAvailability() {
        System.out.println("checkBikeAvailability()");
        Scanner s = new Scanner(System.in);

        try {
            System.out.println("Bike ID:");
            int bikeId = Integer.parseInt(s.nextLine());
            System.out.println("Date (format: YYYY-MM-DD HH:MI:SS):");
            String date = s.nextLine();
            ServiceWithSQL service = new ServiceWithSQL();
            service.checkBikeAvailability(bikeId, date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtainBookings() {
        System.out.println("obtainBookings()");

        try {
            ServiceWithSQL service = new ServiceWithSQL();
            service.obtainBookings();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void makeBooking() {
        System.out.println("makeBooking()");
        Scanner s = new Scanner(System.in);

        try {
            System.out.println("Customer ID:");
            int customerId = s.nextInt();
            System.out.println("Bike ID:");
            int bikeId = s.nextInt();
            System.out.println("Start Date:");
            String startDateTime = s.nextLine();
            System.out.println("End Date:");
            String endDateTime = s.nextLine();
            System.out.println("Price:");
            double price = s.nextDouble();
            new ServiceWithSQL().makeBooking(customerId, bikeId, startDateTime, endDateTime, price);
            System.out.println("Booking created successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cancelBooking() {
        System.out.println("cancelBooking");
        Scanner s = new Scanner(System.in);

        try {
            System.out.println("reservation ID:");
            int reservationId = Integer.parseInt(s.nextLine());
            new ServiceWithSQL().cancelBooking(reservationId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void about() {
        // Adicionar ID do Grupo e nomes dos membros
        int groupId = 22; // Substitua pelo ID do seu grupo
        String[] groupMembers = {"Mariana Muñoz 47076", "João Brito 47272", "Iara Costa 48927"};

        System.out.println("Group ID: " + groupId);
        System.out.println("Group Members:");
        for (String member : groupMembers) {
            System.out.println(" - " + member);
        }

        System.out.println("DAL version: " + isel.sisinf.jpa.Dal.version());
        System.out.println("Core version: " + isel.sisinf.model.Core.version());
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        UI.getInstance().Run();
    }
}