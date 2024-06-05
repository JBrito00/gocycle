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
import isel.sisinf.model.Reservation;
//import isel.sisinf.model.procedures.MakeBooking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Connection;
//import java.sql.SQLException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker
{
    void doWork();
}
class UI
{
    private enum Option
    {
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

    private HashMap<Option,DbWorker> __dbMethods;

    private UI()
    {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> UI.this.createCustomer());
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes());
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {public void doWork() {UI.this.obtainBookings();}});
        __dbMethods.put(Option.makeBooking, new DbWorker() {public void doWork() {UI.this.makeBooking();}});
        __dbMethods.put(Option.cancelBooking, new DbWorker() {public void doWork() {UI.this.cancelBooking();}});
        __dbMethods.put(Option.about, new DbWorker() {public void doWork() {UI.this.about();}});

    }

    public static UI getInstance()
    {
        // DO NOT CHANGE ANYTHING!
        if(__instance == null)
        {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu()
    {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try
        {
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
        }
        catch(RuntimeException ex)
        {
            //nothing to do.
        }

        return option;

    }
    private static void clearConsole() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        Option userInput;
        do
        {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try
            {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            }
            catch(NullPointerException ex)
            {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput!=Option.Exit);
    }

    /**
     * To implement from this point forward. Do not need to change the code above.
     * -------------------------------------------------------------------------------
     * IMPORTANT:
     * --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
     * --- Other Methods and properties can be added to support implementation -------
     * -------------------------------------------------------------------------------
     */

    private static final int TAB_SIZE = 24;

    private void createCustomer() {
        System.out.println("createCustomer()");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
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

            em.getTransaction().begin(); // Iniciar a transação

            String sql = "CALL public.createCustomer(?1, ?2, ?3, ?4, ?5, ?6)";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, name);
            query.setParameter(2, address);
            query.setParameter(3, email);
            query.setParameter(4, phone);
            query.setParameter(5, cc);
            query.setParameter(6, nationality);
            query.executeUpdate();

            em.getTransaction().commit(); // Commitar a transação
            System.out.printf("Customer %s created successfully\n", name);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback da transação em caso de erro
            }
            e.printStackTrace(); // Melhor para depuração do que System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }


    private void listExistingBikes() {
        // TODO
        System.out.println("listExistingBikes()");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            // em.getTransaction().begin();
            String sql = "SELECT * FROM Bicycle";
            Query query = em.createNativeQuery(sql);
            List<Bicycle> result = query.getResultList();
            System.out.println("Bicycle List");
            System.out.println("ID\tModel");
            for (Bicycle b : result) {
                System.out.printf("%d\t%s\n", b.getId(), b.getModel());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    private void checkBikeAvailability() {
        // TODO
        System.out.println("checkBikeAvailability()");
        //Verificar a disponibilidade de uma bicicleta num momento no tempo (têm de usar uma função)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Bike ID:");
            int bikeId = s.nextInt();
            String sql = "SELECT * FROM Bicycle WHERE bicycleId = ?1";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, bikeId);
            List<Bicycle> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("Bike not found");
            } else {
                Bicycle b = result.get(0);
                System.out.printf("Bike %d is %s\n", b.getId(), b.getStatus());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

    }

    private void obtainBookings() {
        // TODO
        System.out.println("obtainBookings()");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT * FROM Reservation";
            Query query = em.createNativeQuery(sql);
            List<Reservation> result = query.getResultList();
            System.out.println("Reservation List");
            System.out.println("ID\tBicycle\tStart\tEnd\tPrice");
            for (Reservation r : result) {
                System.out.printf("%d\t%s\t%s\t%s\t%.2f\n", r.getNumber(), r.getBicycle(), r.getBeginingDate(), r.getEndingDate(), r.getPrice());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }


    }

    private void makeBooking() {
        // TODO
//        System.out.println("makeBooking()");
//        //Make a reservation (it has to use a stored procedure)
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
//        EntityManager em = emf.createEntityManager();
//        try {
//            Scanner s = new Scanner(System.in);
//            System.out.println("Booking ID:");
//            int bookingId = s.nextInt();
//            System.out.println("Customer ID:");
//            int customerId = s.nextInt();
//            System.out.println("Bike ID:");
//            int bikeId = s.nextInt();
//            System.out.println("Start Date:");
//            String startDateTime = s.nextLine();
//            System.out.println("End Date:");
//            String endDateTime = s.nextLine();
//            System.out.println("Price:");
//            double price = s.nextDouble();
//            new MakeBooking().makeBooking(bookingId, customerId, bikeId, startDateTime, endDateTime, price);
//            System.out.println("Booking created successfully");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw e;
//        } finally {
//            em.close();
//            emf.close();
//        }

    }

    private void cancelBooking() {
        // TODO
        System.out.println("cancelBooking");

    }

    private void about() {
        // TODO: Add your Group ID & member names
        System.out.println("DAL version:" + isel.sisinf.jpa.Dal.version());
        System.out.println("Core version:" + isel.sisinf.model.Core.version());

    }
}

public class App {
    public static void main(String[] args) throws Exception {
        UI.getInstance().Run();
    }
}