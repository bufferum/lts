package lts;
import org.junit.Rule;
import org.junit.Test;

import lts.algorithms.Declared_fields;
import lts.signs.Print;


/** @see lts.algorithms.Declared_fields */
public class Test_declared_fields {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void set_class() throws Exception {

        Declared_fields._set_class(new Test_USER());
        Declared_fields._to_string();

    }

    @Test public void edit_field_name() throws Exception {

        Declared_fields._set_class(new Test_USER());

        Print.test("The original version:\n");
        Declared_fields._to_string();

        Declared_fields._edit_field_name(0, "MODIFIED_FILED_NAME_0");

        Print.test("\n\nThe modified version:\n");
        Declared_fields._to_string();

    }

    @Test public void get_list_all_currnet_threads() throws Exception {

        Declared_fields._get_list_all_currnet_threads();

    }


}