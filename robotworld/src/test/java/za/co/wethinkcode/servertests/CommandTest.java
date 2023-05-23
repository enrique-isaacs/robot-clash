package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.commands.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CommandTest {

    private AbstractBot mockBot;
    private ResponseBuilder mockResponseBuilder;

    @BeforeEach
    public void setUp() {
        mockBot = mock(AbstractBot.class);
        mockResponseBuilder = mock(ResponseBuilder.class);
    }

    @Test
    public void testCommandGetName() {
        // Test getting the name of a command
        Command command = new ForwardCommand("3");
        assertEquals("forward", command.getName());
    }

    @Test
    public void testCommandGetArgument() {
        // Test getting the argument of a command
        Command command = new ForwardCommand("3");
        assertEquals("3", command.getArgument());
    }

    @Test
    public void testCommandCreateQuitCommand() {
        // Test creating a QuitCommand
        Command command = Command.create("quit");
        assertTrue(command instanceof RobotQuitCommand);
    }

    @Test
    public void testCommandCreateTurnRightCommand() {
        // Test creating a TurnRightCommand
        Command command = Command.create("turn right");
        assertTrue(command instanceof RightCommand);
    }

    @Test
    public void testCommandCreateTurnLeftCommand() {
        // Test creating a TurnLeftCommand
        Command command = Command.create("turn left");
        assertTrue(command instanceof LeftCommand);
    }

    @Test
    public void testCommandCreateLookCommand() {
        // Test creating a LookCommand
        Command command = Command.create("look");
        assertTrue(command instanceof LookCommand);
    }

    @Test
    public void testCommandCreateFireCommand() {
        // Test creating a FireCommand
        Command command = Command.create("fire");
        assertTrue(command instanceof FireCommand);
    }

    @Test
    public void testCommandCreateForwardCommand() {
        // Test creating a ForwardCommand
        Command command = Command.create("forward 3");
        assertTrue(command instanceof ForwardCommand);
    }

    @Test
    public void testCommandCreateBackCommand() {
        // Test creating a BackCommand
        Command command = Command.create("back 2");
        assertTrue(command instanceof BackCommand);
    }

    @Test
    public void testCommandCreateReloadCommand() {
        // Test creating a ReloadCommand
        Command command = Command.create("reload");
        assertTrue(command instanceof ReloadCommand);
    }

    @Test
    public void testCommandCreateRepairCommand() {
        // Test creating a RepairCommand
        Command command = Command.create("repair");
        assertTrue(command instanceof RepairCommand);
    }

    @Test
    public void testCommandCreateUnsupportedCommand() {
        // Test creating an ErrorHandling command for unsupported commands
        Command command = Command.create("unsupported");
        assertTrue(command instanceof ErrorHandling);
    }

    @Test
    public void testCommandExecuteQuitCommand() {
        // Test executing a QuitCommand
        Command command = new RobotQuitCommand();
        assertFalse(command.execute(mockBot, mockResponseBuilder));
    }

    @Test
    public void testCommandExecuteTurnRightCommand() {
        // Test executing a TurnRightCommand
        Command command = new RightCommand();
        assertTrue(command.execute(mockBot, mockResponseBuilder));
    }

    @Test
    public void testCommandExecuteTurnLeftCommand() {
        // Test executing a TurnLeftCommand
        Command command = new LeftCommand();
        assertTrue(command.execute(mockBot, mockResponseBuilder));
    }

    @Test
    public void testCommandExecuteLookCommand() {
        // Test executing a LookCommand
        Command command = new LookCommand();
        assertTrue(command.execute(mockBot, mockResponseBuilder));
    }

    @Test
    public void testCommandExecuteFireCommand() {
        // Test executing a FireCommand
        Command command = new FireCommand();
        assertTrue(command.execute(mockBot, mockResponseBuilder));
    }

    @Test
    public void testCommandExecuteErrorHandlingCommand() {
        // Test executing an ErrorHandling command
        Command command = new ErrorHandling();
        assertTrue(command.execute(mockBot, mockResponseBuilder));
    }
}
