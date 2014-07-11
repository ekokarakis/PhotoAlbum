package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class Photo extends Model {
	@Id
	public Long id;
	
	@Constraints.Required
	public String title;
	
	@Lob
	@Constraints.Required
	public byte[] data;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date timestamp;
}
