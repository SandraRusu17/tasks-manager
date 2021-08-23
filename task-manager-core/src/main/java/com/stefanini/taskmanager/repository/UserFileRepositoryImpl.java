//package com.stefanini.taskmanager.repository;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Optional;
//
//import com.stefanini.taskmanager.entity.User;
//
//public class UserFileRepositoryImpl implements UserRepository {
//
//    public static UserFileRepositoryImpl INSTANCE;
//
//    private UserFileRepositoryImpl() {
//    }
//
//    public static UserFileRepositoryImpl getInstance() {
//        if(INSTANCE == null) {
//            INSTANCE = new UserFileRepositoryImpl();
//        }
//
//        return INSTANCE;
//    }
//
//    public static final String FILE_LOCATION = "taskmanager.ser";
//
//    @Override
//    public int saveUser(final User user) {
//        try (
//                FileOutputStream fout = new FileOutputStream(FILE_LOCATION);
//                ObjectOutputStream oos = new ObjectOutputStream(fout)
//        ) {
//            List<User> users = findAllUsers();
//            users.add(user);
//            oos.writeObject(users);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Optional<User> findByUsername(final String username) {
//        return findAllUsers()
//                .stream()
//                .filter(u -> u.getUserName().equals(username)).findFirst();
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<User> findAllUsers() {
//        try (
//                FileInputStream fin = new FileInputStream(FILE_LOCATION);
//                ObjectInputStream oos = new ObjectInputStream(fin)
//        ) {
//            return (List<User>) oos.readObject();
//        } catch (Exception e) {
//            //first time usage
//            return new ArrayList<>();
//        }
//    }
//
//    @Override
//    public void update(User user) {
//        List<User> users = findAllUsers();
//        try (
//                FileOutputStream fout = new FileOutputStream(FILE_LOCATION);
//                ObjectOutputStream oos = new ObjectOutputStream(fout)
//        ) {
//            ListIterator<User> iterator = users.listIterator();
//            while (iterator.hasNext()){
//                if(iterator.next().getUserName().equals(user.getUserName())){
//                    iterator.set(user);
//                }
//            }
//            oos.writeObject(users);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
