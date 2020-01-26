package library.service;

import library.dao.InterfaceUser;

import java.util.ArrayList;

public interface UserPersistenceStorable {

    public ArrayList<InterfaceUser> loadUsers();
    public void saveUsers(ArrayList<InterfaceUser> users);
}
