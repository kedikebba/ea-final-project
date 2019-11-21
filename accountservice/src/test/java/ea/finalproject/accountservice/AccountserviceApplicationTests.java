package ea.finalproject.accountservice;

import ea.finalproject.accountservice.model.User;
import ea.finalproject.accountservice.model.UserDTO;
import ea.finalproject.accountservice.repository.UserRepository;
import ea.finalproject.accountservice.service.JwtUserDetailsService;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;


@SpringBootTest
class AccountserviceApplicationTests {
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;


    @Test
    void contextLoads() {
    }
    @Test
    public void getUserByUserName(){
        String username = "saroj";
        when(userRepository.findByUsername(username)).thenReturn(new User("saroj","12345","saroj", "thapa",
                "fairfield", "IA", "US","52557","646565454","sarose301@gmail.com"));
        Assert.assertEquals(username, userDetailsService.findByUsername(username).getUsername());

    }
    @Test
    public void saveUser(){
        User user = new User("saroj","12345","saroj", "thapa",
                "fairfield", "IA", "US","52557","646565454","sarose301@gmail.com");
        when( userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userDetailsService.save(user));
    }

}
