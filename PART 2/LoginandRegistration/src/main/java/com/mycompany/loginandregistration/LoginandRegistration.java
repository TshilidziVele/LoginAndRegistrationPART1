/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginandregistration;

/**
 *
 * @author Tshilidzi
 */


 import java.util.*;
    import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/* -------User class------*/

class User {
    String firstName;
    String username;
    String hashedPassword;
    String phone;
    
    public User(String firstName, String userName, String hashedPassword, String phone){
                this.firstName = firstName;
                this.username = userName;
                this.hashedPassword = hashedPassword;
                this.phone = phone;
                
          
}
}

// ------Main class--------

public class LoginandRegistration{
    static User user;
   public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)){
        
        //----------Register-------
        System.out.println("---- REGISTER-----");
         //------first name----
        String firstName;
        
        while (true){
            System.out.print("Enter first name (letters only ,cannot be empty): ");
            firstName = input.nextLine().trim();
            
            if (!firstName.isEmpty()){
                System.out.println("First name successfully captured.");
                break;
            }
            else{
                System.out.println("Error: First name cannot be empty. Please enter your name.");
            }
        }
        
                                     //-----Username-----
        
        String username;
        while (true){
            System.out.print("Enter username (must contain '_' with 5 characters max): ");
            username = input.nextLine();
            
            String usernameError = getUsernameError(username);
            
            if (usernameError == null){
                System.out.println("Username successfully captured."); break;
            }
            else{
                System.out.println("Error: " + usernameError);
            }
        }
            
            //-----Password-------
            
            String hashedPassword;
            while (true){
                System.out.print("Enter password(8 characters minimum,1 uppercase,1 number,1 special character): ");
                String rawPassword = input.nextLine();
                List<String> passwordErrors = getPasswordErrors(rawPassword);
                
                if (passwordErrors.isEmpty()){
                    hashedPassword = hashPassword(rawPassword);
                    System.out.println("Password successfully captured."); break;
                }
                else{
                    System.out.println("Error: Your password does not meet the following requirements:");
                    for (String err: passwordErrors){
                        System.out.println(" -" + err);
                    }
                }
            }
            
            
                   // -----Phone-----
                   
                   String phone;
                   while (true){
                       System.out.print("Enter cell number(must start with +27 then 9 more digits): ");
                       phone = input.nextLine();
                       String phoneError = getPhoneError(phone);
                       
                   if (phoneError == null){
                       System.out.println("Cell number successfully captured.");break;
                   }
                   else{ 
                       System.out.println("Error: " + phoneError);
                   }
                  }
                   
                   //Storing user in memory and saving file
                   
                   user = new User(firstName, username, hashedPassword, phone);
                   
                   boolean saved = saveUserToFile(user);
                   
                   if(!saved){
                       System.out.println("Critical error: could not save user data. Please check file permissions and try again.");return;
                       
                   }
                   
                   System.out.println("\n***********************");
                   System.out.println("Registed successfully! ");
                   System.out.println("============================\n");
                   
                   
                   /*--------Login-------*/
            System.out.println("Please login with the details you previously registered with.\n");
            
            boolean loginSuccess = false;
            
            while (!loginSuccess){
                System.out.println("-----Login----");
                System.out.print("Enter username (the username you registered   with): ");
                String loginUser = input.nextLine();
                System.out.print("Enter password(the password you registered with): " );
                String loginPass = input.nextLine();
                System.out.print("Enter cell number(the +27 number you registered with): ");
                String loginPhone = input.nextLine();
                
                
                
                // Checing field and reporting which is wrong
                
                boolean usernameMatch = loginUser.equals(user.username);
                boolean passwordMatch = hashPassword(loginPass).equals(user.hashedPassword);
                boolean phoneMatch = loginPhone.replaceAll("\\s+", "").equals(user.phone.replaceAll("\\s+", ""));
                
                      if (usernameMatch && passwordMatch && phoneMatch){
                          loginSuccess = true;
                          System.out.println("\n---------------------------");
                          System.out.println(" Login successful!");
                                  
                          System.out.println(" Welcome, " + user.firstName + "!");
                       
                          System.out.println("-------------------------------------");
                          
                      }
                      else{
                          System.out.println("\nLogin failed. Please check the following: ");
                          if (!usernameMatch) System.out.println(" - Username is incorrect.");
                          if (!passwordMatch) System.out.println(" - Password is incorrect.");
                          if (!phoneMatch) System.out.println(" - Cell number is incorrect.");
                           System.out.println("Please try again.\n");
                      }
            }
                      
                      //------------END------------
                      System.out.println("\nYou are now logged in. Goodbye!");
            }
   }//Close scanner
            //END program
            
            // Saving user to file
            
            public static boolean saveUserToFile(User u){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt")))
                {
                    
                    writer.write("firstName=" +u.firstName);  writer.newLine();
                       writer.write("username=" +u.username);  writer.newLine(); 
                          writer.write("hashedPassword=" +u.hashedPassword);  writer.newLine();
                          writer.write("phone=" +u.phone);  writer.newLine();return true;
                          
                }
                catch (IOException e) {
                    System.out.println("Error saving user infomation: " + e.getMessage());return false;
                }
            
            }

            
            
            
            //------------specific messages validating----------
            
            public static String getUsernameError(String username){
                
                if (username.isEmpty()){
                    return "Username cannot be empty.";
                }
                
                if (!username.contains("_")){
                    return "Username must contain an underscore (_).";
                }
                
                if (username.length() > 5){
                    return "Username length is exceeded (" + username.length() + " characters). Maximum is 5 characters.";
                }return null; 
                    
                }
                    //List of unmet requiremets
                    
                    public static List<String> getPasswordErrors(String password){
                        List<String> errors = new ArrayList<>();
                        if (password.length() < 5){
                            errors.add("Too short - must be atleast 8 characters (yours has " + password.length() +").");
                        }
                        
                        boolean hasCapital = false;
                        boolean hasNumber = false;
                        boolean hasSpecial = false;
                        
                        for (char c : password.toCharArray()){
                            if(Character.isUpperCase(c))  hasCapital = true;
                            if (Character.isDigit(c))     hasNumber = true;
                            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
                        }
                       
                        if (!hasCapital) errors.add("Missing an uppercase letter (e.g. A,B,C.");
                        if (!hasNumber) errors.add("Missing a number (e.g. 1,2,3).");
                        if (!hasSpecial) errors.add("Missing a special character (e.g. @,#,!,$).");
                    return errors;
                    }
                    
                    // Null return if valid and error mesage
                    
                    public static String getPhoneError(String cell){
                        cell = cell.replaceAll("\\s+", "");
                        
                        if (cell.isEmpty()){
                        return "Phone number cannot be empty.";
                        }
                        if (!cell.startsWith("+27")){
                            return "Phone number mus start with +27. You entered: \"" + cell+ "\"";
                        }
                        if (cell.length() != 12){
                            int digits = cell.length() - 3; 
                            return "Phone number must have exactly 9 digits after +27. You entered " + digits + " digit(s).";
                        }
                        
                        String digits = cell.substring(3);
                        for (char c : digits.toCharArray()){
                            if (!Character.isDigit(c)){
                                return "Phone number must contain only digits after +27. Found invalid character: '" + c+ "'";
                            }
                        }
                        return null;
                    }
                    
                    public static boolean checkUsername(String username){
                        return getUsernameError(username) == null;
                    }
                            public static boolean checkRecipientCell(String cell){
                                return getPhoneError(cell) == null;
                            }
                                   //-------------------Hashing---------
                            public static String hashPassword(String password){
                                try{
                                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                                    byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
                                    StringBuilder sb = new StringBuilder();
                                    for (byte b : hash) {
                                        sb.append(String.format("%02x", b));
                                    }
                                    return sb.toString();
                                }
                                catch (NoSuchAlgorithmException e){
                                    throw new RuntimeException("SHA-256 not available", e);
                                }
                            }
                            public static boolean checkLogin(String username,String password,String phone) {
                                return username.equals(user.username) &&
                                        hashPassword(password).equals(user.hashedPassword) &&
                                        phone.replaceAll("\\s+", "").equals(user.phone.replaceAll("\\s+", ""));
                            }
                                    }