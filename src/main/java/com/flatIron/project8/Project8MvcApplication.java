package com.flatIron.project8;

import com.flatIron.project8.model.Genre;
import com.flatIron.project8.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Project8MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project8MvcApplication.class, args);
	}

	@Component
	public class StartUpRunner implements CommandLineRunner {
		@Autowired
		private AuthorRepository authorRepository;

		@Autowired
		private BookRepository bookRepository;

		@Autowired
		private GenreRepository genreRepository;

		@Autowired
		private ReadingListRepository readingListRepository;

		@Autowired
		private UserRepository userRepository;

		@Override
		public void run(String... args) throws Exception {
			// create all genres and save to database
			List<String> genres = new ArrayList<String>(Arrays.asList(
				"science fiction",
				"mystery",
				"thriller",
				"horror",
				"historical",
				"romance",
				"western",
				"fantasy"
			));
			genres.forEach(genre -> genreRepository.save(new Genre(genre)));


		}

	}
}
