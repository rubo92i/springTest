package am.basic.springTest.service.impl;

import am.basic.springTest.model.Card;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.repository.CardRepository;
import am.basic.springTest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {


    @Autowired
    private CardRepository cardRepository;


    @Override
    public void add(Card card) throws DuplicateDataException {
        DuplicateDataException.check(cardRepository.existsByNumber(card.getNumber()),"duplicate.card.number");
        cardRepository.save(card);
    }


    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }


    @Override
    public Optional<Card> getById(int id) {
        return cardRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Card getByNumber(String number) {
        return cardRepository.getByNumber(number);
    }

    @Override
    public void update(Card card) throws DuplicateDataException {
        DuplicateDataException.check(cardRepository.existsByNumberAndIdNot(card.getNumber(),card.getId()),"duplicate.card");
        cardRepository.save(card);
    }
}
