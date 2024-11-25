package lts;
import org.junit.Test;


/** @see lts.Declared_fields */
public class Test_declared_fields {


    ////////// Methods //////////
    @Test
    public void set_class() throws Exception {

        Declared_fields._set_class(new User());
        Declared_fields._to_string();

    }

    @Test
    public void test() throws Exception {

        Declared_fields._set_class(new User());

        Print.test("The original version:\n");
        Declared_fields._to_string();

        Declared_fields._edit_field_name(0, "MODIFIED_FILED_NAME_0");

        Print.test("\n\nThe modified version:\n");
        Declared_fields._to_string();

    }


}