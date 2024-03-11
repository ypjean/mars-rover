package com.mars.rover.impl;

import com.mars.rover.exception.CollisionException;
import com.mars.rover.exception.RoverException;
import com.mars.rover.model.Command;
import com.mars.rover.model.Direction;
import com.mars.rover.model.Rover;
import com.mars.rover.repo.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandCentreImpl implements CommandCentre {

    private RoverRepository roverRepository;
    private RoverManager roverManager;

    @Autowired
    public CommandCentreImpl(RoverRepository roverRepository, RoverManager roverManager) {
        this.roverRepository = roverRepository;
        this.roverManager = roverManager;
    }

    @Override
    public List<Rover> getRovers() {
        return roverRepository.findAll();
    }

    @Override
    public Rover deployRover(String name, String command) {
        String[] commands = command.split(",");
        if (commands.length != 3) {
            throw new RoverException("Invalid command");
        }
        int x = Integer.parseInt(commands[0].strip());
        int y = Integer.parseInt(commands[1].strip());
        Direction direction = getDirection(commands[2].strip().toCharArray()[0]);
        Rover rover = Rover.builder()
                .name(name)
                .x(x)
                .y(y)
                .direction(direction)
                .build();

        roverRepository.save(rover);
        return rover;
    }

    private Direction getDirection(char c) {
        return switch (c) {
            case 'N' -> Direction.N;
            case 'S' -> Direction.S;
            case 'E' -> Direction.E;
            case 'W' -> Direction.W;
            default -> throw new RoverException("Unsupported direction: " + c);
        };
    }

    @Override
    public Rover executeCommand(Long id, String command) {
        Optional<Rover> optionalRover = roverRepository.findById(id);
        if (optionalRover.isEmpty()){
            throw new RoverException("Rover not found");
        }
        Rover rover = optionalRover.get();
        try {
            Command[] instructions = getInstructions(command);
            List<Rover> rovers = getRovers();
            for (Command c : instructions) {
                processCommand(c, rover, rovers);
            }
        } catch (CollisionException ex) {
            System.out.println("\n" + ex.getMessage());
        }
        roverRepository.save(rover);
        return rover;
    }

    private void processCommand(Command command, Rover rover, List<Rover> rovers) {
        switch (command) {
            case f -> roverManager.moveForward(rover, rovers);
            case b -> roverManager.moveBackward(rover, rovers);
            case r -> roverManager.rotateRight(rover);
            case l -> roverManager.rotateLeft(rover);
            default -> throw new RoverException("Should not reach here!");
        }
    }

    private Command[] getInstructions(String command) {
        ArrayList<Command> result = new ArrayList<>();
        String[] commands = command.strip().split(",");

        for (String c : commands) {
            switch (c) {
                case "f" -> result.add(Command.f);
                case "b" -> result.add(Command.b);
                case "r" -> result.add(Command.r);
                case "l" -> result.add(Command.l);
                default -> throw new RoverException("Invalid command: " + c);
            }
        }
        return result.toArray(new Command[result.size()]);
    }

}
