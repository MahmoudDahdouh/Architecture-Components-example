package com.mahmoud.dahdouh.archcomponents;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract NoteDao noteDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            noteDao.insert(new Note("Title 4", "Description 4", 4));
            noteDao.insert(new Note("Title 5", "Description 5", 5));
            noteDao.insert(new Note("Title 6", "Description 6", 6));
            noteDao.insert(new Note("Title 7", "Description 7", 7));
            noteDao.insert(new Note("Title 8", "Description 8", 8));
            noteDao.insert(new Note("Title 9", "Description 9", 9));
            noteDao.insert(new Note("Title 10", "Description 10", 10));
            noteDao.insert(new Note("Title 11", "Description 11", 11));
            noteDao.insert(new Note("Title 12", "Description 12", 12));
            noteDao.insert(new Note("Title 13", "Description 13", 13));
            noteDao.insert(new Note("Title 14", "Description 14", 14));
            noteDao.insert(new Note("Title 15", "Description 15", 15));

            return null;
        }
    }


}
