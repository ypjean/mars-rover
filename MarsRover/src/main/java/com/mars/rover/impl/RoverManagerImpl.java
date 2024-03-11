package com.mars.rover.impl;

import com.mars.rover.exception.CollisionException;
import com.mars.rover.exception.RoverException;
import com.mars.rover.model.Direction;
import com.mars.rover.model.Rover;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverManagerImpl implements RoverManager{

    private void checkForCollisions(int x, int y, List<Rover> rovers) {
        for (Rover r: rovers) {
            if (x == r.getX() && y == r.getY()) {
                throw new CollisionException("Incoming collision with rover " + r.getName() + ". Stopping all commands.");
            }
        }
    }

    public void moveForward(Rover rover, List<Rover> rovers) {
        Direction direction = rover.getDirection();
        int x_axis = rover.getX();
        int y_axis = rover.getY();
        switch (direction) {
            case N -> y_axis++;
            case S -> y_axis--;
            case E -> x_axis++;
            case W -> x_axis--;
            default -> throw new RoverException("cannot move forward!");
        }
        checkForCollisions(x_axis, y_axis, rovers);
        rover.setX(x_axis);
        rover.setY(y_axis);
    }

    public void moveBackward(Rover rover, List<Rover> rovers) {
        Direction direction = rover.getDirection();
        int x_axis = rover.getX();
        int y_axis = rover.getY();
        switch (direction) {
            case N -> y_axis--;
            case S -> y_axis++;
            case E -> x_axis--;
            case W -> x_axis++;
            default -> throw new RoverException("cannot move forward!");
        }
        checkForCollisions(x_axis, y_axis, rovers);
        rover.setX(x_axis);
        rover.setY(y_axis);
    }

    public void rotateLeft(Rover rover) {
        Direction direction = rover.getDirection();
        switch (direction) {
            case N -> rover.setDirection(Direction.W);
            case S -> rover.setDirection(Direction.E);
            case E -> rover.setDirection(Direction.N);
            case W -> rover.setDirection(Direction.S);
            default -> throw new RoverException("should not reach here!");
        }
    }

    public void rotateRight(Rover rover) {
        Direction direction = rover.getDirection();
        switch (direction) {
            case N -> rover.setDirection(Direction.E);
            case S -> rover.setDirection(Direction.W);
            case E -> rover.setDirection(Direction.S);
            case W -> rover.setDirection(Direction.N);
            default -> throw new RoverException("should not reach here!");
        }
    }
}
