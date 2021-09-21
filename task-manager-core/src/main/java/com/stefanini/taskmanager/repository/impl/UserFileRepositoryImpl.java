package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Stream;

public class UserFileRepositoryImpl implements UserRepository {

    public static UserFileRepositoryImpl INSTANCE;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserFileRepositoryImpl.class);

    private UserFileRepositoryImpl() {
    }

    public static UserFileRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserFileRepositoryImpl();
        }

        return INSTANCE;
    }

    public static final String FILE_LOCATION = "taskmanager.ser";

    @Override
    public void saveUser(final User user) {
        try (
                FileOutputStream fout = new FileOutputStream(FILE_LOCATION);
                ObjectOutputStream oos = new ObjectOutputStream(fout)
        ) {
            List<User> users = findAllUsers();
            users.add(user);
            oos.writeObject(users);
        } catch (IOException e) {
            log.error("Something bad happened during fetching user = {} ", user, e);
        }
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public List<User> findAllUsers() {
        try (
                FileInputStream fin = new FileInputStream(FILE_LOCATION);
                ObjectInputStream oos = new ObjectInputStream(fin)
        ) {
            return (List<User>) oos.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User user) {
        List<User> users = findAllUsers();
        try (
                FileOutputStream fout = new FileOutputStream(FILE_LOCATION);
                ObjectOutputStream oos = new ObjectOutputStream(fout)
        ) {
            ListIterator<User> iterator = users.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next().getUserName().equals(user.getUserName())) {
                    iterator.set(user);
                }
            }
            oos.writeObject(users);
        } catch (IOException e) {
            log.error("Something bad happened during fetching users = {} ", users, e);
        }
    }

    @Override
    public void deleteUserById(final Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<User> streamAll() {
        return null;
    }

}