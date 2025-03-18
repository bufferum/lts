package lts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * <h4>Manager for executing bash scripts from Java.</h4>
 *
 * <h4>ATTENTION!!!</h4>
 * <p>Do not use ";" at the end of commands!
 *
 * @version 2.0
 * @author bufferum
 */
public class Terminal {


    ////////// Variables //////////
    private StringBuilder result;


    ////////// Constructors //////////
    @SuppressWarnings("unused")
    private Terminal() { }
    public Terminal(String... commands) {

        try {

            List<String> list_command = new ArrayList<>();
                list_command.add("bash");
                list_command.add("-c");
                list_command.add(String.join(" ; ", commands));

            ProcessBuilder processBuilder = new ProcessBuilder(list_command);

            // Запуск процесса
            Process process = processBuilder.start();

            result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            while((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }

        }
        catch(Exception e) { e.printStackTrace(); }

    }


    ////////// Methods //////////
    public String _get_result() { return result + ""; }


}