"use client";
import { createContext, ReactNode, useContext, useEffect, useState } from "react"
import { AuthService } from "../service/auth/AuthService";
import { error } from "console";
import { tokenStore } from "../lib/tokenStore";
import { UserService } from "../service/user/UserService";
interface AuthContexType{
    user: UserResponse | null,
    isAuthenticated: boolean,
    logout: ()=> void
}
const AuthContext = createContext<AuthContexType | null>(null);

export default function AuthProvider({children} : {children : ReactNode}) {
  const [user,setUser] = useState<UserResponse | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  useEffect (()=>{
    const accesstoken = tokenStore.getAccessToken();
    if(!accesstoken){
      return;
    }
    try {
      const currentUser = await UserService.getCurrentUser();
      setUser(currentUser);
      setIsAuthenticated(true);
    } catch (error) {
      
    }
  }, [])
  const logout = ()=>{
    AuthService.logout();
    setUser(null);
    setIsAuthenticated(false);
  }
  return (
    <AuthContext.Provider
    value={
      {
        user,
        isAuthenticated: isAuthenticated,
        logout
      }
    }
    >
      {children}
    </AuthContext.Provider>
  )
}
export function useAuth(){
  const context = useContext(AuthContext);
  if(!context){
    throw new Error ("useAuth is not found !");
  }
  return context;
}
