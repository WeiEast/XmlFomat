package cn.test;

public class Person {

	private String firstname;
	private String lastname;
	private PhoneNumber phone;
	private PhoneNumber fax;
	public Person(String string, String string2) {
		this.firstname = string;
		this.lastname = string2;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public PhoneNumber getPhone() {
		return phone;
	}
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}
	public PhoneNumber getFax() {
		return fax;
	}
	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}
	
}
