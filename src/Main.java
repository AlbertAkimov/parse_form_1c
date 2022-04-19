import com.sun.istack.internal.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Authot: Albert Akimov
 * @Date: 19.04.2022
 * @Description:
 */
public class Main {

    public static List<String> nameMetadata = new ArrayList<>();
    public static List<Struct> listBin = new ArrayList<>();

    public static void main(String[] args) {

        String PATH_TO_ROOT = "";

        Scanner in = new Scanner(System.in);

        System.out.println("Укажите корневой путь до конфигурации 1С");
        PATH_TO_ROOT = in.nextLine();


        if (PATH_TO_ROOT.equals(""))
            System.out.println("Не указан путь до конфигурации 1С.");
        else {

            File root = new File(PATH_TO_ROOT);
            File[] list = root.listFiles();

            findForm1C(Objects.requireNonNull(list), 0);

        }
    }

    public static void findForm1C(File[] files, int lvl) {

        for(File file : files) {

            File[] listFiles = file.listFiles();

            if(lvl == 0)
                lvl++;

            if(listFiles != null) {
                findForm1C(listFiles, lvl);
            }

            else {
                lvl = 0;
                if(file.getName().equals("Form.bin")) {

                    String name = getNameMetadata(file);

                    if(nameMetadata.isEmpty()) {
                        System.out.println("Metadata - " + name);
                        nameMetadata.add(name);
                    }
                    else {

                        boolean found = nameMetadata.stream().anyMatch(lang -> lang.equals(name));

                        if(!found) {
                            System.out.println("Metadata - " + name);
                            nameMetadata.add(name);
                        }
                    }

                    //System.out.println("    - Name form: " + getNameForm(file));
                }
            }
        }
    }

    public static String getNameForm(@NotNull File file) {

        if(file == null)
            return "";

        String result = "";

        if(!file.getName().equals("Ext"))
            result = getNameForm(file.getParentFile());
        else if(file.getName().equals("Ext"))
            result = file.getParentFile().getName();

        return result;
    }

    public static String getNameMetadata(@NotNull  File file) {

        if(file == null)
            return "Общая форма";

        String result = "";

        if(!file.getName().equals("Forms"))
           result = getNameMetadata(file.getParentFile());
        else if(file.getName().equals("Forms"))
            result = file.getParentFile().getName();

        return result;
    }
}
