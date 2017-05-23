package Logic;

import Database.Shovel;
import Database.SingleCellText;
import javafx.scene.control.CheckBox;

import java.util.List;

/**
 * Created by Anders on 09-May-17.
 */
public class Logic
{
    /*  Logic er laget mellem vores GUI og Shovel.
     *  De forskellige funktioner er defineret ud fra vores kundes ønsker og behov.
     */

    private Shovel shovel;

    public Logic()
    {
        shovel = new Shovel();
    }

    /*  Da vi ikke kan gemme formatteret tekst i vores db,
     *  har vi indsat nogle #n tegn i db.
     *  getCellTextFormat henter tekster ud fra type og kategori,
     *  finder #n tegnene, indsætter et linjeskift.
     *  Det er denne metode som sænker programmet, men det for at kunne formattere teksten.
     */
    public String getCellTextFormat(int typeID, int catID)
    {
        List<SingleCellText> singleCellTexts = shovel.dbShovel(typeID, catID);
        String txt = "";
        String nyTxt = "";

        for (SingleCellText sct: singleCellTexts)
        {
            txt += sct.getText();
        }

        //Opretter et String array med alle elementer fra den hentede tekst uden mellemrum.
        String[] splittedString = txt.split(" ");

        //Løkken finder #n tegnene, erstatter dem med en ny linje. Ellers indsætter den et mellemrum.
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
        nyTxt += "\n\n";
        return nyTxt;
    }

