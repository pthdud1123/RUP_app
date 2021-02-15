package org.techtown.reducetheuseofplastic;

import androidx.annotation.NonNull;

public class User {
    public String userId;
    public String userPw;

    public User(){

    }
    public User(String userId,String userPw){
        this.userId=userId;
        this.userPw=userPw;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserPw(){
        return userPw;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public void setUserPw(String userPw){
        this.userPw=userPw;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{"+"UserID="+userId+'\''+"UserPw= "+userPw+'\''+"}";
    }
}
