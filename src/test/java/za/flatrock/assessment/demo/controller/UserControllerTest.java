package za.flatrock.assessment.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.flatrock.assessment.demo.models.requests.CreateUserRequest;
import za.flatrock.assessment.demo.models.responses.api.ApiResponse;
import za.flatrock.assessment.demo.models.responses.api.ApiSuccessfulResponse;
import za.flatrock.assessment.demo.models.responses.UserResponse;
import za.flatrock.assessment.demo.services.UserServiceImpl;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
public class UserControllerTest {

    public static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    public void createUserTest() throws Exception {

        // Given
        ApiSuccessfulResponse response = new ApiSuccessfulResponse(new UserResponse( 2L,"TestName", "TestSurname","+27726684422","ADMIN"));
        when(userServiceImpl.create(any(), any())).thenReturn(response);
        CreateUserRequest request = new CreateUserRequest(
                "TestName",
                "TestSurname",
                "(+27)72-668-4422",
                "admin"
        );

        // Then When
        this.mockMvc.perform(
                post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
        ).andDo(
                print()
        ).andExpect(result -> {
            ApiResponse apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    ApiSuccessfulResponse.class
            );

            LinkedHashMap linkedHashMap = objectMapper.convertValue(apiResponse, LinkedHashMap.class);
            LinkedHashMap map = (LinkedHashMap) linkedHashMap.getOrDefault("data", null);


            assertEquals(2, map.getOrDefault("id",0));
            assertEquals("TestName", map.getOrDefault("name",""));
            assertEquals("TestSurname", map.getOrDefault("surname",""));
            assertEquals("+27726684422", map.getOrDefault("cellPhoneNumber",""));
            assertEquals("ADMIN", map.getOrDefault("role",""));
        });
    }

}
