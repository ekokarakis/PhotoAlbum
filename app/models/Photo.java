package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

//Photo class
@Entity
public class Photo extends Model {
	@Id
	public Long id;
	
	@Required
	public String title;
	
	@Required
	public Date timestamp;
	
	@OneToOne(mappedBy = "photo")
	public PhotoData data;
	
	@OneToMany
	@OrderBy("timestamp ASC")
	public List<Comment> comments;
	
	//method to retrieve photos
	public static Finder<Long,Photo> find = new Finder<Long,Photo>(Long.class, Photo.class); 
}
