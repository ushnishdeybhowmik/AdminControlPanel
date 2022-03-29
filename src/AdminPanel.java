import java.util.*;
import java.sql.*;

public class AdminPanel implements graphicvars, sql_vars {

    public static void main(String args[]) throws SQLException{

        Start();

    }

    static void Start() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(conn, username, password);
        System.out.println(welcome);
        System.out.println(menu);
        int ch = sc.nextInt();
        switch(ch){
            case 1:
                initiateDocDB(connection, sc);
                break;
            case 2:
                initiatePatientsDB(connection, sc);
                break;
            case 3:
                //initiateReceptionistDB(connection); TO-DO
                break;
            default:
                System.out.println("Choice doesn't exist");
                Start();
        }
    }
    //Doctor Table (CREATE and READ)
    static void initiateDocDB(Connection conn, Scanner sc) throws SQLException{
        System.out.println(docMenu);
        int ch = sc.nextInt();
        if(ch == 1){
            PreparedStatement preparedStatement = conn.prepareStatement(C_Doc_Query);
            System.out.println("Enter Name");
            preparedStatement.setString(1, sc.nextLine());
            System.out.println("Enter Email-ID");
            preparedStatement.setString(2, sc.nextLine());
            preparedStatement.setString(3, "Ushnish05");
            preparedStatement.setString(4, "24, Kailash Nagar");
            preparedStatement.setString(5, "9619367495");
            preparedStatement.setString(6, "30000");
            preparedStatement.setInt(7, 1);
            int num = preparedStatement.executeUpdate();
            if(num > 0) System.out.println("Doctor Registered");

        } else if(ch == 2){
            String query = R_Query + "doctor_table";
            ResultSet result = readData(conn, query);
            while(result.next()){
                System.out.println(result.getString("doc_name")+"\n"+result.getString("email")+"\n"+result.getString("address")+"\n"+result.getString("mobileno")+"\n"+result.getString("fees")+"\n"+result.getInt("specialisation_id"));
            }
        }


    }
    static ResultSet readData(Connection conn, String query) throws SQLException{
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    static void initiatePatientsDB(Connection conn, Scanner sc) throws SQLException{
        System.out.println(pMenu);
        int ch = sc.nextInt();
        switch(ch){
            case 1:
                PreparedStatement preparedStatement = conn.prepareStatement(C_Ptnt_Query);
                System.out.println("Enter Patient Name");
                preparedStatement.setString(1, sc.nextLine());
                System.out.println("Enter Patient Email ID");
                preparedStatement.setString(2, sc.next());
                boolean bool = false;
                while(!bool){
                    System.out.println("Enter Account Password");
                    String temp = sc.next();
                    System.out.println("Re-enter Password");
                    String temp2 = sc.next();
                    if(temp.equals(temp2)){
                        preparedStatement.setString(3, temp2);
                        bool = true;
                    }
                    else{
                        System.out.println("Passwords Do Not Match");

                    }
                }
                boolean bool2 = false;
               while(!bool2){
                   System.out.println("Enter Patient Gender.\n(M) for Male. (F) for Female. (T) for Transgenders.");
                   String g = sc.next();
                   if(g.equals("M") || g.equals("m") || g.equals("F") || g.equals("f") || g.equals("T") || g.equals("t")){
                       preparedStatement.setString(4, g);
                       bool2 = true;

                   }else{
                       System.out.println("Choice incorrect.");
                   }
               }
               //TO-DO
//             //System.out.println("Enter Patient Name");
//             // System.out.println("Enter Patient Name");
        }

    }

}
