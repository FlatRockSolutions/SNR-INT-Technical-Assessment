//package za.flatrock.assessment.demo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import za.flatrock.assessment.demo.models.*;
//import za.flatrock.assessment.demo.services.CreateUserService;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@WebMvcTest
//public class UserControllerTest {
//
//    public static ObjectMapper objectMapper = new ObjectMapper();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CreateUserService createUserService;
//
//    @Test
//    public void createUserTest() throws Exception {
//        UserId userId = new UserId();
//        userId.setFirstName("TestName");
//        userId.setLastName("TestSurname");
//        userId.setCellNumber("000000");
//        // Given
//        CreateUserResponse response = new CreateUserResponse(userId, "TestName", "TestSurname",
//                "000000");
//        Role role = new Role();
//        role.setRole(RoleENUM.USER);
//        role.setId(1L);
//        when(createUserService.create(any())).thenReturn(response);
//        CreateUserRequest request = new CreateUserRequest(
//                "TestName",
//                "TestSurname",
//                "+2700000000",
//                role
//        );
//
//        // Then When
//        this.mockMvc.perform(
//                patch("/user/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//        ).andDo(
//                print()
//        ).andExpect(result -> {
//            CreateUserResponse apiResponse = objectMapper.readValue(
//                    result.getResponse().getContentAsString(),
//                    CreateUserResponse.class
//            );
//            assertEquals(1L, apiResponse.getId());
//            assertEquals("TestName", apiResponse.getName());
//            assertEquals("TestSurname", apiResponse.getSurname());
//        });
//    }
//
//}
