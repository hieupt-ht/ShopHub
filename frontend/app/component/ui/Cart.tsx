import React from 'react'
import { FaCartShopping } from 'react-icons/fa6'

export default function Cart() {
  return (
    <div className='flex justify-around items-center p-[12px] hover:cursor-pointer rounded-[8px] border-[1px] border-[#E2E8F0]'>
        <div>
            <FaCartShopping size={20} className='text-[#0068FF]' />
        </div>
        <div className='w-[20px] h-[17px] bg-[#EF4444] p-[6px] rounded-[10px]'>
            <p className='font-bold text-[#FFFFFF] text-[11px]'>3</p>
        </div>
    </div>
  )
}
