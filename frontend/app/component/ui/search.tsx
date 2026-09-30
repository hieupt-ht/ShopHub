import React from 'react'
import { IoSearch } from "react-icons/io5";
export default function Search() {
  return (
    <div className='flex justify-start items-center rounded-[8px] border-[1px] border-[#E2E8F0]'>
      <div className='flex p-[4px] items-center bg-[#F8FAFC]'>
        <div>
          <IoSearch size = {24} className = 'text-[#64748B]' />
        </div>
        <div>
          <input type="text" placeholder='Tìm kiếm sản phẩm...' className='outline-none pl-[4px]' />
        </div>
      </div>
    </div>
  )
}
