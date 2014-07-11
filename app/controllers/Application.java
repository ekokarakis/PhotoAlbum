package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import models.Photo;
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
    	return ok(views.html.postPhotoPage.render(photoForm));
    }
    
    public static Result postPhoto() throws IOException{
    	MultipartFormData body = request().body().asMultipartFormData();
    	
//    	Form<Photo> photoForm = new Form<Photo>(Photo.class);
//    	photoForm
    	Photo photo = new Photo();// photoForm.bindFromRequest().get();
    	photo.title = body.asFormUrlEncoded().get("title")[0];
    	File file = body.getFile("data").getFile();
//    	byte[] data = new byte[(int) file.length()];
//    	file.re
//    	InputStream is = new FileInputStream(body.getFile("data").getFile());
//    	byte[] data = org.apache.commons.io.IOUtils.toByteArray(is);
    	photo.data = convertToByteArray(file);
    	play.Logger.debug("title: " + photo.title + ", data byte length: " +  photo.data.length);
        return ok(index.render(photo.title));// + photo.data.length));
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
