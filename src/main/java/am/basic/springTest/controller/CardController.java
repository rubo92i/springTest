package am.basic.springTest.controller;

import am.basic.springTest.model.Card;
import am.basic.springTest.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {



    @Autowired
    private CardRepository cardRepository;


    @GetMapping
    public ResponseEntity getAll(){
        //return ResponseEntity.status(200).body(cardRepository.findAll());
        return ResponseEntity.ok(cardRepository.findAll());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Card card){
        return ResponseEntity.ok(cardRepository.save(card));
    }


    @GetMapping("/by-number/{number}")
    public ResponseEntity getByName(@PathVariable String number){
        return ResponseEntity.ok(cardRepository.getByNumber(number));
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        return ResponseEntity.ok(cardRepository.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        cardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Card card,@PathVariable int id){
        card.setId(id);
        return ResponseEntity.ok(cardRepository.save(card));
    }

}
