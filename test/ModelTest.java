import java.util.Date;
import java.util.List;

import models.Comment;
import models.Photo;
import models.PhotoData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.Logger;
import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


public class ModelTest {
	
	@Before
	public void setUp() {
		Logger.debug("setUp running");
	}
	
	@After
	public void tearDown() {
		
	}
	
	
	@Test
	public void saveCommentTest(){
		running(fakeApplication(), new Runnable() {
			public void run() {
		
		Comment comment = new Comment();
		comment.text = "test comment";
		comment.timestamp = new Date();
		Photo photo = new Photo();
		photo.id = (long)1;

		comment.photo = photo;
		comment.save();
		
		assertThat(comment.id).isNotNull();
			}
		});
	}
	
	@Test
	public void loadCommentTest(){
		running(fakeApplication(), new Runnable() {
			public void run() {
		
			}
		});
	}
	

}
