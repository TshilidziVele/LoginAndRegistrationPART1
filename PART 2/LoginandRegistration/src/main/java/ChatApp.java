/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tshilidzi
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.*;


class User {
    String firstName;
    String username;
    String hashedPassword;
    String phone;

    public User(String firstName, String username, String hashedPassword, String phone) {
        this.firstName      = firstName;
        this.username       = username;
        this.hashedPassword = hashedPassword;
        this.phone          = phone;
    }
}
class MessageRecord {
 int    messageNumber;
  String messageId;
String sender;
  String recipient;
  String messageText;
   String hash;
    String status;
   public MessageRecord(int messageNumber, String messageId, String sender,
                         String recipient, String messageText, String hash, String status) {
        this.messageNumber = messageNumber;
        this.messageId     = messageId;
        this.sender        = sender;
        this.recipient     = recipient;
        this.messageText   = messageText;
        this.hash          = hash;
        this.status        = status;
    }
}
class JsonHelper {
   return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
 }






public static String unescape(String s) {
  if (s == null) return "";
    return s.replace("\\\"", "\"")
            .replace("\\\\", "\\")
            .replace("\\n",  "\n")
              .replace("\\r",  "\r")
               .replace("\\t",  "\t");
   }
 public static String readFile(File file) {
     StringBuilder sb = new StringBuilder();
       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
    while ((line = reader.readLine()) != null) {
           sb.append(line).append("\n");
            }
      } 
           catch (IOException e) {
          System.out.println("Error reading file: " + e.getMessage());
          return null;
        }
      return sb.toString();
  }







public static List<String> extractObjects(String json) {
      List<String> objects = new ArrayList<>();
       int depth = 0;
   int start = -1;
      boolean inStr   = false;
  boolean escaped = false;



for (int i = 0; i < json.length(); i++) {
         char c = json.charAt(i);
     if (escaped)            { escaped = false; continue; }
            if (c == '\\' && inStr) { escaped = true;  continue; }
           if (c == '"')           { inStr = !inStr;  continue; }
         if (inStr)              { continue; }
            if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } 
         
            
            
            
            
            
            else if (c == '}') {
                depth--;
                if (depth == 0 && start != -1) {
                    objects.add(json.substring(start, i + 1));
                    start = -1;
                }
            }
        }
        return objects;
    }
  public static String parseString(String json, String key) {
        Pattern p = Pattern.compile(
                "\"" + Pattern.quote(key) + "\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"");
        Matcher m = p.matcher(json);
     
      
      return m.find() ? unescape(m.group(1)) : null;
    }

    public static int parseInt(String json, String key) {
        Pattern p = Pattern.compile(
                "\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+)");
        Matcher m = p.matcher(json);
        return m.find() ? Integer.parseInt(m.group(1)) : 0;
    }
}





class Message {
 private final String messageId;
private final String recipient;
  private final String messageText;
  private final String messageHash;
 private static int                       numSent     = 0;
 
    
    
    
    private static final List<MessageRecord> allMessages = new ArrayList<>(); 
    public Message(String recipient, String messageText) {
        this.recipient   = recipient;
        this.messageText = messageText;
    
      this.messageId   = generateMessageId();
        this.messageHash = createMessageHash();
    }
  private String generateMessageId() {
    Random rand = new Random();
      long id = 1_000_000_000L + (long)(rand.nextDouble() * 9_000_000_000L);
     return String.valueOf(id);
    }

