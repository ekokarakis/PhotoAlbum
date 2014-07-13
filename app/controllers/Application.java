package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import models.Comment;
import models.Photo;
import models.PhotoData;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import play.mvc.Results;
import views.html.index;

public class Application extends Controller {

	//retrieving a list of all the photos and their associated comments 
	public static Result index() {
		List<Photo> photoList = Photo.find.orderBy("timestamp desc").findList();
		Logger.debug("found " + photoList.size() + " photos");
		for (Photo photo: photoList) {
			List<Comment> comments = photo.comments;
			for (Comment comment: comments) {
				Logger.debug(comment.text);
			}
		}
		return ok(index.render(photoList));
	}

    //creating a photo form and returns the rendered photo page template 
	public static Result postPhotoPage() {
		Form<Photo> photoForm = new Form<Photo>(Photo.class);
		return ok(views.html.postPhotoPage.render());
	}

	//retrieving the actual photo data by id from the DB
	public static Result getPhotoData(Long id) {
		List<PhotoData> photoDataList = PhotoData.find.where()
				.eq("photo_id", id).findList();
		if (photoDataList.size() > 0) {
			Logger.debug("found " + photoDataList.size() + " photos");
			PhotoData photoData = photoDataList.get(0);
			Logger.debug("encoding " + photoData.encoding);
			Logger.debug("data length " + photoData.data.length);
			return ok(photoData.data).as("image/" + photoData.encoding);
		} else {
			Logger.debug("no photo found");
			return ok().as("image/jpeg");
		}
	}

	//getting the encoding type of the image
	private static String getImageEncoding(File file) {
		try {
			ImageInputStream imageInputStream = ImageIO
					.createImageInputStream(new FileInputStream(file));
			Iterator<ImageReader> iter = ImageIO
					.getImageReaders(imageInputStream);
			ImageReader reader = (ImageReader) iter.next();
			return reader.getFormatName();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage());
			return null;
		}
	}

	//populating the photo details and data from the template form and save to the DB
	public static Result postPhoto() {
		MultipartFormData body = request().body().asMultipartFormData();

		File file = body.getFile("data").getFile();
		String imageEncoding = getImageEncoding(file);

		//saving the photo details and data only if there is an image
		if (imageEncoding != null) {
			Photo photo = new Photo();
			photo.title = body.asFormUrlEncoded().get("title")[0];
			photo.timestamp = new Date();
			photo.save();

			PhotoData photoData = new PhotoData();
			photoData.photo = photo;
			photoData.data = convertToByteArray(file);
			photoData.encoding = imageEncoding.toLowerCase();
			photoData.save();

			play.Logger.debug("title: " + photo.title + ", data byte length: "
					+ photoData.data.length);
			return redirect(controllers.routes.Application.index());
		} else {
			return Results.badRequest("File was not a photo");
		}
	}

	//converting the image into a byte array
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
	
	//getting the comment from the template form, associating it with the photo and saving to the DB 
	public static Result postComment(Long photoId) {
		DynamicForm formData = Form.form().bindFromRequest();
		String text = formData.get("text");
		Logger.debug("comment added: " + text);
		
		if (text != null) {
			text = text.trim();
			if (text.length() > 0) {
				Comment comment = new Comment();
				comment.text = text;
				comment.timestamp = new Date();
			
				Photo photo = new Photo();
				photo.id = photoId;

				comment.photo = photo;
				comment.save();
			}
		}
		
		return redirect(controllers.routes.Application.index());
	}

}
