package com.example.android.makemyvoiceheard;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class OfficialsRepository {
    private OfficialsDAO mOfficialsDAO;
    private LiveData<List<Officials>> mAllOfficials;

    //constructor that gets a handle to the database and initializes the member variables
    OfficialsRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mOfficialsDAO = db.mOfficalsDAO();
        mAllOfficials = mOfficialsDAO.getAllOfficials();
    }

    // wrapper method called getAllMovies() that returns the cached words as LiveData
    // Room executes on a separate thread. Observed LiveData notifies the observer when data changes
    //List<Officials> getAllOfficials() { return mAllOfficials;}
    LiveData<List<Officials>> getAllOfficials() { return mAllOfficials;}

    // wrapper for insert method, must use AsyncTask to call insert() on non-UI thread or app will crash
    // Room ensures no long-running operations on the main thread which would block UI
    public void insert (Officials officials) {
        new insertAsyncTask(mOfficialsDAO).execute(officials);
    }

    // create insertAsyncTask as an inner class
    private static class insertAsyncTask extends AsyncTask<Officials, Void, Void> {
        private OfficialsDAO mAsyncTaskDao;

        insertAsyncTask(OfficialsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Officials... params) {
            mAsyncTaskDao.nukeTable();
            mAsyncTaskDao.insertOfficials(params[0]);
            return null;
        }
    }
}