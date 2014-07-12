package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class Photo extends Model {
	@Id
	public Long id;
	
	@Constraints.Required
	public String title;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date timestamp;
	
	@OneToOne(mappedBy = "photo")
	public PhotoData data;
}
