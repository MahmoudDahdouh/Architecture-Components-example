package com.mahmoud.dahdouh.archcomponents;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepositroy repositroy;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repositroy = new NoteRepositroy(application);
        allNotes = repositroy.getAllNotes();
    }

    public void insert(Note note) {
        repositroy.insert(note);
    }

    public void delete(Note note) {
        repositroy.delete(note);
    }

    public void update(Note note) {
        repositroy.update(note);
    }

    public void deleteAll() {
        repositroy.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return repositroy.getAllNotes();
    }

}
