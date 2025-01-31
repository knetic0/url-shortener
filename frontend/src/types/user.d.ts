import { Url } from "./url";

export interface User {
    username: string;
    email: string;
    creationDate: Date;
    urls: Url[];
}