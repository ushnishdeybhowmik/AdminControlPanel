public interface sql_vars {
    //SQL CONFIG
    String username = "root";
    String password = "delta#123 ";
    String conn = "jdbc:mysql://localhost:3306/admin_panel";

    //SQL QUERIES
    String R_Query = "SELECT * FROM ";
    String C_Doc_Query = "INSERT INTO doctor_table(doc_name, email, password, address, mobileno, fees, specialisation_id) VALUES(?,?,?,?,?,?,?)";
    String C_Ptnt_Query = "INSERT INTO patients_table(p_name, email, password, gender, mobile, address, age, prescription_id) VALUES(?,?,?,?,?,?,?,?)";
}