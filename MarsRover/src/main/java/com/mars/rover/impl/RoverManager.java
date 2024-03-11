package com.mars.rover.impl;

import com.mars.rover.model.Rover;

import java.util.List;

public interface RoverManager {

    void moveForward(Rover rover, List<Rover> rovers);
    void moveBackward(Rover rover, List<Rover> rovers);
    void rotateLeft(Rover rover);
    void rotateRight(Rover rover);
}
