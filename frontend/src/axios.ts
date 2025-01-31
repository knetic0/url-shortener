import axios, { AxiosInstance } from "axios";
import LocalStorage from "./storage";

const baseURL : string | undefined = process.env.REACT_APP_API_BASE_URL

const api : AxiosInstance = axios.create({
    baseURL: baseURL
})

export class Axios {
    private static readonly TOKEN = "token";

    public static setAuthenticationToken(token : string | null) {
        Axios.setSpesificHeader("Authorization", `Bearer ${token}`);
        LocalStorage.saveToLocalStorage(Axios.TOKEN, token || "");
    }

    public static getAuthenticationToken() : string | null {
        return LocalStorage.getFromLocalStorage(Axios.TOKEN);
    }

    public static setSpesificHeader(key : string, value : string) {
        api.defaults.headers.common[key] = value;
    }
}

export default api;