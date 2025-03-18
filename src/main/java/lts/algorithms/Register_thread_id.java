package lts.algorithms;
import java.util.ArrayList;
import java.util.List;


/**
 * <h4>This class provides an id for numbering streams that store
 * listeners of client sockets.</h4>
 *
 * @see lts.webspace.sockets.Socket_server
 * @version 2.0
 * @author bufferum
 */
public class Register_thread_id {


    ////////// Methods //////////
    public Integer _get_thread_id() {

        List<Integer> registred_thread_id = new ArrayList<>();

        Integer id = 1;
        Boolean key = true;

        for(Integer i : registred_thread_id) {

            if(id != i) {

                key = false;
                registred_thread_id.add(id);

                break;
            }
            else {

                id++;
                continue;
            }

        }

        if(key) {
            registred_thread_id.add(id);
        }

        return id;
    }


}