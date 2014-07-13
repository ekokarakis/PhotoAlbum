package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

//Comment class
@Entity
public class Comment extends Model{ 
	@Id
	public Long id;
	
	@ManyToOne
	public Photo photo;
	
	@Required
	public String text;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	@Required
	public Date timestamp;

}
