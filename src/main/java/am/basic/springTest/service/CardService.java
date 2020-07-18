package am.basic.springTest.service;

import am.basic.springTest.model.Card;
import am.basic.springTest.model.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CardService {
    void add(Card card);

    List<Card> findAll();

    Optional<Card> getById(int id) ;

    void deleteById(int id);

    Card getByNumber(String number);

}