   public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }
 public String checkRecipientCell() {
        String cell = recipient.replaceAll("\\s+", "");
   if (cell.isEmpty() || !cell.startsWith("+")) {
     return "Cell phone number is incorrectly formatted or does not contain "
           + "an international code. Please correct the number and try again.";
        }
  
     
     
     
     String afterPlus = cell.substring(1);
       for (char c : afterPlus.toCharArray()) {
           if (!Character.isDigit(c)) {
            return "Cell phone number is incorrectly formatted or does not contain "
                  + "an international code. Please correct the number and try again.";
          }
        }
        if (afterPlus.length() < 9 || afterPlus.length() > 13) {
            return "Cell phone number is incorrectly formatted or does not contain "
             + "an international code. Please correct the number and try again.";
        }
  return "Cell phone number successfully captured.";
    }
    public String checkMessageLength() {
    
        if (messageText.length() <= 250) return "Message ready to send.";
        int excess = messageText.length() - 250;
      
        
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";

    
    
    }








    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");

        String first = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String last  = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();

        
        if (first.isEmpty()) first = "?";
        if (last.isEmpty())  last  = "?";

        return String.format("%02d:%d:%s%s", numSent, numSent, first, last);
    }
  public String SentMessage(int choice, String sender) {
    switch (choice) {
     case 1:
           numSent++;
         allMessages.add(buildRecord(sender, numSent, "sent"));
    storeMessages();
    
            
            return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                numSent++;
                allMessages.add(buildRecord(sender, numSent, "stored"));
                storeMessages();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
     
    
    
    
    }
    }

    
    public static String printMessages() {
     if (allMessages.isEmpty()) return "No messages sent yet.";
StringBuilder sb = new StringBuilder();
    sb.append("\n--- All Sent / Stored Messages ---\n");
        for (MessageRecord m : allMessages) {
           sb.append("Message #   : ").append(m.messageNumber).append("\n");
         sb.append("Message ID  : ").append(m.messageId).append("\n");
           sb.append("Recipient   : ").append(m.recipient).append("\n");
          sb.append("Message     : ").append(m.messageText).append("\n");
          sb.append("Hash        : ").append(m.hash).append("\n");
           sb.append("Status      : ").append(m.status).append("\n");
       sb.append("-----------------------------------------\n");
       
        
        
        }
  return sb.toString();
    }

   
    
    
    
    public static int returnTotalMessages() { return numSent; }

    public void storeMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < allMessages.size(); i++) {
        MessageRecord m = allMessages.get(i);

          sb.append("  {\n");
          
        sb.append("    \"messageNumber\": ").append(m.messageNumber).append(",\n");
          sb.append("    \"messageId\": \""   ).append(JsonHelper.escape(m.messageId)   ).append("\",\n");
           sb.append("    \"sender\": \""      ).append(JsonHelper.escape(m.sender)      ).append("\",\n");
          sb.append("    \"recipient\": \""   ).append(JsonHelper.escape(m.recipient)   ).append("\",\n");
        sb.append("    \"messageText\": \"" ).append(JsonHelper.escape(m.messageText) ).append("\",\n");
         sb.append("    \"hash\": \""        ).append(JsonHelper.escape(m.hash)        ).append("\",\n");
         sb.append("    \"status\": \""      ).append(JsonHelper.escape(m.status)      ).append("\"\n");
           sb.append("  }");

         
            
            
    if (i < allMessages.size() - 1) sb.append(",");
          sb.append("\n");
      
        
        }
  sb.append("]");


try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.json"))) {
        writer.write(sb.toString());
     } catch (IOException e) {
         System.out.println("Error saving messages: " + e.getMessage());
       


}
    }
    
    
    
    
    
    
    
    public static void loadMessages() {
      File file = new File("messages.json");
        if (!file.exists()) return;

        
        String json = JsonHelper.readFile(file);
        if (json == null || json.trim().isEmpty()) return;


        
      for (String obj : JsonHelper.extractObjects(json)) {
        int    num    = JsonHelper.parseInt(obj,    "messageNumber");
        String id     = JsonHelper.parseString(obj, "messageId");
        String sender = JsonHelper.parseString(obj, "sender");
        String recip  = JsonHelper.parseString(obj, "recipient");
           String text   = JsonHelper.parseString(obj, "messageText");
           String hash   = JsonHelper.parseString(obj, "hash");
           String status = JsonHelper.parseString(obj, "status");

         allMessages.add(new MessageRecord(num, id, sender, recip, text, hash, status));
            if (num > numSent) numSent = num;

      }
    }

