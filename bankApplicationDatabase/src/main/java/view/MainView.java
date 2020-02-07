package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shailesh
 */
import Controller.DbConnection;
import Controller.MyAccountHandler;
import Controller.UserHandler;
import java.util.Scanner;
import model.MyAccount;
public class MainView {
    
    public static MyAccountHandler ah=new MyAccountHandler();
    public static UserHandler uh=new UserHandler();
    public static DbConnection db;
    
    public static void main(String[] args) {
//        int p;
//        Scanner sc=new Scanner(System.in);
//        boolean exit;
//        do{
//        if(loginView())
//        {
//        int ch;
//        do{
//            System.out.println("Welcome, Enter appropriate choice");
//            System.out.println("1. Add Account");
//            System.out.println("2. Check Amount");
//            System.out.println("3. Deposite Amount");
//            System.out.println("4. Withdraw amount");
//            System.out.println("5. Transfer amount");
//            System.out.println("6. Delete account");
//            System.out.println("7. List account");
//            System.out.println("8. Add Users");
//            System.out.println("9. Delete Users");
//            System.out.println("Press 0 key to exit");
//           
//            ch=sc.nextInt();
//            
//            switch(ch){
//                case 1:
//                    addAccountView();
//                    break;
//                    
//                case 2:
//                    checkAmountView();
//                    break;
//                    
//                case 3:
//                    depositeAmountView();
//                    break;
//                    
//                case 4:
//                    withdrawAmountView();
//                    break;
//                case 5:
//                    transferAmountView();
//                    break;
//                case 6:
//                    deleteAccountView();
//                    break;
//                case 7:
//                    listAllAccountView();
//                    break;
//                    
//                case 8:
//                    addUserView();
//                    break;
//                case 9:
//                    deleteUserView();
//                    break;
//                default:
//                    System.out.println("Enter appropriate choice");
//            }
//        } while(ch!=0);
//    }
//        System.out.println("Invalid Login Credential");
//        System.out.println("Press 0 to exit and 1 to relogin");
//         p=sc.nextInt();
//         if(p!=0)
//         exit=false;
//         else
//          exit=true;
//    }while(!exit);
            loginForm lf=new loginForm();
            db=new DbConnection();
            lf.setVisible(true);
           
    }
    public static boolean loginView(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter username");
        String userName=sc.nextLine();
        System.out.println("Enter Password");
        String passWord=sc.nextLine();
        return uh.login(userName,passWord);
    }
    
    public static void addAccountView(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter account name");
        String accName=sc.nextLine();
        System.out.println("Enter account number");
        int accNumber=sc.nextInt();
        System.out.println("Enter opening balance");
        int openBalance=sc.nextInt();
        if(ah.addAccount(accName,accNumber,openBalance)){
            System.out.println("The account is created sucessfully");
        }
        else{
            System.out.println("The account number already exist");
        }
    }
    
    public static void checkAmountView(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter account number");
         int accNumber=sc.nextInt();
         ah.checkAmount(accNumber);
    }
    
    public static void depositeAmountView(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Account number");
        int accNo=sc.nextInt();
        System.out.println("Enter deposite amount");
        int depositeAmount=sc.nextInt();
        if(ah.depositeAmount(accNo, depositeAmount)){
            System.out.println("Balance deposited");
        }
        else{
            System.out.println("Enter the valid account number");
        }
        
    }
    public static void withdrawAmountView(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Account number");
        int accNo=sc.nextInt();
        System.out.println("Enter withdraw amount");
        int withdrawAmount=sc.nextInt();
        int status=ah.withdrawAmount(accNo,withdrawAmount);
        if(status==1){
            System.out.println("Withdraw successful");
        }
        else if(status==-1){
            System.out.println("Insufficient Balance");
        }
        else{
            System.out.println("Enter valid account number");
        }
    }
    public static void transferAmountView(){
       
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Sender Account number");
        int accNo1=sc.nextInt();
        System.out.println("Enter Reciever Account number");
        int accNo2=sc.nextInt();
         System.out.println("Enter transfer amount");
        int transferAmount=sc.nextInt();
        int status=ah.transferAmount(accNo1,accNo2,transferAmount);
        if(status==1){
            System.out.println("Transfer successful");
        }
        else if(status==-1){
            System.out.println("Insufficient Balance");
        }
        else{
            System.out.println("Enter valid account number");
        }
        
    }
    
    public static void deleteAccountView(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Account number");
        int accNo=sc.nextInt();
        if(ah.deleteAccount(accNo)){
            System.out.println("Account deleted");
        }
        else{
            System.out.println("Enter valid Account number");
        }
    }
    public static void listAllAccountView(){
      for(MyAccount acc :  ah.listAllAccount()){
          System.out.println(acc.showAll());
      }
    }
    public static void addUserView(){
        System.out.println("Login again to add user");
        if(loginView()){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter new username");
        String userName=sc.nextLine();
        System.out.println("Enter new password");
        String passWord=sc.nextLine();
        System.out.println("Enter new password again");
        String temp=sc.nextLine();
        if(passWord.equalsIgnoreCase(temp))
           if(uh.addUser(userName,passWord)){
               System.out.println("User added sucessfully");
           }
           else{
               System.out.println("Username already exist");
           }
        else{
            System.out.println("Password didn't match");
        }
        
        }
        else{
            System.out.println("Invalid Login");
        }
    }
    
    public static void deleteUserView(){
        System.out.println("Login again to add user");
        if(loginView()){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter username to delete");
        String userName=sc.nextLine();
        if(uh.deleteUser(userName)){
            System.out.println("Username deleted");
        }
        else{
            System.out.println("Username doesn't exist");
        }
    }
    }
}

