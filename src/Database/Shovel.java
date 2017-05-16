package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Shovel
{

    static final String jdbcDriver = "com.mysql.jdbc.Driver";
    static final String dbURL = "jdbc:mysql://sql11.freemysqlhosting.net:3306";

    static final String user = "sql11172288";
    static final String pass = "Ms1HFKqZXP";

    public List<SingleCellText> dbLinkShovel()
    {
        List<SingleCellText> linkRefs = new ArrayList<>();

        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "SELECT * FROM sql11172288.link_references";

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                linkRefs.add(new SingleCellText(rs.getInt("cat_id"), rs.getString("ref_name")));
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Couldn't load driver");
        }
        catch (SQLException e)
        {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }

        return linkRefs;
    }

    public List<SingleCellText> dbExampleAnsShovel()
    {
        List<SingleCellText> exampleAns = new ArrayList<>();

        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "SELECT * FROM sql11172288.example_ans";

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                exampleAns.add(new SingleCellText(rs.getInt("cat_id"), rs.getString("ans_text")));
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Couldn't load driver");
        }
        catch (SQLException e)
        {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }

        return exampleAns;
    }

    public List<SingleCellText> dbGTextShovel()
    {
        List<SingleCellText> gTexts = new ArrayList<>();

        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "SELECT * FROM sql11172288.text_description";

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                gTexts.add(new SingleCellText(rs.getInt("id"), rs.getString("actual_text")));
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could't load driver.");
        }
        catch (SQLException e)
        {
            System.out.println("SQL Fejl");
            e.printStackTrace();
        }
        return gTexts;
    }

    public User getUser(String userName, int userPass)
    {
        User loginUser = null;
        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            //PreparedStatement!
            String sqlQuery = "SELECT * FROM sql11172288.users WHERE user_name = ? AND user_password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, userName);
            pstmt.setInt(2, userPass);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            if(rs != null)
            {

                loginUser = new User(rs.getString("user_name"),
                            rs.getString("admin_rights"));
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Couldn't load driver");
        }
        catch (SQLException e)
        {
            System.out.println("Fejl");
            e.printStackTrace();
        }
        return loginUser;
    }

    public void createNewUser(String userName, int hashedUserPass, String role)
    {
        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "INSERT INTO sql11172288.users (user_name, user_password, admin_rights) VALUES (?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, userName);
            pstmt.setInt(2, hashedUserPass);
            pstmt.setString(3, role);

            pstmt.executeUpdate();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("SQL fejl.");
            e.printStackTrace();
        }
    }
}