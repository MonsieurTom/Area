package eu.epitech.Model.Entity;

import javax.persistence.Entity;

@Entity
public class Argument {
	public String name;
	public String placeholder;

	public Argument(String name, String placeholder) {
		this.name = name;
		this.placeholder = placeholder;
	}

	public String getName() {
		return name;
	}

	public String getPlaceholder() {
		return placeholder;
	}
}
