package library.service;

import java.util.ArrayList;

public interface CredentialsPersistenceStorable {

    public ArrayList<ArrayList<String>> loadCredentials();
    public void saveCredentials(ArrayList<ArrayList<String>> creds);
}
