package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is now !!! ready."));
    }
    
    public static Result postPhoto(){
    	return ok(views.html.postPhoto.render());
    }

}
