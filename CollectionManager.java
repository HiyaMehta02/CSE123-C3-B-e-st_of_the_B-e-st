import java.io.*;
import java.util.*;

// The CollectionManager class is a way to keep track of all items you want to collect. 
// It has methodss like add (adds the item to your collection), contains (checks if 
// your collection contains the item), toString (prints all the items in your collection),
// save (saves the current collection in a PrintStream), and filter (filters through the 
// collection based on genre).
public class CollectionManager {
    private Show front;

    // this is a constructor
    // Behavior :
    //     - Creates a new CollectionManager
    // Parameters : None 
    // Returns : None
    // Exceptions : None
    public CollectionManager() {
        this.front = null;
    }

    // this is a constructor
    // Behavior :
    //     - Creates a new CollectionManager and inputs all the tvshows in the file in 
    //       the collection 
    // Parameters : 
    //     - Scanner input: the scanner for the file that needs to be added to the 
    //       collection 
    //     - File format:
    //       Title
    //       Genre
    //       Number of Seasons
    // Returns : None
    // Exceptions : 
    //     - thows IllegalArgumentException if the scanner is null
    public CollectionManager(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        while (input.hasNextLine()) {
            this.front = new Show(TVShow.parse(input));
        }
    }

    // Behavior :
    //     - adds a TVshow to the collection 
    // Parameters : 
    //     - TVShow show: the TV show that the you want to add to collection 
    // Returns : None
    // Exceptions : None
    public void add(TVShow show) {
        this.front = add(front, show);
    }

    // Behavior :
    //     - adds a TVshow to the collection using recursion 
    // Parameters : 
    //     - TVShow show: the TV show that the you want to add to collection 
    //     - Show cur: the current node  
    // Returns : 
    //     - Show : the new updated binary tree with the TVShow added to it
    // Exceptions : None
    private Show add(Show cur, TVShow show) {
        if (cur == null) {
            return new Show(show);
        }
        if (show.compareTo(cur.show) < 0) {
            cur.left = add(cur.left, show);
        } else if (show.compareTo(cur.show) > 0) {
            cur.right = add(cur.right, show);
        }
        return cur;
    }

    // Behavior :
    //     - checks if the collection contains the tv show you are looking for 
    // Parameters : 
    //     - TVShow show: the TV show that the you are looking for
    // Returns : 
    //     - boolean : true if the show is in your collection and false otherwise
    // Exceptions : None
    public boolean contains(TVShow show) {
        return contains(show, front);
    }


    // Behavior :
    //     - checks if the collection contains the tv show you are looking for 
    //       using recursion
    // Parameters : 
    //     - TVShow show: the TV show that the you are looking for
    //     - Show cur: the current node  
    // Returns : 
    //     - boolean : true if the show is in your collection and false otherwise
    // Exceptions : None
    private boolean contains(TVShow show, Show cur) {
        if (cur == null) {
            return false;
        }
        if (show.compareTo(cur.show) < 0) {
            contains(show, cur.left);
        } else if (show.compareTo(cur.show) > 0){
            contains(show, cur.right);
        }
        return true;
    }

    private Show helper(Show cur, TVShow show, boolean isAdd) {
        
    }

    // Behavior :
    //     - Returns the contents of your collection sorted 
    // Parameters : None  
    // Returns : 
    //     - String : the contents of your collection sorted in-order
    // Exceptions : None
    public String toString() {
        StringBuilder s = new StringBuilder();
        toString(front, s);
        return s.toString();
    }

    // Behavior :
    //     - Returns the contents of your collection sorted using recursion
    // Parameters : 
    //     - StringBuilder sb: the string that needs to be added to 
    //     - Show cur: the current node  
    // Returns : 
    //     - String : the contents of your collection sorted in-order
    // Exceptions : None
    private String toString(Show cur, StringBuilder sb) {
        if (cur != null) {
            toString(cur.left, sb);
            sb.append(cur.show.toString()).append("\n");
            toString(cur.right, sb);
        }
        return sb.toString();
    }


    // Behavior :
    //     - Saves the contents of the collection to the given PrintStream
    // Parameters : 
    //     - PrintStream output: the output stream where the collection data will be written
    // Returns : None
    // Exceptions : 
    //     - IllegalArgumentException : if the given PrintStream is null
    public void save(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException();
        }
        save(front, output);
    }

    // Behavior :
    //     - Recursively saves the contents of the collection to the given 
    //       PrintStream in pre-order traversal
    // Parameters : 
    //     - Show cur: the current node being processed
    //     - PrintStream output: the output stream where the collection data will be written
    // Returns : None
    // Exceptions : None
    private void save(Show cur, PrintStream output) {
        if (cur != null) {
            output.println(cur.show.toString());
            save(cur.left, output);
            save(cur.right, output);
        }
    }

    // Behavior :
    //     - Filters the collection to return a list of TV shows that match the given genre
    // Parameters : 
    //     - String genre: the genre to filter TV shows by
    // Returns : 
    //     - List<TVShow> : a list of TV shows that belong to the specified genre
    // Exceptions : None
    public List<TVShow> filter(String genre) {
        List<TVShow> result = new ArrayList<>();
        filter(front, genre, result);
        return result;
    }

    // Behavior :
    //     - Recursively filters the collection to find TV shows that match the given genre
    // Parameters : 
    //     - Show cur: the current node being processed
    //     - String genre: the genre to filter TV shows by
    //     - List<TVShow> result: the list to store matching TV shows
    // Returns : None
    // Exceptions : None
    private void filter(Show cur, String genre, List<TVShow> result) {
        if (cur != null) {
            if (cur.show.getGenre().equalsIgnoreCase(genre)) {
                result.add(cur.show);
            }
            filter(cur.left, genre, result);
            filter(cur.right, genre, result);
        }
    }

    // The show class acts as the nodes for this class. It holds
    // references to the left and right nodes, and it has a method
    // toString that gives you the string verson of the show.
    private static class Show {
        public TVShow show;
        public Show left;
        public Show right;

        // this is a constructor
        // Behavior :
        //     - creates a new Show and initializes the TV show, the left node,
        //       and the right node
        // Parameters : 
        //     - TVShow show: the TVShow that this node represents
        //     - Show left: the reference to the left node/show
        //     - Show right: the reference to the right node/show
        // Returns : None
        // Exceptions : None            
        public Show(TVShow show, Show left, Show right) {
            this.show = show;
            this.left = left;
            this.right = right;
        }

        // this is a constructor
        // Behavior :
        //     - creates a new Show and initializes the TV show, the left node as null,
        //       and the right node as null
        // Parameters : 
        //     - TVShow show: the TVShow that this node represents
        // Returns : None
        // Exceptions : None
        public Show(TVShow show) {
            this.show = show;
            this.left = null;
            this.right = null;
        }

        // Behavior :
        //     - returns the string form of the show/node
        // Parameters : None
        // Returns : None
        // Exceptions : None
        public String toString() {
            return show.toString();
        }
    }
}
