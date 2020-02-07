package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shailesh
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;
import static view.MainView.db;

   
public class UserHandler {
ArrayList<User> users=new ArrayList<>();
//DbConnection db=new DbConnection();

public boolean login(String userName, String passWord){
   String sql="SELECT * FROM bankdb.user;";
   ResultSet rs=db.select(sql);
   try{
       while(rs.next()){
           User us=new User(rs.getString(2),rs.getString(3));
           if(us.getUserName().equalsIgnoreCase(userName)&&us.getPassWord().equalsIgnoreCase(passWord))
          return true;
       }
   }catch(SQLException ex){
       System.out.println("Database error");
   }
   if(userName.equalsIgnoreCase("admin")&&passWord.equalsIgnoreCase("admin"))
          return true;
   else
   return false;
}

public boolean addUser(String userName,String passWord){
    if(findUser(userName)==null){
        String sql="INSERT INTO `bankdb`.`user` (`userName`, `passWord`) VALUES ('"+userName+"', '"+passWord+"');";
        return db.iud(sql);
        }
    return false;
}

public User findUser(String userName){
    String sql="SELECT * FROM bankdb.user where userName=\""+userName+"\";";
    ResultSet rs=db.select(sql);
    try{
        while(rs.next()){
          User us=new User(rs.getString(2),rs.getString(3));
          return us;
        }
    }catch(SQLException ex){
        System.out.println("Database Error");
    }
    return null;
}

public boolean deleteUser(String userName){
    if(findUser(userName)!=null){
        User us=findUser(userName);
        String sql="DELETE FROM `bankdb`.`user` WHERE (`userName` = '"+us.getUserName()+"');";
        return db.iud(sql);
    }
    return false;
}


}
