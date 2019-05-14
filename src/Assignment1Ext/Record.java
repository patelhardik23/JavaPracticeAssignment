package Assignment1Ext;

public class Record
{
    String id;
    int sbp;
    int dbp;
    int map;
    String category;

    public Record(String id, int sbp, int dbp, int map, String category)
    {
        super();
        this.id = id;
        this.sbp = sbp;
        this.dbp = dbp;
        this.map = map;
        this.category = category;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getSbp()
    {
        return sbp;
    }

    public void setSbp(int sbp)
    {
        this.sbp = sbp;
    }

    public int getDbp()
    {
        return dbp;
    }

    public void setDbp(int dbp)
    {
        this.dbp = dbp;
    }

    public int getMap()
    {
        return map;
    }

    public void setMap(int map)
    {
        this.map = map;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Record [id=" + id + ", sbp=" + sbp + ", dbp=" + dbp + ", map="
               + map + ", category=" + category + "]";
    }

}
