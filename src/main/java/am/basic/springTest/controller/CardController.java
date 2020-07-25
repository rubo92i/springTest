package am.basic.springTest.controller;

import am.basic.springTest.model.Card;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/cards")
public class CardController {





    //get tvyalner kardalu hamar
    //post tvyalner grelu avelacnelu hamar
    //put tvtyalner@ ttarmacnelu hamar
    //delete jnjelu


    @Autowired
    private CardService cardService;


    @GetMapping
    public ResponseEntity getAll() {
        List<Card> cards = cardService.findAll();
        return ResponseEntity.status(200).body(cards);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Card card) throws DuplicateDataException {
        cardService.add(card);
        return ResponseEntity.ok(card);
    }


    @GetMapping("/by-number/{number}")
    public ResponseEntity getByName(@PathVariable String number) {
        Card card = cardService.getByNumber(number);
        return ResponseEntity.ok(card);
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        log.info("Received request for getting card by id : {}", id);
        Optional<Card> optional = cardService.getById(id);
        return ResponseEntity.of(optional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id, HttpServletRequest request) {
        log.info("Delete request for Card");
        if (log.isDebugEnabled()){
            log.debug("Delete request for Card with id {} from ip {}",id,request.getRemoteAddr());
        }
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Card card, @PathVariable int id) throws DuplicateDataException {
        card.setId(id);
        cardService.update(card);
        return ResponseEntity.ok(card);
    }

}


//cardas/15/update
//cards/15/delete
//cards/15/get

//  /cards  { post - add anum, put - update anum, delete - jnjuma, get - listna vereadarcnum}
