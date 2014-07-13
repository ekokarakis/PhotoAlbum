package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import play.data.validation.Constraints;
import play.db.ebean.Model;

//PhotoData Class
@Entity
public class PhotoData extends Model {
	@Id 
	public Long id;
	
	@OneToOne	@MapsId
	public Photo photo;
	
	@Lob
	@Constraints.Required
	public byte[] data;
	
	public String encoding;
	
	//method to retrieve the photo data
	public static Finder<Long,PhotoData> find = new Finder<Long,PhotoData>(Long.class, PhotoData.class); 

}
