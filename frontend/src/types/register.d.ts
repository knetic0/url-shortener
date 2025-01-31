import { Result } from "./base";
import { User } from "./user";

export interface RegisterRequest {
    email: string;
    username: string;
    password: string;
}

export interface RegisterResponse extends Result {
    data : {
        user : User;
    }
}