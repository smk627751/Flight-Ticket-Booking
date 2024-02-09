package com.smk627751.flightinfo.viewModel;

import com.smk627751.dto.Flight;
import com.smk627751.flightinfo.view.AddFlightView;
import com.smk627751.repository.Repository;

import java.util.List;

public class AddFlightViewModel {
    private AddFlightView view;
    private Repository repo;
    public AddFlightViewModel(AddFlightView view)
    {
        this.view = view;
        this.repo = Repository.getInstance();
        repo.readFlights();
    }

    public void writeFlights() {
        repo.writeFlights();
    }
    public List<Flight> getFlights() {
        repo.readFlights();
        return repo.getFlights();
    }
}
