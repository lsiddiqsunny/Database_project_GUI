package CustomerOrder;

import Main.oracleDBMS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class cartutil {

    public  static void  updatestatus(String id)
    {


        String sql = " {call Updatestatus(?)}";
        try{
            Connection con = new oracleDBMS().getConnection();
            CallableStatement pst = con.prepareCall(sql);
            pst.setInt(1,Integer.parseInt(id));
//System.out.println(type+ " "+id);


            pst.executeQuery();


            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public static void DeleteCustomerOrder(String order_id)
    {
        String sql = "delete from CUSTOMER_ORDER where order_Id=?";
        // System.out.println(sql);

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,order_id);
            //   System.out.println(sql);

            pst.executeQuery();


            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

    }
    public static String  getorderoffer(String bookname)

    {
        String sql = "select NVL(applied_offer,0) from customer_order where order_id=? ";

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,bookname);
            ResultSet rs = pst.executeQuery();


            while (rs.next())
            {

                return  rs.getString(1);

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
    public static void Orderconfirm(String sz,String order_id)
    {
        String sql = "INSERT INTO CUSTOMER_PURCHASE VALUES( ?,?,SYSDATE)";
       // System.out.println(sql);

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,sz);
            pst.setString(2,order_id);
         //   System.out.println(sql);

            pst.executeQuery();


            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

    }


    public static String  bookid(String bookname)

    {
        String sql = "select book_id from book where book_name=?";

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
pst.setString(1,bookname);
            ResultSet rs = pst.executeQuery();


            while (rs.next())
            {

                return  rs.getString(1);

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
    public static int lastid()

    {
        String sql = "(SELECT COUNT(DISTINCT PURCHASE_ID) FROM CUSTOMER_PURCHASE)";

        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();


            while (rs.next())
            {

                return  Integer.parseInt(rs.getString(1));

            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return 0;
    }
    public static List<List<String>> getAllBooks(String Customer_id)
    {
        String sql = "select order_id,(select b.book_name from book b where b.book_id=o.book_id) book_name,amount,To_char(Time,'dd/mm/yyyy') Time,GET_STATUS(STATUS) STATUS from customer_order o where customer_id=?" +
                " order by ORDER_id";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = new oracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,Customer_id);
            ResultSet rs = pst.executeQuery();


            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("order_id"));
                row.add(rs.getString("book_name"));
                row.add(rs.getString("amount"));
                // System.out.println("here");
                row.add(rs.getString("Time"));
                row.add(rs.getString("STATUS"));
                resultList.add(row);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return resultList;
    }
}
