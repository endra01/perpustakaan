package com.sinaukoding.perpustakaan.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@Setter
@Getter
@NoArgsConstructor
public class Book extends BaseEntity<Book> {

    @Column(name = "title", columnDefinition = "VARCHAR(50)")
    private String tittle;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "description")
    private String description;

}
