import java.util.*;
import java.sql.*;

public class AdminPanel implements graphicvars, sql_vars {

    public static void main(String[] args) throws SQLException {

        Start();

    }

    static void Start() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(conn, username, password);
        System.out.println(welcome);
        System.out.println(menu);
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                initiateDocDB(connection, sc);
                break;
            case 2:
                initiatePatientsDB(connection, sc);
                break;
            case 3:
                initiateReceptionistDB(connection, sc);
                break;
            case 4:
                initiatePaymentsDB(connection, sc);
                break;
            case 5:
                System.out.println("Exiting Application");
            default:
                System.out.println(wrongch);
                Start();
        }
    }

    //Doctor Table (CREATE and READ)
    static void initiateDocDB(Connection conn, Scanner sc) throws SQLException {
        System.out.println(docMenu);
        int ch = sc.nextInt();
        if (ch == 1) {
            PreparedStatement preparedStatement = conn.prepareStatement(C_Doc_Query);
            System.out.println("Enter Name");
            String name = sc.nextLine();
            preparedStatement.setString(1, name);
            System.out.println("Enter Email-ID");
            String email = sc.nextLine();
            preparedStatement.setString(2, email);
            boolean bool = false;
            while (!bool) {
                System.out.println("Enter Password");
                String temp = sc.nextLine();
                System.out.println("Re-enter Password");
                String password = sc.nextLine();
                if (temp.equals(password)) {
                    preparedStatement.setString(3, password);
                    bool = true;
                } else {
                    System.out.println(passErr);
                }
            }
            System.out.println("Enter Address");
            String address = sc.nextLine();
            preparedStatement.setString(4, address);
            System.out.println("Enter Phone No.");
            String mobileno = sc.nextLine();
            preparedStatement.setString(5, mobileno);
            System.out.println("Enter Fees");
            String fees = sc.nextLine();
            preparedStatement.setString(6, fees);
            System.out.println("Enter Specialisation ID");
            int specs = sc.nextInt();
            preparedStatement.setInt(7, specs);
            int num = preparedStatement.executeUpdate();
            if (num > 0) {
                System.out.println("Doctor Registered");
                initiateDocDB(conn, sc);
            } else {
                System.out.println(dbErr);
                Start();
            }

        } else if (ch == 2) {
            String query = R_Query + doc_table;
            ResultSet result = readData(conn, query);
            while (result.next()) {
                System.out.println(result.getString("doc_name") + "\n" + result.getString("email") + "\n" + result.getString("address") + "\n" + result.getString("mobileno") + "\n" + result.getString("fees") + "\n" + result.getInt("specialisation_id") + "\n\n");
            }
            System.out.println("\n");
            initiateDocDB(conn, sc);
        } else {
            System.out.println(wrongch);
            initiateDocDB(conn, sc);
        }


    }

    static ResultSet readData(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    //Patients Table (CREATE, READ, UPDATE, DELETE)
    static void initiatePatientsDB(Connection conn, Scanner sc) throws SQLException {
        System.out.println(pMenu);
        int ch = sc.nextInt();
        if (ch == 1) {
            PreparedStatement preparedStatement = conn.prepareStatement(C_Ptnt_Query);
            System.out.println("Enter Patient Name");
            preparedStatement.setString(1, sc.nextLine());
            System.out.println("Enter Patient Email ID");
            preparedStatement.setString(2, sc.next());
            boolean bool = false;
            while (!bool) {
                System.out.println("Enter Account Password");
                String temp = sc.next();
                System.out.println("Re-enter Password");
                String temp2 = sc.next();
                if (temp.equals(temp2)) {
                    preparedStatement.setString(3, temp2);
                    bool = true;
                } else {
                    System.out.println(passErr);

                }
            }
            bool = false;
            while (!bool) {
                System.out.println(gMenu);
                String g = sc.next();
                if (g.equals("M") || g.equals("m") || g.equals("F") || g.equals("f") || g.equals("T") || g.equals("t")) {
                    preparedStatement.setString(4, g);
                    bool = true;

                } else {
                    System.out.println(wrongch);
                }
            }
            System.out.println("Enter Mobile Number");
            String mobileno = sc.next();
            preparedStatement.setString(5, mobileno);
            System.out.println("Enter Address");
            String address = sc.nextLine();
            preparedStatement.setString(6, address);
            System.out.println("Enter Age");
            int age = sc.nextInt();
            preparedStatement.setInt(7, age);
            System.out.println("Enter Prescription ID");
            int pres_id = sc.nextInt();
            preparedStatement.setInt(8, pres_id);
            int num = preparedStatement.executeUpdate();
            if (num > 0) {
                System.out.println("Patient Registered");
                initiatePatientsDB(conn, sc);
            } else {
                System.out.println(dbErr);
                Start();
            }
        } else if (ch == 2) {
            String query = R_Query + patient_table;
            ResultSet result = readData(conn, query);
            while (result.next()) {
                System.out.println(result.getString("p_name") + "\n" + result.getInt("age") + "\n" + result.getString("email") + "\n" + result.getString("gender") + "\n" + result.getString("address") + "\n" + result.getString("mobileno") + "\n" + result.getInt("prescription_id") + "\n\n");
            }
            System.out.println("\n");
            initiatePatientsDB(conn, sc);
        } else if (ch == 3) {
            System.out.println("Enter Patient ID");
            int ID = sc.nextInt();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(R_Ptnt_Query_Specific + ID);
            if (result.next()) {
                System.out.println("Enter Patient Name");
                String name = sc.nextLine();
                System.out.println("Enter Patient Email ID");
                String email = sc.nextLine();
                String pass = "";
                boolean bool = false;
                while (!bool) {
                    System.out.println("Enter Account Password");
                    String temp = sc.next();
                    System.out.println("Re-enter Password");
                    String temp2 = sc.next();
                    if (temp.equals(temp2)) {
                        pass = temp;
                        bool = true;
                    } else {
                        System.out.println(passErr);

                    }
                }
                String gender = "";
                bool = false;
                while (!bool) {
                    System.out.println("Enter Patient Gender.\n(M) for Male. (F) for Female. (T) for Transgenders.");
                    String g = sc.next();
                    if (g.equals("M") || g.equals("m") || g.equals("F") || g.equals("f") || g.equals("T") || g.equals("t")) {
                        gender = g;
                        bool = true;

                    } else {
                        System.out.println(wrongch);
                    }
                }
                System.out.println("Enter Mobile Number");
                String mobileno = sc.next();
                System.out.println("Enter Address");
                String address = sc.nextLine();
                System.out.println("Enter Age");
                int age = sc.nextInt();
                System.out.println("Enter Prescription ID");
                int pres_id = sc.nextInt();

                String query = U_Ptnt_Query + "p_name = '" + name + "', email = '" + email + "' password = '" + pass + "' gender = '" + gender + "' mobileno = '" + mobileno + "' address = '" + address + "' age = '" + age + "' prescription_id = '" + pres_id + "' " + U_Ptnt_Query_2 + ID;
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                int num = preparedStatement.executeUpdate();
                if (num > 0) {
                    System.out.println("Patient Updated");
                    initiatePatientsDB(conn, sc);
                } else {
                    System.out.println(dbErr);
                    Start();
                }
            } else {
                System.out.println("ID doesn't exist");
                initiatePatientsDB(conn, sc);
            }
        } else if (ch == 4) {
            System.out.println("Enter Patient ID");
            int ID = sc.nextInt();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(R_Ptnt_Query_Specific + ID);
            if (result.next()) {
                PreparedStatement preparedStatement = conn.prepareStatement(D_Ptnt_Query + ID);
                int num = preparedStatement.executeUpdate();
                if (num > 0) {
                    System.out.println("Patient Deleted");
                    initiatePatientsDB(conn, sc);
                } else {
                    System.out.println(dbErr);
                    Start();
                }
            } else {
                System.out.println("ID doesn't exist");
                initiatePatientsDB(conn, sc);
            }
        } else {
            System.out.println(wrongch);
            initiatePatientsDB(conn, sc);
        }
    }
    //ReceptionistDB (CREATE, READ)
    static void initiateReceptionistDB(Connection conn, Scanner sc) throws SQLException {
        System.out.println(rMenu);
        int ch = sc.nextInt();
        if (ch == 1) {
            PreparedStatement preparedStatement = conn.prepareStatement(C_Rec_Query);
            System.out.println("Enter Name");
            String name = sc.nextLine();
            preparedStatement.setString(1, name);
            System.out.println("Enter Email-ID");
            String email = sc.nextLine();
            preparedStatement.setString(2, email);
            boolean bool = false;
            while (!bool) {
                System.out.println("Enter Password");
                String temp = sc.nextLine();
                System.out.println("Re-enter Password");
                String password = sc.nextLine();
                if (temp.equals(password)) {
                    preparedStatement.setString(3, password);
                    bool = true;
                } else {
                    System.out.println(passErr);
                }
            }
            System.out.println("Enter Mobile No.");
            String mobileno = sc.nextLine();
            preparedStatement.setString(5, mobileno);
            System.out.println("Enter Address");
            String address = sc.nextLine();
            preparedStatement.setString(4, address);
            System.out.println("Enter Age");
            int age = sc.nextInt();
            preparedStatement.setInt(7, age);
            int num = preparedStatement.executeUpdate();
            if (num > 0) {
                System.out.println("Receptionist Registered");
                initiateReceptionistDB(conn, sc);
            } else {
                System.out.println(dbErr);
                Start();
            }

        } else if (ch == 2) {
            String query = R_Query + receptionists_table;
            ResultSet result = readData(conn, query);
            while (result.next()) {
                System.out.println(result.getString("rec_name") + "\n" + result.getString("email") + "\n" + result.getString("address") + "\n" + result.getString("mobileno") + "\n\n");
            }
            System.out.println("\n");
            initiateReceptionistDB(conn, sc);
        } else {
            System.out.println(wrongch);
            initiateReceptionistDB(conn, sc);
        }


    }
    // PaymentsDB (READ, DELETE)
    static void initiatePaymentsDB(Connection conn, Scanner sc) throws SQLException {
        System.out.println(payMenu);
        int ch = sc.nextInt();
        if (ch == 1) {
            String query = R_Query + payments_table;
            ResultSet result = readData(conn, query);
            while (result.next()) {
                System.out.println(result.getInt("pay_id") + "\n" + result.getInt("p_id") + "\n" + result.getInt("doc_id") + "\n" + result.getString("amount") + "\n" + result.getBoolean("statusCompleted") + "\n\n");
            }
            System.out.println("\n");
            initiatePaymentsDB(conn, sc);
        } else if (ch == 2) {
            System.out.println("Enter Payment ID");
            int ID = sc.nextInt();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(R_Pay_Query_Specific + ID);
            if (result.next()) {
                PreparedStatement preparedStatement = conn.prepareStatement(D_Pay_Query + ID);
                int num = preparedStatement.executeUpdate();
                if (num > 0) {
                    System.out.println("Payment Deleted");
                    initiatePaymentsDB(conn, sc);
                } else {
                    System.out.println(dbErr);
                    Start();
                }
            } else {
                System.out.println("ID doesn't exist");
                initiatePaymentsDB(conn, sc);
            }
        } else {
            System.out.println(wrongch);
            initiatePaymentsDB(conn, sc);
        }
    }

}
