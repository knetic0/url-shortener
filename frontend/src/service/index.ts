import api, { Axios } from "../axios";
import Endpoints from "../constants/api-endpoints";
import LocalStorage from "../storage";
import { LoginRequest, LoginResponse } from "../types/login";
import { RegisterRequest, RegisterResponse } from "../types/register";
import { TokenCheckResponse } from "../types/token-check";
import { UrlCreateRequest, UrlCreateResponse } from "../types/url";

class Service {
    static async login(request : LoginRequest) : Promise<LoginResponse> {
        Axios.setSpesificHeader("X-Security-Key", process.env.REACT_APP_API_SECURITY_KEY || "");
        const response = await api.post(Endpoints.LOGIN_URL, request);
        const data : LoginResponse = response.data;
        if(data.success) {
            Axios.setAuthenticationToken(data.data.token);
            LocalStorage.saveObjectToLocalStorage('user', data.data.user);
        }
        return data;
    }

    static async register(request : RegisterRequest) : Promise<RegisterResponse> {
        Axios.setSpesificHeader("X-Security-Key", process.env.REACT_APP_API_SECURITY_KEY || "");
        const response = await api.post(Endpoints.REGISTER_URL, request);
        return response.data;
    }

    static async tokenCheck() : Promise<TokenCheckResponse> {
        Axios.setAuthenticationToken(Axios.getAuthenticationToken());
        const response = await api.post(Endpoints.TOKEN_CHECK_URL);
        return response.data;
    }

    static async createShortUrl(request : UrlCreateRequest) : Promise<UrlCreateResponse> {
        const response = await api.post(Endpoints.CREATE_SHORT_URL, request);
        const data : UrlCreateResponse = response.data;
        if(data.success) {
            const user = LocalStorage.getObjectFromLocalStorage('user');
            user.urls.push(data.data);
            LocalStorage.saveObjectToLocalStorage('user', user);
        }
        return data;
    }
}

export default Service;