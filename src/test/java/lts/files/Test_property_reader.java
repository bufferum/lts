package lts.files;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.signs.Print;


/** @see lts.files.Property_reader */
public class Test_property_reader {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Variables //////////
    private static final String RESOURCES = "src/test/resources/Test_property_reader/";
    private static File file_property = new File(RESOURCES + "application.properties");


    ////////// Methods //////////
    @Test public void get_value() throws Exception {

        Property_reader reader = new Property_reader(RESOURCES + "application.properties");

        Print.test("database db_ip = " + reader._get_value("db_ip") + "\n");
        Print.test("database db_port = " + reader._get_value("db_port") + "\n");
        Print.test("database db_username = " + reader._get_value("db_username") + "\n");
        Print.test("database db_password = " + reader._get_value("db_password") + "\n");
        Print.test("database db_name = " + reader._get_value("db_name") + "\n");

    }

    @Test public void edit_value() {

        try {

            Property_reader reader = new Property_reader(file_property);

            // Получаем значение ключа
            String result = reader._get_value("project.name");

            Print.test("Has project name: " + reader._get_value("project.name") + "\n");

            // Изменяем значение ключа
            if(result.equals("PROJECT_1")) {

                reader._edit_value("project.name", "PROJECT_2");

            }
            else {

                reader._edit_value("project.name", "PROJECT_1");

            }

            // Проверяем результат
            Print.test("Became project name: " + reader._get_value("project.name") + "\n");

        }
        catch(Exception e) { e.printStackTrace(); }

    }


    @Test public void reload_value() {

        try {

            Property_reader reader = new Property_reader(file_property);

            // Проверка, какое свойство было сначала
            Print.test("Has project name: " + reader._get_value("project.name") + "\n");

            // За 5 секунд если вручную изменить значение свойства в файле, и перезагрузить reader
            Thread.sleep(5000);

            // Вызываем функцию перезагрузки reader
            reader.reload();

            // Проверка, изменилось ли свойство
            Print.test("Became project name: " + reader._get_value("project.name") + "\n");

        }
        catch(Exception e) { e.printStackTrace(); }

    }


}