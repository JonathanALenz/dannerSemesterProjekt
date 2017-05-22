package Database;

/**
 * Created by Anders on 09-May-17.
 */
public class SingleCellText
{
    private int type_id;
    private int cat_id;
    private String text;

    public SingleCellText(int type_id, int cat_id, String text)
    {
        this.type_id = type_id;
        this.cat_id = cat_id;
        this.text = text;
    }


    public int getType_id()
    {
        return type_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
