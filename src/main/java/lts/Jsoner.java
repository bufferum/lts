package lts;
import lts.jsoner.Jsoner_format;
import lts.jsoner.Jsoner_get;
import lts.jsoner.Jsoner_map;
import lts.jsoner.Jsoner_object;
import lts.jsoner.Jsoner_set;


/**
 * <p>Management for JSON.
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
 *      <tr>
 *          <th scope="row">{@link lts.Jsoner#SET SET}</th>
 *          <td>
 *              <p>It is used to change values anywhere in the root of a json file.
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.Jsoner#FORMAT FORMAT}</th>
 *          <td>
 *              <p>It is used to convert the contents of a json file into a
 *                 structured or unstructured (single line) format.
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.Jsoner#MAP MAP}</th>
 *          <td>
 *              <p>Use to create fast JSON structures that do not require the
 *                 creation of objects.
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.Jsoner#OBJECT OBJECT}</th>
 *          <td>
 *              <p>It is used to create JSON structures based on a single object.
 *          </td>
 *      </tr>
 *
 *      <tr>
 *          <th scope="row">{@link lts.Jsoner#GET GET}</th>
 *          <td>
 *              <p>It is used to read values anywhere in the root json file.
 *          </td>
 *      </tr>
 *  </tbody>
 *
 * </table>
 *
 * @patterns Singleton, Builder
 * @version 1.0
 * @author bufferum
 */
public interface Jsoner {


    ////////// Variables //////////
    /**
     * <p>It is used to convert the contents of a json file into a structured or
     * unstructured (single line) format.
     */
    public static final Jsoner_format FORMAT = new Jsoner_format();

    /** <p>It is used to read values anywhere in the root json file. */
    public static final Jsoner_get GET = new Jsoner_get();

    /**
     * <p>Use to create fast JSON structures that do not require the creation
     * of objects.

     * <p>Usage example:
     * <ul>
     *  <li>to transfer data
     *  <li>to be stored in a database cell
     *  <li>to work with the API
     * </ul>
     */
    public static final Jsoner_map MAP = new Jsoner_map();

    /**
     * <p>It is used to create JSON structures based on a single object.
     *
     * <p>Usage example:
     * <ul>
     *  <li>to store big data in a file on the server
     *  <li>to form a certain structure with further placement of this data
     *      in the database
     * </ul>
     */
    public static final Jsoner_object OBJECT = new Jsoner_object();

    /** <p>It is used to modify or create values anywhere in the root json file. */
    public static final Jsoner_set SET = new Jsoner_set();


}