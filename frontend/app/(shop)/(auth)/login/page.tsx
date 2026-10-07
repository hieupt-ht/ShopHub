'use client';

export default function Login() {
  return (
    <div className="w-full flex justify-center items-center">
      <form>
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
                placeholder="Ví dụ: minhtri hoặc tri.nguyen@shophub.vn"
              />
            </div>
                <div>
                <p className="text-[16px] font-bold text-[#334155]">
                    Mật khẩu
                </p>
                <input
                className="w-full py-[10px] px-[8px] bg-[#F8FAFC] rounded-[4px] border-[1px] border-[#E2E8F0]"
                    type="password" placeholder="********"
                />
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}