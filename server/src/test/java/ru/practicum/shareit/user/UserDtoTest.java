package ru.practicum.shareit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.shareit.user.dto.UserDto;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@JsonTest
public class UserDtoTest {

    @Autowired
  private ObjectMapper objectMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "John Doe", "johndoe@example.com");
    }

    @Test
   void testSerializeUserDto() throws Exception {
        String json = objectMapper.writeValueAsString(userDto);
        String expectedJson = "{\"id\":1,\"name\":\"John Doe\",\"email\":\"johndoe@example.com\"}";
        assertEquals("JSON should match expected format", expectedJson, json);
    }

    @Test
    void testDeserializeUserDto() throws Exception {
        String json = "{\"id\":1,\"name\":\"John Doe\",\"email\":\"johndoe@example.com\"}";
        UserDto deserializedDto = objectMapper.readValue(json, UserDto.class);
        assertEquals("Deserialized name should match", userDto.getName(), deserializedDto.getName());
        assertEquals("Deserialized email should match", userDto.getEmail(), deserializedDto.getEmail());
        assertEquals("Deserialized id should match", userDto.getId(), deserializedDto.getId());
    }
}
