package am.basic.springTest.service.impl;

import am.basic.springTest.model.Car;
import am.basic.springTest.model.exceptions.DatabaseException;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.repository.CarRepository;
import am.basic.springTest.service.CarService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) throws DuplicateDataException {
        DuplicateDataException.check(carRepository.existsByVin(car.getVin()),"duplicate.car.win");
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) throws DuplicateDataException {
        DuplicateDataException.check(carRepository.existsByVinAndIdNot(car.getVin(),car.getId()),"duplicate.car.win");
        return carRepository.save(car);
    }
}
