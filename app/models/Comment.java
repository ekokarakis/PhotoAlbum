package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Comment extends Model{ 
	@Id
	public Long id;
	
	@ManyToOne
	public Photo photo;
	
	@Required
	public String text;
	
	@Required
	public Date timestamp;
	
	

}
