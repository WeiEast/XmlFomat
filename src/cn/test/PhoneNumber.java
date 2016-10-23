package cn.test;

public class PhoneNumber {
	
	public PhoneNumber(int i, String string) {
		this.code = i;
		this.number = string;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	private int code;
	private String number;

}
