package com.simon.onemore;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    private static DataLayer instance;

    public static DataLayer getInstance() {
        if(instance == null){
            createInstance();
        }
        return instance;
    }

    private List<SessionData> sessions;

    public List<SessionData> getSessions() {
        return sessions;
    }

    private SessionData activeSession;

    public SessionData getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(SessionData newSession) {
        activeSession = newSession;
    }

    private DataLayer() {
        sessions = new ArrayList<>();
    }

    private static void createInstance(){
        instance = new DataLayer();
    }

    public void saveData(Context context){
        String filename = "sessions";
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(sessions);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(Context context){
        String filename = "sessions";
        try (FileInputStream fis = context.openFileInput(filename)) {
            try(ObjectInputStream ois = new ObjectInputStream(fis)) {
                sessions = (List<SessionData>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
