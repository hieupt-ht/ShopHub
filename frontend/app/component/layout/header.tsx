"use client";
import React, { use } from 'react'
import LogoShop from '../ui/LogoShop'
import { NavHeader } from '../ui/NavHeader'
import Search from '../ui/Search';
import Cart from '../ui/Cart';
export const Header = () => {
  return (
    <div>
        <div className='w-full'>
            <div className='bg-[#0F172A] py-[10px] flex justify-between items-center'>
                <p className='text-[#FFFFFF] ml-[20px]'>Chào mừng đến với ShoHub - Hệ thống mua sắm trực tuyến đáng tin cậy</p>
                <div className='flex justify-between items-center mr-[20px]'>
                    <p className='text-[#FFFFFF]'>Hotline: 1900 6789</p>
                    <p className='text-[#FFFFFF] px-[20px] opacity-30'> | </p>
                    <p className='text-[#FFFFFF]'>Trợ giúp</p>
                </div>
            </div>
            <div className='flex items-center justify-around'>
                <div className='flex items-center px-[10px] py-[10px]'>
                    <LogoShop/>
                    <div className='px-[4px]'>
                        <h1 className='text-[#0F172A] text-[16px]'>ShopHub</h1>
                        <p className='text-[#64748B] text-[16px]'>MVP EDITION</p>
                    </div>
                </div>
                <div>
                    <NavHeader />
                </div>
                <div className='flex items-center'>
                    <div className=''>
                        <Search/>
                    </div>
                    <div>
                        <Cart></Cart>
                    </div>
                    <div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

