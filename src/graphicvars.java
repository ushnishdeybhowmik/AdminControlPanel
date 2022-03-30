public interface graphicvars {

    //DISPLAY
    String welcome = "Admin Control Module";
    String menu = "************\nMENU\n***********\n1) Doctors\n2) Patients\n3) Receptionists\n4) Payments\n5) Exit\n\nEnter Number";
    String docMenu = "What do you want to do?\n1) Registration of Doctors\n2) List all Doctors\n3) Exit";
    String rMenu = "What do you want to do?\n1) Registration of Receptionists\n2) List all Receptionists\n3) Exit";
    String pMenu = "What do you want to do?\n1) Add Patient\n2) Update Patient\n3) List all patients\n4) Remove patient\n5) Exit\n\nEnter Number";
    String gMenu = "Enter Patient Gender.\n(M) for Male. (F) for Female. (T) for Transgenders.";
    String payMenu = "What do you want to do?\n1) List all payments\n2) Delete payment\n3) Exit\n\nEnter Number";

    //ERRORS
    String wrongch = "Choice doesn't exist.";
    String dbErr = "Database Error.";
    String passErr = "Password Error";
}
