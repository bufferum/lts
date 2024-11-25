package lts;
import java.io.File;
import org.junit.Test;


/** @see lts.Property_reader */
public class Test_property_reader {


    ////////// Methods //////////
    @Test
    public void get_value() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        Print.test("database ip = " + reader._get_value("bd_ip") + "\n");
        Print.test("database port = " + reader._get_value("bd_port") + "\n");
        Print.test("database user = " + reader._get_value("bd_user") + "\n");
        Print.test("database password = " + reader._get_value("bd_password") + "\n");
        Print.test("database db_name = " + reader._get_value("bd_name") + "\n");

    }

    @Test
    public void edit_value() {

        try {

            final String RESOURCES = "src/test/resources/Property_reader/";
            File file_property = new File(RESOURCES + "application.properties");
            Property_reader reader = new Property_reader(file_property);

            // Получаем значение ключа
            String result = reader._get_value("project.name");

            // Изменяем значение ключа
            if(result.equals("PROJECT_1")) {

                reader._edit_value("project.name", "PROJECT_2");

            }
            else {

                reader._edit_value("project.name", "PROJECT_1");

            }

            // Проверяем результат
            Print.test("Has project name: " + reader._get_value("project.name") + "\n");
            Print.test("Became project name: " + reader._get_value("project.name") + "\n");

        }
        catch(Exception e) {

            e.printStackTrace();

        }

    }


}