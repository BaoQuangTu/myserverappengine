package entities;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Department {
	@Id
	public String departmentId;
	@Index
	public String name;
	@Index
	public List<String> listTeacher;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getListTeacher() {
		return listTeacher;
	}

	public void setListTeacher(List<String> listTeacher) {
		this.listTeacher = listTeacher;
	}

}
