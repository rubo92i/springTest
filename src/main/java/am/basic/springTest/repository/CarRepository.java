package am.basic.springTest.repository;

import am.basic.springTest.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    boolean existsByVin(String vin);

    boolean existsByVinAndIdNot(String vin, int id);

}
