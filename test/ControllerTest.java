import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.*;

import play.Logger;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ControllerTest {
	
	@Before
	public void setUp() {
		Logger.debug("setUp running");
	}
	
	@After
	public void tearDown() {
		
	}

   

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render(null);
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
    
    /*@Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Your new application is ready.");
            }
        });
    }*/


}