    public String getCellTextFormatWODB(SingleCellText sct)
    {
        String txt = "";
        String nyTxt = "";

        txt += sct.getText();

        //Opretter et String array med alle elementer fra den hentede tekst uden mellemrum.
        String[] splittedString = txt.split(" ");

        //Løkken finder #n tegnene, erstatter dem med en ny linje. Ellers indsætter den et mellemrum.
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


    //Returnerer et enkelt tekstafsnit ud fra type og kategori.
    public String getCellText(int typeID, int catID)
    {

        List<SingleCellText> singleCellTexts = shovel.dbShovel(typeID, catID);
        String txt = "";

        for (SingleCellText sct : singleCellTexts)
        {

            txt += "\t- " + sct.getText() + "\n\n";

        }
        return txt;
    }

    //Returnerer alle tekster af den specifikke type fra alle kategorier.
    public String getCellText(int typeID)
    {
        List<SingleCellText> singleCellTexts = shovel.dbShovel(typeID);
        String txt = "";

        for (SingleCellText sct : singleCellTexts)
        {
            txt += sct.getText() + "\n\n";
        }
        return txt;
    }

    public String getSearchedWord(String word)
    {
        String text = "";
        List<SingleCellText> singleCellTexts = shovel.searchWord(word);

        for (int i = 0; i < singleCellTexts.size(); i++)
        {
            text += singleCellTexts.get(i).getCat_name().toUpperCase() +"\n";
            text += getCellTextFormatWODB(singleCellTexts.get(i));
            text += "\n\n" + printDash(118);
        }

        return text;
    }


    //returnerer et bruger objekt, hvis brugeren findes. Bruger objektet indeholder navn og rolle.
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

    //Modtager alle checkboxe og returnere en sammensat string af de valgte underemners tekster.
    public String option_choosedsend (CheckBox v_generalt, CheckBox v_sex, CheckBox v_psygisk, CheckBox v_fysisk,
                                      CheckBox v_materiel, CheckBox v_oekonomi, CheckBox unge, CheckBox BogV_gen,
                                      CheckBox BogV_VI, CheckBox BogV_BI, CheckBox venner_skriver, CheckBox man_gen,
                                      CheckBox mand_udoever, CheckBox mand_ofre, CheckBox netvaerk,
                                      CheckBox Skilsmisser_gen, CheckBox misbrug, CheckBox p_laege, CheckBox p_psykolog,
                                      CheckBox p_polti, CheckBox p_retshjaelp, CheckBox p_sygehus, CheckBox p_oekonomi,
                                      CheckBox etniske)
    {

        String msg = "";

        if (v_generalt.isSelected())
        {
            msg += "VOLD\n\n" + getCellTextFormat(0, 8) + "\n\n" +
                    "VOLDENS 5 FORMER(ofte er alle 5 former tilstede):\n\n" + getCellText(7, 8) + printDash(118);
        }
        if (v_sex.isSelected())
        {
            msg += "SEKSUEL VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0,11) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1, 11)  +
                    "HENVISNGER:\n\n" + getCellText(2, 11) + printDash(118);
        }

        if (v_psygisk.isSelected())
        {
            msg += "PSYGISK VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0,10) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,10) + printDash(118);
        }
        if (v_fysisk.isSelected())
        {
            msg += "FYSISK VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0,9) + printDash(118);
        }
        if (v_materiel.isSelected())

        {
            msg += "MATERIEL VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0,12) + printDash(118);
        }
        if (v_oekonomi.isSelected())
        {
            msg += "ØKONOMISK VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0, 13) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,13) + printDash(118);
        }
        if (unge.isSelected())
        {
            msg += "UNGE\n\nGENERELT:\n\n" + getCellTextFormat(0,6) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,6) +
                    "HENVISNINGER:\n\n" + getCellText(2,6) + printDash(118);
        }
        if (BogV_gen.isSelected())
        {
            msg += "BØRN OG VOLD\n\nGENERELT:\n\n" + getCellTextFormat(0,14) + printDash(118);
        }
        if (BogV_VI.isSelected())
        {
            msg += "NÅR INDSKRIVER ER VOKSEN:\n\n" + getCellTextFormat(0,15) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,15) + printDash(118);
        }
        if (BogV_BI.isSelected())
        {
            msg += "NÅR INDSKRIVER ER BARN:\n\n" + getCellTextFormat(0,16) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,16) +
                    "HENVISNINGER\n\n" + getCellText(2,16) + printDash(118);
        }
        if (venner_skriver.isSelected())
        {
            msg += "NÅR VEN ELLER FAMILIE SKRIVER IND:\n\nGENERELT:\n\n" +
                    getCellTextFormat(0,5) + "\n\n" +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,5) + printDash(118);
        }
        if (man_gen.isSelected())
        {
            msg += "MÆND\n\nGENERELT:\n\n" + getCellTextFormat(0,7) + printDash(118);
        }
        if (mand_udoever.isSelected())
        {
            msg += "HJÆLP TIL UDØVERE AF VOLD\n\nEKSEMPLER PÅ TEKSTER\n\n" + getCellText(18,7) +
                    printDash(118);
        }
        if (mand_ofre.isSelected())
        {
            msg += "HJÆLP TIL MÆND UDSAT FOR VOLD\n\n" +
                    "HENVISNINGER(OVERNATNINGSMULIGHEDER & RÅDGIVNING\n\n" + getCellTextFormat(2,7) +
                    printDash(118);
        }
        if (netvaerk.isSelected())
        {
            msg += "NETVÆRK\n\nGENERELT:\n\n" + getCellTextFormat(0,19) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,19) + printDash(118);
        }
        if (Skilsmisser_gen.isSelected())
        {
            msg += "SKILSMISSER\n\nGENERELT:\n\n" + getCellTextFormat(0,17) +
                    "EKSEMPLER PÅ SVAR\n\n" + getCellText(1,17) + printDash(118);
        }
        if (misbrug.isSelected())
        {
            msg += "MISBRUGERE OG PSYIGISK SYGE\n\nGENERELT:\n\n" + getCellTextFormat(0,4) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,4) +
                    "HENVISNINGER:\n\n" + getCellText(2,4) + printDash(118);
        }
        if (p_laege.isSelected())
        {
            msg +=  "Professionel hjælp: Læge\n\n" + getCellTextFormat(0,20) +
                    "Eksempler på svar:\n\n" + getCellText(1,20) + printDash(118);
        }
        if (p_psykolog.isSelected())
        {
            msg += "Professionel hjælp: Psykolog\n\n" + getCellTextFormat(0,21) +
                    "Eksempler på svar:\n\n" + getCellText(1,21) +
                    "Henvisninger:\n\n" + getCellText(2, 21) + printDash(118);
        }
        if (p_polti.isSelected())
        {
            msg += "Professionel hjælp: Politi\n\n" + getCellTextFormat(0,22) +
                    "Eksempler på svar:\n\n" + getCellText(1,22) + printDash(118);

        }
        if (p_retshjaelp.isSelected())
        {
            msg += "Professionel hjælp: Retshjælp\n\n" + getCellTextFormat(0,23) +
                    "Eksempler på svar:\n\n" + getCellText(1,23) + printDash(118);
        }
        if (p_sygehus.isSelected())
        {
            msg += "Professionel hjælp: Sygehus\n\n" + getCellTextFormat(0,24) + printDash(118);
        }
        if (p_oekonomi.isSelected())
        {
            msg += "Professionel hjælp: Økonomi\n\n" + getCellTextFormat(0,25) +
                    "Eksempler på svar:\n\n" + getCellText(1,25) + printDash(118);
        }
        if (etniske.isSelected())
        {
            msg += "ETNISKE KVINDER\n\nGENERELT:\n\n" + getCellTextFormat(0,18) +
                    "EKSEMPLER PÅ SVAR:\n\n" + getCellText(1,18) +
                    "HENVISNINGER:\n\n" + getCellText(2,18) + printDash(118);
        }
        return msg;
    }

    public String printDash(int antal)
    {
        String dashs = "";
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < antal; j++)
            {
                dashs += "-";
            }
            dashs += "\n";
        }
        dashs += "\n\n";
        return dashs;
    }
}
