import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;


public class Testing {
    private CollectionManager manager;
    private TVShow show1;
    private TVShow show2;
    private TVShow show3;
    private TVShow show4;
    private TVShow show5;

    @BeforeEach
    public void setUp() {
        manager = new CollectionManager();
        show1 = new TVShow("New Girl", "Sitcom", 7);
        show2 = new TVShow("Heartstopper", "Romance", 3);
        show3 = new TVShow("Back to 15", "Romance", 3);
        show4 = new TVShow("Heartbreak High", "Drama", 2);
        show5 = new TVShow("Heartbreak High", "Drama", 2);
    }

    @Test
    @DisplayName("test constructor")
    public void test0() throws FileNotFoundException {
        File file = new File("shows.txt");
        Scanner scanner = new Scanner(file);
        CollectionManager cm = new CollectionManager(scanner);
        assertNotNull(cm, "CollectionManager should not be null");
        TVShow expectedShow = new TVShow("New Girl", "Sitcom", 7);
        assertTrue(cm.contains(expectedShow), "CollectionManager should contain 'New Girl'");
        TVShow expectedShow2 = new TVShow("On My Block", "Drama", 4);
        assertTrue(cm.contains(expectedShow2), "CollectionManager should contain 'On My Block'");
        TVShow expectedShow3 = new TVShow("The Flash", "Action", 9);
        assertTrue(cm.contains(expectedShow3), "CollectionManager should contain 'The Flash'");
    }

    @Test
    @DisplayName("test add and contains")
    public void test1() {
        assertFalse(manager.contains(show1));
        manager.add(show1);
        assertTrue(manager.contains(show1));
    } 

    @Test
    @DisplayName("test contains empty")
    public void test2() {
        assertFalse(manager.contains(show1));
    }

    @Test 
    @DisplayName("test add multiple and contains")
    public void test3() {
        manager.add(show1);
        manager.add(show2);
        manager.add(show3);
        manager.add(show4);
        assertTrue(manager.contains(show1));
        assertTrue(manager.contains(show3));
        assertTrue(manager.contains(show4));
    }

    @Test
    @DisplayName("test toString")
    public void test4() {
        manager.add(show1);
        manager.add(show2);
        manager.add(show3);
        manager.add(show4);
        String output = manager.toString();
        assertTrue(output.contains("New Girl"));
        assertTrue(output.contains("Heartstopper"));
        assertTrue(output.contains("Back to 15"));
        assertTrue(output.contains("Heartbreak High"));
    }

    @Test
    @DisplayName("test Filter")
    public void test5() {
        manager.add(show1);
        manager.add(show2);
        manager.add(show3);
        manager.add(show4);
        List<TVShow> romanceShows = manager.filter("Romance");
        assertEquals(2, romanceShows.size());
        assertTrue(romanceShows.contains(show2));
        assertTrue(romanceShows.contains(show3));
    }

    @Test
    @DisplayName("test filter no matches")
    public void test6() {
        manager.add(show1);
        manager.add(show2);
        List<TVShow> comedyShows = manager.filter("Comedy");
        assertEquals(0, comedyShows.size());
    }

    @Test
    @DisplayName("test save")
    public void test7() throws FileNotFoundException {
        manager.add(show1);
        manager.add(show2);
        File outputFile = new File("\show.txt");
        try (PrintStream printStream = new PrintStream(outputFile)) {
            manager.save(printStream);
        }
        StringBuilder fileContent = new StringBuilder();
        try (Scanner scanner = new Scanner(outputFile)) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
        }
        String output = fileContent.toString();
        System.out.println(output);
        assertTrue(output.contains("New Girl"));
        assertTrue(output.contains("Heartstopper"));
    }

    @Test
    @DisplayName("test TVShow equals")
    public void test8() {
        assertTrue(show4.equals(show5));
        assertFalse(show1.equals(show2));
    }

    @Test
    @DisplayName("test TVShow hashcode")
    public void test9() {
        assertEquals(show5.hashCode(), show5.hashCode());
        assertNotEquals(show1.hashCode(), show2.hashCode());
    }

    @Test
    @DisplayName("test TVShow compareTo")
    public void test10() {
        assertTrue(show1.compareTo(show2) > 0);
        assertTrue(show2.compareTo(show1) < 0);
        assertEquals(0, show4.compareTo(show5));
    }
}
