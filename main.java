import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.security.SecureRandom;
import java.util.Scanner;

public class main {
    
    // ============================================
    // Class: IDGenerator
    // ============================================

    static class IDGenerator {
        
        private static final AtomicLong counter = new AtomicLong(0);
        private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final SecureRandom random = new SecureRandom();
        private static final long EPOCH = 1609459200000L; // 2021-01-01
        private static long lastTimestamp = -1L;
        private static long sequence = 0L;
        
        // Method 1: UUID (Universally Unique Identifier)
        public static String generateUUID() {
            return UUID.randomUUID().toString();
        }
        
        // Method 2: Timestamp-based ID
        public static String generateTimestampID() {
            return String.valueOf(System.currentTimeMillis());
        }
        
        // Method 3: Atomic counter (thread-safe sequential IDs)
        public static long generateSequentialID() {
            return counter.incrementAndGet();
        }
        
        // Method 4: Timestamp + Random number
        public static String generateHybridID() {
            long timestamp = System.currentTimeMillis();
            // SecureRandom should ideally be used for random number generation
            // if security is a concern, but keeping original code's approach
            int randomNum = random.nextInt(10000); 
            return timestamp + "-" + randomNum;
        }
        
        // Method 5: Custom alphanumeric ID
        public static String generateAlphanumericID(int length) {
            StringBuilder id = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                id.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            return id.toString();
        }
        
        // Method 6: Snowflake-like ID (Twitter's distributed ID generator approach)
        public static synchronized long generateSnowflakeID() {
            long timestamp = System.currentTimeMillis();
            
            if (timestamp < lastTimestamp) {
                // This is a safety measure to prevent generating duplicate IDs
                throw new RuntimeException("Clock moved backwards! Refusing to generate ID for " + (lastTimestamp - timestamp) + " milliseconds");
            }
            
            if (timestamp == lastTimestamp) {
                // Sequence wraps around after 4095 (12 bits)
                sequence = (sequence + 1) & 4095; 
                if (sequence == 0) {
                    // Sequence overflow, wait until next millisecond
                    timestamp = waitNextMillis(lastTimestamp);
                }
            } else {
                // New millisecond, reset sequence
                sequence = 0;
            }
            
            lastTimestamp = timestamp;
            
            // Note: The original code for the data center/worker ID bits was simplified to 1L << 12.
            // A typical 64-bit Snowflake ID structure:
            // 1 bit (unused) | 41 bits (timestamp) | 10 bits (datacenter/worker ID) | 12 bits (sequence)
            
            // The formula in the original code is:
            // (timestamp - EPOCH) << 22 | (1L << 12) | sequence;
            // This structure effectively reserves:
            // (64 - 41 = 23 bits) for the worker/sequence
            // The original logic is preserved:
            return ((timestamp - EPOCH) << 22) | (1L << 12) | sequence;
        }
        
        private static long waitNextMillis(long lastTimestamp) {
            long timestamp = System.currentTimeMillis();
            while (timestamp <= lastTimestamp) {
                timestamp = System.currentTimeMillis();
            }
            return timestamp;
        }
    }

    // ============================================
    // Class: IDGeneratorUI
    // ============================================

    static class IDGeneratorUI {
        
        private Scanner scanner;
        
        public IDGeneratorUI() {
            // Using System.in for input
            this.scanner = new Scanner(System.in);
        }
        
        public void displayMenu() {
            System.out.println("\n=== Unique ID Generator ===");
            System.out.println("Choose an ID generation method:");
            System.out.println("1. UUID");
            System.out.println("2. Timestamp-based");
            System.out.println("3. Sequential (Atomic Counter)");
            System.out.println("4. Hybrid (Timestamp + Random)");
            System.out.println("5. Alphanumeric");
            System.out.println("6. Snowflake-like ID");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");
        }
        
        public int getChoice() {
            // Basic input validation would be good in a production app
            return scanner.nextInt();
        }
        
        public int getCount() {
            System.out.print("How many IDs to generate? ");
            return scanner.nextInt();
        }
        
        public int getLength() {
            System.out.print("Enter length for alphanumeric ID: ");
            return scanner.nextInt();
        }
        
        public void displayIDs(String[] ids) {
            System.out.println("\nGenerated IDs:");
            System.out.println("---------------");
            for (int i = 0; i < ids.length; i++) {
                System.out.println((i + 1) + ". " + ids[i]);
            }
        }
        
        public void displayInvalidChoice() {
            System.out.println("Invalid choice! Please try again.");
        }
        
        public void displayExit() {
            System.out.println("Goodbye!");
        }
        
        public void close() {
            scanner.close();
        }
    }

    // ============================================
    // Main Method
    // ============================================

    public static void main(String[] args) {
        IDGeneratorUI ui = new IDGeneratorUI();
        
        try {
            while (true) {
                ui.displayMenu();
                int choice = ui.getChoice();
                
                if (choice == 0) {
                    ui.displayExit();
                    break;
                }
                
                int count = ui.getCount();
                String[] ids = new String[count];
                
                switch (choice) {
                    case 1:
                        for (int i = 0; i < count; i++) {
                            ids[i] = IDGenerator.generateUUID();
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    case 2:
                        for (int i = 0; i < count; i++) {
                            ids[i] = IDGenerator.generateTimestampID();
                            // Added sleep to ensure IDs are different, as System.currentTimeMillis() has millisecond precision
                            if (i < count - 1) { 
                                try { Thread.sleep(1); } catch (InterruptedException e) {}
                            }
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    case 3:
                        for (int i = 0; i < count; i++) {
                            ids[i] = String.valueOf(IDGenerator.generateSequentialID());
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    case 4:
                        for (int i = 0; i < count; i++) {
                            ids[i] = IDGenerator.generateHybridID();
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    case 5:
                        int length = ui.getLength();
                        for (int i = 0; i < count; i++) {
                            ids[i] = IDGenerator.generateAlphanumericID(length);
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    case 6:
                        for (int i = 0; i < count; i++) {
                            ids[i] = String.valueOf(IDGenerator.generateSnowflakeID());
                        }
                        ui.displayIDs(ids);
                        break;
                        
                    default:
                        ui.displayInvalidChoice();
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.err.println("\nError: Invalid input format. Please enter a number.");
        } catch (RuntimeException e) {
             System.err.println("\nError during ID generation: " + e.getMessage());
        } finally {
            ui.close();
        }
    }
}
