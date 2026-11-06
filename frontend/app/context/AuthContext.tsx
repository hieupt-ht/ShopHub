"use client";
import { createContext, type ReactNode, useCallback, useContext, useEffect, useState } from "react";
import { AuthService } from "../service/auth/AuthService";
import { tokenStore } from "../lib/tokenStore";
import { UserService } from "../service/user/UserService";
import type { UserResponse } from "../type/user/UserResponse";

interface AuthContextType {
    user: UserResponse | null;
    isAuthenticated: boolean;
    isLoading: boolean;
    refreshUser: () => Promise<UserResponse | null>;
    logout: () => void;
}
const AuthContext = createContext<AuthContextType | null>(null);

export default function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<UserResponse | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  const refreshUser = useCallback(async (): Promise<UserResponse | null> => {
    setIsLoading(true);
    try {
      if (!tokenStore.getAccessToken()) {
        setUser(null);
        setIsAuthenticated(false);
        return null;
      }

      const currentUser = await UserService.getCurrentUser();
      setUser(currentUser);
      setIsAuthenticated(true);
      return currentUser;
    } catch (caughtError) {
      console.error("Could not load the current user:", caughtError);
      setUser(null);
      setIsAuthenticated(false);
      return null;
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    void refreshUser();
  }, [refreshUser]);

  const logout = () => {
    AuthService.logout();
    setUser(null);
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ user, isAuthenticated, isLoading, refreshUser, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used inside AuthProvider.");
  }
  return context;
}
