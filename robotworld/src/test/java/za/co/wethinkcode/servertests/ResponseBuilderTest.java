package za.co.wethinkcode.servertests;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * This class contains unit tests for the ResponseBuilder class.
 */
public class ResponseBuilderTest {

    private WORLD mockWorld;
    private ResponseBuilder responseBuilder;

    @BeforeEach
    public void setUp() {
        mockWorld = mock(WORLD.class);
        responseBuilder = new ResponseBuilder(mockWorld);
    }

    @Test
    public void testSetResponseStatus() {
        // Arrange
        String status = "success";

        // Act
        responseBuilder.setResponseStatus(status);

        // Assert
        assertEquals(status, responseBuilder.getResponseStatus());
    }

    @Test
    public void testSetErrorMessage() {
        // Arrange
        String errorMessage = "An error occurred";

        // Act
        responseBuilder.setErrorMessage(errorMessage);

        // Assert
        assertEquals(errorMessage, responseBuilder.getErrorMessage());
    }

    @Test
    public void testSetDataMessage() {
        // Arrange
        String dataMessage = "Data message";

        // Act
        responseBuilder.setDataMessage(dataMessage);

        // Assert
        assertEquals(dataMessage, responseBuilder.getDataMessage());
    }

    @Test
    public void testBuildDataMessage() {
        // Arrange
        String dataMessage = "Data message";
        responseBuilder.setDataMessage(dataMessage);

        // Act
        var dataMessageMap = responseBuilder.buildDataMessage();

        // Assert
        assertNotNull(dataMessageMap);
        assertEquals(1, dataMessageMap.size());
        assertTrue(dataMessageMap.containsKey("message"));
        assertEquals(dataMessage, dataMessageMap.get("message"));
    }
}


