import Link from 'next/link';
import { usePathname } from 'next/navigation';
import React, { useState } from 'react'

export const NavHeader = () => {
    const pathName = usePathname();
    const [activeItem, setActiveItem] = useState<string>("Trang chủ");
    const itemNav = [
        {
            label: "Trang chủ",
            href: "/"
        },
        {
            label: "Sản phẩm",
            href: "/products"
        },
        {
            label: "Danh mục",
            href: "/category"
        }
    ];
    return (
        <div className='flex items-center'>
            {itemNav.map((item)=>{
                    const isActive = item.href === pathName;
                    return (
                        <div>
                            <Link key={item.href} href={item.href}
                                className={`text-[16px] px-[10px] cursor-pointer ${isActive ? 'text-[#0068FF] border-b-[2px] border-[#0068FF]' : 'text-[#334155] hover:text-[#0068FF]'}`}>
                                {item.label}
                            </Link>
                        </div>
                    )
                })
            }
        </div>
    )
}
