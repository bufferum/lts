package lts;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * Allows you to get a list of all fields of the imported class.
 *
 * @return {@code List<Declared_fields>}
 * 	<ul>
 * 		<li>(String) <code>field_name</code></li>
 *  	<li>(Object) <code>field_value</code></li>
 * 	</ul>
 *
 * @patterns Singleton
 * @version 1.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Declared_fields {


    ////////// Variables //////////
    @Nullable private String field_name;
    @Nullable private Object field_value;
    @Nullable private static List<Declared_fields> list_declared_fields;

    /** it length List<Declared_fields> */
    @Nullable public static Integer length;


    ////////// Constructors //////////
    private Declared_fields() { }
    private Declared_fields(String field_name, Object field_value) {
        this.field_name = field_name;
        this.field_value = field_value;
    }


	////////// Methods //////////
	/**
	 * This method collects instances variables, and their names in
     *      {@code list_declared_fields}.
	 *
	 * @return {@code List<Declared_fields>}
	 * 	<ul>
     * 		<li>(String) <code>field_name</code></li>
	 *  	<li>(Object) <code>field_value</code></li>
	 * 	</ul>
	 */
	public static List<Declared_fields> _set_class(Object my_class) throws Exception {

		Field[] fields = my_class.getClass().getDeclaredFields();
		length = fields.length;
		list_declared_fields = new ArrayList<>();

		for(int i = 0; i < length; i++) {

			Field field = fields[i];
			field.setAccessible(true);

            list_declared_fields.add(
                new Declared_fields(
                    field.getName(),
                    (my_class == null) ? "null" : (Object) field.get(my_class)
                )
            );

		}

		return list_declared_fields;
	}

    /** @return {@code List<Declared_fields> ths.field} */
    public static Object _get_field_value(int i) {

        return list_declared_fields.get(i).field_value;
    }

    /** @return {@code List<Declared_fields> this.field_name} */
    public static String _get_field_name(Integer i) {

        return list_declared_fields.get(i).field_name;
    }

    /**
     * <p>This method does not change the object itself. It is not a reference one.
     * It only changes the value of the copy object inside list_declared_fields
     */
    public static void _edit_field_value(int i, Object field_value) {

        list_declared_fields.get(i).field_value = String.valueOf(field_value);

    }

    /**
     * <p>This method does not change the object itself. It is not a reference one.
     * It only changes the name of the copy object inside list_declared_fields
     */
    public static void _edit_field_name(int i, String field_name) {

        list_declared_fields.get(i).field_name = field_name;

    }

    public static void _to_string() throws Exception {

        Print.logger_disable();

        for(int i = 0; i < list_declared_fields.size(); i++) {

            Jsoner.MAP._set(
                "class_name",
                (
                    (list_declared_fields.get(i).field_value == null)
                    ? "?null?"
                    : (Object) _get_field_value(i).getClass().getSimpleName()
                )
            );
            Jsoner.MAP._set(
                "field_name",
                _get_field_name(i)
            );
            Jsoner.MAP._set(
                "field_value",
                (
                    (list_declared_fields.get(i).field_value == null)
                    ? "?null?"
                    : (Object) _get_field_value(i).toString()
                )
            );

            Jsoner.MAP._wrap_array();

        }

        Print.test(Jsoner.MAP._to_json());
        Print.logger_enable();

    }


}