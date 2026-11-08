import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[]args) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schoolmanagement","root","root");
        while(true)
        {
            System.out.println("1. Add Student\n" +
                    "2. View Students\n" +
                    "3. Search Student\n" +
                    "4. Update Student\n" +
                    "5. Delete Student\n" +
                    "6. Exit");
            System.out.print("Choose option : ");
            Scanner sc=new Scanner(System.in);
            int option=sc.nextInt();
            switch(option)
            {
                case 1:
                    AddStudent(con,sc);
                    break;
                case 2:
                    ViewStudents(con);
                    break;
                case 3:
                    SearchStudents(con,sc);
                    break;
                case 4:
                    UpdateStudent(con,sc);
                    break;
                case 5:
                    DeleteStudent(con,sc);
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }
    private static void DeleteStudent(Connection con,Scanner sc) throws SQLException
    {
        System.out.print("Enter Id of Student to be deleted : ");
        int id=sc.nextInt();
        String query="Delete from studentdetails where id=?";
        PreparedStatement pe=con.prepareStatement(query);
        pe.setInt(1,id);
        int rowseffected = pe.executeUpdate();

        if(rowseffected>0)
        {
            System.out.println("Deleted Successfully !!!");
        }
        else{
            System.out.println("Failed....");
        }
    }
    private static void ViewStudents(Connection con) throws SQLException {
        System.out.println();
        PreparedStatement pe=con.prepareStatement("select * from studentdetails");
        ResultSet re=pe.executeQuery();
        while (re.next()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Roll Number : "+re.getInt("id"));
            System.out.println("Name Of Student : "+re.getString("name"));
            System.out.println("Father Name : "+re.getString("fathername"));
            System.out.println("Phone Number : "+re.getInt("number"));
            System.out.println("Class : "+re.getInt("class"));
            System.out.println("-----------------------------------------------------------------");
        }
    }
    private static void UpdateStudent(Connection con,Scanner sc) throws SQLException {
        System.out.print("Enter id Of Student to be Updated : ");
        int id = sc.nextInt();
        System.out.println("1.Change Name of Student");
        System.out.println("2.Change Father Name");
        System.out.println("3.Change Mobile Number of Student");
        System.out.println("4.Class of Student");
        System.out.println("5.Exit");
        System.out.print("Enter Option : ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                StudentName(sc,con,id);
                break;
            case 2:
                FatherName(sc,con,id);
                break;
            case 3:
                MobileNumber(sc,con,id);
                break;
            case 4:
                ClassStudent(sc,con,id);
                break;
            case 5:
                System.exit(0);
        }
    }
    private static void ClassStudent(Scanner sc,Connection con,int id) throws SQLException {
        System.out.println("Selected to Update Class OF Student 😉");
        System.out.print("Enter Class To be Updated : ");
        int Class=sc.nextInt();
        String query="UPDATE studentdetails SET class=? WHERE id=?";
        PreparedStatement pe = con.prepareStatement(query);
        pe.setInt(1,Class);
        pe.setInt(2, id);
        int rowseffected = pe.executeUpdate();
        if (rowseffected > 0) {
            System.out.println("Class of Student has been Updated 👍");
        }
        else {
            System.out.println("Failed to Change");
        }
    }
    private static void MobileNumber(Scanner sc,Connection con,int id) throws SQLException {
        System.out.println("Selected to Update Mobile Number 😉");
        System.out.print("Enter A New Mobile Number To Be Updated : ");
        int number=sc.nextInt();
        String query="UPDATE studentdetails SET number=? WHERE id=?";
        PreparedStatement pe = con.prepareStatement(query);
        pe.setInt(1, number);
        pe.setInt(2, id);
        int rowseffected = pe.executeUpdate();
        if (rowseffected > 0) {
            System.out.println("Number of Student has been Changed 👍");
        }
        else {
            System.out.println("Failed to Change");
        }
    }
    private static void FatherName(Scanner sc,Connection con,int id) throws SQLException {
        System.out.println("Selected to Update Name of Father 😉");
        sc.nextLine();
        System.out.print("Enter A New Name To Be Updated : ");
        String name = sc.nextLine();
        String query = "UPDATE studentdetails SET fathername=? WHERE id=?";
        PreparedStatement pe = con.prepareStatement(query);
        pe.setString(1, name);
        pe.setInt(2, id);
        int rowseffected = pe.executeUpdate();
        if (rowseffected > 0) {
            System.out.println("Name of Father Changed");
        }
        else {
            System.out.println("Failed to Change");
        }
    }
    private static void StudentName(Scanner sc,Connection con,int id) throws SQLException {
        System.out.println("Selected to Update Name of Student 😉");
        sc.nextLine();
        System.out.print("Enter A New Name To Be Updated : ");
        String name = sc.nextLine();

        String query = "UPDATE studentdetails SET name=? WHERE id=?";
        PreparedStatement pe = con.prepareStatement(query);
        pe.setString(1, name);
        pe.setInt(2, id);
        int rowseffected = pe.executeUpdate();
        if (rowseffected > 0) {
            System.out.println("Name of Student Changed");
        }
        else {
        System.out.println("Failed to Change");
        }
    }
    private static void SearchStudents(Connection con, Scanner sc) throws SQLException {

        System.out.print("Enter Student Id : ");
        int id = sc.nextInt();

        String query = "SELECT * FROM studentdetails WHERE id = ?";

        PreparedStatement pe = con.prepareStatement(query);
        pe.setInt(1, id);

        ResultSet rs = pe.executeQuery();

        while (rs.next()) {
            System.out.println();
            System.out.println("---------------------------------------------------");
            System.out.println("ID     : " + rs.getInt("id"));
            System.out.println("Name   : " + rs.getString("name"));
            System.out.println("Father Name     :"+rs.getString("fathername"));
            System.out.println("Phone Number    : " + rs.getInt("number"));
            System.out.println("CLass  : " + rs.getInt("class"));
            System.out.println("---------------------------------------------------");
            System.out.println();
        }}



private static void AddStudent(Connection con, Scanner sc) throws SQLException {
        sc.nextLine(); // consume leftover Enter
//        System.out.println("Enter id: ");
//        int id =sc.nextInt();
        System.out.print("Enter a Student Name = ");
        String name = sc.nextLine();
        System.out.print("Enter Student Mobile Number = ");
        int number=sc.nextInt();
        sc.nextLine(); // consume leftover Enter
        System.out.print("Father Name = ");
        String fatherName = sc.nextLine();
        System.out.print("Which Class have to been joined = ");
        int Class=sc.nextInt();
        String query="Insert into studentdetails(name,number,fathername,class) values(?,?,?,?)";
        PreparedStatement pe= con.prepareStatement(query);
        pe.setString(1,name);
        pe.setLong(2,number);
        pe.setString(3,fatherName);
        pe.setInt(4,Class);
        int rowseffected=pe.executeUpdate();
        if(rowseffected>0)
        {
            System.out.println("Student Added Successfullyy 😍!!!");
            System.out.println("THANK YOU !");
        }
        else{
            System.out.println("failed try again");
        }
    }
    }

