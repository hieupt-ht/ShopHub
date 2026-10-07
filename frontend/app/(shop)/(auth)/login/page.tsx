'use client';

import { AuthService } from "@/app/service/auth/AuthService";
import { LoginRequest } from "@/app/type/auth/loginRequest";
import { useState } from "react";
import type { FormEvent } from "react";

export default function Login() {
    const [loginRequest, setLoginRequest] = useState<LoginRequest>({
        username : "",
        password : ""
    });
    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try{
            const response = await AuthService.login(loginRequest);
            console.log("data login: ", response.result);
        }catch{

        }
    }
  return (
    <div className="w-full flex justify-center items-center py-[60px]">
      <form onSubmit={handleSubmit}>
        <div className="w-[580px] px-[20px] rounded-[4px] bg-[#FFFFFF] border-[1px] border-[#E2E8F0]">
          <div>
            <h1 className="text-center text-[24px] text-[#0F172A]">
              Đăng nhập ShopHub
            </h1>
            <p className="text-[16px] text-center text-[#64748B]">
              Nhập tài khoản MVP để trải nghiệm mua sắm nhanh gọn
            </p>
          </div>

          <div className="pt-[10px] px-[20px] w-full">
            <div>
              <p className="text-[16px] font-bold text-[#334155]">
                Tên đăng nhập hoặc Email
              </p>
              <input
              className="w-full py-[10px] px-[8px] bg-[#F8FAFC] rounded-[4px] border-[1px] border-[#E2E8F0]"
                type="text"
                value={loginRequest.username}
                onChange={
                    (e)=>setLoginRequest({
                        ...loginRequest,
                        username: e.target.value
                    })
                }
                placeholder="Ví dụ: minhtri hoặc tri.nguyen@shophub.vn"
              />
            </div>
            <div className="py-[20px]">
                <p className="text-[16px] font-bold text-[#334155]">
                    Mật khẩu
                </p>
                <input
                className="w-full py-[10px] px-[8px] bg-[#F8FAFC] rounded-[4px] border-[1px] border-[#E2E8F0]"
                    type="password" placeholder="********"
                value={loginRequest.password}
                onChange={(e)=>setLoginRequest({
                    ...loginRequest,
                    password: e.target.value
                })}
                />
            </div>
            <div className="flex">
                <input type="checkbox"

                 />
                <p className="text-[#334155] pl-[8px] text-[16px]">
                    Ghi nhớ đăng nhập
                </p>
            </div>
            <button className="bg-[#0068FF] py-[8px] rounded-[8px] text-[#FFFFFF] hover:cursor-pointer w-full">Đăng nhập</button>
            <div className="flex items-center justify-center py-[10px]">
                <p className="text-[#64748B] text-[16px]">Chưa có tài khoản?</p>
                <p className="text-[#0068FF] text-[16px] font-bold">Đăng ký ngay</p>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}