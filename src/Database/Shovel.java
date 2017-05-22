package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Shovel
{
    /*  Shovel er laget tættest på vores DB.
     *  Bruger JDBC driver til at forbinde med vores mysql database,
     *  hvor alt data er gemt.
     *  Vi gemmer wrapper al tekst i en SingleCellText objekter,
     *  der samtidig holder en type ID og en kategori ID.
     *  Når vi laver et opslag på en bruger, bliver de gemt i en User klasse,
     *  for at vi kan tilgå brugerens rettigheder, navn og ID med et enkelt opslag til DB.
     */

    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static final String dbURL = "jdbc:mysql://sql11.freemysqlhosting.net:3306";

    private static final String user = "sql11172288";
    private static final String pass = "Ms1HFKqZXP";

    public List<SingleCellText> dbShovel (int typeID, int catID)
    {
        List<SingleCellText> singleCellTexts = new ArrayList<>();

        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "SELECT * FROM sql11172288.text_description WHERE type_id = ? AND cat_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, typeID);
            pstmt.setInt(2, catID);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                singleCellTexts.add(new SingleCellText(rs.getInt("type_id"), rs.getInt("cat_id"),
                        rs.getString("actual_text")));
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

        return singleCellTexts;

    }

    public List<SingleCellText> dbShovel (int typeID)
    {
        List<SingleCellText> singleCellTexts = new ArrayList<>();

        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            String sqlQuery = "SELECT * FROM sql11172288.text_description WHERE type_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, typeID);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                singleCellTexts.add(new SingleCellText(rs.getInt("type_id"), rs.getInt("cat_id"),
                        rs.getString("actual_text")));
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

        return singleCellTexts;

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