import { useAuth } from '@/app/context/AuthContext'
import Link from 'next/link';
import React from 'react'

interface accountProps{
    
}
export default function Account() {
  const {user} = useAuth();

  return (
    <>
      {
        !user ? (
          <div>
            <Link href={"/login"}>Login</Link>
          </div>
        ) :(
          <div className='flex justify-between items-center'>
            <div className='w-[20px] h-[20px] rounded-[50%]'>
              <img className='' src="" alt="" />
            </div>
            <p className='text-[#0F172A] text-[16px]'>{user.fullname}</p>
          </div>
        )
      }
    </>
  )
}
