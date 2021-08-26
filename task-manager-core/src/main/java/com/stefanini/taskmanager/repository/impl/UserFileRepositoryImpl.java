package com.stefanini.taskmanager.repository.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFileRepositoryImpl implements UserRepository {

    public static UserFileRepositoryImpl INSTANCE;

    private UserFileRepositoryImpl() {
    }

    public static UserFileRepositoryImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserFileRepositoryImpl();
        }

        return INSTANCE;
    }

    public static final String FILE_LOCATION = "taskmanager.ser";

    @Override
    public int saveUser(final User user) {
        try (
                FileOutputStream fout = new FileOutputStream(FILE_LOCATION);
                ObjectOutputStream oos = new ObjectOutputStream(fout)
        ) {
            List<User> users = findAllUsers();
            users.add(user);
            oos.writeObject(users);
        } catch (IOException e) {
            log.error("Something bad happened during fetching user = {} ", user, e);
            return 0;
        }
        return 1;
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return findAllUsers()
                .stream()
                .filter(u -> u.getUserName().equals(username)).findFirst();
    }

    @SuppressWarnings("unchecked")
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
            while (iterator.hasNext()){
                if(iterator.next().getUserName().equals(user.getUserName())){
                    iterator.set(user);
                }
            }
            oos.writeObject(users);
        } catch (IOException e) {
            log.error("Something bad happened during fetching users = {} ", users, e);
        }
    }

    @Override
    public int deleteUserById(final Long id) {
        throw new UnsupportedOperationException();
    }
}