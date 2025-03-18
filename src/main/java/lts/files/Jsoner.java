package lts.files;
import lts.files.jsoner.Jsoner_format;
import lts.files.jsoner.Jsoner_read;
import lts.files.jsoner.Jsoner_create;
import lts.files.jsoner.Jsoner_update;


/**
 * <h4>Management for JSON.</h4>
 *
 * <table class="striped">
 * <caption style="display:none">Options</caption>
 *
 *  <thead>
 *      <tr>
 *          <th scope="col">Object</th>
 *          <th scope="col">Description</th>
 *      </tr>
 *  </thead>
 *
 *  <tbody>
 *
 *      <tr>
 *          <th scope="row">{@link lts.file.lts.Jsoner#FORMAT FORMAT}</th>
 *          <td>
 *              <p>It is used to convert the contents of a json file into a
 *                 structured or unstructured (single line) format.</p>
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.file.lts.Jsoner#CREATE CREATE}</th>
 *          <td>
 *              <p>Creates json from objects (of a class) or from individual variables.</p>
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.file.lts.Jsoner#READ READ}</th>
 *          <td>
 *              <p>It is used to read values anywhere in the root json file.</p>
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.file.lts.Jsoner#UPDATE UPDATE}</th>
 *          <td>
 *              <p>It is used to change values anywhere in the root of a json file.</p>
 *          </td>
 *      </tr>
 *
 *  </tbody>
 *
 * </table>
 *
 * @patterns Singleton, Builder
 * @version 2.0
 * @author bufferum
 */
public interface Jsoner {


    ////////// Variables //////////
    /**
     * <p>It is used to convert the contents of a json file into a structured or
     * unstructured (single line) format.</p>
     */
    public static final Jsoner_format FORMAT = new Jsoner_format();

    /**
     * <p>The class offers two types of creation:</p>
     * <ul>
     *  <li>Using class objects as a ready-made template. This is useful for working with a database.
     *  <li>Manually using the usual variables. You build the sequence and structure of the json yourself.
     * </ul>
     */
    public static final Jsoner_create CREATE = new Jsoner_create();

    /** <p>It is used to read values anywhere in the root json file.</p> */
    public static final Jsoner_read READ = new Jsoner_read();

    /** <p>It is used to modify or create values anywhere in the root json file.</p> */
    public static final Jsoner_update UPDATE = new Jsoner_update();


}