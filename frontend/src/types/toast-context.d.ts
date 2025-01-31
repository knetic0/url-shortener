import { ReactNode } from "react";

export interface ToastOptions {
    severity: 'success' | 'info' | 'warn' | 'error';
    summary: string;
    detail: string;
}

export interface ToastContextProps {
    showToast: (options: ToastOptions) => void;
}

export interface ToastContextProviderProps {
    children: ReactNode;
}