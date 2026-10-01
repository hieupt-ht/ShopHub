"use client";
import { createContext, ReactNode, useContext, useEffect, useState } from "react"
import { AuthService } from "../service/auth/AuthService";
import { error } from "console";
import { tokenStore } from "../lib/tokenStore";
interface AuthContexType{
    user: User | null,
    isAuthenticated: boolean,
    login: (data: LoginRequest)=> Promise<LoginResponse>
    logout: ()=> void
}
const AuthContext = createContext<AuthContexType | null>(null);

export default function AuthProvider({children} : {children : ReactNode}) {
  const [user,setUser] = useState<User | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  useEffect (()=>{
    const accesstoken = tokenStore.getAccessToken();
    if(!accesstoken){
      return;
    }
    try {
      
    } catch (error) {
      
    }
  }, [])
  const login =  async (data: LoginRequest)=>{
    try{
      const response = await AuthService.login(data);
      setUser(response.result.user);
    }catch(error){
      console.log ("user is not found !");
      setUser(null);
    }
  }
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
        login,
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
