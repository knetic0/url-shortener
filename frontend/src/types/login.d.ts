import { Result } from "./base";
import { User } from "./user";

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse extends Result {
    data : {
        token: string;
        user : User;
    }
}