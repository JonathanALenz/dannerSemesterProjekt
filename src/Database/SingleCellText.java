package Database;

public class SingleCellText
{
    private int type_id;
    private int cat_id;
    private String text;
    private String cat_name;

    public SingleCellText(int type_id, int cat_id, String text, String cat_name)
    {
        this.type_id = type_id;
        this.cat_id = cat_id;
        this.text = text;
        this.cat_name = cat_name;
    }

    public SingleCellText(int type_id, int cat_id, String text)
    {
        this.type_id = type_id;
        this.cat_id = cat_id;
        this.text = text;
    }

    public void setType_id(int type_id)
    {
        this.type_id = type_id;
    }

    public void setCat_name(String cat_name)
    {
        this.cat_name = cat_name;
    }

    public String getCat_name()
    {
        return cat_name;
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