import React, { useEffect, useState } from "react";
import { LoginRequest } from "../../types/login";
import { useLoginMutation } from "../../hooks/react-query";
import { useToastContext } from "../../hooks/use-toast-context";
import { ToastContextProps } from "../../types/toast-context";
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import { Button } from "primereact/button";
import { useNavigate } from "react-router";

const Login = () => {
    const { showToast } : ToastContextProps = useToastContext();

    const navigate = useNavigate();

    const {
        mutate: loginMutationFunc,
        data: loginMutationData,
        isSuccess: loginMutationIsSuccess,
        isError: loginMutationIsError,
        isPending: loginMutationIsPending,
        error: loginMutationError
    } = useLoginMutation();

    const [credentials, setCredentials] = useState<LoginRequest>({
        username: '',
        password: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value
        });
    }

    const checkCredentials = () => {
        if(credentials.username === '' || credentials.password === '') {
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            loginMutationFunc(credentials);
        }
    }

    useEffect(() => {
        if(loginMutationIsSuccess) {
            if(loginMutationData.success) {
                showToast({
                    severity: 'success',
                    summary: 'Login successful',
                    detail: 'You have successfully logged in! Redirecting you to the dashboard...'
                });
                navigate({ pathname: '/home' });
            } else {
                showToast({
                    severity: 'error',
                    summary: 'Login failed',
                    detail: loginMutationData.message
                });
            }
        }
    }, [loginMutationIsSuccess])

    useEffect(() => {
        if(loginMutationIsError) {
            showToast({
                severity: 'error',
                summary: 'Login failed',
                detail: loginMutationError?.message
            });
        }
    }, [loginMutationIsError])

    return (
        <>
            <div className="text-center mb-5">
                <div className="text-900 text-3xl font-medium mb-3">Welcome!</div>
                <span className="text-600 font-medium">Sign in to continue</span>
            </div>
            <div className="flex flex-column">
                <label htmlFor="username" className="block text-900 text-xl font-medium mb-2">
                    Username
                </label>
                <InputText name="username" value={credentials.username} onChange={handleChange} id="username" type="text" placeholder="Username" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="password" className="block text-900 font-medium text-xl mb-2">
                    Password
                </label>
                <Password name="password" value={credentials.password} onChange={handleChange} id="password" toggleMask placeholder="Password" className="w-full mb-5" inputClassName="w-full p-3 md:w-30rem" />
                <Button loading={loginMutationIsPending} severity="secondary" label="Sign In" className="w-full p-3 text-xl" onClick={handleSubmit} />
            </div>
            <span className='font-medium flex justify-content-center align-items-center mt-4 gap-2' style={{color:"#2D3967"}}>
                Dont have account? 
                <a href='/register' style={{color:"#2D3967"}} className='font-semibold'>
                    Sign up
                </a>
            </span>
        </>
    )
}

export default Login;