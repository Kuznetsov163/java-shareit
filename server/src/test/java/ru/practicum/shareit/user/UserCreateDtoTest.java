package ru.practicum.shareit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.shareit.user.dto.UserCreateDto;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@JsonTest
public class UserCreateDtoTest {
    @Autowired
  private ObjectMapper objectMapper;

    private UserCreateDto userCreateDto;

    @BeforeEach
    void setUp() {
                userCreateDto = new UserCreateDto(1L, "John Doe", "johndoe@example.com");
            }

            @Test
   void testSerializeUserCreateDto() throws Exception {
               String json = objectMapper.writeValueAsString(userCreateDto);
               String expectedJson = "{\"id\":1,\"name\":\"John Doe\",\"email\":\"johndoe@example.com\"}";
               assertEquals("JSON should match expected format", expectedJson, json);
    }

    @Test
   void testDeserializeUserCreateDto() throws Exception {
        String json = "{\"id\":1,\"name\":\"John Doe\",\"email\":\"johndoe@example.com\"}";
        UserCreateDto deserializedDto = objectMapper.readValue(json, UserCreateDto.class);
        assertEquals("Deserialized name should match", userCreateDto.getName(), deserializedDto.getName());
        assertEquals("Deserialized email should match", userCreateDto.getEmail(), deserializedDto.getEmail());
        assertEquals("Deserialized id should match", userCreateDto.getId(), deserializedDto.getId());
}}