private MessageRecord buildRecord(String sender, int messageNumber, String status) {
        return new MessageRecord(messageNumber, messageId, sender,
                                 recipient, messageText, messageHash, status);
    }

   public String getMessageId()   { return messageId;   }
 public String getMessageHash() { return messageHash; }
 public String getRecipient()   { return recipient;   }
           public String getMessageText() { return messageText; }
}





public class ChatApp {
    static User user;
    public static void main(String[] args) {


        
        Message.loadMessages();

        try (Scanner input = new Scanner(System.in)) {

           
          
            
            
            /****************/
            System.out.println("========== REGISTER ==========");

     /* ---- First Name ---- */
            
            
            
            
        System.out.println("\nFirst name requirements:");
     System.out.println("  - Cannot be empty");

         String firstName;
        while (true) {
               System.out.print("Enter first name: ");
           firstName = input.nextLine().trim();
           
            
            if (!firstName.isEmpty()) {
                    System.out.println("First name successfully captured.");
                    break;
                }
                System.out.println("  Error: First name cannot be empty. Please try again.");
            }

     /* ---- Username ---- */
      System.out.println("\nUsername requirements:");
         System.out.println("  - Must contain an underscore ( _ )");
         System.out.println("  - Maximum 5 characters");

           String username;
          
    while (true) {
          System.out.print("Enter username: ");
              username = input.nextLine();
              String error = getUsernameError(username);
              
        
        
    if (error == null) {
            System.out.println("Username successfully captured.");
                    break;
                }
            
        System.out.println("  Error: " + error + " Please try again.");
            }

 /* ---- Password ---- */
      System.out.println("\nPassword requirements:");
          System.out.println("  - Minimum 8 characters");
          System.out.println("  - At least 1 uppercase letter");
          System.out.println("  - At least 1 number");
      System.out.println("  - At least 1 special character (e.g. @, #, !)");
            
           
            
    String hashedPassword;
       while (true) {
          System.out.print("Enter password: ");
         String rawPassword = input.nextLine();
        List<String> errors = getPasswordErrors(rawPassword);
           
           
           
    if (errors.isEmpty()) {
            hashedPassword = hashPassword(rawPassword);
            System.out.println("Password successfully captured.");
                break;
              
}
            
           
           
    System.out.println("  Error: Password does not meet requirements:");
      for (String err : errors) System.out.println("    - " + err);
         System.out.println("  Please try again.");
           
       
       }

     /* ---- Phone ---- */
           
            
System.out.println("\nCell number requirements:");
  System.out.println("  - Must start with +27");
   System.out.println("  - Followed by exactly 9 digits");
       System.out.println("  - Example: +27821234567");

    
    String phone;
      while (true) {
           System.out.print("Enter cell number: ");
           phone = input.nextLine();
    String error = getPhoneError(phone);
               if (error == null) {
                 System.out.println("Cell number successfully captured.");
                    break;
            }
      System.out.println("  Error: " + error + " Please try again.");
       }
    user = new User(firstName, username, hashedPassword, phone);

         
            
            
            
            
            
            if (!saveUserToJson(user)) {
                System.out.println("Critical error: Could not save user data.");
                return;
            }

     System.out.println("\n==============================");
         System.out.println("  Registered successfully!");
            System.out.println("==============================\n");




            
            System.out.println("Please log in with the details you just registered.\n");
            System.out.println("========== LOGIN ==========");

         
            
        /* ---- Username ---- */
        
            
            
            

        System.out.println("\nUsername requirements:");
         System.out.println("  - Must contain an underscore ( _ )");
            System.out.println("  - Maximum 5 characters");
           
while (true) {
         System.out.print("Enter username: ");
         String entered = input.nextLine();
          String usernameError = getUsernameError(entered);
              
    if (usernameError != null) {
                 System.out.println("  Error: " + usernameError + " Please try again.");
              }
    else if (!entered.equals(user.username)) {
              System.out.println("  Error: Username is incorrect. Please try again.");
                }
    else {
                    break;
                }
            }

        
            
    /* ---- Password ---- */
         
            
    System.out.println("\nPassword requirements:");
      System.out.println("  - Minimum 8 characters");
       System.out.println("  - At least 1 uppercase letter");
          System.out.println("  - At least 1 number");
      System.out.println("  - At least 1 special character (e.g. @, #, !)");

          
            
    
    while (true) {
          System.out.print("Enter password: ");
          String entered = input.nextLine();
          List<String> pwErrors = getPasswordErrors(entered);
            
        if (!pwErrors.isEmpty()) {
                System.out.println("  Error: Password does not meet requirements:");
                for (String err : pwErrors) System.out.println("    - " + err);
                System.out.println("  Please try again.");
                }
        
        else if (!hashPassword(entered).equals(user.hashedPassword)) {
               System.out.println("  Error: Password is incorrect. Please try again.");
                } 
        else {
                break;
              }
            }

        
            
    
/* ---- Cell Number ---- */

  System.out.println("\nCell number requirements:");
      System.out.println("  - Must start with +27");
        System.out.println("  - Followed by exactly 9 digits");
        System.out.println("  - Example: +27821234567");

            
            
    while (true) {
         System.out.print("Enter cell number: ");
            String entered = input.nextLine();
             String phoneError = getPhoneError(entered);
            
        
        
  if (phoneError != null) {
               System.out.println("  Error: " + phoneError + " Please try again.");
            }
  else if (!entered.replaceAll("\\s+", "").equals(user.phone.replaceAll("\\s+", ""))) {
            System.out.println("  Error: Cell number is incorrect. Please try again.");
               } 
  
  else {
                   
      break;
                }
         
    }

           
        System.out.println("\n---------------------------");
         System.out.println("Login successful!");
         System.out.println("Welcome, " + user.firstName + "!");
         System.out.println("Welcome to QuickChat.");
          System.out.println("---------------------------");

        
           
        int totalMessages = 0;
          System.out.println("\nHow many messages would you like to send?");
          System.out.println("  - Must be a whole number greater than 0");

         
            
        while (true) {
           System.out.print("Enter number: ");
              try {
                   totalMessages = Integer.parseInt(input.nextLine().trim());
        if (totalMessages < 1) System.out.println("  Error: Please enter a number greater than 0.");
                  else break;
                } 
    catch (NumberFormatException e) {
                System.out.println("  Error: Invalid input. Please enter a whole number.");
              }
      }
    
        
            
    boolean running = true;

      
            
    while (running) {
        System.out.println("\n===== QUICKCHAT MENU =====");
         System.out.println("1) Send Messages");
          System.out.println("2) Show recently sent messages");
          System.out.println("3) Quit");
                System.out.print("Choose an option: ");

            
       switch (input.nextLine().trim()) {
         case "1":
                for (int i = 1; i <= totalMessages; i++) {
            System.out.println("\n--- Message " + i + " of " + totalMessages + " ---");

                          
                          
                          
                          
                          
                    /* ---- Recipient ---- */
               
                          
  System.out.println("Recipient cell number requirements:");
    
        System.out.println("- Must start with + and an international code");
         System.out.println("- Digits only after the +");
     System.out.println("- Between 9 and 13 digits after the +");
    System.out.println("- Example: +27821234567");

          String recipient;
                          
                          
        
                          
      while (true) {
              System.out.print("Enter recipient cell number: ");
                       recipient = input.nextLine();
               if (checkRecipientCell(recipient)) break;
                 System.out.println("Error: Cell phone number is incorrectly formatted "
                           + "or does not contain an international code. Please try again.");
                         
                  
                  }

                         
                          
                          
                          
                          
        /* ---- Message Text ---- */
                      System.out.println("Message requirements:");
           System.out.println("  - Maximum 250 characters");

                
     String messageText;
            
                          
                          while (true) {
                    System.out.print("Enter your message: ");
                           messageText = input.nextLine();
                       
                  
 if (messageText.length() <= 250) break;
   
                    int excess = messageText.length() - 250;
          System.out.println("Error: Message exceeds 250 characters ny " + excess + ". Please shorten it.");
                            }

                         
                    Message msg = new Message(recipient, messageText);
               System.out.println("Message ID  : " + msg.getMessageId());
    
                          
                          
                    System.out.println("Message Hash: " + msg.getMessageHash());
                       System.out.println(msg.checkRecipientCell());
                
                    System.out.println(msg.checkMessageLength());

                  System.out.println("\n1) Send Message");
                     System.out.println("2) Discard Message");
                      System.out.println("3) Store Message");
          int subChoice = 0;
                
                          
               while (true) {
                          System.out.print("Choose an option: ");
                        
                            
     try {
            subChoice = Integer.parseInt(input.nextLine().trim());
                                  
              if (subChoice >= 1 && subChoice <= 3) break;
                         System.out.println("  Error: Please enter 1, 2, or 3.");
                                } 
                  catch (NumberFormatException e) {
                                System.out.println("  Error: Please enter a number.");
                          }
       }

                          
                          
                System.out.println(msg.SentMessage(subChoice, user.username));

      if (subChoice == 2) {
                 System.out.print("Press 0 to confirm deletion, or any other key to keep: ");
               String confirm = input.nextLine().trim();
                            
                   
        System.out.println(confirm.equals("0") ? "Message deleted." : "Message kept.");
                            }
                       }

                     
                      
        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                      break;
                
                  
    case "2":
                        System.out.println(Message.printMessages());
                        break;

  case "3":
      running = false;
            System.out.println("Thank you for using QuickChat.");
                 break;

            default:
                  
                      
                      System.out.println("  Error: Invalid option. Please enter 1, 2, or 3.");
              
              }
           
        }
        }
 }

