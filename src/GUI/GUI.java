package GUI;

import Logic.Logic;
import Database.User;

/**
 * Created by Anders on 09-May-17.
 */
public class GUI
{
    private Logic logic;

    public GUI()
    {
        logic = new Logic();
    }

    public void printRefs()
    {
        System.out.println(logic.specLinks(10));
    }

    public void printExampleAns()
    {
        System.out.println(logic.specAns(4));
    }

    public void printGText()
    {
        System.out.println(logic.specGText(3));
    }

    public void isValidLogin()
    {

        User user = logic.userIsValidLogin("Elvi", "minsønsnavn");
        if(user != null)
        {
            System.out.println("Du er nu logget ind!");
            System.out.println(user.getName());
            System.out.println(user.getRole());
        }
        else
        {
            System.out.println("Brugeren eksisterer ikke!");
        }
    }

    public void createUser()
    {
        logic.createNewUser("Elvi", "minsønsnavn", "admin");
    }
}
