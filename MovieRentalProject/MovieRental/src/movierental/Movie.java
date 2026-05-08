package movierental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Movie {

    private int movieID;
    private String movieName;
    private String movieDescription;
    private int movieStock;

    public Movie(String movieName, String movieDescription){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }

    //#region getters and setters
    public int getMovieID(){
        return movieID;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getMovieDescription(){
        return movieDescription;
    }

    public int getMovieStock(){
        return movieStock;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public void setMovieDescription(String movieDescription){
        this.movieDescription = movieDescription;
    }

    public void setMovieStock(int movieStock){
        this.movieStock = movieStock;
    }
    //#endregion

    public static boolean addMovie(String movieName, String movieDescription, int movieStock) throws SQLException{
        Connection connection = DBManager.getConnection();

        String insert_movie = "INSERT INTO movie (movie_name, movie_description, movie_stock)" + "VALUES (? ? ?)";

        PreparedStatement stmt_insert_movie = connection.prepareStatement(insert_movie);

        stmt_insert_movie.setString(1, movieName);
        stmt_insert_movie.setString(2, movieDescription);
        stmt_insert_movie.setInt(3, movieStock);

        int updatedRows = stmt_insert_movie.executeUpdate();

        return updatedRows > 0;
    }

    public static boolean removedMovie(int movieID) throws SQLException{
        Connection connection = DBManager.getConnection();

        String delete_movie = "DELETE FROM movie WHERE movie_id = ?";

        PreparedStatement stmt_delete_movie = connection.prepareStatement(delete_movie);

        stmt_delete_movie.setInt(1, movieID);

        int deletedRows = stmt_delete_movie.executeUpdate();

        return deletedRows > 0;
    }
}
