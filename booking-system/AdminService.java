import java.util.ArrayList;
import java.util.List;

class AdminService {

    List<Movie> movies = new ArrayList<>();
    List<Theater> theaters = new ArrayList<>();
    List<Show> shows = new ArrayList<>();

    void createMovie(Movie movie) {
        movies.add(movie);
    }

    void createTheater(Theater theater) {
        theaters.add(theater);
    }

    boolean createShow(Show show) {

        for (Show old : shows) {

            if (old.screen.id == show.screen.id) {

                if (!(show.end.compareTo(old.start) <= 0 ||
                      show.start.compareTo(old.end) >= 0)) {

                    return false;
                }
            }
        }

        shows.add(show);
        return true;
    }
}