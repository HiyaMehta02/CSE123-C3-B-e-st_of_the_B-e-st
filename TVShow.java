import java.io.*;
import java.util.*;

// The TVShow class 
public class TVShow implements Comparable<TVShow> {
    private String name;
    private String genre;
    private int numSeasons;

    public TVShow(String name, String genre, int numSeasons) {
        this.name = name;
        this.genre = genre;
        this.numSeasons = numSeasons;
    }

    // Behavior :
    //     - returns the name of the tv show
    // Parameters : None
    // Returns : None
    // Exceptions : None
    public String getName() {
        return name;
    }

    // Behavior :
    //     - returns the genre of the tv show
    // Parameters : None
    // Returns : None
    // Exceptions : None
    public String getGenre() {
        return genre;
    }

    // Behavior :
    //     - returns the number of seasons the tv show has
    // Parameters : None
    // Returns : None
    // Exceptions : None
    public int getNumSeasons() {
        return numSeasons;
    }

    // Behavior :
    //     - returns the string representation of the TV show
    // Parameters : None
    // Returns : 
    //     - String : the string representation of the TV show
    // Exceptions : None
    public String toString() {
        return name + "\n" + genre + "\n" + numSeasons;
    }

    // Behavior :
    //     - checks if two tv shows are equal to each other
    // Parameters : 
    //     - TVShow other: the tv show that you want to compare this one to
    // Returns : 
    //     - boolean : true if the tv shows are equal and false otherwise
    // Exceptions : None
    public boolean equals(TVShow other) {
        if (this.getName() == other.getName() && this.getGenre() == other.getGenre() && 
                        this.getNumSeasons() == other.getNumSeasons()) {
            return true;
        }
        return false;
    }

    // Behavior :
    //     - returns the hashcode for the tvshow
    // Parameters : None
    // Returns : 
    //     - int : the hashcode for the tvshow
    // Exceptions : None
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + genre.hashCode();
        hash = 31 * hash + Integer.hashCode(numSeasons);
        return hash;
    }

    // Behavior :
    //     - Parses user input to create a new TVShow object
    // Parameters : 
    //     - Scanner input: the scanner used to read user input
    // Returns : None
    // Exceptions : 
    //     - IllegalArgumentException : if the input scanner is null or if the number of 
    //       seasons is not a valid integer
    public static TVShow parse(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        System.out.println("Enter TV Show name: ");
        String name = input.nextLine().trim();
        System.out.println("Enter genre: ");
        String genre = input.nextLine().trim();
        System.out.println("Enter number of seasons: ");
        int numSeasons;
        if (input.hasNextInt()) {
            numSeasons = input.nextInt();
            input.nextLine();
        } else {
            throw new IllegalArgumentException();
        }
        return new TVShow(name, genre, numSeasons);
    }

    // Behavior :
    //     - Compares this TVShow object with another TVShow for ordering
    //     - Compare rules:
    //          first title gets sorted alphabetically 
    //          second the genre gets sorted alphabetically
    //          third number of seasons gets sorted from more seasons to less
    // Parameters : 
    //     - TVShow other: the TVShow object to compare against
    // Returns : 
    //     - int : a negative value if this TVShow is less than the other, 
    //             a positive value if greater, and 0 if they are equal
    // Exceptions : 
    //     - IllegalArgumentException : if the provided TVShow object is null
    public int compareTo(TVShow other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        int genreComparison = this.genre.compareTo(other.genre);
        if (genreComparison != 0) {
            return genreComparison;
        }
        if (this.numSeasons < other.numSeasons) {
            return -1;
        } else if (this.numSeasons > other.numSeasons) {
            return 1;
        } else {
            return 0;
        }
    }
}
