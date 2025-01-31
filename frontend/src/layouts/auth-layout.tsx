import { classNames } from "primereact/utils";
import { Outlet } from "react-router";

const AuthLayout = () => {
    return (
        <div className={classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden')}>
            <div className="flex flex-column align-items-center justify-content-center">
                <div
                    className="border-round-2xl p-1"
                    style={{
                        background: 'linear-gradient(180deg, var(--surface-400) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}>
                        <div className="w-full surface-card py-8 px-5 sm:px-8 border-round-3xl">
                            <Outlet />
                        </div>
                </div>
            </div>
        </div>
    )
}

export default AuthLayout;