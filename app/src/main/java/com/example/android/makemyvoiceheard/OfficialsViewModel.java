package com.example.android.makemyvoiceheard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class OfficialsViewModel extends AndroidViewModel {

    private OfficialsRepository mRepository;

    // private LiveData member variable to cache list of words
    private LiveData<List<Officials>> mAllOfficials;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public OfficialsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OfficialsRepository(application);
        mAllOfficials = mRepository.getAllOfficials();
    }

    // add a "getter" method that gets all the words, hides implementation from UI
    LiveData<List<Officials>> getAllMovies() { return mAllOfficials;}

    // create wrapper insert() method that calls Repository's insert() method,
    // implementation of insert() is hidden from UI
    public void insert(Officials officials) {

        mRepository.insert(officials);
    }
}
