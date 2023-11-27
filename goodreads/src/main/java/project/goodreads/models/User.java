package project.goodreads.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> bookShelf;

}
