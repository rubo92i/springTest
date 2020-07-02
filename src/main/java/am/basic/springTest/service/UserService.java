package am.basic.springTest.service;


import am.basic.springTest.model.User;
import am.basic.springTest.model.exceptions.AccessDeniedException;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.model.exceptions.NotFoundException;
import am.basic.springTest.model.exceptions.UnverifiedException;

public interface UserService {


    void register(User user) throws DuplicateDataException;

    User login(String username, String password) throws NotFoundException, UnverifiedException;

    User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException;

    void sendCode(String username) throws NotFoundException;

    void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException;

    void verify(String username, String code) throws NotFoundException, AccessDeniedException;
    
}
