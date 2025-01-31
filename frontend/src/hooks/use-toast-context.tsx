import { useContext } from "react"
import { ToastContext } from "../context/toast-context";
import { ToastContextProps } from "../types/toast-context";

export const useToastContext = () => {
    const context : ToastContextProps | undefined = useContext(ToastContext);
    if(!context) throw new Error("useToastContext must be used within a ToastContextProvider");
    return context;
}