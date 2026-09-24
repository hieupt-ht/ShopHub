const ACCESSTOKEN_KEY = "access_token";
const REFRESHTOKEN_KEY = "refresh_token";
import React from 'react'

export const tokenStore = {
    getAccessToken(){
        if(typeof window === "undefined"){
            return null;
        }
        return localStorage.getItem(ACCESSTOKEN_KEY);
    },

    getRefreshToken(){
        if(typeof window === "undefined"){
            return null;
        }
        return localStorage.getItem(REFRESHTOKEN_KEY);
    },

    setTokens(accessToken: string , refreshToken: string){
        localStorage.setItem(ACCESSTOKEN_KEY,  accessToken);
        localStorage.setItem(REFRESHTOKEN_KEY, refreshToken);
    },
    clearTokens(){
        localStorage.removeItem(ACCESSTOKEN_KEY);
        localStorage.removeItem(REFRESHTOKEN_KEY);
    }

}
