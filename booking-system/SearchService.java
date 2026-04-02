import java.util.ArrayList;
import java.util.List;

class SearchService {

    List<Show> shows;

    SearchService(List<Show> shows) {
        this.shows = shows;
    }

    List<Theater> getTheatersByMovie(Movie movie) {

        List<Theater> list = new ArrayList<>();

        for (Show show : shows) {

            if (show.movie.id == movie.id)
                list.add(show.theater);
        }

        return list;
    }

    List<Movie> getMoviesByTheater(Theater theater) {

        List<Movie> list = new ArrayList<>();

        for (Show show : shows) {

            if (show.theater.id == theater.id)
                list.add(show.movie);
        }

        return list;
    }
}