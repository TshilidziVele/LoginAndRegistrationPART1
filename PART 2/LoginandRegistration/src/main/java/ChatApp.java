/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tshilidzi
 */
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/* ----------------------------------------*/


class User {

    String firstName;
    String username;
    String hashedPassword;
    String phone;


    public User(String firstName,
                String username,
                String hashedPassword,
                String phone) {

        this.firstName      = firstName;
        this.username       = username;
        this.hashedPassword = hashedPassword;
        this.phone          = phone;
    }
}


/**************************************************/


class Message {


    /* INSTANCES */
    private final String messageId;
    private final String recipient;
    private final String messageText;
    private final String messageHash;


    /*************************************************/

    private static int            numSent     = 0;
    private static final List<String[]> allMessages = new ArrayList<>();

    /*
     * Each String[] stores:
     * [0] messageNumber
     * [1] messageId
     * [2] sender
     * [3] recipient
     * [4] messageText
     * [5] hash
     * [6] status
     */

    /*****************************************************/

    public Message(String recipient, String messageText) {

        this.recipient   = recipient;
        this.messageText = messageText;
        this.messageId   = generateMessageId();
        this.messageHash = createMessageHash();
    }


    /******************************************************/

    private String generateMessageId() {

        Random rand = new Random();
        long id = 1_000_000_000L
                + (long) (rand.nextDouble() * 9_000_000_000L);
        return String.valueOf(id);
    }

    /*********************************/

    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    /***************************************************/

    public String checkRecipientCell() {

        String cell = recipient.replaceAll("\\s+", "");

        if (cell.isEmpty() || !cell.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does "
                 + "not contain an international code. Please correct "
                 + "the number and try again.";
        }

        String afterPlus = cell.substring(1);

        for (char c : afterPlus.toCharArray()) {
            if (!Character.isDigit(c)) {
                return "Cell phone number is incorrectly formatted or "
                     + "does not contain an international code. Please "
                     + "correct the number and try again.";
            }
        }

        /*******************************************/

        if (afterPlus.length() < 9 || afterPlus.length() > 13) {
            return "Cell phone number is incorrectly formatted or does "
                 + "not contain an international code. Please correct "
                 + "the number and try again.";
        }

        return "Cell phone number successfully captured.";
    }

    /***************************************************/

    public String checkMessageLength() {

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = messageText.length() - 250;
        return "Message exceeds 250 characters by " + excess
             + "; please reduce the size.";
    }

    /*-------------------------------------------------------*/

    public String createMessageHash() {

        String[] words = messageText.trim().split("\\s+");

        String first = words[0]
                .replaceAll("[^a-zA-Z]", "")
                .toUpperCase();

        String last = words[words.length - 1]
                .replaceAll("[^a-zA-Z]", "")
                .toUpperCase();

        // Check word with no letters
        if (first.isEmpty()) first = "?";
        if (last.isEmpty())  last  = "?";

        return String.format("%02d:%d:%s%s",
                numSent, numSent, first, last);
    }

    /**************************************************/

    public String SentMessage(int choice, String sender) {

        switch (choice) {

            case 1:
                numSent++;
                allMessages.add(buildRecord(sender, numSent, "sent"));
                storeMessage();
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                numSent++;
                allMessages.add(buildRecord(sender, numSent, "stored"));
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    /******************************************/

    public static String printMessages() {

        if (allMessages.isEmpty()) {
            return "No messages sent yet.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n--- All Sent / Stored Messages ---\n");

        for (int i = 0; i < allMessages.size(); i++) {

            String[] m = allMessages.get(i);

            sb.append("Message #   : ").append(m[0]).append("\n");
            sb.append("Message ID  : ").append(m[1]).append("\n");
            sb.append("Recipient   : ").append(m[3]).append("\n");
            sb.append("Message     : ").append(m[4]).append("\n");
            sb.append("Hash        : ").append(m[5]).append("\n");
            sb.append("Status      : ").append(m[6]).append("\n");
            sb.append("-----------------------------------------\n");
        }

        return sb.toString();
    }

    /************************************************/

    public static int returnTotalMessages() {
        return numSent;
    }

    /******************************************/

    public void storeMessage() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("messages.txt"))) {

            for (String[] m : allMessages) {

                writer.write("messageNumber=" + m[0]);  writer.newLine();
                writer.write("messageId="     + m[1]);  writer.newLine();
                writer.write("sender="        + m[2]);  writer.newLine();
                writer.write("recipient="     + m[3]);  writer.newLine();
                writer.write("message="       + m[4]);  writer.newLine();
                writer.write("hash="          + m[5]);  writer.newLine();
                writer.write("status="        + m[6]);  writer.newLine();
                writer.write("---");                     writer.newLine();
            }

        }
        catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    /*************************************************/

