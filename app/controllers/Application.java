package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import models.Photo;
import models.PhotoData;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("LOL"));
    }
    
    public static Result postPhotoPage(){
    	Form<Photo> photoForm = new Form<Photo>(Photo.class);//form(Photo.class);
    	return ok(views.html.postPhotoPage.render());
    }
    
    private static byte[] photoData;
    
    public static Result getPhoto() {
    	if (photoData != null) {
    		return ok(photoData).as("image/jpeg");
    	} else {
    		return ok().as("image/jpeg");
    	}
    }
    
    public static Result postPhoto() throws IOException{
    	MultipartFormData body = request().body().asMultipartFormData();
    	
//    	Form<Photo> photoForm = new Form<Photo>(Photo.class);
//    	photoForm
    	Photo photo = new Photo();// photoForm.bindFromRequest().get();
    	photo.title = body.asFormUrlEncoded().get("title")[0];
    	photo.save();
    	
    	PhotoData photoData = new PhotoData();
    	photoData.photo = photo;
    	File file = body.getFile("data").getFile();
    	photoData.data = convertToByteArray(file);
    	photoData.save();
    	
    	
    	play.Logger.debug("title: " + photo.title + ", data byte length: " +  photoData.data.length);
        return ok(index.render(photo.title));// + photo.data.length));
//    	return ok(photo.data).as("image/jpeg");
    }
    
    private static byte[] convertToByteArray(File file) {
    	byte[] result = new byte[(int) file.length()];
    
    	FileInputStream fis = null;
    	try {
			fis = new FileInputStream(file);
			fis.read(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) { 
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	
    	return result;
    }

}
