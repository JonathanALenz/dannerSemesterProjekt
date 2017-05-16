package Logic;

import Database.Shovel;
import Database.SingleCellText;
import Database.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders on 09-May-17.
 */
public class Logic
{
    private Shovel shovel;

    public Logic()
    {
        shovel = new Shovel();
    }

    public String genLogic(int typeID, int catID)
    {
        List<SingleCellText> singleCellTexts = shovel.dbShovel(typeID, catID);
        String txt = "";
        String nyTxt = "";

        for (SingleCellText sct: singleCellTexts)
        {
            txt += sct.getText();
        }

        String[] splittedString = txt.split(" ");

        for (int i = 0; i < splittedString.length; i++)
        {
            if(splittedString[i].equals("#n"))
            {
                nyTxt += "\n";
            }
            else
            {
                nyTxt += splittedString[i] + " ";
            }
        }
        return nyTxt;
    }


    public String specLogic(int typeID, int catID)
    {

        List<SingleCellText> singleCellTexts = shovel.dbShovel(typeID, catID);
        String txt = "";

        for (SingleCellText sct : singleCellTexts)
        {

            txt += "\t- " + sct.getText() + "\n\n";

        }
        return txt;
    }

    public String specAns(int catId)
    {
        List<SingleCellText> singleCellTexts = shovel.dbExampleAnsShovel(); // ENESTE LINJE ANDERLEDES FRA SPECREFS
        String chosenExampleAns = "";

        for (SingleCellText sct : singleCellTexts)
        {
            if(sct.getCat_id() == catId)
            {
                chosenExampleAns += "\t" + "- " + sct.getText() + "\n\n";
            }
        }
        return chosenExampleAns;
    }

    public String specGText(int catId)
    {
        List <SingleCellText> singleCellTexts = shovel.dbGTextShovel();
        String chosenGTexts = "";
        String[] splittedString;
        String ny = "";


        return ny;
    }

    public User userIsValidLogin(String userName, String userPass)
    {
        int hashedUserPass = userPass.hashCode();

        User user = shovel.getUser(userName, hashedUserPass);

        return user;
    }

    public void createNewUser(String userName, String userPass, String role)
    {
        int hashedUserPass = userPass.hashCode();

        shovel.createNewUser(userName, hashedUserPass, role);
    }
}
