import React, { useEffect, useState } from "react";
import { RegisterRequest } from "../../types/register";
import { useRegisterMutation } from "../../hooks/react-query";
import { useToastContext } from "../../hooks/use-toast-context";
import { ToastContextProps } from "../../types/toast-context";
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import { Button } from "primereact/button";
import { useNavigate } from "react-router";

const Register = () => {
    const { showToast } : ToastContextProps = useToastContext();

    const navigate = useNavigate();

    const {
        mutate: registerMutationFunc,
        data: registerMutationData,
        isSuccess: registerMutationIsSuccess,
        isError: registerMutationIsError,
        isPending: registerMutationIsPending,
        error: registerMutationError
    } = useRegisterMutation();

    const [credentials, setCredentials] = useState<RegisterRequest>({
        username: '',
        password: '',
        email: ''
    })

    const handleChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value
        })
    }

    const checkCredentials = () => {
        if(credentials.username === '' || credentials.password === '' || credentials.email === '') {
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            registerMutationFunc(credentials);
        }
    }

    useEffect(() => {
        if(registerMutationIsSuccess) {
            if(registerMutationData.success) {
                showToast({
                    severity: 'success',
                    summary: 'Registration successful',
                    detail: 'You have successfully registered! Redirecting you to the login page...'
                });
                navigate({ pathname: '/' });
            } else {
                showToast({
                    severity: 'error',
                    summary: 'Registration failed',
                    detail: registerMutationData.message
                });
            }
        }
    }, [registerMutationIsSuccess])

    useEffect(() => {
        if(registerMutationIsError) {
            showToast({
                severity: 'error',
                summary: 'Registration failed',
                detail: registerMutationError?.message
            });
        }
    }, [registerMutationIsError])

    return (
        <>
            <div className="text-center mb-5">
                <div className="text-900 text-3xl font-medium mb-3">Welcome!</div>
                <span className="text-600 font-medium">Sign up to continue</span>
            </div>
            <div className="flex flex-column">
                <label htmlFor="username" className="block text-900 text-xl font-medium mb-2">
                    Username
                </label>
                <InputText name="username" value={credentials.username} onChange={handleChange} id="username" type="text" placeholder="Username" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="email" className="block text-900 text-xl font-medium mb-2">
                    Email
                </label>
                <InputText name="email" value={credentials.email} onChange={handleChange} id="email" type="text" placeholder="Email" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="password" className="block text-900 font-medium text-xl mb-2">
                    Password
                </label>
                <Password name="password" value={credentials.password} onChange={handleChange} id="password" toggleMask placeholder="Password" className="w-full mb-5" inputClassName="w-full p-3 md:w-30rem" />
                <Button severity="secondary" label="Sign Up" className="w-full p-3 text-xl" onClick={handleSubmit} />
            </div>
            <span className='font-medium flex justify-content-center align-items-center mt-4 gap-2' style={{color:"#2D3967"}}>
                Have an account? 
                <a href='/' style={{color:"#2D3967"}} className='font-semibold'>
                    Sign in
                </a>
            </span>
        </>
    )
}

export default Register;