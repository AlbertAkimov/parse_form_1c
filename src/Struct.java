import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 19.04.2022
 * @Description:
 */

public class Struct {

    private String nameMetaData;
    private List<String> nameForms;

    public Struct() {
    }

    public String getNameMetaData() {
        return nameMetaData;
    }

    public void setNameMetaData(String nameMetaData) {
        this.nameMetaData = nameMetaData;
    }

    public List<String> getNameForms() {
        return nameForms;
    }

    public void addNameForm(String name) {
        nameForms.add(name);
    }
}
