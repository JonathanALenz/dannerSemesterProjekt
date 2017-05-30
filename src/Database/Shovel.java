package Database;

import GUI.NotificationMessage;
import com.mysql.jdbc.CommunicationsException;

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
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
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
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
            e.printStackTrace();
        }

        return singleCellTexts;

    }

    public List<SingleCellText> searchWord(String word)
    {
        List<SingleCellText> singleCellTexts = new ArrayList<>();
        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            //PreparedStatement!
            String sqlQuery = "SELECT text_description.id," +
                    "       text_description.type_id," +
                    "       text_description.cat_id," +
                    "       text_description.actual_text," +
                    "       m_category.category FROM sql11172288.text_description" +
                    "    INNER JOIN sql11172288.m_category " +
                    "    ON m_category.id = text_description.cat_id" +
                    "    WHERE actual_text LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,"%" + word + "%");

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                singleCellTexts.add(new SingleCellText(rs.getInt("type_id"), rs.getInt("cat_id"),
                rs.getString("actual_text"),rs.getString("category")));
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
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
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        return loginUser;
    }

    public boolean checkUsername(String userName)
    {
        boolean userExist = false;
        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            //PreparedStatement!
            String sqlQuery = "SELECT * FROM sql11172288.users WHERE user_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, userName);


            ResultSet rs = pstmt.executeQuery();
            rs.next();


            if(rs!=null)
            {
                if(rs.getString("user_name").equalsIgnoreCase(userName))
                {
                    userExist = true;
                }
            }
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        return userExist;
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
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke loade driver", "Fejl");
            System.out.println("Couldn't load driver");
            e.printStackTrace();
        }
        catch (CommunicationsException e)
        {
            NotificationMessage notificationMessage = new NotificationMessage("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            System.out.println("SQLException");
            e.printStackTrace();
        }
    }

    //funktion som henter alt i et træk, frem et kald for hver afkrydsning TEST
//    public List<SingleCellText> test(List<TypeogCat> tocs)
//    {
//        List<SingleCellText> scts = new ArrayList<>();
//        String sqlQuery = "SELECT * FROM sql11172288.text_description WHERE ";
//
//        try
//        {
//            Class.forName(jdbcDriver);
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
//            Statement stmt = conn.createStatement();
//            sqlQuery += "(cat_id = " + tocs.get(0).getCatID() + " and " + "type_id = " + tocs.get(0).getTypeID() + ")";
//
//            if(tocs.size() > 1)
//            {
//                for (int i = 1; i < tocs.size(); i++)
//                {
//                    sqlQuery += " OR (cat_id = " + tocs.get(i).getCatID() + " and " + "type_id = " + tocs.get(i).getTypeID() + ")";
//                }
//
//            }
//            ResultSet rs = stmt.executeQuery(sqlQuery);
//            rs.first();
//            System.out.println(rs.getString("actual_text"));
//
//            while(rs.next())
//            {
//                scts.add(new SingleCellText(rs.getInt("type_id"), rs.getInt("cat_id"),
//                        rs.getString("actual_text")));
//            }
//        }
//        catch (ClassNotFoundException e)
//        {
//            wrongCredentials("Systemfejl: Kunne ikke loade driver", "Fejl");
//            System.out.println("Couldn't load driver");
//            e.printStackTrace();
//        }
//        catch (CommunicationsException e)
//        {
//            wrongCredentials("Systemfejl: Kunne ikke få forbindelse til internettet","Fejl");
//            e.printStackTrace();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("SQLException");
//            e.printStackTrace();
//        }
//
//        return scts;
//    }
}