    private String[] buildRecord(String sender,
                                 int    messageNumber,
                                 String status) {

        return new String[] {
            String.valueOf(messageNumber),
            messageId,
            sender,
            recipient,
            messageText,
            messageHash,
            status
        };
    }


    public String getMessageId()   { return messageId;   }
    public String getMessageHash() { return messageHash; }
    public String getRecipient()   { return recipient;   }
    public String getMessageText() { return messageText; }
}


/****************************************************/


public class ChatApp {

    static User user;

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("---- REGISTER ----");

            /* ---- First Name ---- */

            String firstName;

            while (true) {

                System.out.print("Enter first name (cannot be empty): ");
                firstName = input.nextLine().trim();

                if (!firstName.isEmpty()) {
                    System.out.println("First name successfully captured.");
                    break;
                }
                else {
                    System.out.println("Error: First name cannot be empty.");
                }
            }

            /* ---- Username ---- */

            String username;

            while (true) {

                System.out.print("Enter username (must contain '_' and max 5 characters): ");
                username = input.nextLine();

                String error = getUsernameError(username);

                if (error == null) {
                    System.out.println("Username successfully captured.");
                    break;
                }
                else {
                    System.out.println("Error: " + error);
                }
            }

            /* ---- Password ---- */

            String hashedPassword;

            while (true) {

                System.out.print("Enter password (minimum 8 characters, "
                        + "1 uppercase, 1 number, 1 special character): ");

                String rawPassword = input.nextLine();
                List<String> errors = getPasswordErrors(rawPassword);

                if (errors.isEmpty()) {
                    hashedPassword = hashPassword(rawPassword);
                    System.out.println("Password successfully captured.");
                    break;
                }
                else {
                    System.out.println("Error: Password requirements not met:");
                    for (String err : errors) {
                        System.out.println("  - " + err);
                    }
                }
            }

            /* ---- Phone ---- */

            String phone;

            while (true) {

                System.out.print("Enter cell number (must start with +27 then 9 digits): ");
                phone = input.nextLine();

                String error = getPhoneError(phone);

                if (error == null) {
                    System.out.println("Cell number successfully captured.");
                    break;
                }
                else {
                    System.out.println("Error: " + error);
                }
            }

            user = new User(firstName, username, hashedPassword, phone);

            if (!saveUserToFile(user)) {
                System.out.println("Critical error: Could not save user data.");
                return;
            }

            System.out.println("\n============================");
            System.out.println("Registered successfully!");
            System.out.println("============================\n");

            System.out.println("Please login with the details you registered with.\n");
            System.out.println("----- LOGIN -----");

            /* ---- Username ---- */

            while (true) {

                System.out.print("Enter username: ");
                String entered = input.nextLine();

                if (entered.equals(user.username)) {
                    break;
                }
                else {
                    System.out.println("Error: Username is incorrect. Please try again.");
                }
            }

            /* ---- Password ---- */

            while (true) {

                System.out.print("Enter password: ");
                String entered = input.nextLine();

                if (hashPassword(entered).equals(user.hashedPassword)) {
                    break;
                }
                else {
                    System.out.println("Error: Password is incorrect. Please try again.");
                }
            }

            /* ---- Cell Number ---- */

            while (true) {

                System.out.print("Enter cell number: ");
                String entered = input.nextLine().replaceAll("\\s+", "");

                if (entered.equals(user.phone.replaceAll("\\s+", ""))) {
                    break;
                }
                else {
                    System.out.println("Error: Cell number is incorrect. Please try again.");
                }
            }

            /* ---- Login success ---- */

