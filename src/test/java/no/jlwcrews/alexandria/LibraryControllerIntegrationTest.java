/*
package no.jlwcrews.alexandria;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.jlwcrews.alexandria.author.Author;
import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.location.Location;
import no.jlwcrews.alexandria.models.Status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @Order(1)
    void shouldRetrieveAllBooks() throws Exception {
        this.mockMvc.perform(get("/api/books/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(50)));
    }

    @Order(2)
    @Test
    void shouldRetrieveOneBook() throws Exception {
        this.mockMvc.perform(get("/api/books/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookId").value(5));
    }

    @Test
    @Order(3)
    void shouldAddNewBook() throws Exception {
        Book book = new Book(
                92,
                "Tapping the Source",
                new Author("Kem", "Nunn"),
                "St Martins Press",
                new Status(true),
                new Location("shelf 203934"));

        this.mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    void shouldFetchNewBook() throws Exception {
        this.mockMvc.perform(get("/api/books/92"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(92));
    }
}
*/
