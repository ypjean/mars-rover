package com.mars.rover.impl;

import com.mars.rover.model.Rover;

import java.util.List;

public interface CommandCentre {

    List<Rover> getRovers();
    Rover deployRover(String name, String command) throws Exception;
    Rover executeCommand(Long id, String command);

}
