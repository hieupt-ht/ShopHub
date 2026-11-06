import React from 'react'
import { FaCartShopping } from 'react-icons/fa6'

export default function Cart() {
  return (
    <div className='px-[12px] py-[8px] flex justify-between items-center hover:cursor-pointer rounded-[8px] border-[1px] border-[#E2E8F0]'>
        <div className='px-[2px]'>
            <FaCartShopping size={20} className='text-[#0068FF]' />
        </div>
        <div className='flex items-center justify-center w-[20px] h-[17px] bg-[#EF4444] p-[6px] rounded-[10px]'>
            <p className='font-bold text-[#FFFFFF] text-[11px]'>3</p>
        </div>
    </div>
  )
}
