package io.wkrzywiec.hexagonal.library.domain;

import io.wkrzywiec.hexagonal.library.domain.book.model.NewBookDTO;
import io.wkrzywiec.hexagonal.library.domain.book.ports.outgoing.BookRepository;

import java.util.concurrent.ConcurrentHashMap;


public class InMemoryBookRepository implements BookRepository {

    ConcurrentHashMap<Long, NewBookDTO> books = new ConcurrentHashMap<>();

    @Override
    public void save(NewBookDTO newBookDTO) {
        Long id = books.size() + 1L;
        books.put(id, newBookDTO);
    }
}