            System.out.println("\n---------------------------");
            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.firstName + "!");
            System.out.println("Welcome to QuickChat.");
            System.out.println("---------------------------");

            int totalMessages = 0;

            while (true) {

                System.out.print("\nHow many messages would you like to send? ");

                try {
                    totalMessages = Integer.parseInt(input.nextLine().trim());

                    if (totalMessages < 1) {
                        System.out.println("Error: Please enter a number greater than 0.");
                    }
                    else {
                        break;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a whole number.");
                }
            }

            boolean running = true;

            while (running) {

                System.out.println("\n===== QUICKCHAT MENU =====");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                String menuChoice = input.nextLine().trim();

                switch (menuChoice) {

                    case "1":

                        for (int i = 1; i <= totalMessages; i++) {

                            System.out.println("\nMessage " + i + " of " + totalMessages);

                            String recipient;

                            while (true) {
                                System.out.print("Enter recipient cell number: ");
                                recipient = input.nextLine();

                                if (checkRecipientCell(recipient)) {
                                    break;
                                }
                                else {
                                    System.out.println("Cell phone number is incorrectly "
                                            + "formatted or does not contain an "
                                            + "international code. Please correct "
                                            + "the number and try again.");
                                }
                            }

                            String messageText;

                            while (true) {
                                System.out.print("Enter your message: ");
                                messageText = input.nextLine();

                                if (messageText.length() <= 250) {
                                    break;
                                }
                                else {
                                    System.out.println("Please enter a message of "
                                            + "less than 250 characters.");
                                }
                            }

                            Message msg = new Message(recipient, messageText);

                            // Display generated info
                            System.out.println("Message ID generated: " + msg.getMessageId());
                            System.out.println("Message Hash: " + msg.getMessageHash());
                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());

                            /* -- Send / Discard / Store sub-menu -- */

                            System.out.println("\n1) Send Message");
                            System.out.println("2) Discard Message");
                            System.out.println("3) Store Message");

                            int subChoice = 0;

                            while (true) {
                                System.out.print("Choose an option: ");

                                try {
                                    subChoice = Integer.parseInt(input.nextLine().trim());

                                    if (subChoice >= 1 && subChoice <= 3) {
                                        break;
                                    }

                                    System.out.println("Please enter 1, 2, or 3.");
                                }
                                catch (NumberFormatException e) {
                                    System.out.println("Error: Please enter a number.");
                                }
                            }

                            String result = msg.SentMessage(subChoice, user.username);
                            System.out.println(result);

                            if (subChoice == 2) {
                                System.out.print("Press 0 to confirm deletion, "
                                        + "or any other key to keep: ");
                                String confirm = input.nextLine().trim();

                                if (confirm.equals("0")) {
                                    System.out.println("Message deleted.");
                                }
                                else {
                                    System.out.println("Message kept.");
                                }
                            }
                        }

                        System.out.println("\nTotal messages sent: "
                                + Message.returnTotalMessages());
                        break;

                    case "2":
                        System.out.println("Coming Soon.");
                        break;

                    case "3":
                        running = false;
                        System.out.println("Thank you for using QuickChat.");
                        break;

                    /* ---- Invalid input ---- */

                    default:
                        System.out.println("Invalid option selected.");
                }
            }
        }
    }

    public static boolean saveUserToFile(User u) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("user.txt"))) {

            writer.write("firstName="      + u.firstName);      writer.newLine();
            writer.write("username="       + u.username);       writer.newLine();
            writer.write("hashedPassword=" + u.hashedPassword); writer.newLine();
            writer.write("phone="          + u.phone);          writer.newLine();
            return true;
        }
        catch (IOException e) {
            System.out.println("Error saving user information: " + e.getMessage());
            return false;
        }
    }

    public static String getUsernameError(String username) {

        if (username.isEmpty()) {
            return "Username cannot be empty.";
        }
        if (!username.contains("_")) {
            return "Username must contain an underscore (_).";
        }
        if (username.length() > 5) {
            return "Username exceeds 5 characters.";
        }
        return null;
    }

    public static List<String> getPasswordErrors(String password) {

        List<String> errors = new ArrayList<>();

        if (password.length() < 8) {
            errors.add("Password must be at least 8 characters.");
        }

        boolean hasUppercase = false;
        boolean hasDigit     = false;
        boolean hasSpecial   = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c))      hasUppercase = true;
            if (Character.isDigit(c))          hasDigit     = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial   = true;
        }

        if (!hasUppercase) errors.add("Missing uppercase letter.");
        if (!hasDigit)     errors.add("Missing number.");
        if (!hasSpecial)   errors.add("Missing special character.");

        return errors;
    }

    public static String getPhoneError(String cell) {

        cell = cell.replaceAll("\\s+", "");

        if (cell.isEmpty()) {
            return "Phone number cannot be empty.";
        }
        if (!cell.startsWith("+27")) {
            return "Phone number must start with +27.";
        }
        if (cell.length() != 12) {
            return "Phone number must contain exactly 9 digits after +27.";
        }
        for (char c : cell.substring(3).toCharArray()) {
            if (!Character.isDigit(c)) {
                return "Phone number contains invalid characters.";
            }
        }
        return null;
    }

    public static String getRecipientError(String cell) {

        cell = cell.replaceAll("\\s+", "");

        if (cell.isEmpty() || !cell.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does "
                 + "not contain an international code.";
        }

        String afterPlus = cell.substring(1);

        for (char c : afterPlus.toCharArray()) {
            if (!Character.isDigit(c)) {
                return "Cell phone number contains invalid characters.";
            }
        }

        if (afterPlus.length() < 9 || afterPlus.length() > 13) {
            return "Cell phone number length is invalid.";
        }

        return null;
    }

    public static boolean checkRecipientCell(String cell) {
        return getRecipientError(cell) == null;
    }

    public static String hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available.", e);
        }
    }
}