package com.example.githubmobile.Model.Login;

public class UpdateResponse {
    private String password;
    private String updatedAt;
    private PhotoProfile photoProfile;
    private int V;
    private String name;
    private String createdAt;
    private String id;
    private String email;

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setPhotoProfile(PhotoProfile photoProfile){
        this.photoProfile = photoProfile;
    }

    public PhotoProfile getPhotoProfile(){
        return photoProfile;
    }

    public void setV(int V){
        this.V = V;
    }

    public int getV(){
        return V;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return
                "CreateResponse{" +
                        "password = '" + password + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",photo_profile = '" + photoProfile + '\'' +
                        ",__v = '" + V + '\'' +
                        ",name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",_id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}
