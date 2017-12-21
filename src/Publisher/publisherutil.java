package Publisher;

import Main.oracleDBMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Createaccount.InsertCustomerdata.getLocation;

public class publisherutil {

    public  static String  getusername(String Publisher_id)
    {
        String sql = "SELECT Publisher_name FROM Publisher Where publisher_id=?";
        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,Publisher_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {

                return   rs.getString(1);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return "";
    }
    public static String getuserLocationid(String publisher_id)
    {
        String sql = "SELECT location_id FROM publisher Where Publisher_id=?";
        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,publisher_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {

                return   rs.getString(1);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return "";
    }

    public static String getuserLocation(String Location_id)
    {
        String sql = "SELECT * FROM LOCATION WHERE LOCATION_ID="+Location_id;

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                return rs.getString("Street_Address")+","+rs.getString("Post_code")+","+rs.getString("City")+".";


            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return "";
    }
    public static List<List<String>> getAllBooks(String publisher_id)
    {
        String sql = "Select DISTINCT BOOK_NAME,GET_AUTHOR_NAME(b.book_id) AUTHOR_NAME,PRICE\n" +
                "from Book b,Publisher Pb,Author A\n" +
                "where b.publisher_id=Pb.PUBLISHER_ID and  b.publisher_id=? and b.book_id=A.book_id";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,publisher_id);
            ResultSet rs = pst.executeQuery();


            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("BOOK_NAME"));
                row.add(rs.getString("AUTHOR_NAME"));
                row.add(rs.getString("PRICE"));
                resultList.add(row);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {

        }
        return resultList;
    }
    public  static void Inserdata(String Location)
    {


        Location=getLocation(Location);
        System.out.println(Location);

        String sql = "INSERT INTO Customer (Customer_id,Customer_name,Email,Phone_number,Password,Location_id,Branch_id) VALUES (?,?,?,?,?,?,?)";

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,Location);

            pst.executeQuery();

            pst.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }
    public  static boolean  setName(String Name,String id,String Type) {
        String sql = "Update Publisher Set "+Type+"=? Where Publisher_id=?";
        // System.out.println(sql);
        try {
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, Name);
            pst.setString(2, id);
            ResultSet rs = pst.executeQuery();


            pst.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } return false;
    }
    public  static boolean  setPrice(String id,String Value) {
        String sql = "Update Book Set Price=? Where Book_id=?";
        // System.out.println(sql);
        try {
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, Value);
            pst.setString(2, id);
            ResultSet rs = pst.executeQuery();


            pst.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } return false;
    }
    public  static String  getBook(String book)
    {
        String sql = "SELECT Book_id FROM Book Where Book_name=?";
        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,book);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {

                return   rs.getString("Book_id");
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
}