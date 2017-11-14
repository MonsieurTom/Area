package eu.epitech.Model.Entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Action {
	public String      		name;
	public List<Argument>	arguments;

	public String getName() {
		return name;
	}

	public List<Argument> getArguments() {
		return arguments;
	}
}
