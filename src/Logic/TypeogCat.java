package Logic;

/**
 * Created by Anders on 25-May-17.
 */
public class TypeogCat
{

    //TEST KLASSE
    private int typeID;
    private int catID;

    public TypeogCat(int typeID, int catID)
    {
        this.typeID = typeID;
        this.catID = catID;
    }

    public int getTypeID()
    {
        return typeID;
    }

    public void setTypeID(int typeID)
    {
        this.typeID = typeID;
    }

    public int getCatID()
    {
        return catID;
    }

    public void setCatID(int catID)
    {
        this.catID = catID;
    }
}
