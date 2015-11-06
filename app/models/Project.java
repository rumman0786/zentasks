package models;

import javax.persistence.*;
//import play.db.ebean.*;
import com.avaje.ebean.Model;
import com.avaje.ebean.Ebean;
import java.util.*;

@Entity
public class Project extends Model{
	@Id
    public Long id;
    public String name;
    public String folder;
    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<User> members = new ArrayList<User>();

    public Project(String name, String folder, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }

    public static Model.Finder<Long,Project> find = new Model.Finder(Project.class);

    public static Project create(String name, String folder, String owner) {
        Project project = new Project(name, folder, User.find.ref(owner));
        project.save();
        Ebean.saveManyToManyAssociations(project,"members");
        return project;
    }

    public static List<Project> findInvolving(String user) {
        return find.where()
            .eq("members.email", user)
            .findList();
    }
}
