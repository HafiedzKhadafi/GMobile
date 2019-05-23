package com.example.githubmobile.Model.Login;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse{
	@SerializedName("name")
	String nama;
	@SerializedName("email")
	String email;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}