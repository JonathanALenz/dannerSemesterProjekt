package Logic;

import Database.Shovel;
import Database.SingleCellText;
import Database.User;

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

    public String specLinks(int catId)
    {

        List<SingleCellText> singleCellTexts = shovel.dbLinkShovel(); // ENESTE LINJE ANDERLEDES FRA SPECANS
        String chosenRefs = "";

        for (SingleCellText sct : singleCellTexts)
        {
            if(sct.getCat_id() == catId)
            {
                chosenRefs += sct.getText() + "\n";
            }
        }
        return chosenRefs;
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

        for (SingleCellText sct: singleCellTexts)
        {
            if(sct.getCat_id() == catId)
            {
                chosenGTexts += sct.getText();
                splittedString = chosenGTexts.split(" ");

                for (int i = 0; i < splittedString.length; i++)
                {
                    if(splittedString[i].equals("#n"))
                    {
                        ny += "\n";
                    }
                    else
                    {
                        ny += splittedString[i] + " ";
                    }
                }
            }
        }
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
