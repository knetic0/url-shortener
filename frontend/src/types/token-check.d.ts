import { Result } from "./base";

export interface TokenCheckResponse extends Result {
    data: {
        serverTime: Date;
    }
}