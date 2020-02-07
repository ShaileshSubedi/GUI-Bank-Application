/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.MyAccount;
import static view.MainView.db;

/**
 *
 * @author Shailesh
 */

public class MyAccountHandler {
    
    ArrayList<MyAccount> accounts=new ArrayList<>();
    //DbConnection db=new DbConnection();
    
    public boolean addAccount(String accName,int accNo,int accBal){
        //MyAccount acc=new MyAccount(accName,accNo,accBal);
        if(findAccount(accNo)==null){
            String sql="INSERT INTO `bankdb`.`account` (`accountNumber`, `accountName`, `balance`) VALUES ('"+accNo+"', '"+accName+"', '"+accBal+"');";
            return db.iud(sql);
        }
        return false;
    }
    
    public MyAccount findAccount(int accNumber){
        String sql="SELECT * FROM bankdb.account WHERE accountNumber="+accNumber+";";
        ResultSet rs=db.select(sql);
        try{
        while(rs.next()){
            MyAccount acc=new MyAccount(rs.getString(2),rs.getInt(1),rs.getInt(3));
            return acc;
        }
        }catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public void checkAmount(int accNo){
        if(findAccount(accNo)!=null){
             MyAccount acc=findAccount(accNo);
             int balance=acc.getBalance();
            System.out.println("The amount in account is " + balance);
           }
        else{
            System.out.println("Enter valid account number");
        }
    }
    
    public boolean depositeAmount(int accNo, int amount){
        if(findAccount(accNo)!=null){
            MyAccount acc =findAccount(accNo);
            acc.setBalance(acc.getBalance()+amount);
            String sql="UPDATE `bankdb`.`account` SET `balance` = '"+acc.getBalance()+"' WHERE (`accountNumber` = '"+acc.getAccountNumber()+"');";
            
            return db.iud(sql);
        }
        return false;
    }
    
    public int withdrawAmount(int accNo,int withdrawAmount){
        if(findAccount(accNo)!=null){
            MyAccount acc=findAccount(accNo);
            if(acc.getBalance()>withdrawAmount)
            {
                acc.setBalance(acc.getBalance()-withdrawAmount);
                String sql="UPDATE `bankdb`.`account` SET `balance` = '"+acc.getBalance()+"' WHERE (`accountNumber` = '"+acc.getAccountNumber()+"');";
            
              if( db.iud(sql))
                return 1;
                else
                return 2;
            }
            else{
                return -1;
            }
        }
        return 0;
    }
    
    public int transferAmount(int accNo1, int accNo2, int amount){
        if(findAccount(accNo1)!=null&&findAccount(accNo2)!=null){
            MyAccount acc1=findAccount(accNo1);
            MyAccount acc2=findAccount(accNo2);
            if(acc1.getBalance()>amount){
                acc1.setBalance(acc1.getBalance()-amount);
                acc2.setBalance(acc2.getBalance()+amount);
                String sql1="UPDATE `bankdb`.`account` SET `balance` = '"+acc1.getBalance()+"' WHERE (`accountNumber` = '"+acc1.getAccountNumber()+"');";
                String sql2="UPDATE `bankdb`.`account` SET `balance` = '"+acc2.getBalance()+"' WHERE (`accountNumber` = '"+acc2.getAccountNumber()+"');";
               if((db.iud(sql1))&&(db.iud(sql2)))
                return 1;
               else
                   return 2;
            }
            else{
                return -1;
            }
           
        }
        return 0;
    }
    
    public ArrayList<MyAccount> listAllAccount(){
       String sql="Select * from bankdb.account;";
       ResultSet rs=db.select(sql);
       this.accounts.clear();
       try{
           while(rs.next()){
               MyAccount acc=new MyAccount(rs.getString(2),rs.getInt(1),rs.getInt(3));
               this.accounts.add(acc);
           }
       }catch(SQLException e){
           return null;
       }
        
        
        return this.accounts;
    }
    
    public ArrayList<Object []> listAccount(){
        String sql="Select * from bankdb.account;";
       ResultSet rs=db.select(sql);
       ArrayList<Object[]> data=new ArrayList<>();
       data.clear();
       try{
           while(rs.next()){
               Object acc[]={rs.getString(2),rs.getInt(1),rs.getInt(3)};
               data.add(acc);
           }
       }catch(SQLException e){
           return null;
       }
        
        
        return data;
        
    }
    
    public boolean deleteAccount(int accNo){
        if(findAccount(accNo)!=null){
            MyAccount acc=findAccount(accNo);
            String sql="DELETE FROM `bankdb`.`account` WHERE (`accountNumber` = '"+acc.getAccountNumber()+"');";
            return db.iud(sql);
        }
        return false;
    }
}
