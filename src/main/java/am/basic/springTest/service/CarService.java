package am.basic.springTest.service;

import am.basic.springTest.model.Car;
import am.basic.springTest.model.exceptions.DuplicateDataException;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAll();

    Optional<Car> findById(int id);

    Car save(Car car) throws DuplicateDataException;

    Car update(Car car) throws DuplicateDataException;


}
