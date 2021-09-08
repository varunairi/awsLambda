package com.varuntech.vo;

import java.io.Serializable;

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int age;
	private Name name;
	
	
	
	public static class Name implements Serializable{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6186017846014181999L;
		private String firstName;
		private String middleName;
		private String lastName;
		public final String getFirstName() {
			return firstName;
		}
		public final void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public final String getMiddleName() {
			return middleName;
		}
		public final void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public final String getLastName() {
			return lastName;
		}
		public final void setLastName(String lastName) {
			this.lastName = lastName;
		}
		@Override
		public String toString() {
			return "Name [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + "]";
		}
		
	}



	public final int getAge() {
		return age;
	}



	public final void setAge(int age) {
		this.age = age;
	}



	public final Name getName() {
		return name;
	}



	public final void setName(Name name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Request [age=" + age + ", name=" + name + "]";
	}
}
