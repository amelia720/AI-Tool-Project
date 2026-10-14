package amelia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller used to test that the REST API is working.
 */
@RestController
public class GeneralController
{
    /**
     * Returns a simple message when the home page is requested.
     */
    @GetMapping("/")
    public String index()
    {
        return "ShopManger REST API is running";
    }
}