 public static boolean saveUserToJson(User u) {
      String json = "{\n"
         + "  \"firstName\": \""      + JsonHelper.escape(u.firstName)      + "\",\n"
             + "  \"username\": \""       + JsonHelper.escape(u.username)       + "\",\n"
             + "  \"hashedPassword\": \"" + JsonHelper.escape(u.hashedPassword) + "\",\n"
          + "  \"phone\": \""          + JsonHelper.escape(u.phone)          + "\"\n"
              + "}";

 
        
try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.json"))) {
        writer.write(json);
        return true;
        } 
    
catch (IOException e) {
         System.out.println("Error saving user information: " + e.getMessage());
         return false;
        }
   }

  
    
public static User loadUserFromJson() {
        File file = new File("user.json");
     if (!file.exists()) return null;


        
 String json = JsonHelper.readFile(file);
        if (json == null) return null;

      return new User(
             JsonHelper.parseString(json, "firstName"),
              JsonHelper.parseString(json, "username"),
             JsonHelper.parseString(json, "hashedPassword"),
        JsonHelper.parseString(json, "phone")
     );
 }

 public static String getUsernameError(String username) {
    if (username.isEmpty())       return "Username cannot be empty.";
       if (!username.contains("_"))  return "Username must contain an underscore (_).";
    if (username.length() > 5)    return "Username exceeds 5 characters.";
     
     
     return null;
    }

 public static List<String> getPasswordErrors(String password) {
    
        
List<String> errors = new ArrayList<>();
    
        
if (password.length() < 8) errors.add("Password must be at least 8 characters.");


    boolean hasUppercase = false, hasDigit = false, hasSpecial = false;
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
        if (cell.isEmpty())          return "Phone number cannot be empty.";
     if (!cell.startsWith("+27")) return "Phone number must start with +27.";
     if (cell.length() != 12)     return "Phone number must contain exactly 9 digits after +27.";
     for (char c : cell.substring(3).toCharArray())
        if (!Character.isDigit(c)) return "Phone number contains invalid characters.";
    return null;
    }

    
    
    
public static String getRecipientError(String cell) {
    cell = cell.replaceAll("\\s+", "");
  if (cell.isEmpty() || !cell.startsWith("+"))
          return "Cell phone number is incorrectly formatted or does not contain an international code.";

   String afterPlus = cell.substring(1);
      for (char c : afterPlus.toCharArray())
           if (!Character.isDigit(c)) return "Cell phone number contains invalid characters.";


        
if (afterPlus.length() < 9 || afterPlus.length() > 13)
          return "Cell phone number length is invalid.";

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
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available.", e);
        }
    }
}
