package am.basic.springTest.repository;


import am.basic.springTest.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card getById(int id);

    Card getByNumber(String number);
}


