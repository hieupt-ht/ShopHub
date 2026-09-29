import React, { useState } from 'react'

export const NavHeader = () => {
    const [activeItem, setActiveItem] = useState<string>("Trang chủ");
    const itemNav = [
        "Trang chủ",
        "Sản phẩm",
        "Danh mục"
    ];
    return (
        <div className='flex items-center'>
            {itemNav.map((item)=>{
                    const isActive = item === activeItem;
                    return (
                            <p onClick={()=>setActiveItem(activeItem)} className={`text-[16px] px-[2px] cursor-pointer ${isActive ? 'text-[#0068FF]} border-b-[2px] border-[#0068FF]' : 'text-[#334155]'}`}>
                                {item}
                            </p>
                    )
                })
            }
        </div>
    )
}
