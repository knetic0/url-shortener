import { FloatLabel } from "primereact/floatlabel";
import { InputText } from "primereact/inputtext";
import { Dispatch, SetStateAction, useEffect, useState } from "react";
import { UrlCreateRequest } from "../types/url";
import { Button } from "primereact/button";
import { useCreateShortUrlMutation } from "../hooks/react-query";
import { useToastContext } from "../hooks/use-toast-context";
import { ToastContextProps } from "../types/toast-context";
import { User } from "../types/user";
import { useOutletContext } from "react-router";
import Urls from "../components/urls";

const Home = () => {
    const { showToast } : ToastContextProps = useToastContext();

    const { user, setUser } : { user: User, setUser: Dispatch<SetStateAction<User>>} = useOutletContext();

    const {
        mutate: createShortUrlMutation,
        isPending: createShortUrlIsPending,
        isSuccess: createShortUrlIsSuccess,
        isError: createShortUrlIsError,
        data: createShortUrlData,
        error: createShortUrlError
    } = useCreateShortUrlMutation();

    const [credentials, setCredentials] = useState<UrlCreateRequest>({
        originalUrl: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value
        });
    }

    const checkCredentials = () => {
        if(!credentials.originalUrl) {
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            createShortUrlMutation(credentials);
        }
    }

    useEffect(() => {
        if(createShortUrlIsSuccess) {
            if(createShortUrlData.success) {
                showToast({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'Short URL created successfully'
                });
                setUser({
                    ...user,
                    urls: [...user.urls, createShortUrlData.data]
                });
            } else {
                showToast({
                    severity: 'error',
                    summary: 'Error',
                    detail: createShortUrlData.message
                });
            }
        }
    }, [createShortUrlIsSuccess])

    useEffect(() => {
        if(createShortUrlIsError) {
            showToast({
                severity: 'error',
                summary: 'Error',
                detail: createShortUrlError?.message
            });
        }
    }, [createShortUrlIsError])

    return (
        <div className="flex flex-column gap-3">
            <div className="flex justify-content-between align-items-center gap-3">
                <div className="w-full">
                    <FloatLabel>
                        <InputText className="w-full" id="originalURL" name="originalUrl" value={credentials.originalUrl} onChange={handleChange} />
                        <label htmlFor="originalURL">Enter Original URL...</label>
                    </FloatLabel>
                </div>
                <Button className="w-3" icon="pi pi-plus" label="Create Short Link" severity="secondary" onClick={handleSubmit} loading={createShortUrlIsPending} />
            </div>
            {user?.urls && <Urls urls={user.urls} />}
        </div>
    )
}

export default Home;