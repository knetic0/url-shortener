import { Outlet, useNavigate } from "react-router";
import { useTokenCheckQuery } from "../hooks/react-query";
import { useEffect, useState } from "react";
import { BlockUI } from "primereact/blockui";
import { classNames } from "primereact/utils";
import LocalStorage from "../storage";
import { User } from "../types/user";

const MainLayout = () => {
    const navigate = useNavigate();

    const [blockUI, setBlockUI] = useState<boolean>(false);

    const { isSuccess: tokenCheckIsSuccess, isError: tokenCheckIsError } = useTokenCheckQuery();

    const [user, setUser] = useState<User>(LocalStorage.getObjectFromLocalStorage('user'));

    useEffect(() => {
        if(tokenCheckIsSuccess) {
            setBlockUI(false);
        }
    }, [tokenCheckIsSuccess])

    useEffect(() => {
        if(tokenCheckIsError) {
            setBlockUI(false);
            navigate({ pathname: '/' });
        }
    }, [tokenCheckIsError])

    const blockUITemplate = (
        <i className="pi pi-spin pi-spinner text-6xl"></i>
    )

    return (
        <BlockUI blocked={blockUI} fullScreen style={{ backgroundColor: "transparent"}} template={blockUITemplate}>
            <div className={classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden')}>
                <div className="flex flex-column align-items-center justify-content-center">
                    <div
                        className="border-round-2xl p-1"
                        style={{
                            background: 'linear-gradient(180deg, var(--surface-400) 10%, rgba(33, 150, 243, 0) 30%)'
                        }}>
                            <div className="w-full surface-card py-8 px-5 sm:px-8 border-round-3xl">
                                <Outlet context={{ user, setUser }} />
                            </div>
                    </div>
                </div>
            </div>
        </BlockUI>
    )
}

export default MainLayout;