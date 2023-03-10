package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; //Injectc MockMvc

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(get("/")).// Preform Get
                andExpect(status().isOk()).//Except HTTP 200
                andExpect(view().name("home")).//Except Home View
                andExpect(content().string(containsString("Welcome to Taco CLoud")//Except this string
                ));
    }
}
