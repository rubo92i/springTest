package am.basic.springTest.service.impl;


import am.basic.springTest.model.User;
import am.basic.springTest.model.exceptions.AccessDeniedException;
import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.model.exceptions.NotFoundException;
import am.basic.springTest.model.exceptions.UnverifiedException;
import am.basic.springTest.repository.UserRepository;
import am.basic.springTest.service.UserService;
import am.basic.springTest.util.MailSenderClient;
import am.basic.springTest.util.encoder.Generator;
import am.basic.springTest.util.encoder.Md5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static am.basic.springTest.util.constants.Messages.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Override
    @Transactional
    public void register(User user) throws DuplicateDataException {
        User duplicate = userRepository.getByUsername(user.getUsername());
        DuplicateDataException.check(duplicate != null, DUPLICATE_USER_MESSAGE);
        user.setPassword(Md5Encoder.encode(user.getPassword()));
        user.setCode(Generator.getRandomDigits(5));
        user.setStatus(0);
        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), "Verification", "Your code is " + user.getCode());
    }


    @Override
    public User login(String username, String password) throws NotFoundException, UnverifiedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null || !Md5Encoder.matches(password, user.getPassword()), INVALID_CREDENTIALS_MESSAGE);
        UnverifiedException.check(user.getStatus() != 1, UNVERIFIED_MESSAGE);
        return user;

    }

    @Override
    public User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getPassword().equals(Md5Encoder.encode(password)), WRONG_PASSWORD_MESSAGE);
        user.setPassword(Md5Encoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void sendCode(String username) throws NotFoundException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        user.setCode(Generator.getRandomDigits(5));
        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), "ACCESS CODE", "Your code is " + user.getCode());
    }

    @Override
    public void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setPassword(Md5Encoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void verify(String username, String code) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setStatus(1);
        userRepository.save(user);
    }

}
