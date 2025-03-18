package lts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/** User - is a class used exclusively for conducting JUnit tests */
public class Test_USER implements Serializable {


    ////////// Variables //////////
    private String name;
    private int age;
    private List<Studys> studys;
    private String[] character_traits;


    ////////// Constructors //////////
    public Test_USER() {

        List<Studys> list = new ArrayList<>();
            list.add(new Studys("MGU_1", 2020));
            list.add(new Studys("MGU_2", 2024));

        setName("bufferum");
        setAge(20);
        setCharacter_traits(new String[] { "Kind", "Wit", "Brave" } );
        setStudys(list);

    }


    ////////// Methods //////////
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setStudys(List<Studys> studys) { this.studys = studys; }
    public void setCharacter_traits(String[] character_traits) { this.character_traits = character_traits; }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<Studys> getStudys() { return studys; }
    public String[] getCharacter_traits() { return character_traits; }


    ////////// Class //////////
    class Studys implements Serializable {


        ////////// Variables //////////
        @SuppressWarnings("unused") private String educational_institution;
        @SuppressWarnings("unused") private int year_of_completion;


        ////////// Constructors //////////
        public Studys(String educational_institution, int year_of_completion) {
            this.educational_institution = educational_institution;
            this.year_of_completion = year_of_completion;
        }


    }


}